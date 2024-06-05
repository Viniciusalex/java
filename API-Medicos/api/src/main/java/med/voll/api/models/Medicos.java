package med.voll.api.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.medicosDTO.DadosAtualizacaoMedicoDTO;
import med.voll.api.dto.medicosDTO.MedicosDadosDTO;
import med.voll.api.enums.Especialidade;


@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medicos {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String crm;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

//    @OneToOne(mappedBy = "medicos", cascade = CascadeType.ALL, orphanRemoval = true)
    @Embedded
    private Enderecos endereco;

    public Medicos(MedicosDadosDTO dados) {
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Enderecos(dados.endereco());
        this.ativo = true;

    }

    public void setEndereco(Enderecos endereco) {
        this.endereco = endereco;
    }

    public Enderecos getEndereco() {
        return endereco;
    }

    public void atualizarInformacoes(DadosAtualizacaoMedicoDTO dadosAtualizacaoMedicoDTO) {
        if (dadosAtualizacaoMedicoDTO.nome() != null && !dadosAtualizacaoMedicoDTO.nome().isBlank()) {
            this.nome = dadosAtualizacaoMedicoDTO.nome();
        }
        if (dadosAtualizacaoMedicoDTO.telefone() != null && !dadosAtualizacaoMedicoDTO.telefone().isBlank()) {
            this.telefone = dadosAtualizacaoMedicoDTO.telefone();
        }
        if (dadosAtualizacaoMedicoDTO.endereco() != null) {
            this.endereco.atualizarInformacoes(dadosAtualizacaoMedicoDTO.endereco());
        }
    }

    public void desativarMedico() {
        this.ativo = false;
    }

    public void ativarMedico() {
        this.ativo = true;
    }
}
