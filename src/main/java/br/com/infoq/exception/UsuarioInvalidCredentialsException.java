/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.exception;

/**
 *
 * @author tiagods
 */
public class UsuarioInvalidCredentialsException extends Exception{
    public UsuarioInvalidCredentialsException(String message){
        super(message);
    }
}