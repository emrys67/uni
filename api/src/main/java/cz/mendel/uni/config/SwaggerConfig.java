package cz.mendel.uni.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/swagger")
                .apiInfo(metaData());
    }

    private ApiInfo metaData(){

        Contact contact = new Contact("Mykyta Trofymov", "https://www.linkedin.com/in/mykyta-trofymov-3b6099204/",
                "emriesy@gmail.com");

        return new ApiInfo(
                "SpringBoot university",
                "Simple imitation of university system",
                "1.0",
                "Terms of Service",
                contact,
                "License",
                "Url",
                new ArrayList<>());
    }
}
