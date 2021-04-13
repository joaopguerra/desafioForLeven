package com.forleven.desafioforleven.model.dto;

import com.forleven.desafioforleven.model.entity.EstudanteEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EstudanteResponse {

    private Long matricula;
    private String nome;
    private String sobrenome;
    private List<TelefoneDTO> telefones;

    public EstudanteResponse(Long matricula) {
        this.matricula = matricula;
    }

    public static EstudanteResponse valueOf(EstudanteEntity estudanteEntity){
        return new EstudanteResponse(estudanteEntity.getId(), estudanteEntity.getNome(),
                estudanteEntity.getSobrenome(), estudanteEntity.getTelefones());
    }
}
