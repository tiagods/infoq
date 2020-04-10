/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.dao;

import br.com.infoq.fabrica.Conexao;
import br.com.infoq.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author tiagods
 */
public class ClienteDAO extends Conexao{
    public TableModel consultar(String nome){
        Connection conexao = null;
        try {
            String sql = "select * from tbclientes where nomecli like ?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, nome + "%"); 
            ResultSet rs = pst.executeQuery();
            return DbUtils.resultSetToTableModel(rs);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        } finally {
            closeConnection(conexao);
        }
        return null;
    }
    
    public boolean adicionar(Cliente cliente){
        Connection conexao = null;
        try {
            String sql = "insert into tbclientes(nomecli, endcli, numcli, compcli, emailcli, cpfcli, cnpjcli, rgcli, fonecli, celcli, bairrocli) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getEnd());
            pst.setString(3, cliente.getNum());
            pst.setString(4, cliente.getComp());
            pst.setString(5, cliente.getEmail());
            pst.setString(6, cliente.getCpf());
            pst.setString(7, cliente.getCnpj());
            pst.setString(8, cliente.getRg());
            pst.setString(9, cliente.getFone());
            pst.setString(10, cliente.getCel());
            pst.setString(11, cliente.getBairro());
            
            int adicionado = pst.executeUpdate();
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "Registro salvo com Sucesso!");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            closeConnection(conexao);
        }
        return false;
    }
    
    private boolean alterar(Cliente cli){
        Connection conexao = null;
        String sql = "update tbclientes set nomecli = ?, endcli = ?, numcli = ?, compcli = ?, emailcli = ?, cpfcli = ?, cnpjcli = ?, rgcli = ?, fonecli = ?, celcli = ?, bairrocli = ?  where idcli = ?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, cli.getNome());
            pst.setString(2, cli.getEnd());
            pst.setString(3, cli.getNum());
            pst.setString(4, cli.getComp());
            pst.setString(5, cli.getEmail());
            pst.setString(6, cli.getCpf());
            pst.setString(7, cli.getCnpj());
            pst.setString(8, cli.getRg());
            pst.setString(9, cli.getFone());
            pst.setString(10, cli.getCel());
            pst.setString(11, cli.getBairro());
            pst.setInt(12, cli.getId());
            
            int adicionado = pst.executeUpdate();
            if(adicionado > 0){
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }  finally {
            closeConnection(conexao);
        }
        return false;
    }
    
    public boolean deletar(Integer id) {
        Connection conexao = null;
        try {
            conexao = getConnection();
            String sql = "delete from tbclientes where idcli=?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            int apagado = pst.executeUpdate();
            if (apagado > 0) {
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            closeConnection(conexao);
        }
        return false;
    }
}
