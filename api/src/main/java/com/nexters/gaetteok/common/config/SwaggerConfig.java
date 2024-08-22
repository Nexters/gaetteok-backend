package com.nexters.gaetteok.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Value("${swagger.server.url}")
    private String serverUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl(serverUrl);

        SecurityScheme securityScheme = new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .in(SecurityScheme.In.HEADER)
            .name("Authorization");


        SecurityRequirement securityRequirement = new SecurityRequirement()
            .addList("bearerAuth");

        return new OpenAPI()
            .servers(List.of(server))
            .info(new Info()
                .title("Pawpaw API")
                .version("1.0")
                .description("""
                    멍멍이 산책 기록 서비스 Pawpaw 의 API 문서 페이지입니다.
                    
                    유저 정보를 사용하는 API 사용을 위해서는 로그인이 필요합니다. 로그인은 아래와 같이 수행하면 됩니다.
                    1. 로그인 API (/api/auth/login) 를 호출하여 JWT 토큰을 발급받습니다. 사용자 토큰은 'kakao-token' 이라고 입력합니다.
                    2. 응답으로 발급된 토큰을 복사하여 Swagger UI 우측 상단 Authorize 버튼을 클릭해 입력합니다.
                    3. 이후 API 호출하면 JWT 토큰이 자동으로 요청 헤더에 포함됩니다.
                    
                    관리자용 API 사용을 위해서는 관리자 토큰이 필요합니다.
                    - 관리자용 토큰은 우측 상단 Authorize 헤더 설정창에 미리 지정된 어드민 토큰을 입력하면 됩니다.
                    """))
            .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
            .addSecurityItem(securityRequirement);
    }

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
            .group("api")
            .pathsToMatch("/api/**")
            .build();
    }

}
