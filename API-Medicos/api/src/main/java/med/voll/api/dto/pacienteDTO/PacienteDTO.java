package med.voll.api.dto.pacienteDTO;

import med.voll.api.models.Enderecos;
import med.voll.api.models.Paciente;

public record PacienteDTO(
        Long id,
        String nome,
        String telefone,
        String email,
        String cpf,
        Enderecos enderecos
) {
    public PacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getEmail(), paciente.getCpf(), paciente.getEndereco());
    }
}
