package com.charlesbabbage.fashionblogapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "springdoc.swagger-ui.enabled", havingValue = "true", matchIfMissing = true)
public class SwaggerConfig {
    @Value("${api.info.title: Fashion Blog}")
    private String title;
    @Value("${api.info.description: This is a fashion blog api. The admin creates a post. A visitor to the blog can comment and like. This gives the admin info of his fashion products.}")
    private String description;
    @Value("${api.info.version: v1}")
    private String version;
    @Value("${api.info.term-of-service: Terms}")
    private String termOfService;
    @Value("${api.info.contact.name: Chukwuma Egwuonwu}")
    private String contactName;
    @Value("${api.info.contact.email: egwuonwuchukwuma74@gmail.com}")
    private String contactEmail;
    @Value("${api.info.contact.url: https://github.com/esChukwuma}")
    private String contactUrl;
    @Value("${api.info.licence.name: Licence 2.0}")
    private String licenceName;
    @Value("${api.info.licence.url: https://www.apache.org/licenses/LICENSE-2.0}")
    private String licenceUrl;

    @Bean
    public OpenAPI productApi() {
        return new OpenAPI()
                .info(getApiInfo());
    }

    private Info getApiInfo() {
        Contact contact = new Contact().name(contactName).email(contactEmail).url(contactUrl);
        License licence = new License().name(licenceName).url(licenceUrl);
        return new Info()
                .title(title)
                .description(description)
                .version(version)
                .contact(contact)
                .license(licence);
    }
}







