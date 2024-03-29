package com.yyc.demo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * Swagger配置类.生成API文档，文档地址：http://localhost:8081/swagger-ui/index.html
 *
 * http://springfox.github.io/springfox/
 * "@Configuration" 声明当前配置类
 * 在启动类上需加入@EnableWebMvc注解
 * @author yyc
 */
@EnableOpenApi
@Configuration
public class SwaggerConfiguration {

    /**
     * 当前文档的标题
     */
    @Value("${swagger.title}")
    private String title;

    /**
     * 联系人名字
     */
    @Value("${swagger.contact.name}")
    private String name;

    /**
     * 联系人邮箱
     */
    @Value("${swagger.contact.email}")
    private String email;

    /**
     * 联系人URL
     */
    @Value("${swagger.contact.url}")
    private String url;

    /**
     * 当前文档的版本
     */
    @Value("${swagger.version}")
    private String version;

    /**
     * 当前文档的详细描述
     */
    @Value("${swagger.description}")
    private String description;

    /**
     * controller接口所在的包
     */
    @Value("${swagger.base-package}")
    private String basePackage;

    /**
     * license
     */
    @Value("${swagger.license}")
    private String license;

    /**
     * license-url
     */
    @Value("${swagger.license-url}")
    private String licenseUrl;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                // .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .contact(new Contact(name,url,email))
                .description(description)
                .version(version)
                .license(license)
                .licenseUrl(licenseUrl)
                .build();
    }
}