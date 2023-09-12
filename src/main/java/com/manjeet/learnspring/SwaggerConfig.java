package com.manjeet.learnspring;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        Contact contact = new Contact();
        contact.setName("Java Development team");
        contact.setEmail("abc@abc.com");
        return new OpenAPI()
                .info(new Info()
                        .title("Leaning Spring")
                        .description("Learning Spring from scratch")
                        .contact(contact).version("v2.1"));
    }
}
