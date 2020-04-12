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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author tiagods
 */
public class UsuarioDAO extends Factory{
    
    private Usuario result(ResultSet rs) throws SQLException{
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt(1));
        usuario.setUsuario(rs.getString(2));
        usuario.setFone(rs.getString(3));
        usuario.setLogin(rs.getString(4));
        usuario.setSenha(rs.getString(5));
        usuario.setPerfil(rs.getString(6));
        return usuario;
    }
        
    public Optional<Usuario> buscarPorId(String id){
        String sql = "select * from tbusuarios where iduser=?";
        Connection conexao = null;
        try {
            conexao = getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return Optional.of(result(rs));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            closeConnection(conexao);
        }
        return Optional.empty();
    }
    
    public boolean adicionar(Usuario usuario) {
        String sql = "insert into tbusuarios(iduser, usuario, fone, login, senha, perfil) values (?, ?, ?, ?, ?, ?)";
        Connection conexao = null;
        try {
            conexao = getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, usuario.getId());
            pst.setString(2, usuario.getUsuario());
            pst.setString(3, usuario.getFone());
            pst.setString(4, usuario.getLogin());
            pst.setString(5, usuario.getSenha());
            pst.setString(6, usuario.getPerfil());
            return (pst.executeUpdate() > 0);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            closeConnection(conexao);
        }
        return false;
    }
    public boolean alterar(Usuario usuario) {
        String sql = "update tbusuarios set usuario = ?, fone = ?, login = ?, senha = ?, perfil = ? where iduser = ?";
        Connection conexao = null;
        try {
            conexao = getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, usuario.getUsuario());
            pst.setString(2, usuario.getFone());
            pst.setString(3, usuario.getLogin());
            pst.setString(4, usuario.getSenha());
            pst.setString(5, usuario.getPerfil());
            pst.setInt(6, usuario.getId());
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            closeConnection(conexao);
        }
        return false;
    }
    public boolean deletar(Integer id) {
        String sql = "delete from tbusuarios where iduser=?";
        return deletar(id, sql);
    }
    
    public Optional<Usuario> validarLogin(String login, String senha) {
        Connection conexao = null;
        String sql = "select * from tbusuarios where login = ? and senha = ? ";
        try {
            conexao = getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, login);
            pst.setString(2, senha);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) 
                return Optional.of(result(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            closeConnection(conexao);
        }
        return Optional.empty();
    }
    public Optional<Usuario> buscarLogin(String login) {
        Connection conexao = null;
        String sql = "select * from tbusuarios where login = ?";
        try {
            conexao = getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, login);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) 
                return Optional.of(result(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            closeConnection(conexao);
        }
        return Optional.empty();
    }
}