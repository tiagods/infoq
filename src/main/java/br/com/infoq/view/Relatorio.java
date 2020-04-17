/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.view;

import java.sql.Connection;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author tiagods
 */
@Component
public class Relatorio {
	@PersistenceContext
	EntityManager entityManager;
   
	private Relatorio() {}
	
    public enum Relatorios {
        CLIENTES("clientes"), SERVICOS("servicos"), OS("os");
        private String valor;

        Relatorios(String valor) {
            this.valor = valor;
        }

        public String getRelatorioJasper() {
            return "rel/" + valor + ".jasper";
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
            Connection con = null;
            try {
                con = (Connection) entityManager.unwrap(Session.class).getDelegate();
                JasperPrint print = JasperFillManager.fillReport(relEnum.getRelatorioJasper(), null, con);
                JasperViewer.viewReport(print, false);
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

	public void imprimir(int result, Map params, Relatorios relEnum) {
        if (result == JOptionPane.YES_OPTION) {
            Connection con = null;
            try {
            	con = (Connection) entityManager.unwrap(Session.class).getDelegate();
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
