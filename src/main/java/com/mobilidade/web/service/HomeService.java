package com.mobilidade.web.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobilidade.web.model.Avaliacao;
import com.mobilidade.web.model.Usuario;
import com.mobilidade.web.model.UsuarioDTO;

public interface HomeService {
    public Usuario login(UsuarioDTO usuario) throws JsonProcessingException;
    public Usuario cadastrar(UsuarioDTO usuario) throws JsonProcessingException;
    public List<Avaliacao> listarAvaliacao() throws JsonProcessingException;
}
