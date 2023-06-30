package br.ueg.prog.webi.banco.conta.service.impl;

import br.ueg.prog.webi.banco.api.service.BaseCrudService;
import br.ueg.prog.webi.banco.conta.model.Conta;
import br.ueg.prog.webi.banco.conta.repository.ContaRepository;
import br.ueg.prog.webi.banco.conta.service.ContaService;
import br.ueg.prog.webi.banco.conta.utils.ValidacoesComum;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ContaServiceImpl extends BaseCrudService <Conta, Long, ContaRepository>
        implements  ContaService{
    @Override
    protected void prepararParaIncluir (Conta entidade) {

    }

    @Override
    protected void validarDados (Conta entidade) {

    }

    @Override
    protected void validarCamposObrigatorios (Conta entidade) {

    }
    @Override
    public Conta realizarDeposito(Long id, BigDecimal valor) {
        Conta conta = obterPeloId(id);
        BigDecimal novoSaldo = conta.getSaldoConta().add(valor);
        conta.setSaldoConta(novoSaldo);
        return repository.save(conta);
    }

}
