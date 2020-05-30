package br.com.infoq.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
	@Id
	private Long id;
	private String nome;
	private String cnpj;
	private String endereco;
	private String bairro;
	private String cep;
	private String cidade;
	@Enumerated(EnumType.STRING)
	private Estado estado;
	private String celular;
	private String telefone;
	private String email;
	private String site;
	@Column(columnDefinition = "text")
	private String mensagem;
	// @Lob
	@Transient
	private byte[] logo;
}
