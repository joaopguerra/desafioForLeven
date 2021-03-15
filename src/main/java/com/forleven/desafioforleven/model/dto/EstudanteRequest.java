package com.forleven.desafioforleven.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class EstudanteRequest {

    @NotBlank(message = "Campo nome é obrigatório")
    @Size(min = 3)
    private String nome;

    @NotBlank(message = "Campo sobrenome é obrigatório")
    @Size(min = 3)
    private String sobrenome;

    private List<TelefoneDTO> telefones;

    public EstudanteRequest() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstudanteRequest)) return false;
        EstudanteRequest that = (EstudanteRequest) o;
        return Objects.equals(getNome(), that.getNome()) &&
                Objects.equals(getSobrenome(), that.getSobrenome()) &&
                Objects.equals(getTelefones(), that.getTelefones());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getSobrenome(), getTelefones());
    }

    @Override
    public String toString() {
        return "EstudanteRequest{" +
                "nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", telefones=" + telefones +
                '}';
    }
}
