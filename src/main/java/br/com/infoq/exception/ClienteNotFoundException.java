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
public class ClienteNotFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4261462705313406393L;

	public ClienteNotFoundException(String message){
        super(message);
    }
}
