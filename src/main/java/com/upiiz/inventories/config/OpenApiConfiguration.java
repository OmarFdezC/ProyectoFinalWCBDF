package com.upiiz.inventories.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Expenses",
                description = "Esta API proporciona acceso a la documentacion del proyecto final",
                version = "1.0.0",
                contact = @Contact(
                        name = "Carlos Omar Fernández Casillas",
                        email = "cfernandezc2100@alumno.ipn.mx",
                        url = "http://localhost:8081/contacto"
                ),
                license = @License(),
                termsOfService = "Este programa es publico ajeno a cualquier partido político"
        ),
        servers = {
                @Server(
                        description = "Servidor de pruebas",
                        url = "http://localhost:8081"
                ),
                @Server(
                        description = "Servidor de Produccion",
                        url = "https://proyectofinalwcbdf.onrender.com"
                )
        },
        tags = {
                @Tag(
                        name = "Inventory",
                        description = "Endpoints para inventory"
                )
        }
)
public class OpenApiConfiguration {

}
