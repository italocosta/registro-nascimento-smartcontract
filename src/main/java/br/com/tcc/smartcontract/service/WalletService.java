package br.com.tcc.smartcontract.service;

import java.math.BigInteger;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.WalletFile;

import br.com.tcc.smartcontract.model.Wallet;

@Service
public class WalletService {

	public Wallet criaWallet() throws Exception {
		try {
			String seed = UUID.randomUUID().toString();

			ECKeyPair ecKeyPair = Keys.createEcKeyPair();
			BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();
			String sPrivatekeyInHex = privateKeyInDec.toString(16);

			WalletFile aWallet = org.web3j.crypto.Wallet.createLight(seed, ecKeyPair);
			String sAddress = "0x" + aWallet.getAddress();

			return new Wallet(sAddress, sPrivatekeyInHex);
		} catch (Exception e) {
			throw new Exception("Erro ao gerar nova wallet. " + e.getMessage());
		}
	}

}
