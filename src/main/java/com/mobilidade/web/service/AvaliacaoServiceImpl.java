package com.mobilidade.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobilidade.web.model.Avaliacao;
import com.mobilidade.web.model.AvaliacaoDTO;
import com.mobilidade.web.util.Client;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AvaliacaoServiceImpl implements AvaliacaoService{

    private static final Logger LOGGER = LoggerFactory.getLogger(AvaliacaoServiceImpl.class);
    
    private final Client client;
    
    private final WebClient webClient;
    
    @Override
    public Avaliacao cadastrar(AvaliacaoDTO avaliacao) throws JsonProcessingException {
        return (Avaliacao) client.post("/avaliacao/avaliar", avaliacao, Avaliacao.class).getBody();
    }

    @Override
    public void excluirAvaliacao(String id) throws JsonProcessingException {
        client.delete("/avaliacao/avaliacao/" + id);
    }

    @Override
    public Avaliacao visualizar(String id) throws JsonProcessingException {
        LOGGER.info("GET    REQUEST  - URL: {} ", "/avaliacao/avaliacao/" + id);
        Avaliacao avaliacao =  webClient.get()
                                        .uri("/avaliacao/avaliacao/" + id)
                                        .retrieve()
                                        .bodyToMono(Avaliacao.class)
                                        .block();
        LOGGER.info("GET    RESPONSE - URL: {} - RESPONSE BODY: {}", "/avaliacao/avaliacao/" + id, avaliacao);
        return avaliacao;
    }

    @Override
    public Avaliacao alterar(AvaliacaoDTO avaliacao) throws JsonProcessingException {
        return (Avaliacao) client.put("/avaliacao/alterarAvaliacao", avaliacao, Avaliacao.class).getBody();
    }
}
