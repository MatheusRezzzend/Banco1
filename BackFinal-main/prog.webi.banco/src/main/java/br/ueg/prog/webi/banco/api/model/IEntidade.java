package br.ueg.prog.webi.banco.api.model;

import java.math.BigDecimal;

public interface IEntidade<PK_TYPE> {
    String getTabelaNome();
    PK_TYPE getId();
    void setId(PK_TYPE id);
    BigDecimal getSaldoConta();
    void setSaldoConta(BigDecimal saldoConta);
}
