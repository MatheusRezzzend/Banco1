package br.ueg.prog.webi.banco.conta.model;

import br.ueg.prog.webi.banco.api.model.IEntidade;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "TBL_CONTA",
    uniqueConstraints = {
        @UniqueConstraint(name= Conta.UK_CONTA, columnNames = "conta")
    }

)
public class Conta implements IEntidade<Long> {
    public static final String UK_CONTA = "uk_conta";
    @SequenceGenerator (
            name = "gerador_sequence",
            sequenceName = "gerador_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = SEQUENCE,
            generator = "gerador_sequence"
    )
    @Id
    @Column(name = "codigo")
    private Long id;

    @Column(name = "nome", length = 200, nullable = false)
    private String nome;

    @Column(name = "sobrenome", length = 200)
    private String sobrenome;

    @Column(name = "agencia", length = 4)
    private Integer agencia;

    @Column(name = "conta", length = 8)
    private Integer numeroConta;

    @Column(name = "telefone", length = 11, nullable = false)
    private String contato;

    @Column(name = "status_conta", length = 200)
    private String statusConta;

    @Column(name = "email", length = 300)
    private String eMail;

    @Column (name = "saldo")
    private BigDecimal saldoConta;

    @Override
    public String getTabelaNome() {
        return UK_CONTA;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }



}
