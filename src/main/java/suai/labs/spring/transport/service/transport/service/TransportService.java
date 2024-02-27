package suai.labs.spring.transport.service.transport.service;

import org.springframework.http.ResponseEntity;
import suai.labs.spring.transport.service.transport.Transport;

import java.util.List;

public interface TransportService {
    ResponseEntity<Transport> saveTransport(Transport transport);

    List<Transport> getTransports();

    void deleteTransport(long id);

    void updateAvailability(long id);
}
