package br.ueg.prog.webi.banco.conta.controller;


import br.ueg.prog.webi.banco.api.controller.CrudController;
import br.ueg.prog.webi.banco.conta.dto.ContaDTO;
import br.ueg.prog.webi.banco.conta.mapper.ContaMapper;
import br.ueg.prog.webi.banco.conta.model.Conta;
import br.ueg.prog.webi.banco.conta.service.ContaService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;


@Api(tags = "Conta API")
@RestController
@RequestMapping(path = "${app.api.base}/Conta")
public class contaControler extends CrudController
<Conta, ContaDTO, Long, ContaMapper, ContaService >{
}
