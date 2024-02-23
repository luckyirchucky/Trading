package suai.labs.spring.transport.service.transport.command;

import org.springframework.validation.annotation.Validated;
import suai.labs.spring.transport.service.Id;

import javax.validation.Valid;

@Validated
public interface TransportCommandService {
    Id create(@Valid CreateTransportCommand command);
}
