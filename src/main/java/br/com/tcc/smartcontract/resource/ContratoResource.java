package br.com.tcc.smartcontract.resource;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.tcc.smartcontract.service.ContratoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("contrato")
@Api(tags = { "Contrato" })
@SwaggerDefinition(tags = { @Tag(name = "descricao", description = "Manutenção do contrato") })
public class ContratoResource {
	
	@Autowired
	private ContratoService service;
	
	@PostMapping
	@ApiOperation(value = "Cria um novo contrato e abandona o anterior")
	public ResponseEntity cria(String chavePrivadaAutor) {
		try {
			String contractAddress = service.criaContrato(chavePrivadaAutor);
			return ok(contractAddress);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@GetMapping("/autor")
	@ApiOperation(value = "Mostrar o autor do contrato")
	public ResponseEntity<String> getAutorContrato(String chavePrivadaUsuario) {
		try {
			return ok(service.recuperaAutor(chavePrivadaUsuario));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
