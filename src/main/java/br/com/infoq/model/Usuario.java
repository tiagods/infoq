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
@NoArgsConstructor
@Data
public class Usuario {
    private Integer id;
    private String usuario;
    private String fone;
    private String login;
    private String senha;
    private Perfil perfil;
    public enum Perfil{
        ADMIN,USER
    }
}   
