package grupo6.clinicaOdontologica.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI adopcionOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Citas Odontológicas")
                        .description("""
                                Backend para un sistema simple de gestión de citas odontológicas.
                                
                                Permite administrar **Pacientes**, **Odontologos** y **Citas** con:
                                - Herencia JPA (`@MappedSuperclass`)
                                - Enumeraciones (`@Enumerated`)
                                - Auditoría automática (`@CreatedDate` / `@LastModifiedDate`)
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("dev@odontologia.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
