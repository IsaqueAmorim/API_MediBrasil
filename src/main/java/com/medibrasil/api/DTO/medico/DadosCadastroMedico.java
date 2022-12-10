package com.medibrasil.api.DTO.medico;

import com.medibrasil.api.endereco.DadosEndereco;
import com.medibrasil.api.models.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroMedico(
        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank
        String telefone,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        DadosEndereco endereco,
        @NotBlank
        String crm
) {
}
