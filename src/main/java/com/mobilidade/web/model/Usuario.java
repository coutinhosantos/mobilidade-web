package com.mobilidade.web.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Usuario {
    private String id;
    private String nome;
    private String login;
    private String senha;
    private LocalDateTime dataCadastro;
}
