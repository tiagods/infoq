/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.fabrica;

import br.com.infoq.config.DataBaseConfig;
import java.sql.*;
import java.util.Optional;

/**
 * Fabrica de conexoes
 *
 * @author tiagods
 */
public class Conexao {

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

    protected Connection getConnection() {
        try {
            DataBaseConfig props = DataBaseConfig.getInstance();
            Class.forName(props.getValue("driver"));
            return DriverManager.getConnection(
                    props.getValue("url"),
                    props.getValue("user"),
                    props.getValue("password"));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void closeConnection(Connection con) {
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
