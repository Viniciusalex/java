package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.pacienteDTO.AtualizacaoPacienteDTO;
import med.voll.api.dto.pacienteDTO.ListarPacienteDTO;
import med.voll.api.dto.pacienteDTO.PacienteDTO;
import med.voll.api.models.Medicos;
import med.voll.api.models.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;


    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente(pacienteDTO);
        repository.save(paciente);
    }

    @GetMapping
    public Page<ListarPacienteDTO> listarPacientes(@PageableDefault(size = 5, sort = {"nome"})Pageable pageable) {
        return repository.findAll(pageable).map(ListarPacienteDTO::new);

    }

    @PutMapping
    @Transactional
    public void atualizarPaciente(@RequestBody AtualizacaoPacienteDTO atualizacaoPacienteDTO){
        Paciente paciente = repository.getReferenceById(atualizacaoPacienteDTO.id());
        paciente.atualizarInformacoes(atualizacaoPacienteDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletarPaciente(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        paciente.desativarPaciente();
    }

}
