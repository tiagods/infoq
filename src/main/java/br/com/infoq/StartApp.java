/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq;

import br.com.infoq.config.DataBaseConfig;
import br.com.infoq.fabrica.Factory;
import br.com.infoq.utils.FlywayUtil;
import br.com.infoq.utils.ProviderEnum;
import br.com.infoq.view.frmLogin;
import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author tiagods
 */
@SpringBootApplication
public class StartApp  {
    
    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(StartApp.class)
                .web(WebApplicationType.NONE).headless(false).run(args);
        
        DataBaseConfig props = DataBaseConfig.getInstance();
        ProviderEnum.Scope tipo = ProviderEnum.Scope.valueOf(props.getValue("tipo").toUpperCase());
        if(tipo==null){
            tipo = ProviderEnum.Scope.LOCAL;
        }
        ProviderEnum.getInstance().setTipo(tipo);
        
        frmLogin login = context.getBean(frmLogin.class);
        login.setVisible(true);
    }
}
