package med.voll.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.pacienteDTO.AtualizacaoPacienteDTO;
import med.voll.api.dto.pacienteDTO.PacienteDadosDTO;


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
    private Boolean ativo;
    @Embedded
    private Enderecos endereco;


    public Paciente(PacienteDadosDTO pacienteDadosDTO) {
        this.nome = pacienteDadosDTO.nome();
        this.email = pacienteDadosDTO.email();
        this.telefone = pacienteDadosDTO.telefone();
        this.cpf = pacienteDadosDTO.cpf();
        this.endereco = new Enderecos(pacienteDadosDTO.endereco());
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

    public void ativarMedico() {
        this.ativo = true;
    }
}



