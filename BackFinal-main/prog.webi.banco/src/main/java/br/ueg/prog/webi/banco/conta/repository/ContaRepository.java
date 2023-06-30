package br.ueg.prog.webi.banco.conta.repository;

import br.ueg.prog.webi.banco.conta.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{
    Optional<Conta> findContaBynumeroConta(Integer numeroConta);
    @Query(value = "select count(c) from Conta c where c.numeroConta=:numeroConta")
    Integer countUtilizacaonumeroConta(Integer numeroConta);

    Optional<Conta> findContaByeMail(String eMail);
    @Query(value = "select count(c) from Conta c where c.eMail=:eMail")
    Integer countUtilizacaoEMail (String eMail);
}
