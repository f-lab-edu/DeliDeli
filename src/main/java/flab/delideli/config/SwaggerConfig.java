package flab.delideli.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket swaggerAPI() {

		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(swaggerInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage("flab.delideli"))
			.paths(PathSelectors.any())
			.build()
			.useDefaultResponseMessages(false);

	}

	private ApiInfo swaggerInfo() {

		return new ApiInfoBuilder()
			.title("Delideli API Documentation")
			.version("1.0")
			.description("음식 배달 서비스 Delideli의 API 문서입니다.")
			.build();

	}
}
