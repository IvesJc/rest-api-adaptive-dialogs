package org.salesforce.dto;

public record EmpresaDTO(

        int id,
        String nome,
        String tipoIndustria,
        String tamanho,
        String paisSede,
        ClienteDTO clieId) {
}
