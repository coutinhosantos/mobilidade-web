package com.mobilidade.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobilidade.web.model.Usuario;
import com.mobilidade.web.model.UsuarioDTO;
import com.mobilidade.web.service.AvaliacaoService;
import com.mobilidade.web.service.HomeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    
    @Autowired
    HomeService service;
    
    @Autowired
    AvaliacaoService avaliacaoService;

    @Autowired
    HttpSession session;
    
    @GetMapping("/")
    public ModelAndView index(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) throws JsonProcessingException {
        model.addAttribute("name", name);
        ModelAndView view = null;
        Object usuarioLogado = session.getAttribute("usuario");
        
        if(null!=usuarioLogado) {
            view = new ModelAndView("index");
        }else {
            view = new ModelAndView("/pages/login");
        }
        view.addObject("avaliacoes", service.listarAvaliacao());
        view.addObject("cadastrarUsuario", false);
        view.addObject("page", "Home");
        return view;
    }
    
    @PostMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("Usuario") UsuarioDTO usuario, @ModelAttribute("plataforma") String plataforma) throws JsonProcessingException {
        ModelAndView view = null;

        usuario.setLogin(usuario.getLogin().replace(",",""));
        Usuario user = service.login(usuario);
        
        if (null != user) {
            session.setAttribute("usuario", user);            
            session.setMaxInactiveInterval(5000);
            session.setAttribute("plataforma", plataforma);
            view = new ModelAndView("index");            
        } else {
            view = new ModelAndView("/pages/login");
            view.addObject("message", "Usuario ou Senha Inválido!");
        }

        view.addObject("avaliacoes", service.listarAvaliacao());
        view.addObject("cadastrarUsuario", false);
        view.addObject("page", "Home");
        return view;
    }
    
    
    @GetMapping("/logout")
    public ModelAndView logout(  HttpServletRequest request, 
                           HttpServletResponse response,
                           @ModelAttribute("usuario") UsuarioDTO usuario) throws JsonProcessingException {
        session.setAttribute("usuario", null);       
        ModelAndView view = null;
        view = new ModelAndView("/pages/login");
        view.addObject("page", "Login");
        view.addObject("cadastrarUsuario", false);
        return view;
    }
    
    
    @GetMapping("/avaliar")
    public ModelAndView avaliar() throws JsonProcessingException {
        ModelAndView view = null;
        view = new ModelAndView("index");
        view.addObject("avaliacoes", service.listarAvaliacao());
        view.addObject("page", "Avaliar");
        return view;
    }
    
    @GetMapping("/cadastrarUsuario")
    public ModelAndView cadastrarUsuario() {
        ModelAndView view = null;
        view = new ModelAndView("/pages/login");
        view.addObject("cadastrarUsuario", true);
        view.addObject("page", "Cadastrar");
        return view;
    }
    
    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("Usuario") UsuarioDTO usuario) throws JsonProcessingException {
        ModelAndView view = null;
        Usuario usuarioCadastrado = service.cadastrar(usuario);
        
        if(null!=usuarioCadastrado) {
            view = new ModelAndView("index");
            view.addObject("messageCadastro", "Usuario ".concat(usuario.getNome()).concat("cadastrado com sucesso"));
            view.addObject("cadastrarUsuario", false);
        }else {
            view = new ModelAndView("index");
            view.addObject("message", "Erro ao cadastrar Usuario");
            view.addObject("cadastrarUsuario", true);
        }
        view.addObject("page", "Cadastrar");
        return view;
    }
    
    @GetMapping("/perfil")
    public ModelAndView pefil() {
        return new ModelAndView("index").addObject("page", "Perfil");
    }
}
