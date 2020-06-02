/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * @author tiagods
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Os {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    private String aparelho;
    private String defeito;
    private String servico;
    @Column(nullable= false, precision=11, scale=2)
    private BigDecimal valor = BigDecimal.ZERO;
    @Column(nullable= false, precision=11, scale=2)
    private BigDecimal entrada = BigDecimal.ZERO;
    @ManyToOne
    @JoinColumn(name = "cli_id")
    private Cliente cliente;
    private String obs;
    private String tecnico;
    @Enumerated(EnumType.STRING)
    private Situacao situacao;
    private String garantia = "";
    @Transient
    private BigDecimal pagar= BigDecimal.ZERO;
    
    public enum Tipo {
    	GARANTIA, OS
    }
    
    public enum Situacao {
    	NA_BANCADA("Na Bancada"),
    	ENTREGA_FEITA("Entrega Feita"),
    	ORCAMENTO_REPROVADO("Orçamento Reprovado"),
    	AGUARDANDO_APROVACAO("Aguardando Aprovação"),
    	AGUARDANDO_PECA("Aguardando Peça"),
    	ABANDONADO_CLIENTE("Abandonado Pelo Cliente"),
    	RETORNADO("Retornou"),
    	PRONTO_AVISAR("Está Pronto, Avisar Cliente"),
    	SEM_CONSERTO("Sem Conserto");
    	
    	private String descricao;
    	Situacao(String descricao){
    		this.descricao=descricao;
    	}
    	public String getDescricao() {
			return descricao;
		}
    	@Override
    	public String toString() {
    		return getDescricao();
    	}
    }
}
