package med.voll.api.dto.medicosDTO;

import med.voll.api.enums.Especialidade;
import med.voll.api.models.Enderecos;
import med.voll.api.models.Medicos;

public record MedicosDTO(

        Long id,

        String nome,


        String telefone,


        String email,


        String crm,


        Especialidade especialidade,


        Enderecos enderecos)
{

    public MedicosDTO (Medicos medico) {
        this(medico.getId(), medico.getNome(), medico.getTelefone(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }


}
