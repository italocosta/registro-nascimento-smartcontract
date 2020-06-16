package br.com.tcc.smartcontract;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Calendar;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.crypto.WalletUtils;
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

	public static void main2(String a[]) {
		try {
			Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/e1e0636048534f7fba52ffd42a7e16a3"));

			Credentials autor = Credentials.create("FF7E1A6D7B41E0F1D6A1823E0924F17FCD46906D7FBDF09D1B0F115580BE7ECC");
			Credentials italo = Credentials.create("02BFCECAD81B19A44E7F5F9B276A21D90632C1960253137BF3761A929AF5CBC8");
			Credentials jessica = Credentials
					.create("5B380E1D3FB2D1ADE030A548FCE4F938B4369D07AC26D908950A19189CB31A6B");

			RegistroNascimentoContract registryContract = RegistroNascimentoContract
					.load("0x30b57009ea82CF0288Ff750DA146A9f24AD1bfAB", web3j, italo, new DefaultGasProvider());

			// String autor = registryContract.getAutorAddress().send();

			Calendar dataNascimento = Calendar.getInstance();
			dataNascimento.set(Calendar.YEAR, 1991);
			dataNascimento.set(Calendar.MONTH, 3);
			dataNascimento.set(Calendar.DAY_OF_MONTH, 23);
			BigInteger epoca = BigInteger.valueOf(dataNascimento.getTimeInMillis() / 1000);

			// registryContract.registra(italo.getAddress(), "ITALO RÉGIS ROCHA DA COSTA",
			// autor.getAddress(), autor.getAddress(), epoca).send();

			Tuple meusDados = registryContract.recuperaMeusDados().send();

			System.out.println(meusDados);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main1(String a[]) throws IOException, CipherException, InvalidAlgorithmParameterException,
			NoSuchAlgorithmException, NoSuchProviderException {
		String walletPassword = "secr3t";
		String walletDirectory = "C:\\teste";

		String walletName = WalletUtils.generateNewWalletFile(walletPassword, new File(walletDirectory));
		System.out.println("wallet location: " + walletDirectory + "/" + walletName);

		Credentials credentials = WalletUtils.loadCredentials(walletPassword, walletDirectory + "/" + walletName);

		String accountAddress = credentials.getAddress();
		credentials.getEcKeyPair().getPublicKey();
		System.out.println("Private address: " + credentials.getEcKeyPair().getPrivateKey());
		System.out.println("Public address: " + credentials.getEcKeyPair().getPublicKey());
		System.out.println("Account address: " + credentials.getAddress());
	}

	public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException, JSONException {
		String seed = UUID.randomUUID().toString();
		JSONObject result = process(seed);
		System.out.println(result.toString());
	}

	private static JSONObject process(String seed) throws CipherException, JSONException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {

		JSONObject processJson = new JSONObject();

			ECKeyPair ecKeyPair = Keys.createEcKeyPair();
			BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();

			String sPrivatekeyInHex = privateKeyInDec.toString(16);

			WalletFile aWallet = Wallet.createLight(seed, ecKeyPair);
			String sAddress = aWallet.getAddress();

			processJson.put("address", "0x" + sAddress);
			processJson.put("privatekey", sPrivatekeyInHex);

		

		return processJson;
	}

}