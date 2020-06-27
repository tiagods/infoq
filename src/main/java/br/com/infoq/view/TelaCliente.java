package br.com.infoq.view;

import java.awt.Font;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import br.com.infoq.exception.ClienteNotFoundException;
import br.com.infoq.exception.IdIncorretoException;
import br.com.infoq.model.Cliente;
import br.com.infoq.model.Endereco;
import br.com.infoq.service.ClienteService;
import br.com.infoq.service.SwingOptions;
import br.com.infoq.utils.EnderecoUtil;
import br.com.infoq.utils.SwingUtils;
import br.com.infoq.utils.Validator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author Grazi,tiagods
 */
@Component
public class TelaCliente extends javax.swing.JInternalFrame {
    
    private static final long serialVersionUID = 1L;
    @Autowired private ClienteService clientes;
    @Autowired private SwingOptions swing;
    
    private MaskFormatter formatoCpf, formatoCnpj, formatoCelular, formatoTelefone, formatoCep;
    
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

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel2.setBounds(187, 272, 42, 17);
        jLabel3 = new javax.swing.JLabel();
        jLabel3.setBounds(478, 360, 47, 17);
        jLabel4 = new javax.swing.JLabel();
        jLabel4.setBounds(187, 403, 70, 17);
        txtNome = new javax.swing.JTextField();
        txtNome.setBounds(281, 268, 444, 25);
        txtEmail = new javax.swing.JTextField();
        txtEmail.setBounds(550, 356, 174, 25);
        jLabel6 = new javax.swing.JLabel();
        jLabel6.setBounds(187, 491, 76, 17);
        btnNovo = new javax.swing.JButton();
        btnNovo.setBounds(142, 581, 133, 73);
        btnDeletar = new javax.swing.JButton();
        btnDeletar.setBounds(542, 581, 155, 73);
        btnSalvar = new javax.swing.JButton();
        btnSalvar.setBounds(341, 581, 137, 73);
        jLabel7 = new javax.swing.JLabel();
        jLabel7.setBounds(726, 519, 140, 111);
        txtEnd = new javax.swing.JTextField();
        txtEnd.setBounds(281, 487, 174, 25);
        jLabel5 = new javax.swing.JLabel();
        jLabel5.setBounds(187, 20, 51, 17);
        txtBuscar = new javax.swing.JTextField();
        txtBuscar.setBounds(265, 16, 355, 25);
        jButton1 = new javax.swing.JButton();
        jButton1.setBounds(638, 11, 46, 33);
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setBounds(10, 101, 887, 156);
        tblClientes = new javax.swing.JTable();
        txtId = new javax.swing.JTextField();
        txtId.setBounds(10, 272, 41, 20);
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setBounds(478, 534, 53, 17);
        txtBairro = new javax.swing.JTextField();
        txtBairro.setBounds(550, 530, 172, 25);
        jLabel8 = new javax.swing.JLabel();
        jLabel8.setBounds(478, 491, 60, 17);
        txtCom = new javax.swing.JTextField();
        txtCom.setBounds(281, 530, 84, 25);
        jLabel9 = new javax.swing.JLabel();
        jLabel9.setBounds(478, 403, 62, 17);
        jLabel10 = new javax.swing.JLabel();
        jLabel10.setBounds(187, 534, 49, 17);
        txtNum = new javax.swing.JTextField();
        txtNum.setBounds(550, 487, 86, 25);
        jLabel11 = new javax.swing.JLabel();
        jLabel11.setBounds(187, 316, 29, 17);
        try {
            formatoTelefone = new MaskFormatter("(##)####-####");
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null ,"Não foi possivel receber o valor do telefone: " +erro);
        }
        txtTel = new JFormattedTextField(formatoTelefone);
        txtTel.setBounds(281, 399, 174, 25);
        try {
            formatoCelular = new MaskFormatter("(##)#####-####");
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null ,"Não foi possivel receber o valor do celular: " +erro);
        }
        txtCel = new JFormattedTextField(formatoCelular);
        txtCel.setBounds(550, 399, 175, 25);
        jLabel12 = new javax.swing.JLabel();
        jLabel12.setBounds(481, 315, 38, 19);
        jLabel13 = new javax.swing.JLabel();
        jLabel13.setBounds(187, 360, 23, 17);
        jLabel13.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtRg = new javax.swing.JFormattedTextField();
        txtRg.setBounds(281, 355, 164, 26);
        try {
            formatoCnpj= new MaskFormatter("##.###.###/####-##");
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null ,"Não foi possivel receber o valor do cnpj: " +erro);
        }
        txtCnpj = new JFormattedTextField(formatoCnpj);
        txtCnpj.setBounds(550, 311, 175, 26);
        try {
            formatoCpf= new MaskFormatter("###.###.###-##");
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null ,"Não foi possivel receber o valor do cpf: " +erro);
        }
        txtCpf = new JFormattedTextField(formatoCpf);
        txtCpf.setBounds(281, 311, 150, 26);
        jButton2 = new javax.swing.JButton();
        jButton2.setBounds(868, 8, 46, 33);
        
        try {
            formatoCep = new MaskFormatter("#####-###");
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null ,"Não foi possivel receber o valor do cep: " +erro);
        }
        txtCep = new JFormattedTextField(formatoCep);
        txtCep.setFont(new Font("Dialog", Font.PLAIN, 15));
        txtCep.setFocusLostBehavior(JFormattedTextField.COMMIT);
        txtCep.setBounds(281, 445, 150, 26);

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
        jLabel3.setText("E-MAIL:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("TELEFONE:");

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("ENDEREÇO:");

        btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 10)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inserir.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setToolTipText("Adicionar");
        btnNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 10)); // NOI18N
        btnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/deletar.png"))); // NOI18N
        btnDeletar.setText("Excluir");
        btnDeletar.setToolTipText("Remover");
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 10)); // NOI18N
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
        getContentPane().setLayout(null);
        getContentPane().add(jLabel11);
        getContentPane().add(txtId);
        getContentPane().add(jLabel12);
        getContentPane().add(txtCnpj);
        getContentPane().add(jLabel2);
        getContentPane().add(txtNome);
        getContentPane().add(jLabel6);
        getContentPane().add(jLabel10);
        getContentPane().add(txtEnd);
        getContentPane().add(jLabel5);
        getContentPane().add(txtBuscar);
        getContentPane().add(jButton1);
        getContentPane().add(jLabel4);
        getContentPane().add(jLabel13);
        getContentPane().add(txtCpf);
        getContentPane().add(txtTel);
        getContentPane().add(txtRg);
        getContentPane().add(txtCom);
        getContentPane().add(jLabel9);
        getContentPane().add(jLabel3);
        getContentPane().add(txtEmail);
        getContentPane().add(txtCel);
        getContentPane().add(jLabel8);
        getContentPane().add(jLabel1);
        getContentPane().add(txtBairro);
        getContentPane().add(txtNum);
        getContentPane().add(jButton2);
        getContentPane().add(jScrollPane1);
        getContentPane().add(btnNovo);
        getContentPane().add(btnSalvar);
        getContentPane().add(btnDeletar);
        getContentPane().add(jLabel7);
        getContentPane().add(txtCep);
        
        JLabel lblCep = new JLabel();
        lblCep.setText("CEP:");
        lblCep.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCep.setBounds(187, 450, 70, 17);
        getContentPane().add(lblCep);
        
        JButton btnBuscarCep = new JButton("");
        btnBuscarCep.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		btnBuscarCepActionPerformed(evt);
        	}
        });
        btnBuscarCep.setIcon(new ImageIcon(TelaCliente.class.getResource("/icons/buscar pequeno.png")));
        btnBuscarCep.setBounds(441, 445, 62, 26);
        getContentPane().add(btnBuscarCep);

        setBounds(0, 0, 940, 695);
    }// </editor-fold>//GEN-END:initComponents

    protected void btnBuscarCepActionPerformed(ActionEvent evt) {
    	if(Validator.validarCep(txtCep.getText())) {
	    	Optional<Endereco> result = EnderecoUtil.pegarCEP(txtCep.getText().replace(".", ""));
	    	if(result.isPresent()) {
	    		Endereco end = result.get();
	    		txtEnd.setText(end.getLogradouro());
	    		txtBairro.setText(end.getBairro());
	    	}
	    	else {
	    		JOptionPane.showMessageDialog(null, "Algo deu errado, verifique sua conexão com a internet");
	    	}
    	} else {
    		JOptionPane.showMessageDialog(null, "O cep informado esta incorreto");
    	}
	}

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
    private javax.swing.JFormattedTextField txtCep;
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
