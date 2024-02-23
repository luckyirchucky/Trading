package suai.labs.spring.transport.service.transport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import suai.labs.spring.transport.service.transport.Transport;
import suai.labs.spring.transport.service.transport.TransportRepository;

import java.util.List;

@Service
public class TransportServiceImpl implements TransportService {
    @Autowired
    private TransportRepository transportRepository;

    public Transport saveTransport(Transport transport){
        return transportRepository.save(transport);
    }

    public List<Transport> getTransports(){
        return transportRepository.findAll();
    }

    public void deleteTransport(long id){
        transportRepository.deleteById(id);
    }

    public void updateAvailability(long id) {
        Transport existingTransport = transportRepository.findById(id).orElse(null);
        if (existingTransport != null) {
            if ("Да".equals(existingTransport.getAvailability())) {
                existingTransport.setAvailability("Нет");
            } else {
                existingTransport.setAvailability("Да");
            }
            transportRepository.save(existingTransport);
        }
    }
}
