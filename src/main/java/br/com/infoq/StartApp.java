/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.infoq.view.frmLogin;
/**
 *
 * @author tiagods
 */
@SpringBootApplication
@EnableTransactionManagement
public class StartApp  {
    
    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(StartApp.class)
                .web(WebApplicationType.NONE).headless(false).run(args);
        frmLogin login = context.getBean(frmLogin.class);
        login.setVisible(true);
    }
}
