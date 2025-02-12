package co.com.vanegas.microservice.resolveEnigmaApi.model.client;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the response for getting an Enigma step.
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-15T16:02:12.290-05:00")
public class ClientGetEnigmaStepResponse {

    @JsonProperty("header")
    private ClientHeader header;

    @JsonProperty("step")
    private String step;

    @JsonProperty("stepDescription")
    private String stepDescription;

    public ClientGetEnigmaStepResponse header(ClientHeader header) {
        this.header = header;
        return this;
    }

    /**
     * Get the header of the response.
     * @return header
     **/
    @Schema(required = true, description = "Header of the response containing metadata or status information.")
    @NotNull
    @Valid
    public ClientHeader getHeader() {
        return header;
    }

    public void setHeader(ClientHeader header) {
        this.header = header;
    }

    public ClientGetEnigmaStepResponse step(String step) {
        this.step = step;
        return this;
    }

    /**
     * Get the step of the response.
     * @return step
     **/
    @Schema(required = true, description = "The step identifier or name in the Enigma process.")
    @NotNull
    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public ClientGetEnigmaStepResponse stepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
        return this;
    }

    /**
     * Get the description of the step.
     * @return stepDescription
     **/
    @Schema(description = "A description of the step, providing additional details.")
    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClientGetEnigmaStepResponse that = (ClientGetEnigmaStepResponse) o;
        return Objects.equals(header, that.header) &&
                Objects.equals(step, that.step) &&
                Objects.equals(stepDescription, that.stepDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, step, stepDescription);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClientGetEnigmaStepResponse {\n");
        sb.append("    header: ").append(toIndentedString(header)).append("\n");
        sb.append("    step: ").append(toIndentedString(step)).append("\n");
        sb.append("    stepDescription: ").append(toIndentedString(stepDescription)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
