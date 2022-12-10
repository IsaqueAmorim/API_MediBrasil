package com.medibrasil.api.models;

import com.medibrasil.api.DTO.medico.DadosAtualizaMedico;
import com.medibrasil.api.endereco.Endereco;
import com.medibrasil.api.DTO.medico.DadosCadastroMedico;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String email;

    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;


    public Medico(DadosCadastroMedico dados) {

        this.nome = dados.nome();
        this.email = dados.email();
        this.especialidade = dados.especialidade();
        this.crm = dados.crm();
        this.endereco = new Endereco(dados.endereco());

    }

    public void dadosAtualiza(DadosAtualizaMedico dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.endereco() != null){
            this.endereco.atualiza(dados.endereco());
        }

    }
}
