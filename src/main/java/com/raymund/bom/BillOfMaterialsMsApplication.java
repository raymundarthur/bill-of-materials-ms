package com.raymund.bom;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main driver class
 * @author Raymund
 *
 */
@SpringBootApplication
@EnableSwagger2
public class BillOfMaterialsMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillOfMaterialsMsApplication.class, args);
	}

	/**
	 * Swagger auto scan REST controller method
	 * @return
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("components-bom")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.raymund.bom"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}


	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Component Bill Of Materials Service",
				"A sample springboot microservice to test out developer discipline",
				"Version: 0.0.1-SNAPSHOT",
				null,
				new Contact("Raymund's Github Page", "https://github.com/raymundarthur/", null),
				"License of API", "API license URL", Collections.emptyList());
	}
}
