/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.utils;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author tiagods
 */
@Getter
@Setter
public class ProviderEnum {
    
    private ProviderEnum(){}
    
    private static ProviderEnum instance;
    
    public static ProviderEnum getInstance(){
        if(instance==null) instance = new ProviderEnum();
        return instance;
    }
    
    public enum Scope {
        LOCAL, REMOTO, TEST
    }
    
    private Scope tipo = Scope.LOCAL;
    
}
