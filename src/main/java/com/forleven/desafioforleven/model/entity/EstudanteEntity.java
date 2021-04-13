package com.forleven.desafioforleven.model.entity;

import com.forleven.desafioforleven.model.dto.TelefoneDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.List;

@Entity(name = "estudante")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EstudanteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricula")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @ElementCollection
    @CollectionTable(name = "telefones", joinColumns = @JoinColumn(name = "matricula_estudante"))
    @Fetch(FetchMode.JOIN)
    private List<TelefoneDTO> telefones;

    public EstudanteEntity(String nome, String sobrenome, List<TelefoneDTO> telefones) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefones = telefones;
    }


}
