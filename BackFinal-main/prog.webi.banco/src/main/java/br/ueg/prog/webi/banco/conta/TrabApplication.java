package br.ueg.prog.webi.banco.conta;

import br.ueg.prog.webi.banco.conta.model.Conta;
import br.ueg.prog.webi.banco.conta.repository.ContaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class TrabApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabApplication.class, args);

	}
	@Bean
	public CommandLineRunner commandLineRunner(ContaRepository contaRepository) {
		return args -> {
			System.out.println("Executado");
			System.out.println(contaRepository);
			Conta c1 = new Conta();
			c1.setNome("Matheus");
			c1.setSobrenome("Rodrigues");
			c1.setContato("62992782369");
			c1.setAgencia(0432);
			c1.setNumeroConta(12345678);
			c1.setStatusConta("ATIVO");
			c1.setSaldoConta(new BigDecimal("1000.50"));
			try {
				contaRepository.save(c1);
			}catch (Exception e) {
				e.printStackTrace();
			}
			c1.setId(1L);
			c1 = contaRepository.save(c1);
			System.out.println("Conta:" + c1);
			c1.setSobrenome("Rezende");
			c1 = contaRepository.save(c1);
			System.out.println("Conta 2:" + c1);

			c1 = new Conta();
			c1.setId(2L);
			c1.setNome("Eric");
			c1.setSobrenome("Rezende");
			c1.setContato("62991782145");
			c1.setAgencia(7894);
			c1.setNumeroConta(12345678);
			boolean conta_duplicada = false;
			Integer totalUsoConta = contaRepository.countUtilizacaonumeroConta(c1.getNumeroConta());
			if(totalUsoConta>0){
				System.out.println("O numero de conta: "+c1.getNumeroConta()+"não pode ser utilizado!");
				System.out.println("Total de utilização:"+totalUsoConta);
			}else {
				contaRepository.save(c1);
			}

			Optional<Conta> contaNumeroLocalizador = contaRepository.findContaBynumeroConta(c1.getNumeroConta());
			if(contaNumeroLocalizador.isPresent()){
				Conta conta = contaNumeroLocalizador.get();
				System.out.println("Não é possível usar este número de conta:"+c1.getNumeroConta());
				System.out.println("Em uso por:"+c1.getNome());
			}else{
				c1 = contaRepository.save(c1);
			}
			/*try {
				c1 = contaRepository.save(c1);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				conta_duplicada = e.getMessage().contains(Conta.UK_CONTA);
			}*/
			if(conta_duplicada){
				System.out.println("Conta duplicada:"+c1.getNumeroConta());
			}

		};
	}

	private static void imprimirLista(ContaRepository contaRepository) {
		List<Conta> lista = contaRepository.findAll();
		lista.forEach(item ->{
			System.out.println("Conta 2:" + item);
		});
	}
}