/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.utils;

import java.awt.Component;
import java.awt.Container;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tiagods
 */
public class SwingUtils {

    private final static String clean = "";

    public static void limparCampos(Container container) {
        List<Component> lista = Arrays.asList(container.getComponents());
        lista.forEach(c -> {
            limpar(c);
        });

    }
    
    public static void limpar(Component c) {
        if (c instanceof JTextField) {
            ((JTextField) c).setText(clean);
        } else if (c instanceof JTextArea) {
            ((JTextArea) c).setText(clean);
        } else if (c instanceof JTextPane) {
            ((JTextPane) c).setText(clean);
        } else if (c instanceof JPasswordField) {
            ((JPasswordField) c).setText(clean);
        } else if (c instanceof JFormattedTextField) {
            ((JFormattedTextField) c).setText(clean);
        } else if (c instanceof JComboBox) {
            ((JComboBox) c).setSelectedIndex(0);
        } else if (c instanceof JPanel) {
            limparCampos((Container) c);
        } else if (c instanceof JScrollPane) {
            limpar(((JScrollPane) c).getViewport().getView());
        }

    }
    
    public static void limparTabela(JTable table){
        DefaultTableModel dm = (DefaultTableModel)table.getModel();
        while(dm.getRowCount() > 0){
            dm.removeRow(0);
        }
    }

}
