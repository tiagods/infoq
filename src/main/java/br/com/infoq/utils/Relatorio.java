/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.utils;

import br.com.infoq.fabrica.Conexao;
import java.sql.Connection;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author tiagods
 */
public class Relatorio extends Conexao {
    
    public enum Relatorios{
        CLIENTES("clientes"),SERVICOS("servicos");
        private String valor;
        Relatorios(String valor){
            this.valor=valor;
        }
        public String getRelatorio(){ return "rel/"+valor+".jasper";}
    }
    /**
     * 
     * @param result O valor do metodo pegarResultadoImpressao()
     * @param relatorio o tipo de relatorio
     */
    public void imprimirRelatorio(int result, Relatorios relEnum){
        if (result == JOptionPane.YES_OPTION){
            Connection con = null;
            try {
               JasperPrint print = JasperFillManager.fillReport(relEnum.getRelatorio(), null, con);
               JasperViewer.viewReport(print, false);
           } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
           } finally {
                closeConnection(con);
            }
       }
    }

    public int pegarResultadoImpressao(){
        return JOptionPane.showConfirmDialog(null, "Confirma a visualização do Relatório?", "Atenção", JOptionPane.YES_NO_OPTION);
    }
}
