package med.voll.api.dto.medicosDTO;

import med.voll.api.enums.Especialidade;
import med.voll.api.models.Medicos;

public record DadosListagemMedicoDTO(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {

    public DadosListagemMedicoDTO (Medicos medicos){
        this(medicos.getId(),medicos.getNome(),medicos.getEmail(),medicos.getCrm(),medicos.getEspecialidade());
    }
}

