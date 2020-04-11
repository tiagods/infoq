/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq;

import br.com.infoq.config.DataBaseConfig;
import br.com.infoq.fabrica.Conexao;
import br.com.infoq.utils.FlywayUtil;
import br.com.infoq.utils.TipoEnum;
import br.com.infoq.view.frmLogin;
import java.sql.Connection;

/**
 *
 * @author tiagods
 */
public class StartApp extends Conexao{
    public static void main(String[] args) {
        DataBaseConfig props = DataBaseConfig.getInstance();
        TipoEnum.Tipo tipo = TipoEnum.Tipo.valueOf(props.getValue("tipo").toUpperCase());
        if(tipo==null){
            tipo = TipoEnum.Tipo.LOCAL;
        }
        
        TipoEnum.getInstance().setTipo(tipo);
        Connection con = null;
        try{
            con = getConnection();
            if(!tipo.equals(TipoEnum.Tipo.REMOTO)){
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
