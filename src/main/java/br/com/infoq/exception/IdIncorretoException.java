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
public class IdIncorretoException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3770526910862417386L;

	public IdIncorretoException(String message){
        super(message);
    }
}
