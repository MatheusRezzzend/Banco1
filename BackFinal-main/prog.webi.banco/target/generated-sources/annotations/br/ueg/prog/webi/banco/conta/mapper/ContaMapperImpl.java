package br.ueg.prog.webi.banco.conta.mapper;

import br.ueg.prog.webi.banco.conta.dto.ContaDTO;
import br.ueg.prog.webi.banco.conta.model.Conta;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-30T15:37:23-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2 (Oracle Corporation)"
)
@Component
public class ContaMapperImpl implements ContaMapper {

    @Override
    public Conta toModelo(ContaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Conta conta = new Conta();

        if ( dto.getId() != null ) {
            conta.setId( dto.getId().longValue() );
        }
        conta.setNome( dto.getNome() );
        conta.setSobrenome( dto.getSobrenome() );
        conta.setAgencia( dto.getAgencia() );
        conta.setNumeroConta( dto.getNumeroConta() );
        conta.setContato( dto.getContato() );
        conta.setStatusConta( dto.getStatusConta() );
        conta.setEMail( dto.getEMail() );
        conta.setSaldoConta( dto.getSaldoConta() );

        return conta;
    }

    @Override
    public ContaDTO toDTO(Conta modelo) {
        if ( modelo == null ) {
            return null;
        }

        ContaDTO contaDTO = new ContaDTO();

        if ( modelo.getId() != null ) {
            contaDTO.setId( modelo.getId().intValue() );
        }
        contaDTO.setNome( modelo.getNome() );
        contaDTO.setSobrenome( modelo.getSobrenome() );
        contaDTO.setAgencia( modelo.getAgencia() );
        contaDTO.setNumeroConta( modelo.getNumeroConta() );
        contaDTO.setContato( modelo.getContato() );
        contaDTO.setStatusConta( modelo.getStatusConta() );
        contaDTO.setEMail( modelo.getEMail() );
        contaDTO.setSaldoConta( modelo.getSaldoConta() );

        return contaDTO;
    }

    @Override
    public List<ContaDTO> toDTO(List<Conta> lista) {
        if ( lista == null ) {
            return null;
        }

        List<ContaDTO> list = new ArrayList<ContaDTO>( lista.size() );
        for ( Conta conta : lista ) {
            list.add( toDTO( conta ) );
        }

        return list;
    }
}
