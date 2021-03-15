package com.forleven.desafioforleven.model.entity;

import com.forleven.desafioforleven.model.dto.EstudanteRequest;
import com.forleven.desafioforleven.model.dto.TelefoneDTO;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity(name = "estudante")
public class EstudanteEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricula")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @ElementCollection
    @CollectionTable(name = "telefones", joinColumns = @JoinColumn(name = "id"))
    @Fetch(FetchMode.JOIN)
    private List<TelefoneDTO> telefones;


    public EstudanteEntity() {
    }

    public EstudanteEntity(String nome, String sobrenome, List<TelefoneDTO> telefones) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefones = telefones;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
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
        if (!(o instanceof EstudanteEntity)) return false;
        EstudanteEntity estudanteEntity = (EstudanteEntity) o;
        return getId().equals(estudanteEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public static EstudanteEntity valueOf(EstudanteRequest estudanteRequest){
        return new EstudanteEntity(estudanteRequest.getNome(),
                estudanteRequest.getSobrenome(), estudanteRequest.getTelefones());
    }
}
