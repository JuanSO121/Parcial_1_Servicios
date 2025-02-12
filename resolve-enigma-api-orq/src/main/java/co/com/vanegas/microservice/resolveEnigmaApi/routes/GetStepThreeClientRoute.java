package co.com.vanegas.microservice.resolveEnigmaApi.routes;

import co.com.vanegas.microservice.resolveEnigmaApi.model.client.ClientJsonApiBodyResponseSuccess;
import co.com.vanegas.microservice.resolveEnigmaApi.utils.CircuitBreakerUtil;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetStepThreeClientRoute extends RouteBuilder {

    @Autowired
    private CircuitBreakerUtil circuitBreakerUtil;

    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .process(exchange -> {
                    Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
                    String errorMessage = exception != null ? exception.getMessage() : "Unknown error";

                    exchange.setProperty("Error", "0002");
                    exchange.setProperty("descError", "Error consulting the step three");
                    exchange.setProperty("ErrorMessage", errorMessage);
                })
                .handled(true)
                .log("Handled Exception: ${exchangeProperty[Error]} - ${exchangeProperty[descError]}")
                .log("Exception Message: ${exchangeProperty[ErrorMessage]}");

        from("direct:get-step-three")
                .routeId("getStepThree")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("freemarker:templates/GetStepThreeClientTemplate.ftl")
                .log("Request body before sending: ${body}")
                .to("http://localhost:8083/v1/getOneEnigma/getStep")
                .log("Response from microservice step three ${body}")
                .convertBodyTo(String.class)
                .unmarshal().json(JsonLibrary.Jackson, ClientJsonApiBodyResponseSuccess.class)
                .log("Java Response microservice step three ${body}")
                .process(exchange -> {
                    ClientJsonApiBodyResponseSuccess stepOneResponse = (ClientJsonApiBodyResponseSuccess) exchange.getIn().getBody();
                    if (stepOneResponse.getData().get(0).getStep().equalsIgnoreCase("3")) {
                        exchange.setProperty("Step3", stepOneResponse.getData().get(0).getStepDescription());
                    } else {
                        exchange.setProperty("Error", "0001");
                        exchange.setProperty("descError", "Step three is not valid");
                    }
                })
                .log("Response code ${exchangeProperty[Error]}")
                .log("Response description ${exchangeProperty[descError]}")
                .log("Response content ${exchangeProperty[Step3]}");
    }
}
