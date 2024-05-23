package com.mobilidade.web.config;

import java.util.function.Consumer;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties
public class WebClientConfig {

    @Bean 
    public WebClient webClient() { 
      
      return WebClient.builder()
                      .baseUrl("http://localhost:8080/api/v1")
                      .defaultHeaders(montaHeaders())
                      .build();
    }
    
    private Consumer<HttpHeaders> montaHeaders() {
        return new Consumer<HttpHeaders>() {
            @Override
            public void accept(HttpHeaders httpHeaders) {
                httpHeaders.add("Content-Type", "application/json");
                httpHeaders.add("User-Agent", "PostmanRuntime/7.37.3");
                httpHeaders.add("Accept", "*/*");
                httpHeaders.add("Accept-Encoding", "gzip, deflate, br");
                httpHeaders.add("Connection", "keep-alive");
            }
        };
    }

}
