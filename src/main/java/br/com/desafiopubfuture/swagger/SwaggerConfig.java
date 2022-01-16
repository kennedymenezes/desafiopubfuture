package br.com.desafiopubfuture.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket videoConferenciaAPI() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("br.com.desafiopubfuture")).build().apiInfo(metaDados())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, getResponseMessagesGet())
                .globalResponseMessage(RequestMethod.DELETE, getResponseMessagesDelete())
                .globalResponseMessage(RequestMethod.PUT, getResponseMessagesDelete())
                .globalResponseMessage(RequestMethod.POST, getResponseMessagesPost());
    }

    private ApiInfo metaDados() {
        return new ApiInfoBuilder().title("API- Pub Fututre Challenge")
                .title("API Rest - Pub Fututre Challenge.")
                .description("Accounts Payable and Receivable management methods.")
                .version("1.0.0").build();
    }

    private List<ResponseMessage> getResponseMessagesGet() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(
                new ResponseMessageBuilder().code(500).message("Falha interna ao processar a requisição.").build());
        responseMessages.add(new ResponseMessageBuilder().code(404).message("Recurso não encontrado.").build());
        responseMessages.add(new ResponseMessageBuilder().code(200).message("Sucesso.").build());
        return responseMessages;
    }

    private List<ResponseMessage> getResponseMessagesDelete() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(
                new ResponseMessageBuilder().code(500).message("Falha interna ao processar a requisição.").build());
        responseMessages.add(
                new ResponseMessageBuilder().code(204).message("Sucesso.").build()
        );
        return responseMessages;
    }

    private List<ResponseMessage> getResponseMessagesPost() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(
                new ResponseMessageBuilder().code(500).message("Falha interna ao processar a requisição.").build());
        responseMessages.add(new ResponseMessageBuilder().code(404).message("Recurso não encontrado.").build());
        responseMessages.add(new ResponseMessageBuilder().code(201).message("Sucesso.").build());
        return responseMessages;
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptador());
    }
}
