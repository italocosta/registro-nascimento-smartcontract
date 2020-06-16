package br.com.tcc.smartcontract.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class RegistroNascimentoResponse {

	private String chavePublica;
	private String nome;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
	private Date dataNascimento;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
	private Date dataRegistro;
	private String enderecoMae;
	private String enderecoPai;
	
	
}
