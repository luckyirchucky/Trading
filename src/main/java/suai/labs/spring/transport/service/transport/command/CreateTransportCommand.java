package suai.labs.spring.transport.service.transport.command;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CreateTransportCommand {

    @NotBlank
    private final String carName;

    @NotBlank
    private final String number;

    @NotBlank
    private final String year;

    @NotBlank
    private final String price;

    @NotBlank
    private final String stateTransport;

    @NotBlank
    private final String availability;
}
