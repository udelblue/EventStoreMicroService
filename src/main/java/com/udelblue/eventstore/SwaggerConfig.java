package com.udelblue.eventstore;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ImplicitGrant;
import springfox.documentation.service.LoginEndpoint;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Add Swagger and Configuration for Swagger 
@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfig {

	@Value("${swagger.oauth.url}")
	private String swaggerOAuthUrl;

	@Value("${api.version}")
	private String version;

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Event Store")
				.description(
						"API for storing system events. [additional information](https://github.com/udelblue/EventStoreMicroService).")
				.termsOfServiceUrl("http://springfox.io").contact("Open Source Community").license("MIT License")
				.licenseUrl("https://github.com/udelblue/EventStoreMicroService/blob/master/LICENSE")
				.version(this.version).build();
	}

	List<GrantType> grantTypes() {
		ImplicitGrant implicitGrant = new ImplicitGrant(new LoginEndpoint(swaggerOAuthUrl), "access_code");
		return newArrayList(implicitGrant);
	}

	@Bean
	SecurityScheme oauth2() {
		return new OAuthBuilder().name("Authorization").grantTypes(grantTypes()).scopes(scopes()).build();
	}

	List<AuthorizationScope> scopes() {
		return newArrayList(new AuthorizationScope("trust", "Allow access to data, from Swagger"));
	}

	@Bean
	public SecurityConfiguration securityInfo() {
		return new SecurityConfiguration("EventStore-swagger", "test", "realm", "EventStore API", "",
				ApiKeyVehicle.HEADER, "api_key", ",");
	}

	@Bean
	public Docket swaggerSpringMvcPlugin1() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.paths(Predicates.not(PathSelectors.regex("/error.*"))).build().apiInfo(apiInfo())
				.securitySchemes(newArrayList(oauth2()));
	}

}