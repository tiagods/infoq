/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.dao;

import br.com.infoq.fabrica.Factory;
import br.com.infoq.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class UsuarioTest extends Factory{
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    private static Usuario user;
    
    @BeforeClass
    public static void init(){
        TestDB.init();
        user = new Usuario(
            55,"Tiago","(11) 9666-9666","tiagods","tiagods","user"
        );
    }
    
    @Test
    public void usuario_1_ok(){
        boolean adicionar = usuarioDAO.adicionar(user);
        Assert.assertTrue(adicionar);
    }
    @Test
    public void usuario_2_erro_mesmo_login(){
        Usuario user2 = user;
        user2.setId(2);
        Optional<Usuario> buscarLogin = usuarioDAO.buscarLogin(user2.getLogin());
        Assert.assertTrue(buscarLogin.isPresent());
    }
    @Test
    public void usuario_3_alterar(){
        Usuario u2 = user;
        u2.setId(10);
        boolean adicionar = usuarioDAO.adicionar(u2);
        Assert.assertTrue(adicionar);
        u2.setPerfil("admin");
        u2.setUsuario("Fulano Silva");
        boolean alterou = usuarioDAO.alterar(u2);
        Assert.assertTrue(alterou);
        Optional<Usuario> retorno = usuarioDAO.buscarPorId("10");
        Assert.assertEquals(true, retorno.isPresent());
        Assert.assertEquals(u2, retorno.get());
    }
    @Test
    public void usuario_4_alterar_inexistente(){
        Usuario user2 = user;
        user2.setId(99);
        boolean alterar = usuarioDAO.alterar(user2);
        Assert.assertFalse(alterar);
    }
    @Test
    public void usuario_5_aceitar_login(){
        Optional<Usuario> validarLogin = usuarioDAO.validarLogin(user.getLogin(), user.getSenha());
        Assert.assertTrue(validarLogin.isPresent());
    }
    @Test
    public void usuario_6_recusar_login(){
        Optional<Usuario> validarLogin = usuarioDAO.validarLogin("piracicaba", "jujuba");
        Assert.assertFalse(validarLogin.isPresent());
    }
    @Test
    public void usuario_7_buscar(){
        Usuario u2 = user;
        u2.setId(11);
        boolean adicionar = usuarioDAO.adicionar(u2);
        Assert.assertTrue(adicionar);
        Optional<Usuario> buscarPorId = usuarioDAO.buscarPorId(u2.getId()+"");
        Assert.assertTrue(buscarPorId.isPresent());
    }
    @Test
    public void usuario_8_buscar_inexistente(){
        Optional<Usuario> buscarPorId = usuarioDAO.buscarPorId("99");
        Assert.assertFalse(buscarPorId.isPresent());
    }
    @Test
    public void usuario_9_deletar(){
        Usuario userPurge = user;
        userPurge.setId(99);
        userPurge.setLogin("teste");
        boolean adicionar = usuarioDAO.adicionar(userPurge);
        Assert.assertTrue(adicionar);
        boolean deletar = usuarioDAO.deletar(99);
        Assert.assertTrue(deletar);
    }
    
    @AfterClass
    public static void after(){
        Connection con = getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("DELETE FROM tbusuarios");
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            closeConnection(con);
        }
    }
}
