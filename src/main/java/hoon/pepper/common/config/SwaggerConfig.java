package hoon.pepper.common.config;

import com.google.common.collect.ImmutableList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
	@Bean
	public Docket api() { // swagger 문서의 설정빈
		return new Docket(DocumentationType.SWAGGER_2)
			.useDefaultResponseMessages(false)  // 기본 응답 메시지를 사용하지 않음
			// 각 requestMethod 타입에 따른 메시지 타입 , globalResponseMessage() 메소드를 통해 HTTP 메소드의 전역적 오버라이딩 응답메세지
			.globalResponseMessage(RequestMethod.POST, responseMessage())
			.globalResponseMessage(RequestMethod.GET, responseMessage())
			.globalResponseMessage(RequestMethod.PUT, responseMessage())
			.globalResponseMessage(RequestMethod.DELETE, responseMessage())
			.select()
			.apis(RequestHandlerSelectors.basePackage("hoon.pepper.template.controller"))  // 컨트롤러 패키지명으로 해당 패키지의 requsetmapping만 가져 올수 있다 TODO: 패키지 수정
			.paths(PathSelectors.any())
			.build()
			.apiInfo(apiInfo());  // swagger 커스텀 문서의 정보

		//.host("test.com") 서버 호스트를 설정할 수 있다.
	}

	// swagger 커스텀 문서의 정보 TODO: 문서정보 수정
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("template backend API")
			.description("template backend API")
			.version("v0.1")
			.contact(new Contact("@@cpdev.mis", "", ""))
			.license("License of API")
			.licenseUrl("")
			.extensions(Collections.emptyList())
			.build();
	}

	private List<ResponseMessage> responseMessage() {
		return ImmutableList.of(
			new ResponseMessageBuilder().code(403).message("인증오류").build(),
			new ResponseMessageBuilder().code(500).message("서버오류").build());
	}

	@Override  // 리소스 추가 UI , page를 볼수 있게
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
