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
public class UsuarioInvalidCredentialsException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5871742492599200711L;

	public UsuarioInvalidCredentialsException(String message){
        super(message);
    }
}
