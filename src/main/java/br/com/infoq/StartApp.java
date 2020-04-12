/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq;

import br.com.infoq.config.DataBaseConfig;
import br.com.infoq.fabrica.Factory;
import br.com.infoq.utils.FlywayUtil;
import br.com.infoq.utils.ProviderEnum;
import br.com.infoq.view.frmLogin;
import java.sql.Connection;

/**
 *
 * @author tiagods
 */
public class StartApp extends Factory{
    public static void main(String[] args) {
        DataBaseConfig props = DataBaseConfig.getInstance();
        ProviderEnum.Scope tipo = ProviderEnum.Scope.valueOf(props.getValue("tipo").toUpperCase());
        if(tipo==null){
            tipo = ProviderEnum.Scope.LOCAL;
        }
        
        ProviderEnum.getInstance().setTipo(tipo);
        Connection con = null;
        try{
            con = getConnection();
            if(!tipo.equals(ProviderEnum.Scope.REMOTO)){
                FlywayUtil.initialize(credenciais);
                FlywayUtil.flwvayInfo(con);
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        frmLogin login = new frmLogin();
        login.setVisible(true);
    }
}
