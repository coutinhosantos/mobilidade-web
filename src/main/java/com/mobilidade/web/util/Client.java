package com.mobilidade.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.mobilidade.web.exception.ApplicationException;

@Component
public class Client<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);
    
    private final WebClient webClient;

    @Autowired
    public Client(WebClient webClient) {
        this.webClient = webClient;
    }
    
    //PAREI AQUIIIII
    public ResponseEntity<?> get(final String url, final Class<T> tipo) {
        try {
            LOGGER.info("GET REQUEST - URL: {}", url);

            ResponseEntity<?> response =  (ResponseEntity<?>)  webClient.get()
                                                                        .uri(url)
                                                                        .retrieve()
                                                                        .bodyToMono(tipo)
                                                                        .block();        
            
            
            LOGGER.info("GET RESPONSE: {}", response);
            return response;
        } catch (final Exception exception) {
            final String exceptionResponse = String.format("Erro ao fazer a requisição GET para a url %s. ERRO: %s", url, exception.getMessage());
            LOGGER.error(exceptionResponse, exception.getCause());
            throw new ApplicationException(500, exceptionResponse);
        }
    }
    
    public ResponseEntity<String> post(final String url, final T input, final Class<T> tipo) {
        try {
            LOGGER.info("POST   REQUEST  - URL: {} - HEADERS: {} - REQUEST BODY: {}", url, input);
            
            ResponseEntity<String> response = (ResponseEntity<String>) webClient.post()
                                                                                .uri(url)
                                                                                .bodyValue(input)
                                                                                .retrieve()
                                                                                .toEntity(tipo)
                                                                                .block();
            
            LOGGER.info("POST   RESPONSE - URL: {} - RESPONSE BODY: {}", url, response.getBody());
            return response;
        } catch (final Exception exception) {
            final String exceptionResponse = String.format("Erro ao fazer a requisição POST para a url %s. ERRO: %s", url, exception.getMessage());
            LOGGER.error(exceptionResponse, exception);
            throw new ApplicationException(500, exceptionResponse);
        }
    }
    
    public ResponseEntity<String> put(final String url, final T input, final Class<T> tipo) {
        try {
            LOGGER.info("PUT    REQUEST  - URL: {} - HEADERS: {} - REQUEST BODY: {}", url, input);
            
            ResponseEntity<String> response = (ResponseEntity<String>) webClient.put()
                                                                                .uri(url)
                                                                                .bodyValue(input)
                                                                                .retrieve()
                                                                                .toEntity(tipo)
                                                                                .block();
            
            LOGGER.info("PUT    RESPONSE - URL: {} - RESPONSE BODY: {}", url, response.getBody());
            return response;
        } catch (final Exception exception) {
            final String exceptionResponse = String.format("Erro ao fazer a requisição PUT para a url %s. ERRO: %s", url, exception.getMessage());
            LOGGER.error(exceptionResponse, exception);
            throw new ApplicationException(500, exceptionResponse);
        }
    }
    
    public Void delete(final String url) {
        try {
            LOGGER.info("DELETE REQUEST  - URL: {}", url);
            Void response = webClient.delete().uri(url).retrieve().bodyToMono(Void.class).block();
            LOGGER.info("DELETE RESPONSE - URL: {}", url);
            return response;
        } catch (final Exception exception) {
            final String exceptionResponse = String.format("Erro ao fazer a requisição DELETE para a url %s. ERRO: %s", url, exception.getMessage());
            LOGGER.error(exceptionResponse, exception);
            throw new ApplicationException(500, exceptionResponse);
        }
    }
}
