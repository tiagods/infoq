/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.fabrica;

import br.com.infoq.config.DataBaseConfig;
import java.sql.*;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fabrica de conexoes
 *
 * @author tiagods
 */
public class Conexao {
    public enum Tipo{
        LOCAL, REMOTO
    }
    public static Tipo tipo = Tipo.REMOTO;
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
        Conexao conexao = new Conexao();
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
            tipo = Tipo.valueOf(props.getValue("tipo").toUpperCase());
            String param = tipo.toString().toLowerCase();
            
            credenciais = new Credenciais(props.getValue("driver_"+param),
                    props.getValue("url_"+tipo.toString().toLowerCase()),
                    props.getValue("user_"+tipo.toString().toLowerCase()),
                    props.getValue("password_"+tipo.toString().toLowerCase())
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

    protected static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.err.println("ERRO: " + ex);
        }
    }

    protected void closeConnection(Connection con, PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            System.err.println("ERRO " + ex);
        }
        closeConnection(con);
    }

    protected void closeConnection(Connection con, PreparedStatement stnt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            System.err.println("ERRO " + ex);
        }
        closeConnection(con, stnt);
    }
}
