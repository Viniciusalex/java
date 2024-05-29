package med.voll.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.pacienteDTO.AtualizacaoPacienteDTO;
import med.voll.api.dto.pacienteDTO.PacienteDTO;


@Table(name = "paciente")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Enderecos endereco;
    private Boolean ativo;


    public Paciente(PacienteDTO pacienteDTO) {
        this.nome = pacienteDTO.nome();
        this.email = pacienteDTO.email();
        this.telefone = pacienteDTO.telefone();
        this.cpf = pacienteDTO.cpf();
        this.endereco = new Enderecos(pacienteDTO.endereco());
        this.ativo = true;
    }

    public void setEndereco(Enderecos endereco) {
        this.endereco = endereco;
    }

    public Enderecos getEndereco() {
        return endereco;
    }


    public void atualizarInformacoes(AtualizacaoPacienteDTO atualizacaoPacienteDTO) {
        if (atualizacaoPacienteDTO.nome() != null && !atualizacaoPacienteDTO.nome().isBlank()) {
            this.nome = atualizacaoPacienteDTO.nome();
        }
    }

    public void desativarPaciente() {
        this.ativo = false;
    }
}



