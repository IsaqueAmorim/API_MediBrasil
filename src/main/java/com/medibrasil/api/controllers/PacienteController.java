package com.medibrasil.api.controllers;

import com.medibrasil.api.DTO.pacientes.DadosAtualizaPaciente;
import com.medibrasil.api.DTO.pacientes.DadosCadastroPaciente;
import com.medibrasil.api.DTO.pacientes.DadosListPaciente;
import com.medibrasil.api.models.*;
import com.medibrasil.api.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public void cadastro(@RequestBody @Valid DadosCadastroPaciente dados){
        pacienteRepository.save(new Paciente(dados));
        System.out.println(dados);
    }

    @GetMapping
    public List<DadosListPaciente> list(){
        return pacienteRepository.findAll().stream().map(DadosListPaciente::new).toList();

    }
    @PutMapping
    @Transactional
    public void atualiza(@RequestBody @Valid DadosAtualizaPaciente dados){
       var paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizaDados(dados);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void deleta(@PathVariable long id){
        System.out.println("Paciente Deletado: " + pacienteRepository.getReferenceById(id).getNome());
        pacienteRepository.deleteById(id);
    }

}
