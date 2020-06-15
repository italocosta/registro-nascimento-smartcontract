package br.com.tcc.smartcontract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tcc.smartcontract.facade.SmartContractFacade;
import br.com.tcc.smartcontract.model.RegistroNascimento;

@Service
public class RegistroNascimentoService {
	
	@Autowired
	private SmartContractFacade contractFacade;

	public void registro(String chavePrivadaUsuario,RegistroNascimento novoRegistro) throws Exception {
		contractFacade.registrar(chavePrivadaUsuario,novoRegistro);
	}
	
	public RegistroNascimento recuperaMeusDados(String chavePrivadaUsuario) throws Exception {
		return contractFacade.recuperarMeusDados(chavePrivadaUsuario);
	}
	
	public RegistroNascimento recuperaDadosOutro(String chavePrivadaUsuario, String chavePublicaOutro) throws Exception {
		return contractFacade.recuperarDadosOutro(chavePrivadaUsuario, chavePublicaOutro);
	}
	
	public String recuperaAutor(String chavePrivadaUsuario) throws Exception {
		return contractFacade.recuperarAutor(chavePrivadaUsuario);
	}
}
