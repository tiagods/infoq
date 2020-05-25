package br.com.infoq;

import java.io.File;
import java.io.FileInputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.infoq.exception.EmpresaNotFoundException;
import br.com.infoq.model.Empresa;
import br.com.infoq.service.EmpresaService;
import br.com.infoq.utils.FileUtils;
import br.com.infoq.view.frmLogin;
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
        frmLogin login = context.getBean(frmLogin.class);
        login.setVisible(true);
    }

    @Autowired
    EmpresaService empresas;
    
	public void run(String... args) throws Exception {
		
		try {
			empresas.buscarPorId(1L);
		} catch (EmpresaNotFoundException e) {
			File file = new File("images/logo.jpg");
			byte[] picInBytes = new byte[(int) file.length()];
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(picInBytes);
			fileInputStream.close();
			Empresa empresa = new Empresa();
			empresa.setId(1L);
			empresa.setLogo(picInBytes);
			empresas.adicionar(empresa);
			return;
		}
	}
}
