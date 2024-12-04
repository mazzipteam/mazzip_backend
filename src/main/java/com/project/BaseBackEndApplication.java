package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BaseBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseBackEndApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*")
						.allowedOrigins("http://localhost:3000") // React 개발 서버 주소
						.allowedMethods("GET", "POST", "PATCH", "DELETE", "PUT") // 허용할 HTTP 메서드
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
