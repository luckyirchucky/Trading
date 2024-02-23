package suai.labs.spring.transport.service.transport;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransportView {

    private final Long id;

    private final String carName;

    private final String number;

    private final String year;

    private final String price;

    private final String stateTransport;

    private final String availability;
}
