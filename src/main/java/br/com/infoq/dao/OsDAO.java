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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import org.mentabean.BeanManager;
import org.mentabean.BeanSession;
import org.mentabean.jdbc.MySQLBeanSession;

/**
 *
 * @author tiagods
 */
public class OsDAO extends Conexao{
    
    public Os result(ResultSet rs) throws SQLException{
        
        return new Os(
                rs.getInt(1),
                rs.getDate(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getBigDecimal(7),
                rs.getBigDecimal(8),
                rs.getInt(9),
                rs.getString(10),
                rs.getString(11),
                rs.getString(12),
                rs.getString(13)
        );
    }
//    public Optional<Os> consultar() {
//
//        String sql = "select * from tbos order by os desc";
//        Connection conexao = null;
//        try {
//            conexao = getConnection();
//            PreparedStatement pst = conexao.prepareStatement(sql);
//            ResultSet rs = pst.executeQuery();
//            if (rs.next()) {
//                return Optional.of(result(rs));
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e);
//        } finally{
//            closeConnection(conexao);
//        }
//        return Optional.empty();
//    }
    
    public Optional<Os> buscarPorId(Integer id) {

        String sql = "select * from tbos where os = ?";
        Connection conexao = null;
        try {
            conexao = getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return Optional.of(result(rs));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally{
            closeConnection(conexao);
        }
        return Optional.empty();
    }
    
    public boolean deletar(Integer id) {
        String sql = "delete from tbos where os=?";
        return deletar(id, sql);
    }
    public boolean alterar(Os os) {
        String sql = "update tbos set tipo=?, situacao=?, aparelho=?, defeito=?, servico=?, tecnico=?, valor=?, entrada=?, obs=? where os=?";
        Connection conexao = null;
        try {
            conexao = getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, os.getTipo());
            pst.setString(2, os.getSituacao());
            pst.setString(3, os.getAparelho());
            pst.setString(4, os.getDefeito());
            pst.setString(5, os.getServico());
            pst.setString(6, os.getTecnico());
            pst.setBigDecimal(7, os.getValor());
            pst.setBigDecimal(8, os.getEntrada());
            pst.setString(9, os.getObs());
            pst.setInt(10, os.getId());
            return pst.executeUpdate()>0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally{
            closeConnection(conexao);
        }
        return false;
    }
    public Optional<Integer> inserir(Os os) {
        String sql = "insert into tbos(Tipo, situacao, aparelho, defeito, servico, tecnico, valor, entrada, obs, idcli, garantia) values (?,?,?,?,?,?,?,?,?,?,?)";
        Connection conexao = null;
        Integer key = -1;
        try {
            conexao = getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, os.getTipo());
            pst.setString(2, os.getSituacao());
            pst.setString(3, os.getAparelho());
            pst.setString(4, os.getDefeito());
            pst.setString(5, os.getServico());
            pst.setString(6, os.getTecnico());
            pst.setBigDecimal(7, os.getValor());
            pst.setBigDecimal(8, os.getEntrada());
            pst.setString(9,os.getObs());
            pst.setInt(10, os.getCli_id());
            pst.setString(11, os.getGarantia());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if(rs.next()){
                key = rs.getInt(1);
                return Optional.of(key);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally{
            closeConnection(conexao);
        }
        return Optional.empty();
    
    }
}
