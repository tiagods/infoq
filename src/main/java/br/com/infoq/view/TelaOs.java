/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.view;
import java.sql.*;
import br.com.infoq.fabrica.Conexao;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author hugov
 */
public class TelaOs extends javax.swing.JInternalFrame {
Connection conexao = null;
PreparedStatement pst = null;
ResultSet rs = null;

private String Tipo;
    /**
     * Creates new form TelaOs
     */
    public TelaOs() {
        initComponents();
        conexao = Conexao.getConnection();
    }
    
    
    private void pesquisar_cliente(){
        String sql = "select idcli as ID, nomecli as NOME, fonecli as TELFONE, celcli as CELULAR from tbclientes where nomecli like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliente.getText() + "%");
            rs = pst.executeQuery();
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setar_campos(){
        int setar = tblClientes.getSelectedRow();
        txtCodigo.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
    }
    
    private void emitir_os(){
        String sql = "insert into tbos(Tipo, situacao, aparelho, defeito, servico, tecnico, valor, entrada, obs, idcli, garantia) values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
             pst = conexao.prepareStatement(sql);
             pst.setString(1, Tipo);
             pst.setString(2, cbSituacao.getSelectedItem().toString());
             pst.setString(3, txtAparelho.getText().toUpperCase());
             pst.setString(4, txtDefeito.getText().toUpperCase());
             pst.setString(5, txtServico.getText().toUpperCase());
             pst.setString(6, txtTecnico.getText().toUpperCase());
             pst.setString(7, txtValor.getText().replace(",", "."));
             pst.setString(8, txtEntrada.getText().replace(",", "."));
             pst.setString(9, txtObs.getText().toUpperCase().toUpperCase());
             pst.setString(10, txtCodigo.getText());
             pst.setString(11, txtGarantia.getText().toUpperCase());
             
             if((txtCodigo.getText().isEmpty()) || (txtAparelho.getText().isEmpty()) || (txtDefeito.getText().isEmpty())){
                  JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
             }else{
                 int adicionado = pst.executeUpdate();
                 if(adicionado > 0){
                      JOptionPane.showMessageDialog(null, "OS Salva com Sucesso!!");
                      
                      consultarOS();
                      imprimir();
                      limparCampos();
                      pesquisar_cliente();
                 }
             }
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void consultarOS(){
       
         String sql = "select * from tbos order by os desc";
            try {
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();
                if(rs.next()){
                txtOs.setText(rs.getString(1));
                }else{
                    JOptionPane.showMessageDialog(null, "Nao encontrada");
                }
            }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    private void pesquisar_os(){
        String num_os = JOptionPane.showInputDialog("Numero da OS");
        String sql = "select * from tbos where os = " + num_os;
        try {
             pst = conexao.prepareStatement(sql);
             rs = pst.executeQuery();
             if(rs.next()){
                 txtOs.setText(rs.getString(1));
                 txtData.setText(rs.getString(2));
                 String rbTipo = rs.getString(3);
                 if(rbTipo.equals("OS")){
                     rbos.setSelected(true);
                     Tipo="OS";
                     
                 }else{
                     rbgarantia.setSelected(true);
                     Tipo = "Garantia";
                 }
                 cbSituacao.setSelectedItem(rs.getString(11));
                 txtAparelho.setText(rs.getString(4));
                 txtDefeito.setText(rs.getString(5));
                 txtServico.setText(rs.getString(6));
                 txtTecnico.setText(rs.getString(10));
                 txtValor.setText(rs.getString(7));
                 txtEntrada.setText(rs.getString(8));
                 txtCodigo.setText(rs.getString(9));
                 txtObs.setText(rs.getString(10));
                 
                 btnInserir.setEnabled(false);
                 txtCliente.setEnabled(false);
                 tblClientes.setVisible(false);
                 
                 
             }else{
                JOptionPane.showMessageDialog(null, "OS não cadastrada!");
             }
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
             JOptionPane.showMessageDialog(null, "OS Inválida, digite apenas números!");
             //System.out.println(e);
        } catch(Exception e2){
             JOptionPane.showMessageDialog(null, e2);
        }
    }
    
    
    private void alterar(){
        String sql = "update tbos set tipo=?, situacao=?, aparelho=?, defeito=?, servico=?, tecnico=?, valor=?, entrada=?, obs=? where os=?";
        
        try {
             pst = conexao.prepareStatement(sql);
             pst.setString(1, Tipo);
             pst.setString(2, cbSituacao.getSelectedItem().toString());
             pst.setString(3, txtAparelho.getText().toUpperCase().toUpperCase());
             pst.setString(4, txtDefeito.getText().toUpperCase().toUpperCase());
             pst.setString(5, txtServico.getText().toUpperCase().toUpperCase());
             pst.setString(6, txtTecnico.getText().toLowerCase());
             pst.setString(7, txtValor.getText().replace(",", "."));
             pst.setString(8, txtEntrada.getText().replace(",", "."));
             pst.setString(9, txtObs.getText().toUpperCase().toUpperCase());
            pst.setString(10, txtOs.getText().toUpperCase());

            btnInserir.setEnabled(true);
            txtCliente.setEnabled(true);
            tblClientes.setVisible(true);
             
             if((txtCodigo.getText().isEmpty()) || (txtAparelho.getText().isEmpty()) || (txtDefeito.getText().isEmpty())){
                  JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
             }else{
                 int adicionado = pst.executeUpdate();
                 if(adicionado > 0){
                      JOptionPane.showMessageDialog(null, "OS Editada com Sucesso!!");
                      imprimir();
                      limparCampos();
                      pesquisar_cliente();
                 }
             }
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    private void excluir(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja realmente excluir o registro?", "AtenÃ§Ã£o", JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
            String sql = "delete from tbos where os=?";
            try {
                 pst = conexao.prepareStatement(sql);
                pst.setString(1, txtOs.getText());

                btnInserir.setEnabled(true);
                txtCliente.setEnabled(true);
                tblClientes.setVisible(true);
                 
                 int apagado = pst.executeUpdate();
                 if (apagado > 0){
                     JOptionPane.showMessageDialog(null, "Registro deletado com sucesso!!");
                     limparCampos();
                 }
                 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    
    private void limparCampos(){
        txtCodigo.setText(null);
        txtAparelho.setText(null);
        txtDefeito.setText(null);
        txtValor.setText("0");
        txtEntrada.setText("0");
        txtTecnico.setText(null);
        txtServico.setText(null);
        txtCliente.setText(null);
        txtData.setText(null);
        txtObs.setText(null);
       // txtOs.setText(null);
    }
    
    private void imprimir(){
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a visualizaÃ§Ã£o do RelatÃ³rio?", "AtenÃ§Ã£o", JOptionPane.YES_NO_OPTION);
       if (confirma == JOptionPane.YES_OPTION){
           Map p = new HashMap();
           p.put("codigo_os", txtOs.getText());
           JasperReport relatorio;
           JasperPrint impressao;
           try {
               if(!txtOs.getText().equals("")){
               relatorio = JasperCompileManager.compileReport(new File("").getAbsolutePath()+"/rel/os.jrxml");
               impressao = JasperFillManager.fillReport(relatorio, p, conexao);
               JasperViewer view = new JasperViewer(impressao, false); 
               view.setTitle("RelatÃ³rio de Ordem de ServiÃ§os");
               view.setVisible(true);
               }else{
                   JOptionPane.showMessageDialog(null, "Pesquise uma OS para ImpressÃ£o!!"); 
               }
               
           } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
           }
       }
    }
 
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOs = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        rbos = new javax.swing.JRadioButton();
        rbgarantia = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        cbSituacao = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        txtCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtAparelho = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDefeito = new javax.swing.JTextField();
        txtServico = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTecnico = new javax.swing.JTextField();
        btnInserir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextPane();
        jLabel12 = new javax.swing.JLabel();
        txtGarantia = new javax.swing.JTextField();
        txtEntrada = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        txtValor = new javax.swing.JFormattedTextField();

        setClosable(true);
        setTitle("ORÇAMENTO"); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nº OS");

        jLabel2.setText("DATA");

        txtOs.setEditable(false);
        txtOs.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtOs.setEnabled(false);

        txtData.setEditable(false);
        txtData.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtData.setEnabled(false);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtData)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(rbgarantia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbos)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbos)
                    .addComponent(rbgarantia))
                .addContainerGap())
        );

        jLabel3.setText("SITUAÇÃO:");

        cbSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NA BANCADA", "ENTREGA FEIRA", "ORÇAMENTO REPROVADO", "AGUARDANDO APROVAÇÃO", "AGUARDANDO PEÇA", "ABANDOANDO PELO CLIENTE", "RETORNOU", "ESTÁ PRONTO, AVISAR CLIENTE", "SEM CONSERTO", " ", " " }));
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

        txtCliente.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infoq/icones/busca 25x25.png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setText("CÓDIOG");

        txtCodigo.setEditable(false);
        txtCodigo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtCodigo.setEnabled(false);

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "TELEFONE", "CELULAR"
            }
        ));
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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(39, 39, 39)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(144, 144, 144))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("APARELHO:");

        txtAparelho.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("DEFEITO:");

        txtDefeito.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtServico.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("SERVIÇO:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("TÉCNICO:");

        txtTecnico.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTecnico.setText("Moniel Ferreira");
        txtTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTecnicoActionPerformed(evt);
            }
        });

        btnInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inserir.png"))); // NOI18N
        btnInserir.setText("ADICIONAR");
        btnInserir.setToolTipText("Adicionar");
        btnInserir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/editar.png"))); // NOI18N
        btnEditar.setText("EDITAR");
        btnEditar.setToolTipText("Editar");
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
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

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infoq/icones/print.png"))); // NOI18N
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.setToolTipText("Imprimir");
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/buscar.png"))); // NOI18N
        btnPesquisar.setText("PESQUISAR");
        btnPesquisar.setToolTipText("Pesquisar");
        btnPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("OBSERVAÇÃO:");

        jScrollPane2.setViewportView(txtObs);

        jLabel12.setText("GARANTIA:");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbSituacao, 0, 1, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAparelho)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26)
                                                .addComponent(jLabel8)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtServico, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(72, 72, 72)
                                                .addComponent(jLabel10)
                                                .addGap(6, 6, 6)
                                                .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(21, 21, 21)
                                .addComponent(txtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnInserir)
                        .addGap(30, 30, 30)
                        .addComponent(btnEditar)
                        .addGap(26, 26, 26)
                        .addComponent(btnDeletar)
                        .addGap(34, 34, 34)
                        .addComponent(btnPesquisar)
                        .addGap(27, 27, 27)
                        .addComponent(btnImprimir))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAparelho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnInserir)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditar)
                        .addComponent(btnDeletar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPesquisar)
                        .addComponent(btnImprimir)))
                .addGap(27, 27, 27))
        );

        jPanel2.getAccessibleContext().setAccessibleName("CLIENTES");

        setBounds(0, 0, 939, 685);
    }// </editor-fold>//GEN-END:initComponents

    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirActionPerformed
        emitir_os();
    }//GEN-LAST:event_btnInserirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
         alterar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        excluir()    ;    
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
         imprimir();
         limparCampos();
         btnInserir.setEnabled(true);
         txtCliente.setEnabled(true);
         tblClientes.setVisible(true);
         pesquisar_cliente();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
       pesquisar_os();
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        pesquisar_cliente();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        pesquisar_cliente();
    }//GEN-LAST:event_txtClienteKeyReleased

    private void rbgarantiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbgarantiaActionPerformed
        Tipo = "Garantia";
    }//GEN-LAST:event_rbgarantiaActionPerformed

    private void rbosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbosActionPerformed
         Tipo = "OS";
    }//GEN-LAST:event_rbosActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        pesquisar_cliente();
        rbos.setSelected(true);
        Tipo = "OS";
    }//GEN-LAST:event_formInternalFrameOpened

    private void cbSituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSituacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSituacaoActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        setar_campos();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnInserir;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbSituacao;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbgarantia;
    private javax.swing.JRadioButton rbos;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtAparelho;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDefeito;
    private javax.swing.JFormattedTextField txtEntrada;
    private javax.swing.JTextField txtGarantia;
    private javax.swing.JTextPane txtObs;
    private javax.swing.JTextField txtOs;
    private javax.swing.JTextField txtServico;
    private javax.swing.JTextField txtTecnico;
    private javax.swing.JFormattedTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
