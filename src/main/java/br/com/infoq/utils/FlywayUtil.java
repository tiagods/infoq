/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.utils;

import br.com.infoq.fabrica.Factory.Credenciais;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 *
 * @author tiagods
 */
public class FlywayUtil{
    public static void initialize(Credenciais credenciais) {
//        Flyway flyway = new Flyway();
//        flyway.setDataSource( 
//                        credenciais.getUrl(),
//                        credenciais.getUser(),
//                        credenciais.getPassword()); 
//        flyway.setLocations("classpath:migrations/db");
//        flyway.setTable("FLYWAY_SCHEMA_TABLE");
//        flyway.migrate();
    }
    public static void flwvayInfo(Connection c) {
        try {
                Statement show = c.createStatement();
                ResultSet rs = show.executeQuery("SELECT * FROM FLYWAY_SCHEMA_TABLE ");
                ResultSetMetaData rsmd = rs.getMetaData();
                System.out.println(" SELECT * FROM FLYWAY_SCHEMA_TABLE ");
                while (rs.next()) {
                        System.out.println(" - ");
                        System.out.print("-" + "-"
                        +"installed_rank >>"		+ rs.getString(rsmd.getColumnName(1)) + "***"
                        +"version >>"                   + rs.getString(rsmd.getColumnName(2)) + "***"
                        +"description >>"		+ rs.getString(rsmd.getColumnName(3)) + "***"
                            +"type >>"                  + rs.getString(rsmd.getColumnName(4)) + "***"
                            +"script >>"		+ rs.getString(rsmd.getColumnName(5)) + "***"
                            +"checksum >>"		+ rs.getString(rsmd.getColumnName(6)) + "***"
                        +"installed_by >>"		+ rs.getString(rsmd.getColumnName(7)) + "***"
                        +"installed_on >>"		+ rs.getString(rsmd.getColumnName(8)) + "***"
                        +"execution_time >>"		+ rs.getString(rsmd.getColumnName(9)) + "***"
                        +"success >>"                   + rs.getInt(rsmd.getColumnName(10))   + "***");
                }
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

}
