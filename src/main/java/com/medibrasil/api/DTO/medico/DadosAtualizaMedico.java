package com.medibrasil.api.DTO.medico;

import com.medibrasil.api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaMedico(
        @NotNull long id,
        String nome,
        DadosEndereco endereco


) {
}
