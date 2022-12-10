package com.medibrasil.api.models;

import com.medibrasil.api.DTO.pacientes.DadosCadastroPaciente;
import com.medibrasil.api.DTO.pacientes.DadosAtualizaPaciente;
import com.medibrasil.api.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pacientes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizaDados(DadosAtualizaPaciente dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null){
            this.endereco.atualiza(dados.endereco());
        }
    }
}
