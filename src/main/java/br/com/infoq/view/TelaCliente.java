package br.com.infoq.view;

import br.com.infoq.exception.ClienteNotFoundException;
import br.com.infoq.exception.IdIncorretoException;
import br.com.infoq.model.Cliente;
import br.com.infoq.service.ClienteService;
import br.com.infoq.service.SwingOptions;
import br.com.infoq.utils.SwingUtils;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 *
 * @author Grazi,tiagods
 */
@Component
public class TelaCliente extends javax.swing.JInternalFrame {
    
    private static final long serialVersionUID = 1L;
    @Autowired private ClienteService clientes;
    @Autowired private SwingOptions swing;
    
    private MaskFormatter formatoCpf, formatoCnpj, formatoCelular, formatoTelefone;
    
    private TelaCliente() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
    }

    private Optional<Long> validarId() throws IdIncorretoException {
        if(txtId.getText().trim().isEmpty()) return Optional.empty();
        try {
            Long id = Long.parseLong(txtId.getText().trim());
            return Optional.of(id);
        } catch (Exception e) {
            throw new IdIncorretoException("Campo id incorreto");
        }
    }

    private boolean validar() {
        if ((txtNome.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Preencha os Dados Corretamenre!\n Campo Nome é obrigatório");
            return false;
        }
        return true;
    }

    private Cliente clienteBuilder(Optional<Long> result) {
        return new Cliente(
                result.isPresent() ? result.get() : -1L,
                txtNome.getText().toUpperCase(),
                txtEnd.getText().toUpperCase(),
                txtNum.getText(),
                txtCom.getText().toUpperCase(),
                txtEmail.getText().toUpperCase(),
                txtCpf.getText(),
                txtCnpj.getText(),
                txtRg.getText(),
                txtTel.getText(),
                txtCel.getText(),
                txtBairro.getText().toUpperCase()
        );
    }

    private void setar_campos() {
        int setar = tblClientes.getSelectedRow();
        
        txtId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
        Optional<Long> id = Optional.empty();
        try {
            id = validarId();
        } catch (IdIncorretoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
        if(id.isPresent()){
            try {
                clientes.buscarPorId(id.get()).ifPresent(c->{
                    txtNome.setText(c.getNome());
                    txtEnd.setText(c.getEndereco());
                    txtNum.setText(c.getNum());
                    txtCom.setText(c.getComp());
                    txtEmail.setText(c.getEmail());
                    txtCpf.setText(onlyNumber(c.getCpf()));
                    txtCnpj.setText(onlyNumber(c.getCnpj()));
                    txtRg.setText(c.getRg());
                    txtTel.setText(onlyNumber(c.getFone()));
                    txtCel.setText(onlyNumber(c.getCel()));
                    txtBairro.setText(c.getBairro());
                });
            } catch (ClienteNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            
        }
    }
    private String onlyNumber(String value){
        return value.replace("(", "")
                .replace(")", "")
                .replace("-", "")
                .replace(".", "")
                .replace("/", "").trim();
    }

    private void limparCampos() {
        SwingUtils.limparCampos(getContentPane());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtEnd = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        txtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCom = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNum = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        try {
            formatoTelefone = new MaskFormatter("(##)####-####");
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null ,"Não foi possivel receber o valor do telefone: " +erro);
        }
        txtTel = new JFormattedTextField(formatoTelefone);
        try {
            formatoCelular = new MaskFormatter("(##)#####-####");
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null ,"Não foi possivel receber o valor do celular: " +erro);
        }
        txtCel = new JFormattedTextField(formatoCelular);
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtRg = new javax.swing.JFormattedTextField();
        try {
            formatoCnpj= new MaskFormatter("##.###.###/####-##");
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null ,"Não foi possivel receber o valor do cnpj: " +erro);
        }
        txtCnpj = new JFormattedTextField(formatoCnpj);
        try {
            formatoCpf= new MaskFormatter("###.###.###-##");
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null ,"Não foi possivel receber o valor do cpf: " +erro);
        }
        txtCpf = new JFormattedTextField(formatoCpf);
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(948, 688));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("NOME:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("E-MAIL");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("TELEFONE:");

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("ENDEREÇO:");

        btnNovo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inserir.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setToolTipText("Adicionar");
        btnNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnDeletar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/deletar.png"))); // NOI18N
        btnDeletar.setText("EXCLUIR");
        btnDeletar.setToolTipText("Remover");
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        btnSalvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setToolTipText("Alterar");
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clientes.png"))); // NOI18N

        txtEnd.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtEnd.setFocusTraversalPolicyProvider(true);
        txtEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEndActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("BUSCAR");

        txtBuscar.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/buscar pequeno.png"))); // NOI18N
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblClientes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "ENDEREÇO", "TELEFONE", "E-MAIL"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);
        if (tblClientes.getColumnModel().getColumnCount() > 0) {
            tblClientes.getColumnModel().getColumn(0).setMinWidth(0);
            tblClientes.getColumnModel().getColumn(0).setPreferredWidth(0);
        }

        txtId.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("BAIRRO:");

        txtBairro.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("NÚMERO:");

        txtCom.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("CELULAR:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("COMPL:");

        txtNum.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("CPF:");

        txtTel.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        txtTel.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtCel.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        txtCel.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel12.setText("CNPJ:");

        jLabel13.setText("RG:");

        txtRg.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N

        txtCnpj.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        txtCnpj.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N

        txtCpf.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        txtCpf.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/btn_sair_25_25.png"))); // NOI18N
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(33, 33, 33))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(232, 232, 232)
                                .addComponent(jLabel12)
                                .addGap(42, 42, 42)
                                .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(30, 30, 30)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(27, 27, 27)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel13))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCom, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(btnNovo)
                        .addGap(66, 66, 66)
                        .addComponent(btnSalvar)
                        .addGap(64, 64, 64)
                        .addComponent(btnDeletar)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(57, 57, 57)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtCom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnNovo)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDeletar)
                            .addComponent(btnSalvar))))
                .addGap(35, 35, 35))
        );

        setBounds(0, 0, 940, 695);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        remover();
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        pesquisar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // evento a baixa vai preecehnado em quanto vou digitando
        pesquisar();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyReleased

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        pesquisar();
    }//GEN-LAST:event_formComponentShown

    private void txtEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEndActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        swing.abrirTelaMenu();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JFormattedTextField txtCel;
    private javax.swing.JFormattedTextField txtCnpj;
    private javax.swing.JTextField txtCom;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEnd;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNum;
    private javax.swing.JFormattedTextField txtRg;
    private javax.swing.JFormattedTextField txtTel;
    // End of variables declaration//GEN-END:variables

    public void pesquisar() {
        SwingUtils.limparTabela(tblClientes);
        List<Cliente> lista = txtBuscar.getText().trim().equals("")? clientes.listar(Sort.Direction.DESC, "id") : clientes.buscarClientePorNome(txtBuscar.getText());
        DefaultTableModel dm = (DefaultTableModel)tblClientes.getModel();
        lista.forEach(c->dm.addRow(new Object[]{c.getId(),c.getNome(),c.getEndereco(),c.getFone(), c.getEmail()}));
    }

    private void salvar() {
        if (!validar()) {
            return;
        }
        Optional<Long> id = Optional.empty();
        try {
            id = validarId();
        } catch (IdIncorretoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
        try {
            if(id.isPresent())
                clientes.alterar(clienteBuilder(id), id.get());
            else 
                clientes.adicionar(clienteBuilder(Optional.empty()));
            JOptionPane.showMessageDialog(null, "Registro Salvo com Sucesso!");
            limparCampos();
            pesquisar();
        } catch (ClienteNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void remover() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                Optional<Long> result = validarId();
                if (!result.isPresent()) { return; }
                clientes.deletar(result.get());
                JOptionPane.showMessageDialog(null, "Registro Apagado com Sucesso!");
                limparCampos();
            } catch (ClienteNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (IdIncorretoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            pesquisar();
        }
    }

}
