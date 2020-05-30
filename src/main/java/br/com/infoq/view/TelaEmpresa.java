package br.com.infoq.view;


import br.com.infoq.exception.EmpresaNotFoundException;
import br.com.infoq.exception.IdIncorretoException;
import br.com.infoq.model.Empresa;
import br.com.infoq.service.EmpresaService;
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
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JFormattedTextField$AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author tiagods
 */
@Component
public class TelaEmpresa extends javax.swing.JInternalFrame {
    
	private static final long serialVersionUID = 1L;
    @Autowired private EmpresaService empresas;
    
    private MaskFormatter formatoCpf;
    MaskFormatter formatoCnpj;
    MaskFormatter formatoCelular;
    MaskFormatter formatoTelefone;
    
    private TelaEmpresa() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
    }
    
	private void helpOs() {
	    JOptionPane.showMessageDialog(null, "A informação dessa area aparecerá durante a emissão da via do cliente\"Solicitaçao de Serviços do cliente\"!\n"
	    		+ "A parametrização aqui é unica para todas as notas.\n\n"
	    		+ "Exemplo: Atenção, a não retirada do aparelho no prazo de XX dias após o aviso do produto pronto, \n"
	    		+ "será cobrado um aluguel de R$x,xx (xx reais) ao dia pela guarda e seguro do aparelho.");
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

    private Empresa emrpesaBuilder(Optional<Long> result) {
        return new Empresa(
                result.isPresent() ? result.get() : -1L,
                txtNome.getText().toUpperCase(),
                txtEnd.getText().toUpperCase(),
                txtNum.getText(),
                txtCom.getText().toUpperCase(),
                txtEmail.getText().toUpperCase(),
                txtCpf.getText(),
                txtCnpj.getText(),
                txtCidade.getText(),
                txtTel.getText(),
                txtCel.getText(),
                txtBairro.getText().toUpperCase()
        );
    }

    private void setar_campos() {
    	try {
            clientes.buscarPorId(1L).ifPresent(c->{
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
        } catch (EmpresaNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
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
        jLabel2.setBounds(10, 102, 42, 17);
        jLabel3 = new javax.swing.JLabel();
        jLabel3.setBounds(10, 309, 70, 17);
        txtNome = new javax.swing.JTextField();
        txtNome.setBounds(109, 98, 444, 25);
        txtEmail = new javax.swing.JTextField();
        txtEmail.setBounds(109, 305, 269, 25);
        jLabel6 = new javax.swing.JLabel();
        jLabel6.setBounds(10, 204, 76, 17);
        txtEnd = new javax.swing.JTextField();
        txtEnd.setBounds(109, 200, 444, 25);
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setBounds(308, 240, 53, 17);
        try {
            formatoTelefone = new MaskFormatter("(##)####-####");
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null ,"Não foi possivel receber o valor do telefone: " +erro);
        }
        try {
            formatoCelular = new MaskFormatter("(##)#####-####");
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null ,"Não foi possivel receber o valor do celular: " +erro);
        }
        try {
            formatoCnpj= new MaskFormatter("##.###.###/####-##");
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null ,"Não foi possivel receber o valor do cnpj: " +erro);
        }
        try {
            formatoCpf= new MaskFormatter("###.###.###-##");
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null ,"Não foi possivel receber o valor do cpf: " +erro);
        }

        setClosable(true);
        setTitle("Empresa");
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
        jLabel3.setText("E-MAIL:");

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("ENDEREÇO:");

        txtEnd.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtEnd.setFocusTraversalPolicyProvider(true);
        txtEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEndActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("BAIRRO:");
        getContentPane().setLayout(null);
        getContentPane().add(jLabel2);
        getContentPane().add(txtNome);
        jLabel12 = new javax.swing.JLabel();
        jLabel12.setBounds(10, 138, 38, 19);
        
                jLabel12.setFont(new Font("Tahoma", Font.PLAIN, 14)); // NOI18N
                jLabel12.setText("CNPJ:");
                getContentPane().add(jLabel12);
        txtCnpj = new JFormattedTextField(formatoCnpj);
        txtCnpj.setBounds(109, 134, 175, 26);
        
                txtCnpj.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
                txtCnpj.setFont(new java.awt.Font("Ubuntu", 0, 15));
                getContentPane().add(txtCnpj);
        jLabel4 = new javax.swing.JLabel();
        jLabel4.setBounds(10, 172, 70, 17);
        
                jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                jLabel4.setText("TELEFONE:");
                getContentPane().add(jLabel4);
        txtTel = new JFormattedTextField(formatoTelefone);
        txtTel.setBounds(109, 168, 174, 25);
        
                txtTel.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
                txtTel.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                getContentPane().add(txtTel);
        jLabel9 = new javax.swing.JLabel();
        jLabel9.setBounds(308, 172, 62, 17);
        
                jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                jLabel9.setText("CELULAR:");
                getContentPane().add(jLabel9);
        txtCel = new JFormattedTextField(formatoCelular);
        txtCel.setBounds(386, 168, 167, 25);
        
                txtCel.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
                txtCel.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                getContentPane().add(txtCel);
        
        JLabel lblCep = new JLabel();
        lblCep.setBounds(10, 240, 49, 17);
        lblCep.setText("CEP:");
        lblCep.setFont(new Font("Tahoma", Font.PLAIN, 14));
        getContentPane().add(lblCep);
        
        txtCep = new JFormattedTextField((AbstractFormatter) null);
        txtCep.setBounds(109, 235, 175, 26);
        txtCep.setFont(new Font("Dialog", Font.PLAIN, 15));
        txtCep.setFocusLostBehavior(JFormattedTextField.COMMIT);
        getContentPane().add(txtCep);
        getContentPane().add(jLabel1);
        txtBairro = new javax.swing.JTextField();
        txtBairro.setBounds(386, 236, 167, 25);
        
                txtBairro.setFont(new java.awt.Font("Tahoma", 0, 15));
                getContentPane().add(txtBairro);
        jLabel13 = new javax.swing.JLabel();
        jLabel13.setBounds(10, 277, 76, 17);
        jLabel13.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
                jLabel13.setText("CIDADE:");
                getContentPane().add(jLabel13);
        txtCidade = new javax.swing.JFormattedTextField();
        txtCidade.setBounds(109, 272, 175, 26);
        
                txtCidade.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
                getContentPane().add(txtCidade);
        jLabel10 = new javax.swing.JLabel();
        jLabel10.setBounds(308, 277, 70, 17);
        
                jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                jLabel10.setText("ESTADO:");
                getContentPane().add(jLabel10);
        
        JComboBox cbEstado = new JComboBox();
        cbEstado.setBounds(388, 272, 70, 25);
        getContentPane().add(cbEstado);
        getContentPane().add(jLabel3);
        getContentPane().add(txtEmail);
        getContentPane().add(jLabel6);
        getContentPane().add(txtEnd);
        
        JLabel lblSite = new JLabel();
        lblSite.setText("SITE:");
        lblSite.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSite.setBounds(10, 345, 70, 17);
        getContentPane().add(lblSite);
        
        txtSite = new JTextField();
        txtSite.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtSite.setBounds(109, 341, 269, 25);
        getContentPane().add(txtSite);
        jButton2 = new javax.swing.JButton();
        jButton2.setBounds(868, 11, 46, 33);
        
                jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/btn_sair_25_25.png"))); // NOI18N
                jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jButton2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton2ActionPerformed(evt);
                    }
                });
                btnSalvar = new javax.swing.JButton();
                btnSalvar.setBounds(386, 542, 137, 73);
                
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
                        
                        lblObservacao = new JLabel();
                        lblObservacao.setText("OBSERVACAO:");
                        lblObservacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
                        lblObservacao.setBounds(10, 402, 98, 17);
                        getContentPane().add(lblObservacao);
                        
                        JScrollPane scrollPane = new JScrollPane();
                        scrollPane.setBounds(109, 377, 444, 73);
                        getContentPane().add(scrollPane);
                        
                        JTextArea textArea = new JTextArea();
                        scrollPane.setViewportView(textArea);
                        getContentPane().add(btnSalvar);
                getContentPane().add(jButton2);
                
                JButton btnNewButton = new JButton("?");
                btnNewButton.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		helpOs();
                	}

                });
                btnNewButton.setBounds(563, 401, 38, 23);
                getContentPane().add(btnNewButton);

        setBounds(0, 0, 940, 695);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyReleased

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        pesquisar();
    }//GEN-LAST:event_formComponentShown

    private void txtEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEndActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        swing.abrirTelaMenu();
    }//GEN-LAST:event_jButton2ActionPerformed
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCel;
    private javax.swing.JFormattedTextField txtCnpj;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEnd;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtCidade;
    private javax.swing.JFormattedTextField txtTel;
    private JFormattedTextField txtCep;
    private JTextField txtSite;
    private JLabel lblObservacao;
    // End of variables declaration//GEN-END:variables

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
                empresas.alterar(empresaBuilder(id), id.get());
            else 
                empresas.adicionar(empresaBuilder(Optional.empty()));
            JOptionPane.showMessageDialog(null, "Registro Salvo com Sucesso!");
            limparCampos();
            pesquisar();
        } catch (EmpresaNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
