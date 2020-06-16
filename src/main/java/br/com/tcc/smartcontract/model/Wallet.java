package br.com.tcc.smartcontract.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Wallet {
	
	private String endereco;
	private String chavePrivada;

}
