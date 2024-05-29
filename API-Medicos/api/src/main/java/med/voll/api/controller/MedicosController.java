package med.voll.api.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.medicosDTO.DadosAtualizacaoMedicoDTO;
import med.voll.api.dto.medicosDTO.DadosListagemMedicoDTO;
import med.voll.api.dto.medicosDTO.MedicosDadosDTO;
import med.voll.api.models.Medicos;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicosController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastra(@RequestBody @Valid MedicosDadosDTO medicosDadosDTO) {
        Medicos medicos = new Medicos(medicosDadosDTO);
        repository.save(medicos);
    }


    @GetMapping
    public Page<DadosListagemMedicoDTO> listaMedicos(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicoDTO::new);
    }


    @PutMapping
    @Transactional
    public void AttMedico(@RequestBody DadosAtualizacaoMedicoDTO dadosAtualizacaoMedicoDTO) {
        Medicos medicos = repository.getReferenceById(dadosAtualizacaoMedicoDTO.id());
        medicos.atualizarInformacoes(dadosAtualizacaoMedicoDTO);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public void excluirMedico(@PathVariable Long id) {
        Medicos medicos = repository.getReferenceById(id);
        medicos.desativarMedico();
    }
}
