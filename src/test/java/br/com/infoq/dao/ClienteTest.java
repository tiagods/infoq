/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.dao;

import br.com.infoq.fabrica.Factory;
import br.com.infoq.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
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
public class ClienteTest extends Factory{
    static ClienteDAO dao = new ClienteDAO();
    public static Cliente cliente;
    
    @BeforeClass
    public static void init(){
        TestDB.init();
        cliente = new Cliente(
            1, "Ficticio","Rua A", "74", "casa 2", "cliente@cliente.com",
                "99999999999","99.999.999/0001-00",
            "5555555-0","(11)9999-9999","(11)99999-9999", "Jardim Paulista"
        );
    }
    @Test
    public void cliente_1_adicionar(){
        Optional<Integer> adicionar = dao.adicionar(cliente);
        Assert.assertTrue(adicionar.isPresent());
    }
    
    
    @Test
    public void cliente_2_pesquisar_cliente(){
        TableModel pesquisarCliente = dao.pesquisarClienteOsTable("Fic");
        Assert.assertNotNull(pesquisarCliente);
        pesquisarCliente = dao.consultar("Fic");
        Assert.assertNotNull(pesquisarCliente);
    }
    @Test
    public void cliente_3_buscar(){
        Optional<Integer> buscarPorId = dao.buscarPorId(1);
        Assert.assertTrue(buscarPorId.isPresent());
    }
    @Test
    public void cliente_4_buscar_inexistente(){
        Optional<Integer> buscarPorId = dao.buscarPorId(99);
        Assert.assertFalse(buscarPorId.isPresent());
    }
    @Test
    public void cliente_5_alterar(){
        Cliente cli = cliente;
        Optional<Integer> adicionar = dao.adicionar(cli);
        Assert.assertTrue(adicionar.isPresent());
        cli.setId(adicionar.get());
        cli.setEmail("email@email.com.br");
        boolean alterar = dao.alterar(cli);
        Assert.assertTrue(alterar);
    }
    @Test
    public void cliente_6_deletar(){
        Cliente cli = cliente;
        Optional<Integer> adicionar = dao.adicionar(cli);
        Assert.assertTrue(adicionar.isPresent());
        cli.setId(adicionar.get());
        boolean remove = dao.deletar(cli.getId());
        Assert.assertTrue(remove);
    }
    
    @AfterClass
    public static void after(){
        Connection con = getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("TRUNCATE TABLE tbclientes");
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            closeConnection(con);
        }
    }
}
