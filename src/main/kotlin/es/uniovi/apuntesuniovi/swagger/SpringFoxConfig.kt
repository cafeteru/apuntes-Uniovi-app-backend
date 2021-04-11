package es.uniovi.apuntesuniovi.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.Tag
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


/**
 * Configure the Swagger service
 */
@Configuration
@EnableSwagger2
class SpringFoxConfig {

    /**
     * Create Swagger's bean
     */
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .tags(
                Tag("Learn Subjects", "Define learn subject`s endpoints"),
                Tag("Subjects", "Define subject`s endpoints"),
                Tag("Teach Subjects", "Define teach subject`s endpoints"),
                Tag("Users", "Define user`s endpoints")
            )
    }
}