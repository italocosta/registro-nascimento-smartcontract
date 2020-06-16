package br.com.tcc.smartcontract.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tcc.smartcontract.facade.SmartContractFacade;
import br.com.tcc.smartcontract.model.RegistroNascimentoRequest;
import br.com.tcc.smartcontract.model.RegistroNascimentoResponse;
import br.com.tcc.smartcontract.model.Wallet;

@Service
public class RegistroNascimentoService {
	
	@Autowired
	private SmartContractFacade contractFacade;
	@Autowired
	private WalletService walletService;

	public Wallet registro(String chavePrivadaUsuario,RegistroNascimentoRequest novoRegistro) throws Exception {
		Wallet novaWallet = walletService.criaWallet();
		novoRegistro.setEndereco(novaWallet.getEndereco());
		contractFacade.registrar(chavePrivadaUsuario,novoRegistro);
		
		return novaWallet;
	}
	
	public List recuperarTodosRegistros(String chavePrivadaUsuario) throws Exception {
		return contractFacade.recuperarTodosRegistros(chavePrivadaUsuario);
	}
	
	public RegistroNascimentoResponse recuperaMeusDados(String chavePrivadaUsuario) throws Exception {
		return contractFacade.recuperarMeusDados(chavePrivadaUsuario);
	}
	
	public RegistroNascimentoResponse recuperaDadosOutro(String chavePrivadaUsuario, String chavePublicaOutro) throws Exception {
		return contractFacade.recuperarDadosOutro(chavePrivadaUsuario, chavePublicaOutro);
	}
	
	public String recuperaAutor(String chavePrivadaUsuario) throws Exception {
		return contractFacade.recuperarAutor(chavePrivadaUsuario);
	}
}
