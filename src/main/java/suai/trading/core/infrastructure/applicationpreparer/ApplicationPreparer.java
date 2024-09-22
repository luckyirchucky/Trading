package suai.trading.core.infrastructure.applicationpreparer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import suai.trading.core.service.role.initializer.ClientRolesInitializer;

@Component
@RequiredArgsConstructor
public class ApplicationPreparer {

    private final ClientRolesInitializer clientRolesInitializer;

    @EventListener(ApplicationReadyEvent.class)
    public void prepare() {
        clientRolesInitializer.initialize();
    }
}
