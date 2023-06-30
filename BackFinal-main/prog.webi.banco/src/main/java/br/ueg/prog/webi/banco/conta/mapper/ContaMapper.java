package br.ueg.prog.webi.banco.conta.mapper;

import br.ueg.prog.webi.banco.api.mapper.BaseMapper;
import br.ueg.prog.webi.banco.conta.dto.ContaDTO;
import br.ueg.prog.webi.banco.conta.model.Conta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContaMapper extends BaseMapper <Conta, ContaDTO> {

}
