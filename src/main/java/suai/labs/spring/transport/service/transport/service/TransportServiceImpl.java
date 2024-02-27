package suai.labs.spring.transport.service.transport.service;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import suai.labs.spring.transport.service.transport.Transport;
import suai.labs.spring.transport.service.transport.TransportRepository;

import java.util.List;

@Service
public class TransportServiceImpl implements TransportService {
    @Autowired
    private TransportRepository transportRepository;

    public ResponseEntity<Transport> saveTransport(Transport transport){
        try {
            Transport savedTransport = transportRepository.save(transport);
            return new ResponseEntity<>(savedTransport, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public List<Transport> getTransports(){
        var transports = transportRepository.findAll();
        if(transports.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Транспортные средства не обнаружены");
        }
        return transports;
    }

    public void deleteTransport(long id){
        if(transportRepository.existsById(id)) {
            transportRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Транспорт с id " + id + " не найден");
        }
    }

    public void updateAvailability(long id) {
        Transport existingTransport = transportRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Транспорт с id " + id + " не найден"));
        if ("Да".equals(existingTransport.getAvailability())) {
            existingTransport.setAvailability("Нет");
        } else {
            existingTransport.setAvailability("Да");
        }
        transportRepository.save(existingTransport);
    }
}
