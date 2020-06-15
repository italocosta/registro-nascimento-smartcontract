package br.com.tcc.smartcontract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@SpringBootApplication
public class PocSmartContractApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PocSmartContractApplication.class, args);
	}
	
	@Bean
	public Web3j getServiceWeb3j() {
		return Web3j.build(new HttpService("https://ropsten.infura.io/v3/e1e0636048534f7fba52ffd42a7e16a3"));
	}

	/*
	public static void teste() {
		try {
			Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/e1e0636048534f7fba52ffd42a7e16a3"));

			Credentials autor = Credentials.create("0x8f2a55949038a9610f50fb23b5883af3b4ecb3c3bb792cbcefbd1542c692be63");
			Credentials italo = Credentials.create("02BFCECAD81B19A44E7F5F9B276A21D90632C1960253137BF3761A929AF5CBC8");
			Credentials jessica = Credentials.create("5B380E1D3FB2D1ADE030A548FCE4F938B4369D07AC26D908950A19189CB31A6B");

			RegistroNascimento registryContract = RegistroNascimento.load("0x30b57009ea82CF0288Ff750DA146A9f24AD1bfAB",web3j, jessica, new DefaultGasProvider());

			//String autor = registryContract.getAutorAddress().send();
			
			Calendar dataNascimento = Calendar.getInstance();
			dataNascimento.set(Calendar.YEAR, 1991);
			dataNascimento.set(Calendar.MONTH, 3);
			dataNascimento.set(Calendar.DAY_OF_MONTH, 23);
			BigInteger epoca = BigInteger.valueOf(dataNascimento.getTimeInMillis()/1000);
			
			registryContract.registra(italo.getAddress(), "ITALO RÉGIS ROCHA DA COSTA", autor.getAddress(), autor.getAddress(), epoca).send();
			
			//Tuple meusDados = registryContract.recuperaMeusDados().send();
			
			//System.out.println(meusDados);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	*/
}
