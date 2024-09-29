package suai.trading.core.security.currentclient;

import java.util.UUID;

public interface CurrentClient {

    UUID getId();
    String getClientRoleName();
    boolean isPresent();
}
