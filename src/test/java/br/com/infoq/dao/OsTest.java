/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.dao;

import br.com.infoq.fabrica.Factory;
import br.com.infoq.model.Cliente;
import br.com.infoq.model.Os;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author tiagods
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OsTest extends Factory{

    static ClienteDAO clienteDAO = new ClienteDAO();
    static OsDAO osDAO = new OsDAO();
    public static Cliente cliente;
    public static Os os;
    @BeforeClass
    public static void init(){
        TestDB.init();
        os = new Os(
                1,
                new Date(),
                "Garantia",
                "DVD",
                "Nao Liga",
                "Limpeza",
                new BigDecimal("120.00"),
                BigDecimal.ZERO,
                1,
                "Tudo em order",
                "Tiago",
                "A devolver",
                ""
        );
        
        cliente = new Cliente(
            1, "Ficticio","Rua A", "74", "casa 2", "cliente@cliente.com",
                "99999999999","99.999.999/0001-00",
            "5555555-0","(11)9999-9999","(11)99999-9999", "Jardim Paulista"
        );
        Optional<Integer> result = clienteDAO.adicionar(cliente);
        Assert.assertTrue(result.isPresent());
        cliente.setId(result.get());
        
    }
    
    @Test
    public void os_1_inserir(){
        os.setCli_id(cliente.getId());
        Optional<Integer> inserir = osDAO.inserir(os);
        Assert.assertTrue(inserir.isPresent());
        os.setId(inserir.get());
    }
    @Test
    public void os_3_alterar(){
        Os os2 = os;
        Optional<Integer> inserir = osDAO.inserir(os2);
        Assert.assertTrue(inserir.isPresent());
        os2.setId(inserir.get());
        os2.setAparelho("PC All In One");
        boolean alterar = osDAO.alterar(os2);
        Assert.assertTrue(alterar);
        Optional<Os> result = osDAO.buscarPorId(os2.getId());
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals("PC All In One", result.get().getAparelho());
    }
    @Test
    public void os_4_buscarPorId(){
        Optional<Os> buscarPorId = osDAO.buscarPorId(os.getId());
        Assert.assertTrue(buscarPorId.isPresent());
    }
    @Test
    public void os_5_buscarPorId_invalido(){
        Optional<Os> buscarPorId = osDAO.buscarPorId(99);
        Assert.assertFalse(buscarPorId.isPresent());
    }
    @Test
    public void os_6_deletar(){
        Os os2 = os;
        Optional<Integer> inserir = osDAO.inserir(os2);
        Assert.assertTrue(inserir.isPresent());
        os2.setId(inserir.get());
        boolean deletar = osDAO.deletar(os2.getId());
        Assert.assertTrue(deletar);
    }
    @AfterClass
    public static void after(){
        Connection con = getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from tbos; delete from tbclientes");
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            closeConnection(con);
        }
    }
}
