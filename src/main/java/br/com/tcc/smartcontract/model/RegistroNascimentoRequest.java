package br.com.tcc.smartcontract.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class RegistroNascimentoRequest {

	@JsonIgnore
	private String endereco;
	private String nome;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
	private Date dataNascimento;
	private String enderecoMae;
	private String enderecoPai;
	
	
	
}
