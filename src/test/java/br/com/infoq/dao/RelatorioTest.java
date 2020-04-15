/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.dao;

import br.com.infoq.model.Cliente;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author tiagods
 */
public class RelatorioTest {
    public static void main(String[] args) throws IOException, DocumentException{
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        
        List<Cliente> lista = new ArrayList();
        
        Cliente cli = new Cliente(
            1,
            "Cliente A",
            "Rua A",
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
        
        for(int i = 0;i<50;i++){
            lista.add(cli); 
        }
          
        context.setVariable("clientes", lista);
        String html = templateEngine.process("clientes", context);
        
        File file = new File("message.pdf");
        OutputStream outputStream = new FileOutputStream(file);
        
        
        Document document = new Document();
        
//        PdfWriter.getInstance(document, outputStream);
//        document.open();
//        
//        Scanner sc= new  Scanner(html).useDelimiter("\n");
//        while(sc.hasNext()){
//            
//            Chunk chunk = new Chunk(sc.next());
//            document.add(chunk);
//            
//        }
        
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.setScaleToFit(true);
        renderer.layout();
        renderer.createPDF(outputStream);
        
        document.close();
        outputStream.close();
        
        
       Desktop.getDesktop().open(file);
    }
}
