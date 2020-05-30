package br.com.infoq.view;

import br.com.infoq.service.SwingOptions;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_os = new javax.swing.JButton();
        btn_sair = new javax.swing.JButton();
        btn_usuarios = new javax.swing.JButton();
        btn_clientes = new javax.swing.JButton();
        btn_empresas = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/imagem de fundo.jpeg"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 930, 750);

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
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
