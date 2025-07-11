package durgaproject.jornalapp1.confogure;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI myOpenAPI() {


        return new OpenAPI().info(new Info().title("Journal App Apis")
                        .description("By Durga")
                )
                .servers(List.of(new Server().url("http://localhost:8082").description("server-1 for prod"),new Server().url("http://localhost:8080").description("server-2")))
                .tags(List.of(new Tag().name("Public Apis"),new Tag().name("User Apis"),new Tag().name("Journal Apis"),new Tag().name("Admin apis")))
                .addSecurityItem(new SecurityRequirement().addList("barrerAuth"))
                .components(new Components().addSecuritySchemes("barrerAuth",new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("Jwt").in(SecurityScheme.In.HEADER).name("Authorization"))
                        );

    }
}


