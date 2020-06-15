package br.com.tcc.smartcontract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tcc.smartcontract.facade.SmartContractFacade;

@Service
public class ContratoService {
	
	@Autowired
	private SmartContractFacade contractFacade;
	
	public String criaContrato(String chavePrivadaAutor) throws Exception {
		return contractFacade.criar(chavePrivadaAutor);
	}

	public String recuperaAutor(String chavePrivadaUsuario) throws Exception {
		return contractFacade.recuperarAutor(chavePrivadaUsuario);
	}
}
