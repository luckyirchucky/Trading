package suai.labs.spring.transport.service.transport.converter;

import suai.labs.spring.transport.service.EntityConverter;
import suai.labs.spring.transport.service.transport.Transport;
import suai.labs.spring.transport.service.transport.TransportView;

public class TransportConverter implements EntityConverter<Transport, TransportView> {
    @Override
    public TransportView convertToView(Transport entity) {
        return TransportView.builder()
                .id(entity.getId())
                .carName(entity.getCarName())
                .price(entity.getPrice())
                .year(entity.getYear())
                .stateTransport(entity.getStateTransport())
                .availability(entity.getAvailability())
                .build();
    }
}
