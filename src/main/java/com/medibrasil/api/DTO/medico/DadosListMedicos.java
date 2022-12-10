package com.medibrasil.api.DTO.medico;

import com.medibrasil.api.models.Especialidade;
import com.medibrasil.api.models.Medico;

public record DadosListMedicos(
        long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public DadosListMedicos(Medico dados){
        this(dados.getId(),dados.getNome(), dados.getEmail(), dados.getCrm(), dados.getEspecialidade());
    }
}
