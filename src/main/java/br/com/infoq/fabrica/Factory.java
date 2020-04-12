/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.fabrica;

import br.com.infoq.config.DataBaseConfig;
import br.com.infoq.utils.ProviderEnum;
import java.sql.*;
import java.util.Optional;
import javax.swing.JOptionPane;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fabrica de conexoes
 *
 * @author tiagods
 */
public class Factory {
    
    public static Credenciais credenciais;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Credenciais{
        private String driver;
        private String url;
        private String user;
        private String password;
    }
    
    public static boolean testarConexao() {
        Factory conexao = new Factory();
        Optional<Connection> opt = Optional.ofNullable(conexao.getConnection());
        if (opt.isPresent()) {
            conexao.closeConnection(opt.get());
            return true;
        } else {
            return false;
        }
    }
    
    protected static Connection getConnection() {
        try {
            DataBaseConfig props = DataBaseConfig.getInstance();
            
            ProviderEnum tipo = ProviderEnum.getInstance();
            String param = tipo.getTipo().toString().toLowerCase();
            
            credenciais = new Credenciais(props.getValue("driver_"+param),
                    props.getValue("url_"+param),
                    props.getValue("user_"+param),
                    props.getValue("password_"+param)
            );
            
            Class.forName(credenciais.getDriver());
            return DriverManager.getConnection(
                    credenciais.getUrl(),
                    credenciais.getUser(),
                    credenciais.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean deletar(Integer id, String sql) {
        Connection conexao = null;
        try {
            conexao = getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            return (pst.executeUpdate() > 0);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            closeConnection(conexao);
        }
        return false;
    }
    protected static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.err.println("ERRO: " + ex);
        }
    }
}
