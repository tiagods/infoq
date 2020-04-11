/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.utils;

/**
 *
 * @author tiago
 */
public class TipoEnum {
    
    private TipoEnum(){}
    
    private static TipoEnum instance;
    
    public static TipoEnum getInstance(){
        if(instance==null) instance = new TipoEnum();
        return instance;
    }

    
    public void setTipo(Tipo tipo){
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }
    
    
    public enum Tipo {
        LOCAL, REMOTO, TEST
    }
    
    private Tipo tipo = Tipo.LOCAL;
    
}
