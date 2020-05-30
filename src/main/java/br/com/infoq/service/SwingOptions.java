/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.infoq.service;

import br.com.infoq.utils.DateUtil;
import br.com.infoq.view.TelaCliente;
import br.com.infoq.view.TelaEmpresa;
import br.com.infoq.view.TelaMenu;
import br.com.infoq.view.TelaOs;
import static br.com.infoq.view.TelaPrincipal.desktop;
import br.com.infoq.view.TelaSobre;
import br.com.infoq.view.TelaUsuario;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author tiagods
 */
@Component
@Slf4j
public class SwingOptions {
    
    @Autowired private TelaSobre sobre;
    @Autowired private TelaUsuario usuario;
    @Autowired private TelaCliente cliente;
    @Autowired private TelaOs os;
    @Autowired private TelaMenu menu;
    @Autowired private TelaEmpresa empresa;
    
    private void abrirTela(JInternalFrame frame){
        desktop.removeAll();
        desktop.add(frame);
        frame.setVisible(true);
    }
    
    public void abrirTelaCliente(){
        abrirTela(cliente);
        cliente.pesquisar();
    }

    public void abrirTelaEmpresa(){
        abrirTela(empresa);
        empresa.buscarEmpresa();
    }

    public void abrirTelaOs(){
        abrirTela(os);
        os.pesquisarOs();
        os.pesquisarCliente();
        os.limparCampos();
    }

    public void abrirTelaUsuario(){
        abrirTela(usuario);
        usuario.pesquisar();
    }
    public void abrirTelaMenu() {
        abrirTela(menu);
    }
    public void abrirTelaSobre(){
        sobre.setVisible(true);
    }
    
    public void timeForever(JLabel data, JLabel hora){
        Runnable run = () -> {
            boolean forever = true;
            while (forever) {
                if (data != null || hora != null) {
                    Date date = new Date();
                    data.setText(DateUtil.formatarData(date, DateFormat.ERA_FIELD));
                    hora.setText(DateUtil.formatarData(date, "HH:mm:ss"));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        log.error(ex.getMessage());
                        forever = false;
                    }
                } else {
                    forever = false;
                }
            }
        };
        new Thread(run).start();
    }
}
