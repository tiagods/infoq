/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.view;

import br.com.infoq.exception.UsuarioInvalidCredentialsException;
import br.com.infoq.model.Usuario;
import br.com.infoq.service.UsuarioService;
import static br.com.infoq.view.TelaPrincipal.desktop;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Optional;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Grazi, tiagods
 */
@Component
public class frmLogin extends javax.swing.JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private UsuarioService usuarios;
    @Autowired
    private TelaPrincipal principal;
    @Autowired
    private TelaMenu menu;

    public frmLogin() {
        initComponents();
        ImageIcon icone = new ImageIcon(getClass().getResource("/icons/user.png"));
        setIconImage(icone.getImage());
        String imagem = "dbok.png";
        lblstatus.setIcon(new ImageIcon(getClass().getResource("/icons/" + imagem)));

    }

    private void initComponents() {

        txt_usuario = new javax.swing.JTextField();
        txt_senha = new javax.swing.JPasswordField();
        btn_login = new javax.swing.JButton();
        lblstatus = new javax.swing.JLabel();
        lb_fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        txt_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_usuarioKeyPressed(evt);
            }
        });
        getContentPane().add(txt_usuario);
        txt_usuario.setBounds(185, 113, 330, 40);

        txt_senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_senhaActionPerformed(evt);
            }
        });
        getContentPane().add(txt_senha);
        txt_senha.setBounds(185, 180, 330, 40);

        btn_login.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cadeado icone 40x40.png"))); // NOI18N
        btn_login.setText("Login");
        btn_login.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        getContentPane().add(btn_login);
        btn_login.setBounds(303, 233, 210, 50);

        lblstatus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblstatus.setForeground(new java.awt.Color(248, 248, 248));
        lblstatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dberro.png"))); // NOI18N
        getContentPane().add(lblstatus);
        lblstatus.setBounds(10, 20, 50, 40);

        lb_fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/login.jpg"))); // NOI18N
        lb_fundo.setMinimumSize(new java.awt.Dimension(450, 230));
        lb_fundo.setPreferredSize(new java.awt.Dimension(450, 230));
        getContentPane().add(lb_fundo);
        lb_fundo.setBounds(0, 0, 530, 310);

        setSize(new java.awt.Dimension(535, 338));
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        logar();
    }//GEN-LAST:event_btn_loginActionPerformed

    private void txt_senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_senhaActionPerformed
        logar();
    }//GEN-LAST:event_txt_senhaActionPerformed

    private void txt_usuarioKeyPressed(java.awt.event.KeyEvent evt) {
        int key = evt.getKeyCode();
        Arrays.asList(KeyEvent.VK_ENTER, KeyEvent.VK_TAB)
                .stream()
                .filter(c -> c == key)
                .findFirst()
                .ifPresent(c -> txt_senha.requestFocus());;
    }

    private javax.swing.JButton btn_login;
    private javax.swing.JLabel lb_fundo;
    private javax.swing.JLabel lblstatus;
    private javax.swing.JPasswordField txt_senha;
    private javax.swing.JTextField txt_usuario;

    public void logar() {
        try {
            Usuario usuario = usuarios.validarLoginESenha(txt_usuario.getText().trim(), new String(txt_senha.getPassword()));
            Optional<String> optPerfil = Optional.ofNullable(usuario.getPerfil());
            principal.setVisible(true);
            principal.lblUsuario.setText(usuario.getUsuario());
            desktop.add(menu);
            menu.setVisible(true);
            if (optPerfil.isPresent() && optPerfil.get().toUpperCase().equals("ADMIN")) {
                principal.menuCadUsu.setEnabled(true);
                menu.btn_usuarios.setEnabled(true);
                principal.lblUsuario.setForeground(Color.blue);
            }
            this.dispose();
        } catch (UsuarioInvalidCredentialsException e) {
            txt_senha.setText("");
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
