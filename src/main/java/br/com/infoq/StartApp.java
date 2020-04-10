/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq;

import br.com.infoq.fabrica.Conexao;
import br.com.infoq.utils.FlywayUtil;
import br.com.infoq.view.frmLogin;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author tiagods
 */
public class StartApp extends Conexao{
    public static void main(String[] args) {
        Connection con = null;
        try{
            con = getConnection();
            if(tipo.equals(Tipo.LOCAL)){
                FlywayUtil.initialize(credenciais);
                FlywayUtil.flwvayInfo(con);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Falha ao iniciar a aplicacao "+e.getMessage());
        } finally {
            closeConnection(con);
        }
        frmLogin login = new frmLogin();
        login.setVisible(true);
    }
}
