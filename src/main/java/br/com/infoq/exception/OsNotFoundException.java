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
public class OsNotFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6938458181779411277L;

	public OsNotFoundException(String message){
        super(message);
    }
}
