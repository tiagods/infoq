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
public class UsuarioNotFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7647954193406373224L;

	public UsuarioNotFoundException(String message){
        super(message);
    }
}
