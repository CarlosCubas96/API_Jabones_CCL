package com.dwes.api.configuracion;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("jabones-public").pathsToMatch("/api/v1/jabones/**").build();
	}

	@Bean
	public GroupedOpenApi categoriaApi() {
		return GroupedOpenApi.builder().group("categorias-public").pathsToMatch("/api/categorias/**").build();
	}

}
