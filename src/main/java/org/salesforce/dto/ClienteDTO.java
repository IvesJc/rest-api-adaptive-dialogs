package org.salesforce.dto;

public record ClienteDTO(
        int id,
        String nome,
        String sobrenome,
        String email,
        String tipo,
        String idioma,
        String pais,
        String telefone) {
}
