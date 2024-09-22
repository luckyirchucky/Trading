package suai.trading.core.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserException extends RuntimeException {

    public UserException() {
        super();
        log.warn("Неизместная ошибка");
    }

    public UserException(String message) {
        super(message);
        log.warn(message);
    }
}
