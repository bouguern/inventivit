package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Mohamed Bouguern",
                        email = "bouguern.mohamed.10@.com",
                        url = "https://some-url.com"
                ),
                description = "OpenApi documentation for Spring Boot CRUD application",
                title = "CRUD application for Inventiv IT - Mohamed Bouguern",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "https://some-url.com"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "DEV ENV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "https://some-url.com"
                ),
                @Server(
                        description = "TEST ENV",
                        url = "http://localhost:8080"
                )
        }
)

public class SwaggerConfig {

}