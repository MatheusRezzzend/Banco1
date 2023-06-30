package br.ueg.prog.webi.banco.conta.service;

import br.ueg.prog.webi.banco.api.service.CrudService;
import br.ueg.prog.webi.banco.conta.model.Conta;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


public interface ContaService extends CrudService <Conta, Long> {
    Conta realizarDeposito(Long id, BigDecimal valor);

}
