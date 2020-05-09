/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.utils;

/**
 *
 * @author tiagods
 */
public class Validator {
    private static final String patternNumerico = "[0-9]+$";
    
    public static boolean validarNumero(String valor){
        return valor.matches(patternNumerico);
    }
}
