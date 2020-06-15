// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.6.8;

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
    
    mapping (address => Cidadao) private registros;
    
    modifier temAutorizacaoAdmin(){
        require(msg.sender == autorAddress, "Sem permissão para realizar operação");
        
        _;
    }
    
    modifier temRegistro(){
        require(registros[msg.sender].endereco != address(0), "Nenhum registro encontrado");
        
        _;
    }
    
    modifier temRegistroAdmin(){
        require(msg.sender == autorAddress, "Sem permissão para realizar operação");
        require(registros[msg.sender].endereco != address(0), "Nenhum registro encontrado");
        
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
                          
        registros[cidadaoEndereco] = Cidadao(cidadaoEndereco, nomeCidadao, maeEndereco, paiEndereco, dataNascimento, now);
        
    }
    
    function recuperaDados(address cidadaoEndereco) public view temRegistroAdmin 
        returns (address enderecoCidadao,
                 string memory nomeCidadao,
                 address maeEndereco,
                 address paiEndereco,
                 uint dataNascimento,
                 uint dataRegistro) {
        Cidadao memory c = registros[cidadaoEndereco];
        return (c.endereco, c.nome, c.mae, c.pai, c.dataNascimento, c.dataRegistro);
    }
    
    function recuperaMeusDados() public view temRegistro 
        returns (address enderecoCidadao,
                 string memory nomeCidadao,
                 address maeEndereco,
                 address paiEndereco,
                 uint dataNascimento,
                 uint dataRegistro) {
        Cidadao memory c = registros[msg.sender];
        return (c.endereco, c.nome, c.mae, c.pai, c.dataNascimento, c.dataRegistro);
    }
    
    function getAutorAddress() public view returns (address) {
        return autorAddress;
    }
    
}