/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.dao;

import br.com.infoq.fabrica.Factory;
import static br.com.infoq.fabrica.Factory.credenciais;
import br.com.infoq.utils.FlywayUtil;
import br.com.infoq.utils.ProviderEnum;
import java.sql.Connection;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author tiagods
 */
public class TestDB extends Factory{
    
    public static void init(){
        ProviderEnum.getInstance().setTipo(ProviderEnum.Scope.TEST);
        Connection con = null;
        try{
            con = getConnection();
            FlywayUtil.initialize(credenciais);
            FlywayUtil.flwvayInfo(con);
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
    }
}
