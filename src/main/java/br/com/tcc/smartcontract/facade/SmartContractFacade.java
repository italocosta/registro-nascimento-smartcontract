package br.com.tcc.smartcontract.facade;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tuples.generated.Tuple6;

import br.com.tcc.contracts.generated.RegistroNascimentoContract;
import br.com.tcc.smartcontract.CustomGasProvider;
import br.com.tcc.smartcontract.model.RegistroNascimentoRequest;
import br.com.tcc.smartcontract.model.RegistroNascimentoResponse;

@Component
public class SmartContractFacade {

	@Autowired
	private Web3j serviceWeb3j;
	@Value("${contract.address}")
	private String contractAddress;
	
	public RegistroNascimentoContract getContrato(String chavePrivadaUsuario) {
		return RegistroNascimentoContract
				.load(contractAddress,serviceWeb3j, Credentials.create(chavePrivadaUsuario), new CustomGasProvider());
	}
	
	public String criar(String chavePrivadaAutor) throws Exception {
		RegistroNascimentoContract contrato = RegistroNascimentoContract.deploy(serviceWeb3j, Credentials.create(chavePrivadaAutor), new CustomGasProvider()).send();
		contractAddress = contrato.getContractAddress();
		return contractAddress;
	}
	
	public void registrar(String chavePrivadaUsuario, RegistroNascimentoRequest novoRegistro) throws Exception {
		BigInteger epoca = BigInteger.valueOf(novoRegistro.getDataNascimento().getTime()/1000);
		getContrato(chavePrivadaUsuario).registra(novoRegistro.getEndereco(),
				novoRegistro.getNome(),
				novoRegistro.getEnderecoMae(),
				novoRegistro.getEnderecoPai(), 
				epoca)
		.send();
	}
	
	public String recuperarAutor(String chavePrivadaUsuario) throws Exception {
		return getContrato(chavePrivadaUsuario).getAutorAddress().send();
	}
	
	public RegistroNascimentoResponse recuperarMeusDados(String chavePrivadaUsuario) throws Exception {
		RegistroNascimentoContract contrato = getContrato(chavePrivadaUsuario);
		Tuple6<String, String, String, String, BigInteger, BigInteger> dados = 
				contrato.recuperaMeusDados().send();
		Date dataNascimento = new Date();
		dataNascimento.setTime(dados.component5().multiply(BigInteger.valueOf(1000)).longValue());
		
		Date dataRegistro = new Date();
		dataRegistro.setTime(dados.component6().multiply(BigInteger.valueOf(1000)).longValue());
		
		RegistroNascimentoResponse meuRegistro = new RegistroNascimentoResponse(
						dados.component1(),
						dados.component2(),
						dataNascimento,
						dataRegistro,
						dados.component3(),
						dados.component4());
		return meuRegistro;
	}
	
	public RegistroNascimentoResponse recuperarDadosOutro(String chavePrivadaUsuario, String chavePublicaOutro) throws Exception {
		RegistroNascimentoContract contrato = getContrato(chavePrivadaUsuario);
		Tuple6<String, String, String, String, BigInteger, BigInteger> dados = 
				contrato.recuperaDados(chavePublicaOutro).send();
		Date dataNascimento = new Date();
		dataNascimento.setTime(dados.component5().multiply(BigInteger.valueOf(1000)).longValue());
		
		Date dataRegistro = new Date();
		dataRegistro.setTime(dados.component6().multiply(BigInteger.valueOf(1000)).longValue());
		
		RegistroNascimentoResponse meuRegistro = new RegistroNascimentoResponse(
				dados.component1(),
				dados.component2(),
				dataNascimento,
				dataRegistro,
				dados.component3(),
				dados.component4());
		return meuRegistro;
	}
	
	public List recuperarTodosRegistros(String chavePrivadaUsuario) throws Exception {
		RegistroNascimentoContract contrato = getContrato(chavePrivadaUsuario);
		List registros = contrato.recuperaTodosRegistros().send();
		
		return registros;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}
}
