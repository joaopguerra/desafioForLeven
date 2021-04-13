package com.forleven.desafioforleven.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
public class EstudanteRequest {

    @NotBlank(message = "Campo nome é obrigatório")
    @Size(min = 3)
    private String nome;

    @NotBlank(message = "Campo sobrenome é obrigatório")
    @Size(min = 3)
    private String sobrenome;

    private List<TelefoneDTO> telefones;

}
