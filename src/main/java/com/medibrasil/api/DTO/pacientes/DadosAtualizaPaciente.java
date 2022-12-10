package com.medibrasil.api.DTO.pacientes;

import com.medibrasil.api.endereco.DadosEndereco;
import com.medibrasil.api.endereco.Endereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaPaciente(
        @NotNull long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
