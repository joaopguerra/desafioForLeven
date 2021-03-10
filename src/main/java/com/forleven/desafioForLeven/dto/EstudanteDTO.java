package com.forleven.desafioForLeven.dto;

import com.forleven.desafioForLeven.entities.Estudante;

import java.io.Serializable;

public class EstudanteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long matricula;
    private String nome;
    private String sobrenome;

    public EstudanteDTO() {
    }

    public EstudanteDTO(Long matricula, String nome, String sobrenome) {
        this.matricula = matricula;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public EstudanteDTO(Estudante estudante) {
        this.matricula = estudante.getMatricula();
        this.nome = estudante.getNome();
        this.sobrenome = estudante.getSobrenome();
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}
