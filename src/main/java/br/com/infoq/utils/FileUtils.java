/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.infoq.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.springframework.data.util.Pair;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author tiago
 */
@Slf4j
public class FileUtils {
    
    static String appDir = System.getProperty("user.dir");
    static String tmpDir = System.getProperty("java.io.tmpdir");
    static File imagem = new File(appDir+"\\images\\logo.jpg");
    
    public static void main(String[] args) {
		calcularRedimensionamento(1280, 720, 1200, 800);
		calcularRedimensionamento(1280, 720, 1380, 700);
		calcularRedimensionamento(1280, 720, 1380, 800);
	}
    
    public static Pair<Integer, Integer> calcularRedimensionamento(double largulaMax, double alturaMax, double componenteLargula, double componenteAltura) {
    	Double novaImgLargura = componenteLargula;
    	Double novaImgAltura = componenteAltura;
		if(componenteLargula > largulaMax) {
			novaImgLargura = largulaMax;
			novaImgAltura = (componenteAltura/componenteLargula)*novaImgLargura;
		}
		if (componenteAltura > alturaMax){
			novaImgAltura = alturaMax;
			novaImgLargura = componenteLargula/componenteAltura*novaImgAltura;
		}
		log.info("Medidas Limites "+largulaMax+"*"+alturaMax+" Componente "+componenteLargula+"*"+componenteAltura);
		log.info("Medida final "+novaImgLargura+"*"+novaImgAltura);
		return Pair.of(novaImgLargura.intValue(), novaImgAltura.intValue());
    }
    
    public static File carregarArquivo() {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setToolTipText("Carregar");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG and JPG images", "png", "jpg", "jpeg");
		fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        
        if( result == JFileChooser.APPROVE_OPTION) {
        	File file = fileChooser.getSelectedFile();
        	return file;
        }
        return null;
    }
    
    public static void moverImagem(File image) {
    	if(Files.notExists(image.toPath())) {
			JOptionPane.showMessageDialog(null, "Falha ao copiar, o arquivo de imagem não existe");
			return;
    	}
    	try {
    		if(image.getName().toLowerCase().endsWith(".jpg")) {
        		convertFormat(image.getAbsolutePath());
        	} else {
        		Files.copy(image.toPath(), imagem.toPath(), StandardCopyOption.REPLACE_EXISTING);
        	}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Falha ao copiar o arquivo: "+ image.getAbsolutePath()+"\nTente novamente");
		}
    }
    
    private static void convertFormat(String inputImagePath) throws IOException {
    	FileInputStream inputStream = new FileInputStream(inputImagePath);
		FileOutputStream outputStream = null;
		try{
			outputStream = new FileOutputStream(imagem);
			BufferedImage inputImage = ImageIO.read(inputStream);
			BufferedImage newBufferedImage = null;
			if(inputImagePath.toLowerCase().endsWith(".png")) {
				newBufferedImage = new BufferedImage(inputImage.getWidth(),
						inputImage.getHeight(), BufferedImage.TYPE_INT_RGB);
				newBufferedImage.createGraphics().drawImage(inputImage, 0, 0, Color.WHITE, null);
			} else {
				newBufferedImage = inputImage;
			}
			ImageIO.write(newBufferedImage, "jpg", outputStream);
		} finally {
			inputStream.close();
			outputStream.close();
		}
	}
    
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
            //JOptionPane.showMessageDialog(null, "Falha ao gerar backup : "+ex.getMessage());
        	log.error("Falha ao gerar backup : "+ex.getMessage());
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
