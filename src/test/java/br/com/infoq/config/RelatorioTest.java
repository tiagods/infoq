package br.com.infoq.config;

import br.com.infoq.model.Cliente;
import br.com.infoq.model.Os;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.css.CssConstants;
import static com.itextpdf.html2pdf.css.CssConstants.LANDSCAPE;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.css.media.MediaType;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 *
 * @author tiagods
 */
public class RelatorioTest {
    
    public static void main() throws IOException, java.lang.IllegalArgumentException{
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        
        List<Cliente> lista = new ArrayList();
        StringBuilder st = new StringBuilder();
        Cliente cli = new Cliente(
            1L,
            "Cliente A",
            "Rua Antonio Francisco de Oliveira",
            "77",
            "B",
            "email@email.com",
            "100.000.000-00",
            "00.000.000/0001-00",
            "99.999.999-9",
            "(11)9999-9999",
            "(11)99999-9999",
            "Jardim Paulista"
        );
        st.append(cli.getNum().trim().length()>0? " "+cli.getNum()+" "+cli.getComp(): "");
        st.append(cli.getBairro().trim().length()>0 ? " - "+cli.getBairro() : "");
        cli.setEndereco(cli.getEndereco()+st.toString());
        
        for(int i = 0;i<50;i++){
            lista.add(cli); 
        }
        
        context.setVariable("clientes", lista);
        
        
        //String htmlText = templateEngine.process("clientes", context);
        
        File fileHtml = new File(System.getProperty("java.io.tmpdir")+"/clientes123.html");
        File pdf = new File(System.getProperty("java.io.tmpdir")+"/message.pdf");

        FileWriter writer = new FileWriter(fileHtml);
        templateEngine.process("clientes", context, writer); 
        ConverterProperties properties = new ConverterProperties();
        HtmlConverter.convertToPdf(fileHtml, pdf, properties);
        Desktop.getDesktop().open(pdf);
    }
    
    public static void main2() throws IOException, java.lang.IllegalArgumentException{
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        
        List<Os> lista = new ArrayList();
        StringBuilder st = new StringBuilder();
        
        Cliente cli = new Cliente(
            1L,
            "Cliente A",
            "Rua Antonio Francisco de Oliveira",
            "77",
            "B",
            "email@email.com",
            "100.000.000-00",
            "00.000.000/0001-00",
            "99.999.999-9",
            "(11)9999-9999",
            "(11)99999-9999",
            "Jardim Paulista"
        );
        
        Os os = new Os(
                1L,
                Calendar.getInstance(),
                "Garantia",
                "Mac Book",
                "Não Liga",
                "Reset placa",
                new BigDecimal("155.90"),
                new BigDecimal("0.00"),
                cli,
                "asjdkfljasldjflasjdflkjaklsdfjlkasjdfkl",
                "Tiago",
                "NA BANCADA",
                "3 MESES"
        );
        st.append(cli.getNum().trim().length()>0? " "+cli.getNum()+" "+cli.getComp(): "");
        st.append(cli.getBairro().trim().length()>0 ? " - "+cli.getBairro() : "");
        cli.setEndereco(cli.getEndereco()+st.toString());
        
        for(int i = 0;i<50;i++){
            lista.add(os); 
        }
        
        context.setVariable("lista", lista);
        
        File fileHtml = new File(System.getProperty("java.io.tmpdir")+"/servicos123.html");
        File pdf = new File(System.getProperty("java.io.tmpdir")+"/message.pdf");

        FileWriter writer = new FileWriter(fileHtml);
        templateEngine.process("servicos", context, writer);
        
        ConverterProperties properties = new ConverterProperties();
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(pdf));
        pdfDocument.setDefaultPageSize(PageSize.A4.rotate());
        
        HtmlConverter.convertToPdf(new FileInputStream(fileHtml), pdfDocument, properties);
        Desktop.getDesktop().open(pdf);
    }
}