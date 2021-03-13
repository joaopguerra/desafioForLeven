package com.forleven.desafioforleven.dto;

import com.forleven.desafioforleven.entities.Estudante;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class EstudanteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long matricula;

    @NotBlank(message = "Insira um nome")
    @Size(min = 3, max = 100)
    private String nome;

    @NotBlank(message = "Insira um sobrenome")
    @Size(min = 3, max = 100)
    private String sobrenome;

    private Set<String> telefones = new HashSet<>();

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

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }
}
