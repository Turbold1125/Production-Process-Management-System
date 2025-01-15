package com.example.Backend;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact();
        contact.setName("Your Name");
        contact.setUrl("www.s.com");
        contact.setEmail("your@email.com");

        return new OpenAPI()
                .info(new Info()
                        .title("Process Product API")
                        .description("API documentation for Process Product")
                        .version("1.0.0") // Static version if BuildProperties isn't used
                        .license(new License().name("API License").url("https://www.s.com/license"))
                        .contact(contact));
    }
}
