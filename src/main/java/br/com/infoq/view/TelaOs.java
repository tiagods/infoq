package br.com.infoq.view;

import br.com.infoq.exception.ClienteNotFoundException;
import br.com.infoq.exception.IdIncorretoException;
import br.com.infoq.exception.OsNotFoundException;
import br.com.infoq.model.Cliente;
import br.com.infoq.model.Os;
import br.com.infoq.service.ClienteService;
import br.com.infoq.service.OsService;
import br.com.infoq.service.SwingOptions;
import br.com.infoq.utils.DateUtil;
import br.com.infoq.utils.Relatorio;
import br.com.infoq.utils.SwingUtils;
import br.com.infoq.utils.Validator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author hugov
 */
@Component
public class TelaOs extends javax.swing.JInternalFrame {

    private static final long serialVersionUID = 1L;
    @Autowired private Relatorio rel;
    @Autowired private OsService osService;
    @Autowired private ClienteService clientes;
    @Autowired private SwingOptions swing;
    
    private String Tipo;

    public TelaOs() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
    }

    private Os osBuilder(Optional<Long> result) {
        return new Os(
                result.isPresent() ? result.get() : -1L,
                Calendar.getInstance(),
                Tipo,
                txtAparelho.getText().toUpperCase().trim(),
                txtDefeito.getText().toUpperCase().trim(),
                txtServico.getText().toUpperCase().trim(),
                new BigDecimal(txtValor.getText().replace(",", ".")),
                new BigDecimal(txtEntrada.getText().replace(",", ".")),
                new Cliente(Long.parseLong(txtClienteCodigo.getText().trim())),
                txtObs.getText().toUpperCase(),
                txtTecnico.getText().toUpperCase().trim(),
                cbSituacao.getSelectedItem().toString(),
                txtGarantia.getText(),
                BigDecimal.ZERO
        );
    }

    private boolean validar() {
        if (txtClienteCodigo.getText().isEmpty() || txtAparelho.getText().isEmpty() || txtDefeito.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return false;
        }
        if (!Validator.validarNumero(txtClienteCodigo.getText())) {
            JOptionPane.showMessageDialog(null, "Campo Id Cliente deve ser numerico!");
            return false;
        }

        try {
            clientes.buscarPorId(Long.parseLong(txtClienteCodigo.getText()));
        } catch (ClienteNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Campo Id Cliente - Nao existe cliente com o id informado!");
            return false;
        }
        if (txtValor.getText().trim().equals("")) {
            txtValor.setText("0");
        }
        if (!Validator.validarNumero(txtValor.getText().replace(",", ""))) {
            JOptionPane.showMessageDialog(null, "Campo valor deve ser do tipo 0,00!");
            return false;
        }
        if (txtEntrada.getText().trim().equals("")) {
            txtEntrada.setText("0");
        }
        if (!Validator.validarNumero(txtEntrada.getText().replace(",", ""))) {
            JOptionPane.showMessageDialog(null, "Campo entrada deve ser do tipo 0,00!");
            return false;
        }
        return true;
    }

    private Optional<Long> validarId(String value) throws IdIncorretoException {
        if(value.trim().length()==0) return Optional.empty();
        try {
            Long id = Long.parseLong(value.trim());
            return Optional.of(id);
        } catch (Exception e) {
            throw new IdIncorretoException("Campo id incorreto");
        }
    }

    private void limparCampos() {
        SwingUtils.limparCampos(getContentPane());
        txtValor.setText("0");
        txtEntrada.setText("0");
        txtTotal.setText("R$ 0,00");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel3 = new javax.swing.JLabel();
        cbSituacao = new javax.swing.JComboBox<String>();
        jPanel2 = new javax.swing.JPanel();
        txtClienteNome = new javax.swing.JTextField();
        txtClienteCodigo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtAparelho = new javax.swing.JTextField();
        txtTecnico = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDefeito = new javax.swing.JTextField();
        txtGarantia = new javax.swing.JTextField();
        txtServico = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextPane();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtValor = new javax.swing.JFormattedTextField();
        txtEntrada = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txtCodOs = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtData = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rbos = new javax.swing.JRadioButton();
        rbgarantia = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        txPesquisarOs = new javax.swing.JTextField();
        lblPesquisarOs = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbOs = new javax.swing.JTable();
        cbBuscarTipo = new javax.swing.JComboBox<String>();
        jLabel16 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("ORÇAMENTO"); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setText("SITUAÇÃO:");

        cbSituacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NA BANCADA", "ENTREGA FEIRA", "ORÇAMENTO REPROVADO", "AGUARDANDO APROVAÇÃO", "AGUARDANDO PEÇA", "ABANDOANDO PELO CLIENTE", "RETORNOU", "ESTÁ PRONTO, AVISAR CLIENTE", "SEM CONSERTO", " ", " " }));
        cbSituacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSituacaoActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Clientes"));
        jPanel2.setToolTipText("");
        jPanel2.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jPanel2.setName(""); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 176));

        txtClienteNome.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtClienteNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteNomeKeyReleased(evt);
            }
        });

        txtClienteCodigo.setEditable(false);
        txtClienteCodigo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtClienteCodigo.setEnabled(false);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/busca 25x25.png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setText("CÓDIGO");

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "TELEFONE", "CELULAR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);
        if (tblClientes.getColumnModel().getColumnCount() > 0) {
            tblClientes.getColumnModel().getColumn(0).setMinWidth(2);
            tblClientes.getColumnModel().getColumn(0).setPreferredWidth(2);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(4, 4, 4)
                        .addComponent(txtClienteCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtClienteCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addComponent(txtClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("APARELHO:");

        txtAparelho.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtTecnico.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTecnicoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("DEFEITO:");

        txtDefeito.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtServico.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("SERVIÇO:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("TÉCNICO:");

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inserir.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setToolTipText("Novo");
        btnNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salvar.png"))); // NOI18N
        btnSalvar.setText("SALVAR");
        btnSalvar.setToolTipText("Salvar");
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/deletar.png"))); // NOI18N
        btnDeletar.setText("EXCLUIR");
        btnDeletar.setToolTipText("Remover");
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("ENTRADA: R$");

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/print.png"))); // NOI18N
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.setToolTipText("Imprimir");
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("OBSERVAÇÃO:");

        jScrollPane2.setViewportView(txtObs);

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel12.setText("GARANTIA:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("VALOR: R$");

        txtValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,#00.00#"))));
        txtValor.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        txtValor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtValorFocusLost(evt);
            }
        });
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });

        txtEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,#00.00#"))));
        txtEntrada.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        txtEntrada.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEntradaFocusLost(evt);
            }
        });
        txtEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEntradaActionPerformed(evt);
            }
        });

        jLabel14.setText("TOTAL A PAGAR R$:");

        txtTotal.setEditable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/btn_sair_25_25.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtCodOs.setEditable(false);
        txtCodOs.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtCodOs.setEnabled(false);

        jLabel1.setText("Nº OS:");

        txtData.setEditable(false);
        txtData.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtData.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("DATA");

        buttonGroup1.add(rbos);
        rbos.setText("ORDEM DE SERVIÇO");
        rbos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbosActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbgarantia);
        rbgarantia.setText("GARANTIA");
        rbgarantia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbgarantiaActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisar OS"));
        jPanel3.setToolTipText("");
        jPanel3.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jPanel3.setName(""); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 176));

        txPesquisarOs.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txPesquisarOs.setToolTipText("");
        txPesquisarOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txPesquisarOsActionPerformed(evt);
            }
        });
        txPesquisarOs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txPesquisarOsKeyReleased(evt);
            }
        });

        lblPesquisarOs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/busca 25x25.png"))); // NOI18N
        lblPesquisarOs.setToolTipText("Buscar OS");
        lblPesquisarOs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblPesquisarOs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPesquisarOsMouseClicked(evt);
            }
        });

        tbOs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "APARELHO", "SITUACAO", "CLIENTE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbOs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbOsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbOs);
        if (tbOs.getColumnModel().getColumnCount() > 0) {
            tbOs.getColumnModel().getColumn(0).setMinWidth(2);
            tbOs.getColumnModel().getColumn(0).setPreferredWidth(2);
        }

        cbBuscarTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CODIGO OS", "NOME CLIENTE", "APARELHO" }));
        cbBuscarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBuscarTipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txPesquisarOs, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbBuscarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(lblPesquisarOs, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPesquisarOs)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txPesquisarOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbBuscarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel16.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel16.setText("TIPO:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel11))
                                .addComponent(jLabel6))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDefeito)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                            .addComponent(txtAparelho)
                            .addComponent(txtServico)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCodOs, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rbgarantia)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rbos))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtGarantia)
                                        .addComponent(txtValor)
                                        .addComponent(txtEntrada)
                                        .addComponent(txtTotal)
                                        .addComponent(txtTecnico)
                                        .addComponent(txtData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnSalvar)
                        .addGap(36, 36, 36)
                        .addComponent(btnDeletar)
                        .addGap(38, 38, 38)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtCodOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(rbos)
                            .addComponent(rbgarantia)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAparelho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(txtServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2)
                                .addGap(18, 18, 18))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeletar)
                    .addComponent(btnImprimir)
                    .addComponent(btnSalvar)
                    .addComponent(btnNovo))
                .addGap(27, 27, 27))
        );

        jPanel2.getAccessibleContext().setAccessibleName("CLIENTES");
        jPanel3.getAccessibleContext().setAccessibleName("Pesquisar Os");

        setBounds(0, 0, 939, 685);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        excluir();
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            Optional<Long> result = validarId(txtCodOs.getText());
            if(result.isPresent()){
                try {
                    Optional<Os> os = osService.buscarPorId(result.get());
                    imprimir(os.get());
                } catch (OsNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        } catch (IdIncorretoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        pesquisarCliente();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void txtClienteNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteNomeKeyReleased
        pesquisarCliente();
    }//GEN-LAST:event_txtClienteNomeKeyReleased

    private void rbgarantiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbgarantiaActionPerformed
        Tipo = "Garantia";
    }//GEN-LAST:event_rbgarantiaActionPerformed

    private void rbosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbosActionPerformed
        Tipo = "OS";
    }//GEN-LAST:event_rbosActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        pesquisarCliente();
        rbos.setSelected(true);
        Tipo = "OS";
    }//GEN-LAST:event_formInternalFrameOpened

    private void cbSituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSituacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSituacaoActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        setarCamposCliente();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void txtEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEntradaActionPerformed

    private void txtEntradaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEntradaFocusLost


    }//GEN-LAST:event_txtEntradaFocusLost

    private void txtTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTecnicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTecnicoActionPerformed

    private void txtValorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorFocusLost

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        swing.abrirTelaMenu();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txPesquisarOsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPesquisarOsKeyReleased
        pesquisarOs();
    }//GEN-LAST:event_txPesquisarOsKeyReleased

    private void lblPesquisarOsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPesquisarOsMouseClicked
        pesquisarOs();
    }//GEN-LAST:event_lblPesquisarOsMouseClicked

    private void tbOsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbOsMouseClicked
        setarCamposOs();
    }//GEN-LAST:event_tbOsMouseClicked

    private void txPesquisarOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txPesquisarOsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txPesquisarOsActionPerformed

    private void cbBuscarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBuscarTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbBuscarTipoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbBuscarTipo;
    private javax.swing.JComboBox<String> cbSituacao;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblPesquisarOs;
    private javax.swing.JRadioButton rbgarantia;
    private javax.swing.JRadioButton rbos;
    private javax.swing.JTable tbOs;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txPesquisarOs;
    private javax.swing.JTextField txtAparelho;
    private javax.swing.JTextField txtClienteCodigo;
    private javax.swing.JTextField txtClienteNome;
    private javax.swing.JTextField txtCodOs;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDefeito;
    private javax.swing.JFormattedTextField txtEntrada;
    private javax.swing.JTextField txtGarantia;
    private javax.swing.JTextPane txtObs;
    private javax.swing.JTextField txtServico;
    private javax.swing.JTextField txtTecnico;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JFormattedTextField txtValor;
    // End of variables declaration//GEN-END:variables

    public void pesquisarCliente() {
        SwingUtils.limparTabela(tblClientes);
        List<Cliente> lista = txtClienteNome.getText().trim().equals("") ? clientes.listarTop100() : clientes.buscarClientePorNome(txtClienteNome.getText());
        DefaultTableModel dm = (DefaultTableModel)tblClientes.getModel();
        lista.forEach(c->dm.addRow(new Object[]{c.getId(), c.getNome(), c.getEndereco(), c.getFone(), c.getEmail()}));
    }
    
    public void pesquisarOs(){
        SwingUtils.limparTabela(tbOs);
        List<Os> lista = new ArrayList<>();
        if(txPesquisarOs.getText().trim().length()>0){
            switch(cbBuscarTipo.getSelectedItem().toString()){
                case "APARELHO":
                    lista.addAll(osService.buscarPorAparelho(txPesquisarOs.getText()));
                    break;
                case "CODIGO OS":
                    try {
                        Optional<Long> value = validarId(txPesquisarOs.getText().trim());
                        if(value.isPresent()){
                            lista.addAll(osService.buscarPorIdComecaPor(value.get()));
                        }
                    } catch (IdIncorretoException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                    break;
                case "NOME CLIENTE":
                    lista.addAll(osService.buscarPorClienteNome(txPesquisarOs.getText()));
                    break;
                default:
                    lista.addAll(osService.listarTop100Os());
                    break;
            }
        }
        else {
            lista.addAll(osService.listarTop100Os());
        }
        DefaultTableModel dm = (DefaultTableModel)tbOs.getModel();
        lista.forEach(c->dm.addRow(new Object[]{c.getId(), c.getAparelho(), c.getSituacao(), c.getCliente().getNome()}));
    }

    
    private void setarCamposCliente() {
        int setar = tblClientes.getSelectedRow();
        txtClienteCodigo.setText(setar == -1? "": tblClientes.getModel().getValueAt(setar, 0).toString());
    }
    
    private void setarCamposOs() {
        int setar = tbOs.getSelectedRow();
        if(setar==-1) return;
        try {
            Optional<Long> result = validarId(tbOs.getModel().getValueAt(setar, 0).toString());
            if (!result.isPresent()) { return; }
            Optional<Os> opt = osService.buscarPorId(result.get());
            Os os = opt.get();
            preencherCampos(os);
        } catch (OsNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (IdIncorretoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    private void preencherCampos(Os os){
        txtCodOs.setText("" + os.getId());
            txtData.setText(DateUtil.formatarData(os.getData().getTime()));
            String rbTipo = os.getTipo();
            if (rbTipo.equals("OS")) {
                rbos.setSelected(true);
                Tipo = "OS";

            } else {
                rbgarantia.setSelected(true);
                Tipo = "Garantia";
            }
            BigDecimal valor = os.getValor();
            BigDecimal entrada = os.getEntrada();
            cbSituacao.setSelectedItem(os.getSituacao());
            txtAparelho.setText(os.getAparelho());
            txtDefeito.setText(os.getDefeito());
            txtServico.setText(os.getServico());
            txtTecnico.setText(os.getTecnico());
            txtValor.setText(valor.toString().replace(".", ","));
            txtEntrada.setText(entrada.toString().replace(".", ","));
            txtClienteCodigo.setText(os.getCliente().getId() + "");
            txtObs.setText(os.getObs());
            txtGarantia.setText(os.getGarantia());
            BigDecimal total = valor.subtract(entrada).setScale(2, RoundingMode.UP);
            txtTotal.setText("RS " + total.toString().replace(".", ","));
    }

    private void emitirOs() {
        if (!validar()) {
            return;
        }
        Os os = osService.adicionar(osBuilder(Optional.empty()));
        JOptionPane.showMessageDialog(null, "OS Salva com Sucesso!!");
        txtCodOs.setText(os.getId() + "");
        imprimir(os);
        limparCampos();
        pesquisarCliente();
    }
    
    private void salvar() {
        if (!validar()) {
            return;
        }
        try {
            Optional<Long> result = validarId(txtCodOs.getText().trim());
            Os os = null; 
            if(result.isPresent()) {
                os = osService.alterar(osBuilder(result), result.get());
            }   
            else {
                os = osService.adicionar(osBuilder(Optional.empty()));
            }
            JOptionPane.showMessageDialog(null, "OS Salva com Sucesso!!");
            pesquisarOs();
            preencherCampos(os);
            if(!result.isPresent()) imprimir(os);
        } catch (IdIncorretoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (OsNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void excluir() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                Optional<Long> result = validarId(txtCodOs.getText());
                if (!result.isPresent()) { return; }
                osService.deletar(result.get());
                JOptionPane.showMessageDialog(null, "Registro Apagado com Sucesso!");
                limparCampos();
            } catch (OsNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (IdIncorretoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            pesquisarOs();
        }
    }

    @SuppressWarnings("unchecked")
    private void imprimir(Os os) {
        if (txtCodOs.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pesquise uma OS para Impressao!!");
            return;
        }
        rel.imprimirOs(rel.pegarResultadoImpressao(), os, Relatorio.Relatorios.OS);
    }
}
