package med.voll.api.dto.medicosDTO;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.enderecoDTO.EnderecoDTO;
import med.voll.api.enums.Especialidade;

public record DadosAtualizacaoMedicoDTO(
        @NotNull
        Long id,

        String nome,

        String telefone,

        String email,

        EnderecoDTO endereco) {


}
