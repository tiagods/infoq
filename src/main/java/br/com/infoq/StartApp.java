package br.com.infoq;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import br.com.infoq.utils.FileUtils;
import br.com.infoq.view.TelaLogin;
/**
 *
 * @author tiagods
 */
@SpringBootApplication
@EnableTransactionManagement
public class StartApp {
    
    public static void main(String[] args) {
        FileUtils.backupDatabase();
        ApplicationContext context = new SpringApplicationBuilder(StartApp.class)
                .web(WebApplicationType.NONE).headless(false).run(args);
        TelaLogin login = context.getBean(TelaLogin.class);
        login.setVisible(true);
    }
}
