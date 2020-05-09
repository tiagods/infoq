/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.infoq.service;

import br.com.infoq.utils.DateUtil;
import static br.com.infoq.view.TelaPrincipal.desktop;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 * @author tiagods
 */
@Component
@Slf4j
public class SwingOptions {
    
    public void abrirTela(JInternalFrame frame){
        desktop.removeAll();
        desktop.add(frame);
        frame.setVisible(true);
    }
    
    public void timeForever(JLabel data, JLabel hora){
                Runnable run = () -> {
                    boolean forever = true;
                    while(forever){
                        if(data!=null || hora!=null) {
                            Date date = new Date();
                            data.setText(DateUtil.formatarData(date, DateFormat.ERA_FIELD));
                            hora.setText(DateUtil.formatarData(date, "HH:mm:ss"));
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                log.error(ex.getMessage());
                                forever = false;
                            }
                        } 
                        else{
                            forever = false;
                        }
                    }
        };
        new Thread(run).start();
    }
}
