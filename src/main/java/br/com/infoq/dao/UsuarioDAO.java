/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.dao;

import br.com.infoq.fabrica.Conexao;
import br.com.infoq.model.Usuario;
import br.com.infoq.model.Usuario.Perfil;
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
public class UsuarioDAO extends Conexao{
    
    private Usuario result(ResultSet rs) throws SQLException{
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt(1));
        usuario.setUsuario(rs.getString(2));
        usuario.setFone(rs.getString(3));
        usuario.setLogin(rs.getString(4));
        usuario.setSenha(rs.getString(5));
        usuario.setPerfil(Perfil.valueOf(rs.getString(6)));
        return usuario;
    }
    
    public Optional<Usuario> validarLogin(String login, String senha) {
        Connection conexao = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
 
        String sql = "select * from tbusuarios where login = ? and senha = ? ";
        try {
            conexao = getConnection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, login);
            pst.setString(2, senha);
            rs = pst.executeQuery();
            if (rs.next()) 
                return Optional.of(result(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            closeConnection(conexao, pst, rs);
        }
        return Optional.empty();
    }
}
