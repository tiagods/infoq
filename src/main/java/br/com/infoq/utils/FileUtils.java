/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.infoq.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JOptionPane;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author tiago
 */
@Slf4j
public class FileUtils {
    
    static String appDir = System.getProperty("user.dir");
    static String tmpDir = System.getProperty("java.io.tmpdir");
    
    static File arquivoTemporario(String dir, String nome,String extensao){
        StringBuilder sb = new StringBuilder();
        sb.append(dir)
                .append("/")
                .append(nome)
                .append("-")
                .append(DateUtil.formatarData(new Date(), "ddMMyyyyHHmmss"))
                .append(".")
                .append(extensao);
        return new File(sb.toString());
    }
   
    public static void backupDatabase(){
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            String sourceFile = "data/db.mv.db";
            if(!Files.exists(Paths.get(sourceFile))) return;
            File backupFolder = new File("backup");
            if(!Files.exists(backupFolder.toPath())) Files.createDirectory(backupFolder.toPath());
            fos = new FileOutputStream(arquivoTemporario(backupFolder.getAbsolutePath(), "db", "zip"));
            
            try (ZipOutputStream zipOut = new ZipOutputStream(fos)) {
                File fileToZip = new File(sourceFile);
                fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);
                byte[] bytes = new byte[1024];
                int length;
                
                while((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
            }
          
            apagarAntigos(backupFolder);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao gerar backup : "+ex.getMessage());
        } catch (IOException ex){
            JOptionPane.showMessageDialog(null, "Falha ao gerar backup : "+ex.getMessage());
        } finally {
            try {
                if(fos != null) fos.close();
                if(fis != null) fis.close();
            } catch (IOException ex) {
                log.error(ex.getMessage());
            }
        }
    }

    private static void apagarAntigos(File backupFolder) throws IOException {
        File[] files = backupFolder.listFiles();
        for(File file : files){
            LocalDate past30Days = LocalDate.now().plusMonths(-1);
            LocalDate dateFile = LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault()).toLocalDate();
            if(dateFile.isBefore(past30Days)){
                Files.deleteIfExists(file.toPath());
            }    
        }
    }
}
