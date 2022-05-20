package com.ibm.academia.apirest.configuraciones;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

 */

import java.util.Collections;

@Configuration
//@EnableSwagger2
public class SpringFoxSwgger {

    @Bean
    public OpenAPI springAppOpenAPI() {
        OpenAPI openAPI = new OpenAPI();

        openAPI.info(new Info().title("API Ruleta Documentacion").version("v.1.0.0")
                .description("Api sobre el funcionamiento de apuestas en  " +
                        "una ruleta americana de casino tradicional ")
                .contact(new Contact().name("Jordan Gonzalez").email(
                        "jordan.gc@ibm.com"
                )));
        return openAPI;

    }
/*
    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ibm.academia.apirest.controladores"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }



    private ApiInfo apiInfo()
    {
        return new ApiInfo(
                "Ruleta Backend API",
                "API para el manejo de ruletas",
                "V1",
                "Terminos del servicio",
                new Contact("Jordan Gonzalez", "www.google.com", "jordangon55@gmail.com"),
                "Licencia de API", "API licencia url", Collections.emptyList()
        );
    }
     */
}
