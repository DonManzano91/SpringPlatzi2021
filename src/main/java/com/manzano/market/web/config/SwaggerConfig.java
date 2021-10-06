package com.manzano.market.web.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author alexmanzano
 * Clase que configura la ejecución del swagger como el medio de exposición de la documentación del api
 * */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /*Docket es un objeto que se encargara de llevar y transportar los datos de la clase a documentar a
    modo de interfaz*/

    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select() //elegir que factor se va a pasar al documentador
                .apis(RequestHandlerSelectors.basePackage("com.manzano.market.web.controller")) //eligiendo el pack
                .build(); //Orden de construir el objeto para exponerlo despues en el ui
    }


}
