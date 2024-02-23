package suai.labs.spring.transport.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import suai.labs.spring.transport.service.transport.Transport;
import suai.labs.spring.transport.service.transport.service.TransportService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransportRestController {

    @Autowired
    private TransportService service;

    @GetMapping("/transports")
    public List<Transport> getAllTransports() {
        return service.getTransports();
    }

    @PostMapping("/add")
    public Transport addTransport(@RequestBody Transport transport) {
        return service.saveTransport(transport);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTransport(@PathVariable long id) {
        service.deleteTransport(id);
    }

    @PostMapping("/updateAvailability/{id}")
    public void updateAvailability(@PathVariable long id) {
        service.updateAvailability(id);
    }
}
