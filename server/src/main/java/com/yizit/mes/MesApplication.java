package com.yizit.mes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MesApplication.class, args);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("上海因致信息科技有限公司")
				.description("yizit mes api")
				.termsOfServiceUrl("http://www.yizit.cn/")
				.contact("yizit")
				.version("1.0")
				.build();
	}
}
