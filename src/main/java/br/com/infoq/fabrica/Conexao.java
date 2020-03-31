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
 * @author Grazi
 */
public class Conexao {

    public static boolean testarConexao() {
          Optional<Connection> opt = Optional.ofNullable(new Conexao().conector());
          return opt.isPresent();
    }

    protected Connection conector() {
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
        if (con != null) {
            try { con.close();
            } catch (SQLException ex) {
                System.err.println("ERRO: " + ex);
            }
        }
    }

    protected void closeConnection(Connection con, PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("ERRO " + ex);
            }
        }
        closeConnection(con);
    }

    protected void closeConnection(Connection con, PreparedStatement stnt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("ERRO " + ex);
            }
        }
        closeConnection(con, stnt);

    }
}
