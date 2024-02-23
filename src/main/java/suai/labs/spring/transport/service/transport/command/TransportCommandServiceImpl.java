package suai.labs.spring.transport.service.transport.command;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suai.labs.spring.transport.service.transport.Transport;
import suai.labs.spring.transport.service.transport.TransportRepository;
import suai.labs.spring.transport.service.Id;

@Service
@Transactional
@RequiredArgsConstructor
public class TransportCommandServiceImpl implements TransportCommandService {

    private final TransportRepository transportRepository;

    @Override
    public Id create(CreateTransportCommand command) {
        var transport = new Transport(
                command.getCarName(),
                command.getNumber(),
                command.getYear(),
                command.getPrice(),
                command.getStateTransport(),
                command.getAvailability()
        );
        transportRepository.save(transport);
        return new Id(transport.getId());
    }
}
