package br.com.tcc.smartcontract;

import java.math.BigInteger;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.Tuple;
import org.web3j.tx.gas.DefaultGasProvider;

import br.com.tcc.contracts.generated.RegistroNascimentoContract;

@SpringBootTest
class PocSmartContractApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String a[]) {
		try {
			Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/e1e0636048534f7fba52ffd42a7e16a3"));

			Credentials autor = Credentials.create("FF7E1A6D7B41E0F1D6A1823E0924F17FCD46906D7FBDF09D1B0F115580BE7ECC");
			Credentials italo = Credentials.create("02BFCECAD81B19A44E7F5F9B276A21D90632C1960253137BF3761A929AF5CBC8");
			Credentials jessica = Credentials.create("5B380E1D3FB2D1ADE030A548FCE4F938B4369D07AC26D908950A19189CB31A6B");

			RegistroNascimentoContract registryContract = RegistroNascimentoContract
					.load("0x30b57009ea82CF0288Ff750DA146A9f24AD1bfAB",web3j, italo, new DefaultGasProvider());

			//String autor = registryContract.getAutorAddress().send();
			
			Calendar dataNascimento = Calendar.getInstance();
			dataNascimento.set(Calendar.YEAR, 1991);
			dataNascimento.set(Calendar.MONTH, 3);
			dataNascimento.set(Calendar.DAY_OF_MONTH, 23);
			BigInteger epoca = BigInteger.valueOf(dataNascimento.getTimeInMillis()/1000);
			
			//registryContract.registra(italo.getAddress(), "ITALO RÉGIS ROCHA DA COSTA", autor.getAddress(), autor.getAddress(), epoca).send();
			
			Tuple meusDados = registryContract.recuperaMeusDados().send();
			
			System.out.println(meusDados);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
