package br.ueg.prog.webi.banco.api.controller;

import br.ueg.prog.webi.banco.api.exception.MessageResponse;
import br.ueg.prog.webi.banco.api.mapper.BaseMapper;
import br.ueg.prog.webi.banco.api.model.IEntidade;
import br.ueg.prog.webi.banco.api.service.CrudService;
import br.ueg.prog.webi.banco.conta.model.Conta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

public abstract class CrudController<
        ENTIDADE extends IEntidade<PK_TYPE>,
        DTO,
        PK_TYPE,
        MAPPER extends BaseMapper<ENTIDADE, DTO>,
        SERVICE extends CrudService<ENTIDADE, PK_TYPE>
        > {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected MAPPER mapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected SERVICE service;

    @GetMapping()
    @Operation(description = "Listagem Geral", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem geral",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema())),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "403", description = "Acesso negado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Erro de Negócio",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public ResponseEntity<List<DTO>> listAll() {
        List<ENTIDADE> modelo = service.listarTodos();
        return ResponseEntity.ok(mapper.toDTO(modelo));
    }

    @PostMapping
    @Operation(description = "Método utilizado para realizar a inclusão de um entidade", responses = {
            @ApiResponse(responseCode = "200", description = "Entidade Incluida",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "403", description = "Acesso negado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Erro de Negócio",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public ResponseEntity<DTO> incluir(@RequestBody DTO modeloDTO) {
        //prepração para entrada.
        ENTIDADE modeloIncluir = this.mapper.toModelo(modeloDTO);
        modeloIncluir.setId(null);
        //chamada do serviço
        System.out.println(modeloIncluir);
        modeloIncluir = this.service.incluir(modeloIncluir);

        //preparação para o retorno
        return ResponseEntity.ok(this.mapper.toDTO(modeloIncluir));
    }

    @PutMapping(path = "/{id}")
    @Operation(description = "Método utilizado para altlerar os dados de uma entidiade", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem geral",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "403", description = "Acesso negado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Erro de Negócio",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    }
    )
    public ResponseEntity<DTO> alterar(@RequestBody() DTO modeloDTO, @PathVariable(name = "id") PK_TYPE id
    ) {
        ENTIDADE pModelo = mapper.toModelo(modeloDTO);
        ENTIDADE alterar = service.alterar(pModelo, id);
        return ResponseEntity.ok(mapper.toDTO(alterar));
    }

    @DeleteMapping(path = "/{id}")
    @Operation(description = "Método utilizado para remover uma entidiade pela id informado", responses = {
            @ApiResponse(responseCode = "200", description = "Entidade Removida",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "403", description = "Acesso negado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public ResponseEntity<DTO> remover(@PathVariable(name = "id") PK_TYPE id) {
        ENTIDADE modeloExcluido = this.service.excluir(id);
        return ResponseEntity.ok(mapper.toDTO(modeloExcluido));
    }

    @GetMapping(path = "/{id}")
    @Operation(description = "Obter os dados completos de uma entidiade pelo id informado!", responses = {
            @ApiResponse(responseCode = "200", description = "Entidade encontrada",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "403", description = "Acesso negado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public ResponseEntity<DTO> ObterPorId(@PathVariable(name = "id") PK_TYPE id) {
        ENTIDADE conta = service.obterPeloId(id);
        return ResponseEntity.ok(this.mapper.toDTO(conta));
    }

    @PostMapping("/{id}/deposito")
    public ResponseEntity<String> fazerDeposito(@PathVariable Long id, @RequestBody BigDecimal valorConta) {
        ENTIDADE contaAtual = service.obterPeloId((PK_TYPE) id);

        if (contaAtual == null) {
            return ResponseEntity.notFound().build();
        }

        BigDecimal novoSaldo = contaAtual.getSaldoConta().add(valorConta);
        contaAtual.setSaldoConta(novoSaldo);
        ENTIDADE contaAtualizada = service.alterar(contaAtual, (PK_TYPE) id);

        return ResponseEntity.ok("Depósito realizado com sucesso!");
    }


}



