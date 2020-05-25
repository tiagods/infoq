package br.com.infoq.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

//@Entity
@Data
public class Empresa {
	//@Id
	private Long id;
	private String nome;
	private String razao;
	private String cnpj;
	private String endereco;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	private String contato;
	private String email;
	private String site;
	//@Lob    
	private byte[] logo;
}
