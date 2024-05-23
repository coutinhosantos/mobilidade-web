package com.mobilidade.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobilidade.web.model.Avaliacao;
import com.mobilidade.web.model.AvaliacaoDTO;

public interface AvaliacaoService {
    public Avaliacao cadastrar(AvaliacaoDTO avaliacao) throws JsonProcessingException;
    public void excluirAvaliacao(String id) throws JsonProcessingException;
    public Avaliacao visualizar(String id) throws JsonProcessingException;
    public Avaliacao alterar(AvaliacaoDTO avaliacao) throws JsonProcessingException;
}
