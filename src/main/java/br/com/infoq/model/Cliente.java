/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author tiagods
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Cliente {
    private int id;
    private String nome;
    private String end;
    private String num;
    private String comp;
    private String email;
    private String cpf;
    private String cnpj;
    private String rg;
    private String fone;
    private String cel;
    private String bairro;
}
