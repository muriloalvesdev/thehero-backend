package br.com.thehero.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.google.common.base.Predicate;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig extends WebMvcConfigurerAdapter {

  @Value("${developer.owner}")
  private String name;

  @Value("${developer.linkedin}")
  private String linkedin;

  @Value("${developer.email}")
  private String email;

  @Value("${title.swagger}")
  private String title;

  @Value("${description.swagger}")
  private String description;

  @Value("${license.swagger}")
  private String license;

  @Value("${license.url.swagger}")
  private String licenseUrl;

  @Value("${version.software}")
  private String version;

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(apis())
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());
  }

  private Predicate<RequestHandler> apis() {
    return RequestHandlerSelectors.basePackage("br.com.thehero");
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .contact(new Contact(this.name, this.linkedin, this.email))
        .description(this.description)
        .license(this.license)
        .licenseUrl(this.licenseUrl)
        .version(this.version)
        .title(this.title)
        .build();
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
    registry.addRedirectViewController(
        "/api/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
    registry.addRedirectViewController(
        "/api/swagger-resources/configuration/security",
        "/swagger-resources/configuration/security");
    registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("/swagger-ui.html**")
        .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
    registry
        .addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }
}
