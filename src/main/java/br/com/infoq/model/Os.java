/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    private Date data;
    private String tipo;
    private String aparelho;
    private String defeito;
    private String servico;
    @Column(nullable= false, precision=7, scale=2)
    private BigDecimal valor = BigDecimal.ZERO;
    @Column(nullable= false, precision=7, scale=2)
    private BigDecimal entrada = BigDecimal.ZERO;
    @ManyToOne
    @JoinColumn(name = "cli_id")
    private Cliente cliente;
    private String obs;
    private String tecnico;
    private String situacao;
    private String garantia = "";
}
