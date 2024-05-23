package com.mobilidade.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobilidade.web.model.Avaliacao;
import com.mobilidade.web.model.Usuario;
import com.mobilidade.web.model.UsuarioDTO;
import com.mobilidade.web.util.Client;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HomeServiceImpl implements HomeService{

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeServiceImpl.class);
    
    private WebClient webClient;
    
    private final Client client;
    
    @Override
    public Usuario login(UsuarioDTO usuario) throws JsonProcessingException {
        return (Usuario) client.post("/usuario/login", usuario, Usuario.class).getBody();
    }

    @Override
    public Usuario cadastrar(UsuarioDTO usuario) throws JsonProcessingException {
//        return (Usuario) client.post("/usuario/cadastrar", usuario, Usuario.class).getBody();
        String url = "/usuario/cadastrar";
        LOGGER.info("POST   REQUEST  - URL: {} - HEADERS: {} - REQUEST BODY: {}", url, usuario);
        
        ResponseEntity<Usuario> response =  webClient.post()
                .uri("/usuario/cadastrar")
                .bodyValue(usuario)
                .retrieve()
                .toEntity(Usuario.class)
                .block();
        
        LOGGER.info("POST   RESPONSE - URL: {} - RESPONSE BODY: {}", url, response);
        return response.getBody();
    }
    
    @Override
    public List<Avaliacao> listarAvaliacao() throws JsonProcessingException {
        LOGGER.info("GET    REQUEST  - URL: {} ", "/avaliacao/avaliacao");
        List<Avaliacao> list = webClient.get()
                                        .uri("/avaliacao/avaliacao")
                                        .retrieve()
                                        .bodyToMono(new ParameterizedTypeReference<List<Avaliacao>>() {})
                                        .block();
        LOGGER.info("GET    RESPONSE - URL: {} - RESPONSE BODY: {}", "/avaliacao/avaliacao", list);
        return list;
    }
}
