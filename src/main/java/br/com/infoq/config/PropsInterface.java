/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.config;

/**
 *
 * @author tiagods
 */
public interface PropsInterface {
    void fileLoad(PropsEnum propsEnum);
    public String getValue(String key);
    public boolean containskey(String key);
}
