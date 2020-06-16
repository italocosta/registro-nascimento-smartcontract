package br.com.tcc.smartcontract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tcc.smartcontract.facade.SmartContractFacade;

@Service
public class ContratoService {
	
	@Autowired
	private SmartContractFacade contratoFacade;
	
	public String criaContrato(String chavePrivadaAutor) throws Exception {
		return contratoFacade.criar(chavePrivadaAutor);
	}
	
	public void carregaContrato(String enderecoContrato) {
		contratoFacade.setContractAddress(enderecoContrato);
	}

	public String recuperaAutor(String chavePrivadaUsuario) throws Exception {
		return contratoFacade.recuperarAutor(chavePrivadaUsuario);
	}
}
