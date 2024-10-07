package suai.trading.core.security;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import suai.trading.core.security.handler.CustomAuthenticationSuccessHandler;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationSuccessHandler successHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @SneakyThrows
    protected void configure(HttpSecurity httpSecurity) {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers( "/registration", "/login", "/index").permitAll()
                    .antMatchers("/usersPage", "/userData", "/wallet").hasRole("Пользователь")
                    .antMatchers("/updateUsers").hasRole("Администратор")
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .successHandler(successHandler)
                        .permitAll()
                .and()
                    .logout()
                        .permitAll()
                        .logoutSuccessUrl("/");
    }
}
