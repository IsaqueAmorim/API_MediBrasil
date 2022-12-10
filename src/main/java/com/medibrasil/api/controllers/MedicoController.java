package com.medibrasil.api.controllers;

import com.medibrasil.api.DTO.medico.DadosAtualizaMedico;
import com.medibrasil.api.DTO.medico.DadosListMedicos;
import com.medibrasil.api.DTO.medico.DadosCadastroMedico;
import com.medibrasil.api.models.Medico;
import com.medibrasil.api.repositories.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

     @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
       medicoRepository.save(new Medico(dados));

    }
    @GetMapping
    public List<DadosListMedicos> list(){
        return medicoRepository.findAll().stream().map(DadosListMedicos::new).toList();
    }
    @PutMapping
    @Transactional
    public void atualiza(@RequestBody @Valid DadosAtualizaMedico dados){
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.dadosAtualiza(dados);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void deleta(@PathVariable long id){
        System.out.println("Medico Excluido: "+ medicoRepository.getReferenceById(id).getNome());
        medicoRepository.deleteById(id);
    }
}
