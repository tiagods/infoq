/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.utils;

import br.com.infoq.model.Cliente;
import br.com.infoq.model.Os;
import br.com.infoq.service.ClienteService;
import br.com.infoq.service.OsService;
import br.com.infoq.service.UsuarioService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.List;
import java.util.function.Function;
import static java.util.stream.Collectors.toList;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author tiagods
 */
@Component
public class Relatorio {
    
    @Autowired
    private TemplateEngine templateEngine;
    
    @Autowired
    private ClienteService clientes;
    
    @Autowired
    private OsService os;
    
    @Autowired
    private UsuarioService usuarios;

    private Context getContext(Relatorios relEnum) {
        Context context = new Context();
        if(relEnum.equals(Relatorios.CLIENTES)){
            List<Cliente> list = clientes.listar()
                    .stream()
                    .map(cli->{
                        StringBuilder st = new StringBuilder();
                        st.append(cli.getNum().trim().length()>0? " "+cli.getNum()+" "+cli.getComp(): "");
                        st.append(cli.getBairro().trim().length()>0 ? " - "+cli.getBairro() : "");
                        cli.setEndereco(cli.getEndereco()+st.toString());
                        return cli;
                    })
                    .collect(toList());
            context.setVariable(relEnum.getValor(), list);
        }
        else{
            List<Os> list = os.listar()
                    .stream()
                    .map(c-> {c.setPagar(c.getValor().subtract(c.getEntrada())); return c;})
                    .collect(toList());
            context.setVariable(relEnum.getValor(), list);
        }
        return context;
    }

    public enum Relatorios {
        CLIENTES("clientes"), OS("os"),SERVICOS("servicos");
        private String valor;
        Relatorios(String valor) { this.valor = valor; }
        public String getValor() {return valor;}
    }

    public void imprimirRelatorio(int result, Relatorios relEnum, boolean rotate) {
        if (result == JOptionPane.YES_OPTION) {
            try {
                Context context = getContext(relEnum);
                File pdf = htmlToPdf(context, relEnum.getValor(), relEnum.getValor(), rotate);
                Desktop.getDesktop().open(pdf);
            } catch (IOException ex) {
                   JOptionPane.showMessageDialog(null, ex.getMessage());
            }     
        }
    }
    
    private File htmlToPdf(Context context, String nome, String template, boolean rotate) throws IOException{
        File pdf = FileUtils.arquivoTemporario(FileUtils.tmpDir, nome, "pdf");
        String html = templateEngine.process(template, context);
        ConverterProperties properties = new ConverterProperties();
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(pdf));
        if(rotate){
            pdfDocument.setDefaultPageSize(PageSize.A4.rotate());
        }
        HtmlConverter.convertToPdf(html, pdfDocument, properties);
        return pdf;
    }  

    public void imprimirOs(int result, Os param, Relatorios relEnum) {
        if (result == JOptionPane.YES_OPTION) {
            try {
                Context context = new Context();
                context.setVariable("os", param);
                File pdf = htmlToPdf(context, relEnum.getValor(), relEnum.getValor(), false);
                Desktop.getDesktop().open(pdf);
            } catch (IOException ex) {
                   JOptionPane.showMessageDialog(null, ex.getMessage());
            }  
        }
    }

    public int pegarResultadoImpressao() {
        return JOptionPane.showConfirmDialog(null, "Confirma a visualização do Relatório?", "Atenção", JOptionPane.YES_NO_OPTION);
    }
}
