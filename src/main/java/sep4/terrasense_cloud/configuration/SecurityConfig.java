package sep4.terrasense_cloud.configuration;


import jakarta.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sep4.terrasense_cloud.jwt.JwtAuthenticationFilter;


@Configuration
public class SecurityConfig {

    @Bean
    public FilterRegistrationBean<Filter> jwtAuthenticationFilterRegistration(JwtAuthenticationFilter jwtAuthenticationFilter) {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(jwtAuthenticationFilter());
        registration.addUrlPatterns("/*");
        registration.setName("jwtAuthenticationFilter");
        return registration;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        // Create and return your custom JwtAuthenticationFilter instance
        return new JwtAuthenticationFilter();
    }
}