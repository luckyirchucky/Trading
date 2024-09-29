package suai.trading.core.infrastructure.configuration.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/usersPage").setViewName("usersPage");
        registry.addViewController("/updateUsers").setViewName("updateUsers");
        registry.addViewController("/userData").setViewName("userData");
        registry.addViewController("/wallet").setViewName("wallet");
        registry.addViewController("/index").setViewName("index");
    }
}
