package be.lennert.finalwork.server;


import be.lennert.finalwork.server.core.services.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Slf4j
@EnableAutoConfiguration
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"be.lennert.finalwork.server.core", "be.lennert.finalwork.server.rest", "be.lennert.finalwork.server.async"})
public class LaboPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaboPortalApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }
}
