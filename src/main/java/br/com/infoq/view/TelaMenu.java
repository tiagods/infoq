package br.com.infoq.view;

import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.infoq.service.SwingOptions;

/**
 *
 * @author hugov,tiagods
 */
@Component
public class TelaMenu extends javax.swing.JInternalFrame {

    private static final long serialVersionUID = 1L;
    @Autowired private SwingOptions swing;

    public TelaMenu() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        carregarLogoOficial();
    }
    
    public void carregarLogoOficial() {
        File logoFile = new File(System.getProperty("user.dir")+"\\images\\logo.jpg");
        if(Files.exists(logoFile.toPath())) {
        	BufferedImage img = null;
        	ImageIcon image = null;
        	try {
				img = ImageIO.read(logoFile);
				if(img.getWidth() > lbLogo.getWidth() || img.getHeight() > lbLogo.getHeight()) {
					//Pair<Integer, Integer> value = FileUtils.calcularRedimensionamento((double)lbLogo.getWidth()-20, (double)lbLogo.getHeight(), (double)img.getWidth(), (double)img.getHeight());
		        	//Image dimg = img.getScaledInstance(value.getFirst(), value.getSecond(), Image.SCALE_SMOOTH);
					Image dimg = img.getScaledInstance(lbLogo.getWidth(), lbLogo.getHeight(), Image.SCALE_SMOOTH);
			        image = new ImageIcon(dimg);
				}
				else {
					image = new ImageIcon(img);
				}
		        image.getImage().flush();
		        lbLogo.setIcon(image); 
		        lbLogo.setText("");
			} catch (IOException e) {
		    	lbLogo.setText("Não foi vincular uma logo no sistema\nAcesse a guia Perfil e inclua uma imagem");
			}
	    } else {
        	lbLogo.setText("Não foi encontrado uma logo para o sistema\nAcesse a guia Perfil e inclua uma imagem");
        }
    }

    private void initComponents() {

        btn_os = new javax.swing.JButton();
        btn_os.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btn_sair = new javax.swing.JButton();
        btn_sair.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btn_usuarios = new javax.swing.JButton();
        btn_usuarios.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btn_clientes = new javax.swing.JButton();
        btn_clientes.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btn_empresas = new javax.swing.JButton();
        btn_empresas.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lbLogo = new javax.swing.JLabel();
        lbLogo.setHorizontalAlignment(SwingConstants.CENTER);

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Menu do Sistema");
        setPreferredSize(new java.awt.Dimension(976, 695));
        getContentPane().setLayout(null);

        btn_os.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/btn_os_2.png"))); // NOI18N
        btn_os.setText("ORDEM DE SERVIÇO");
        btn_os.setToolTipText("OS");
        btn_os.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_os.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_osActionPerformed(evt);
            }
        });
        getContentPane().add(btn_os);
        btn_os.setBounds(10, 20, 220, 78);

        btn_sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/btn_sair.png"))); // NOI18N
        btn_sair.setText("SAIR");
        btn_sair.setToolTipText("Sair");
        btn_sair.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sairActionPerformed(evt);
            }
        });
        getContentPane().add(btn_sair);
        btn_sair.setBounds(780, 20, 130, 78);

        btn_usuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/btn_users.png"))); // NOI18N
        btn_usuarios.setText("USUÁRIOS");
        btn_usuarios.setToolTipText("Usuário");
        btn_usuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_usuarios.setEnabled(false);
        btn_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuariosActionPerformed(evt);
            }
        });
        getContentPane().add(btn_usuarios);
        btn_usuarios.setBounds(410, 20, 160, 78);

        btn_clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/btn_clientes.png"))); // NOI18N
        btn_clientes.setText("CLIENTES");
        btn_clientes.setToolTipText("Cliente");
        btn_clientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clientesActionPerformed(evt);
            }
        });
        getContentPane().add(btn_clientes);
        btn_clientes.setBounds(240, 20, 160, 78);

        btn_empresas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/btn_empresas.png"))); // NOI18N
        btn_empresas.setText("PERFIL");
        btn_empresas.setToolTipText("Perfil");
        btn_empresas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_empresas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_empresas);
        btn_empresas.setBounds(590, 20, 170, 78);
        getContentPane().add(lbLogo);
        lbLogo.setBounds(0, 0, 925, 655);

        setBounds(0, 0, 940, 685);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_osActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_osActionPerformed
        swing.abrirTelaOs();
    }//GEN-LAST:event_btn_osActionPerformed

    private void btn_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sairActionPerformed
        int sair = JOptionPane.showConfirmDialog(null, "Deseja Sair?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btn_sairActionPerformed

    private void btn_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuariosActionPerformed
        swing.abrirTelaUsuario();
    }//GEN-LAST:event_btn_usuariosActionPerformed

    private void btn_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clientesActionPerformed
        swing.abrirTelaCliente();
    }//GEN-LAST:event_btn_clientesActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        swing.abrirTelaEmpresa();
    }//GEN-LAST:event_btn_buscarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_empresas;
    private javax.swing.JButton btn_clientes;
    private javax.swing.JButton btn_os;
    private javax.swing.JButton btn_sair;
    public static javax.swing.JButton btn_usuarios;
    private static javax.swing.JLabel lbLogo;
    // End of variables declaration//GEN-END:variables
}
