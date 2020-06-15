package br.com.tcc.smartcontract.resource;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.tcc.smartcontract.model.RegistroNascimento;
import br.com.tcc.smartcontract.service.RegistroNascimentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("registro")
@Api(tags = { "Registro Nascimento" })
@SwaggerDefinition(tags = { @Tag(name = "descricao", description = "Registro de nascimento") })
public class RegistroNascimentoResource {
	
	@Autowired
	private RegistroNascimentoService service;
	
	@PostMapping
	@ApiOperation(value = "Registrar Novo Nascido")
	public ResponseEntity registrar(String chavePrivadaUsuario, @RequestBody RegistroNascimento registro) {
		try {
			service.registro(chavePrivadaUsuario, registro);
			return status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@GetMapping("/meus-dados")
	@ApiOperation(value = "Visualizar Meus Dados")
	public ResponseEntity VisualizarMeusDados(String chavePrivadaUsuario) {
		try {
			RegistroNascimento meusDados = service.recuperaMeusDados(chavePrivadaUsuario);
			return ok(meusDados);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@GetMapping("/dados")
	@ApiOperation(value = "Visualizar Dados de outro")
	public ResponseEntity VisualizarDadosOutro(String chavePrivadaUsuario, String chavePublicaOutro) {
		try {
			RegistroNascimento meusDados = service.recuperaDadosOutro(chavePrivadaUsuario, chavePublicaOutro);
			return ok(meusDados);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	

}
