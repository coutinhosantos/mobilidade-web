package com.mobilidade.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobilidade.web.model.Avaliacao;
import com.mobilidade.web.model.AvaliacaoDTO;
import com.mobilidade.web.service.AvaliacaoService;
import com.mobilidade.web.service.HomeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AvaliacaoController {

    @Autowired
    AvaliacaoService service;
    
    @Autowired
    HomeService homeService;
    
    @PostMapping("/cadastrarAvaliacao")
    public ModelAndView cadastrarAvaliacao(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("Avaliacao") AvaliacaoDTO avaliacao) throws JsonProcessingException {
        ModelAndView view = null;
        Avaliacao obj = null;
        
        if(null != avaliacao.getId() && !"".equalsIgnoreCase(avaliacao.getId())) {
            obj = service.alterar(avaliacao);
            
            if(null!=obj) {
                view = new ModelAndView("index");
                view.addObject("avaliacoes", homeService.listarAvaliacao());
                view.addObject("msgSuccess", "Avaliação alterada com sucesso");
            }else {
                view = new ModelAndView("index");
                view.addObject("avaliacoes", homeService.listarAvaliacao());
                view.addObject("msgError", "Erro ao alterar Avaliação");
            }
        }else {
            obj = service.cadastrar(avaliacao);
            
            if(null!=obj) {
                view = new ModelAndView("index");
                view.addObject("avaliacoes", homeService.listarAvaliacao());
                view.addObject("msgSuccess", "Avaliação cadatrada com sucesso");
            }else {
                view = new ModelAndView("index");
                view.addObject("avaliacoes", homeService.listarAvaliacao());
                view.addObject("msgError", "Erro ao cadastrar Avaliação");
            }
        }

        
        
        return view;
    }
    
    
    @GetMapping("/excluirAvaliacao")
    public ModelAndView excluirAvaliacao(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("id") String id) throws JsonProcessingException {
        service.excluirAvaliacao(id);
        ModelAndView view = new ModelAndView("index");
        view.addObject("avaliacoes", homeService.listarAvaliacao());
        view.addObject("msgSuccess", "Avaliação excluida com sucesso");
        
        return view;
    }
    
    @GetMapping("/visualizarAvaliacao")
    public ModelAndView visualizarAvaliacao(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("id") String id) throws JsonProcessingException {
        ModelAndView view = new ModelAndView("index");
        view.addObject("avaliacao", service.visualizar(id));
        view.addObject("avaliacoes", homeService.listarAvaliacao());
        view.addObject("page", "Avaliar");
        return view;
    }
}
