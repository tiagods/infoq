/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;

/**
 *
 * @author tiagods
 */
@Component
public class Relatorio {
    
    @Autowired
    private TemplateEngine templateEngine;

    
    public enum Relatorios {
        CLIENTES("clientes"), SERVICOS("servicos"), OS("os");

        private String valor;

        Relatorios(String valor) {
            this.valor = valor;
        }

        public String getValor() {
            return valor;
        }

        public String getRelatorioJrxml() {
            return "rel/" + valor + ".jrxml";
        }
    }

    /**
     *
     * @param result O valor do metodo pegarResultadoImpressao()
     * @param relEnum o tipo de relatorio
     */
    public void imprimirRelatorio(int result, Relatorios relEnum) {
        if (result == JOptionPane.YES_OPTION) {
            
            
            
            
            try {
                //jasper cliente e SERVICOS
                FileInputStream os = new FileInputStream(new File(""));
            } catch (FileNotFoundException ex) {
                   JOptionPane.showMessageDialog(null, ex.getMessage());
            }     
        }
    }

    @Transactional
    public void imprimir(int result, Map params, Relatorios relEnum) {
        if (result == JOptionPane.YES_OPTION) {

            try {
                Connection con = null;
                JasperReport relatorio = JasperCompileManager.compileReport(relEnum.getRelatorioJrxml());
                JasperPrint impressao = JasperFillManager.fillReport(relatorio, params, con);
                JasperViewer view = new JasperViewer(impressao, false);
                view.setTitle("Relatório de Ordem de Serviços");
                view.setVisible(true);
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public int pegarResultadoImpressao() {
        return JOptionPane.showConfirmDialog(null, "Confirma a visualização do Relatório?", "Atenção", JOptionPane.YES_NO_OPTION);
    }
}
