package com.medibrasil.api.DTO.pacientes;

import com.medibrasil.api.models.Paciente;

public record DadosListPaciente(
        long id,
        String nome,
        String email,
        String cpf
) {
    public DadosListPaciente(Paciente paciente){
       this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }


}
