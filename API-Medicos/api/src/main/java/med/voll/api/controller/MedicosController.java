package med.voll.api.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.medicosDTO.MedicosDTO;
import med.voll.api.dto.medicosDTO.DadosAtualizacaoMedicoDTO;
import med.voll.api.dto.medicosDTO.DadosListagemMedicoDTO;
import med.voll.api.dto.medicosDTO.MedicosDadosDTO;
import med.voll.api.models.Medicos;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicosController {


    @Autowired
    private MedicoRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity cadastra(@RequestBody @Valid MedicosDadosDTO medicosDadosDTO, UriComponentsBuilder uriBuilder) {
       try {
           Medicos medicos = new Medicos(medicosDadosDTO);
           repository.save(medicos);
           var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medicos.getId()).toUri();
           return ResponseEntity.created(uri).body(new MedicosDTO(medicos));
       }catch (DataIntegrityViolationException ex) {
           throw ex;
       }catch (Exception ex) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
       }

    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicoDTO>> listaMedicos(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicoDTO::new);
        return ResponseEntity.ok(page);
    }


    @GetMapping("/{id}")
    public ResponseEntity detalharMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new MedicosDTO(medico));
    }



    @PutMapping
    @Transactional
    public ResponseEntity AttMedico(@RequestBody DadosAtualizacaoMedicoDTO dadosAtualizacaoMedicoDTO) {
        Medicos medicos = repository.getReferenceById(dadosAtualizacaoMedicoDTO.id());
        medicos.atualizarInformacoes(dadosAtualizacaoMedicoDTO);
        return ResponseEntity.ok(new MedicosDTO(medicos));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirMedico(@PathVariable Long id) {
        Medicos medicos = repository.getReferenceById(id);
        medicos.desativarMedico();
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity ativarMedico(@PathVariable Long id) {
        Medicos medicos = repository.getReferenceById(id);
        medicos.ativarMedico();
        return ResponseEntity.ok().build();
    }

}
