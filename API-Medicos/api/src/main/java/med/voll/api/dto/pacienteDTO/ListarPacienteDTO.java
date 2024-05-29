package med.voll.api.dto.pacienteDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.enderecoDTO.EnderecoDTO;
import med.voll.api.models.Medicos;
import med.voll.api.models.Paciente;

public record ListarPacienteDTO(

        String nome,
        String email,
        String cpf

) {

    public ListarPacienteDTO(Paciente paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }

}
