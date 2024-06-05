package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.pacienteDTO.AtualizacaoPacienteDTO;
import med.voll.api.dto.pacienteDTO.ListarPacienteDTO;
import med.voll.api.dto.pacienteDTO.PacienteDTO;
import med.voll.api.dto.pacienteDTO.PacienteDadosDTO;
import med.voll.api.models.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPaciente(@RequestBody @Valid PacienteDadosDTO pacienteDadosDTO, UriComponentsBuilder uriBuilder) {
        Paciente paciente = new Paciente(pacienteDadosDTO);
        repository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new PacienteDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<ListarPacienteDTO>> listarPacientes(@PageableDefault(size = 5, sort = {"nome"}) Pageable pageable) {
        var page = repository.findAllByAtivoTrue(pageable).map(ListarPacienteDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity detalharPaciente(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new PacienteDTO(paciente));

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarPaciente(@RequestBody AtualizacaoPacienteDTO atualizacaoPacienteDTO) {
        Paciente paciente = repository.getReferenceById(atualizacaoPacienteDTO.id());
        paciente.atualizarInformacoes(atualizacaoPacienteDTO);
        return ResponseEntity.ok().body(new PacienteDTO(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarPaciente(@PathVariable Long id) {
        Paciente paciente = repository.getReferenceById(id);
        paciente.desativarPaciente();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity ativarMedico(@PathVariable Long id) {
        Paciente paciente = repository.getReferenceById(id);
        paciente.ativarMedico();
        return ResponseEntity.ok(new PacienteDTO(paciente).nome());
    }

}
