package no.henrik.insurance.example.config;

import no.henrik.insurance.example.service.InsuranceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfiguration {
    @Bean
    public InsuranceService service() {
        return new InsuranceService();
    }
}
