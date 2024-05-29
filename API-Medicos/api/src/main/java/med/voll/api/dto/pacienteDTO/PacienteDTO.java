package med.voll.api.dto.pacienteDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.enderecoDTO.EnderecoDTO;

public record PacienteDTO(

        @NotBlank
        String nome,

        @NotBlank
        String telefone,


        @NotBlank
        @Email
        String email,


        @NotBlank
        String cpf,

        @NotNull
        @Valid
        EnderecoDTO endereco
) {
}
