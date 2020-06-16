// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.6.8;
pragma experimental ABIEncoderV2;


contract RegistroNascimentoContract {
    
    address private autorAddress;
    
    struct Cidadao {
        address endereco;
        string nome;
        address mae;
        address pai;
        uint dataNascimento;
        uint dataRegistro;
    }
    
    mapping (address => uint) private registros;
    Cidadao[] private cidadaos;
    
    modifier temAutorizacaoAdmin(){
        require(msg.sender == autorAddress, "Sem permissão para realizar operação");
        
        _;
    }
    
    modifier temRegistro(){
        require(registros[msg.sender] != 0, "Nenhum registro encontrado");
        
        _;
    }
    
    constructor() public {
        autorAddress = msg.sender;
    }
    
    function registra(address cidadaoEndereco,
                      string memory nomeCidadao,
                      address maeEndereco,
                      address paiEndereco,
                      uint dataNascimento) public temAutorizacaoAdmin {
        cidadaos.push(Cidadao(cidadaoEndereco, nomeCidadao, maeEndereco, paiEndereco, dataNascimento, now));
        registros[cidadaoEndereco] = cidadaos.length;
    }
    
    function recuperaDados(address cidadaoEndereco) public view temAutorizacaoAdmin 
        returns (address enderecoCidadao,
                 string memory nomeCidadao,
                 address maeEndereco,
                 address paiEndereco,
                 uint dataNascimento,
                 uint dataRegistro) {
        
        require(registros[cidadaoEndereco] != 0, "Nenhum registro encontrado");
        
        uint indice = registros[cidadaoEndereco];
        Cidadao memory c = cidadaos[indice-1];
        return (c.endereco, c.nome, c.mae, c.pai, c.dataNascimento, c.dataRegistro);
    }
    
    function recuperaMeusDados() public view temRegistro 
        returns (address enderecoCidadao,
                 string memory nomeCidadao,
                 address maeEndereco,
                 address paiEndereco,
                 uint dataNascimento,
                 uint dataRegistro) {
        
        uint indice = registros[msg.sender];
        Cidadao memory c = cidadaos[indice-1];
        return (c.endereco, c.nome, c.mae, c.pai, c.dataNascimento, c.dataRegistro);
    }
    
    function getAutorAddress() public view returns (address) {
        return autorAddress;
    }
    
    function recuperaTodosRegistros() public temAutorizacaoAdmin view returns (address[] memory) {
    	address[] memory enderecos = new address[](cidadaos.length);
    	for (uint i = 0; i < cidadaos.length; i++) {
    		enderecos[i] = cidadaos[i].endereco;
    	}
       	return enderecos;
    }
    
}