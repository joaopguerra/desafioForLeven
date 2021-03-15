package com.forleven.desafioforleven.model.dto;

import com.forleven.desafioforleven.model.entity.EstudanteEntity;

import java.util.List;

public class EstudanteResponse {

    private Long matricula;
    private String nome;
    private String sobrenome;
    private List<TelefoneDTO> telefones;

    public EstudanteResponse(Long matricula, String nome, String sobrenome, List<TelefoneDTO> telefones) {
        this.matricula = matricula;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefones = telefones;
    }

    public EstudanteResponse(Long matricula) {
        this.matricula = matricula;
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

    public List<TelefoneDTO> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneDTO> telefones) {
        this.telefones = telefones;
    }

    public static EstudanteResponse valueOf(EstudanteEntity estudanteEntity){

        return new EstudanteResponse(estudanteEntity.getId(), estudanteEntity.getNome(),
                estudanteEntity.getSobrenome(), estudanteEntity.getTelefones());
    }
}
