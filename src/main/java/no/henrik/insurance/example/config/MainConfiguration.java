package no.henrik.insurance.example.config;

import no.henrik.insurance.example.external.BrevTjenesteImpl;
import no.henrik.insurance.example.external.Brevtjeneste;
import no.henrik.insurance.example.external.FagSystem;
import no.henrik.insurance.example.external.FagSystemImpl;
import no.henrik.insurance.example.service.InsuranceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfiguration {
    @Bean
    public InsuranceService service() {
        return new InsuranceService();
    }

    @Bean
    public Brevtjeneste brevTjeneste() {
        return new BrevTjenesteImpl();
    }

    @Bean
    public FagSystem fagSystem() {
        return new FagSystemImpl();
    }
}
