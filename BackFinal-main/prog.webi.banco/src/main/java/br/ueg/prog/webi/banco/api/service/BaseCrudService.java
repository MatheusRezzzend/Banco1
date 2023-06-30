package br.ueg.prog.webi.banco.api.service;

import br.ueg.prog.webi.banco.api.model.IEntidade;
import br.ueg.prog.webi.banco.conta.model.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public abstract class BaseCrudService<
        ENTIDADE extends IEntidade<PK_TYPE>,
        PK_TYPE,
        REPOSITORY extends CrudRepository<ENTIDADE, PK_TYPE>
        > implements CrudService<ENTIDADE, PK_TYPE>{

    @Autowired
    protected REPOSITORY repository;
    @Override
    public ENTIDADE incluir(ENTIDADE modelo) {
        this.validarCamposObrigatorios(modelo);
        this.validarDados(modelo);
        this.prepararParaIncluir(modelo);
        ENTIDADE entidadeIncluido = this.gravarDados(modelo);
        return entidadeIncluido;
    }

    abstract protected void prepararParaIncluir(ENTIDADE entidade) ;

    private ENTIDADE gravarDados(ENTIDADE entidade) {
        return repository.save(entidade);
    }

    abstract protected  void validarDados(ENTIDADE entidade) ;

    abstract protected void validarCamposObrigatorios(ENTIDADE entidade) ;

    @Override
    public ENTIDADE alterar(ENTIDADE entidade, PK_TYPE id) {
        this.validarCamposObrigatorios(entidade);
        this.validarDados(entidade);

        ENTIDADE entidadeBD = recuperarEntidadeOuGeraErro(id);
        entidade.setId(id);

        ENTIDADE save = repository.save(entidade);

        return save;
    }

    protected ENTIDADE recuperarEntidadeOuGeraErro(PK_TYPE id) {
        ENTIDADE entidade = repository
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Erro ao Localizar a entidade!")
                );
        return entidade;
    }

    @Override
    public ENTIDADE excluir(PK_TYPE id) {
        ENTIDADE entidadeExcluir = this.recuperarEntidadeOuGeraErro(id);
        this.repository.delete(entidadeExcluir);
        return entidadeExcluir;
    }

    @Override
    public ENTIDADE obterPeloId(PK_TYPE id) {
        return this.recuperarEntidadeOuGeraErro(id);
    }

    //@Override
   // public ENTIDADE obterPelaConta(PK_TYPE numeroConta) {return this.obterPelaConta(numeroConta);}

    @Override
    public List<ENTIDADE> listarTodos() {
        return (List<ENTIDADE>) repository.findAll();
    }


}
