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
public class EmpresaNotFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3053134318627464469L;

	public EmpresaNotFoundException(String message){
        super(message);
    }
}
