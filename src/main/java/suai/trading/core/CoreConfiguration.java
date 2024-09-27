package suai.trading.core;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableFeignClients
@EnableWebSecurity
public class CoreConfiguration {
}
