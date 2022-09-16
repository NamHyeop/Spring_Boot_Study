package connected.communication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 * packageName    : connected.communication.config
 * fileName       : SwaggerConfig
 * author         : namhyeop
 * date           : 2022/08/31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/31        namhyeop       최초 생성
 */
//Spring Boot에서 작성했던 Bean Validation을 문서화하기 위해 필요한 class
//BeanValidatoPluginsConfiguration.class를 사용하면 @Valid가 선언된 DTO 객체들의 Bean validation 조건을 문서화할 수 있다.
@Import(BeanValidatorPluginsConfiguration.class)
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo()) // api 문서에 대한 정보 작성
                .select() //select()을 통해서 ApiSelectiorBuilder가 반환된다. ApiSEclectorBuilder를 통해 API 문서를 작성할 셀렉터를 지정하고 빌드할 수 있다.
                          //셀력터를 활용하기 위해 컨트롤러 패키지 경로를 지정해준다.
                    .apis(RequestHandlerSelectors.basePackage("connected.communication.controller"))
                    .paths(PathSelectors.any())
                .build()
                .securitySchemes(List.of(apiKey()))
                .securityContexts(List.of(securityContext()));
    }

    //api 문서에 필요한 정보를 입력한 메소드
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("bluish market")
                .description("bluish market REST API Documentation")
                .license("skaguq@gmail.com")
                .licenseUrl("https://github.com/NamHyeop/Spring_Boot_Study/tree/master/practice/communicationProject")
                .version("1.0")
                .build();
    }

    private static ApiKey apiKey(){
        return new ApiKey("Authorization", "Bearer Token", "header");
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(defaultAuth())
                .operationSelector(oc -> oc.requestMappingPattern().startsWith("/api/")).build();
    }

    //Authorization 헤더를 전역적으로 지정하게 설정
    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "global access");
        return List.of(new SecurityReference("Authorization", new AuthorizationScope[]{authorizationScope}));
    }
}
