/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.dao;

import br.com.infoq.fabrica.Conexao;
import br.com.infoq.model.Cliente;
import br.com.infoq.model.Os;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.mentabean.BeanManager;
import org.mentabean.BeanSession;
import org.mentabean.jdbc.MySQLBeanSession;

/**
 *
 * @author tiagods
 */
public class OsDAO extends Conexao{
    
    private int id;
        private Date data;
        private String tipo;
        private String aparelho;
        private String defeito;
        private String servico;
        private BigDecimal valor = BigDecimal.ZERO;
        private BigDecimal entrada = BigDecimal.ZERO;
        private Cliente cli;
        private String obs;
        private String tecnico;
        private String situacao;
        private String garantia;
        
        
    public OsDAO() throws Exception{
        Properties props = new Properties();
        InputStream inStream = getClass().getClassLoader().getResource("application.properties").openStream();
        props.load(inStream);
        
        Connection con = getConnection();
        if(con!=null) {
            System.out.println("ok");
            con.close();
        }
        
    }

    public static void main(String[] args) throws Exception{
        
        new OsDAO();
    }
}
