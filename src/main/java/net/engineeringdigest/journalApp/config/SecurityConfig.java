package net.engineeringdigest.journalApp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final AdminProperties adminProperties;
    private final UserProperties userProperties;
//    private final SwaggerProperties swaggerProperties;

    public SecurityConfig(AdminProperties adminProperties, UserProperties userProperties//                          SwaggerProperties swaggerProperties
    ) {
        this.adminProperties = adminProperties;
        this.userProperties = userProperties;
//        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/journal/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/journal/id/**", "/journal/flat", "/journal/summary").permitAll()
                        .requestMatchers("/journal").permitAll()
//                        .requestMatchers("/swagger-ui/index.html", "/v3/api-docs/**").hasAuthority("SWAGGER")
                        .anyRequest().authenticated()
                )
                .httpBasic(customizer -> {
                })
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername(adminProperties.getUsername())
                .password("{noop}" + adminProperties.getPassword())
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername(userProperties.getUsername())
                .password("{noop}" + userProperties.getPassword())
                .roles("USER")
                .build();

//        UserDetails swagger = User.withUsername(swaggerProperties.getUsername())
//                .password("{noop}" + swaggerProperties.getPassword())
//                .roles("SWAGGER")
//                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}