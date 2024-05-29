package med.voll.api.dto.pacienteDTO;

import jakarta.validation.constraints.NotNull;
import med.voll.api.models.Paciente;

public record AtualizacaoPacienteDTO(
        @NotNull
        Long id,
        String nome

) {

}
