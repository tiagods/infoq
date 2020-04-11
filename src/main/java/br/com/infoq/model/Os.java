/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
/**
 *
 * @author tiagods
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Os {
    private int id;
    private Date data;
    private String tipo;
    private String aparelho;
    private String defeito;
    private String servico;
    private BigDecimal valor = BigDecimal.ZERO;
    private BigDecimal entrada = BigDecimal.ZERO;
    private int cli_id;
    private String obs;
    private String tecnico;
    private String situacao;
    private String garantia = "";
}
