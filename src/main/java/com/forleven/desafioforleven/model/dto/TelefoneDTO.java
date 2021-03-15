package com.forleven.desafioforleven.model.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TelefoneDTO {

    @Column(name = "numero")
    private String numero;

    public TelefoneDTO() {
    }

    public TelefoneDTO(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
