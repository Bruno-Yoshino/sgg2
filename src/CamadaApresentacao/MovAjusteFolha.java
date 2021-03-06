/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CamadaApresentacao;

import CamadaNegocio.Funcionario;
import Controller.AjusteFolhaController;
import java.time.Instant;
import java.util.Date;
import util.SystemControl;
import util.mensagens;

/**
 *
 * @author 羽根川　翼
 * @author 阿賀野
 * @author 矢矧
 * 
 * Tester 2019年07月23日
 * @author 海雪
 * @author 海春
 * 
 */
public class MovAjusteFolha extends javax.swing.JDialog 
{

    private final SystemControl sc = new SystemControl();
    private final mensagens m = new mensagens();
    private final AjusteFolhaController afc = new AjusteFolhaController();
    
    public MovAjusteFolha(java.awt.Frame parent, boolean modal, Funcionario f) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        afc.getAf().setFunc(f);
        btnNovo.setName("btnNovo");
        btnAlterar.setName("btnAlterar");
        btnCancelar.setName("btnCancelar");
        btnGravar.setName("btnGravar");
        btnLocalizar.setName("btnLocalizar");
        btnSair.setName("btnSair");
        btnaddFolha.setName("btnaddFolha");
        btnaddServico.setName("btnaddServico");
        btnlocFolha.setName("btnlocFolha");
        btnlocServico.setName("btnlocServico");
        rbincrement.setSelected(true);
        btnAlterar.setVisible(false);
        btnLocalizar.setVisible(false);
        afc.getAf().setServ(null);
        sc.HabilityComponents(jPanel1.getComponents(), false);
        sc.Initialize(jPanel2.getComponents());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTamnho = new javax.swing.JTextField();
        txtQtdAtual = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtqtd = new javax.swing.JTextField();
        lbTexto = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtServico = new javax.swing.JTextField();
        btnaddServico = new javax.swing.JButton();
        btnlocServico = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        rbdecrement = new javax.swing.JRadioButton();
        rbincrement = new javax.swing.JRadioButton();
        btnaddFolha = new javax.swing.JButton();
        btnlocFolha = new javax.swing.JButton();
        txtData = new br.com.marciorl.beans.DateChooser();
        jPanel2 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnLocalizar = new javax.swing.JButton();
        btnGravar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Codigo:");

        txtcod.setEditable(false);
        txtcod.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtObs.setColumns(20);
        txtObs.setRows(5);
        jScrollPane1.setViewportView(txtObs);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Observação:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Tamanho/Formato:");

        txtTamnho.setEditable(false);
        txtTamnho.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtQtdAtual.setEditable(false);
        txtQtdAtual.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Quantidade atual:");

        txtqtd.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbTexto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbTexto.setText("Quantidade a ser retirado:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Serviço utilizado:");

        txtServico.setEditable(false);
        txtServico.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnaddServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Novo 16.png"))); // NOI18N
        btnaddServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddServicoActionPerformed(evt);
            }
        });

        btnlocServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Localizar 16.png"))); // NOI18N
        btnlocServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlocServicoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Data:");

        rbdecrement.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rbdecrement.setText("Retirar");
        rbdecrement.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbdecrementStateChanged(evt);
            }
        });

        rbincrement.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rbincrement.setText("Adicionar");
        rbincrement.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbincrementStateChanged(evt);
            }
        });

        btnaddFolha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Novo 16.png"))); // NOI18N
        btnaddFolha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddFolhaActionPerformed(evt);
            }
        });

        btnlocFolha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Localizar 16.png"))); // NOI18N
        btnlocFolha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlocFolhaActionPerformed(evt);
            }
        });

        txtData.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtServico, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnaddServico, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnlocServico, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTamnho)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnaddFolha, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnlocFolha, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbincrement)
                                .addGap(18, 18, 18)
                                .addComponent(rbdecrement))
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQtdAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbTexto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtqtd, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbincrement)
                    .addComponent(rbdecrement))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnlocServico, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnaddServico, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTamnho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnlocFolha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnaddFolha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtQtdAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbTexto)
                        .addComponent(txtqtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Novo 16.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Alterar16.png"))); // NOI18N
        btnAlterar.setText("Alterar");

        btnLocalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Localizar 16.png"))); // NOI18N
        btnLocalizar.setText("Localizar");
        btnLocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarActionPerformed(evt);
            }
        });

        btnGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Gravar16.png"))); // NOI18N
        btnGravar.setText("Gravar");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Cancelar16.png.jpg"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Sair.png"))); // NOI18N
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGravar, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                        .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                    .addComponent(btnLocalizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jMenu1.setText("Help");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem1.setText("Ajuda");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
       dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnaddServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddServicoActionPerformed
       CadastroServico frm = new CadastroServico(null, true);
       frm.setTitle("Cadastro Serviço");
       frm.setVisible(true);
    }//GEN-LAST:event_btnaddServicoActionPerformed

    private void btnlocServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlocServicoActionPerformed
        ConsultaPadrao consDS = new ConsultaPadrao(null, true);
        String[] vet = new String[1];
        vet[0] = "Nome";
        consDS.configuraOpcoes(vet, 1, 0, "AServico", false);
        consDS.verificaconsulta(true);
        consDS.setVisible(true);
        if (consDS.getCodigo() != 0)
        {
            afc.buscaServico(consDS.getCodigo());
            consDS.dispose();
            txtServico.setText(afc.getAf().getServ().getNome());
        }
        else
        {
            consDS.dispose();
        }
    }//GEN-LAST:event_btnlocServicoActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        sc.Edity(jPanel2.getComponents());
        sc.HabilityComponents(jPanel1.getComponents(), true);
        txtcod.setText("0");
        txtData.setEnabled(false);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnaddFolhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddFolhaActionPerformed
       CadastroFolha frm = new CadastroFolha(null, true);
       frm.setTitle("Cadastro Folha");
       frm.setVisible(true);
    }//GEN-LAST:event_btnaddFolhaActionPerformed

    private void btnlocFolhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlocFolhaActionPerformed
        ConsultaPadrao consFolha = new ConsultaPadrao(null, true);
        String[] vet = new String[2];
        vet[0] = "Tamanho";
        vet[1] = "Descrição";
        consFolha.configuraOpcoes(vet, 2, 0, "AFolha", false);
        consFolha.verificaconsulta(true);
        consFolha.setVisible(true);
        if (consFolha.getCodigo() != 0)
        {
            afc.buscaFolha(consFolha.getCodigo());
            consFolha.dispose();
            txtTamnho.setText(afc.getAf().getF().getTamanho()+ "/" +afc.getAf().getF().getDescricao());
            txtQtdAtual.setText(""+(afc.getAf().getF().getQtd() - afc.QtdReservado()));
        }
        else
        {
            consFolha.dispose();
        }
    }//GEN-LAST:event_btnlocFolhaActionPerformed

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        switch(afc.validar(txtcod.getText(), txtqtd.getText(), Date.from(Instant.now()), rbincrement.isSelected(), txtObs.getText(), txtTamnho.getText(), txtServico.getText()))
        {
            case 1:  m.InformationMessage("Informe a quantidade!", "Informação"); txtqtd.requestFocus(); break;
            case 2:  m.InformationMessage("Quanrtidade negativa!", "Informação"); txtqtd.requestFocus(); break;
            case 3:  m.InformationMessage("Informe a observação!", "Informação"); txtObs.requestFocus();break;
            case 4:  m.InformationMessage("A quantidade informada é maior que a quantidade existente no estoque!", "Informação"); txtqtd.requestFocus(); break;
            case 5:  m.InformationMessage("Informe a Folha!", "Informação"); btnlocFolha.requestFocus(); break;
            case 6:  m.InformationMessage("Informe o Serviço!", "Informação"); btnlocServico.requestFocus(); break;
            default:
                if(afc.gravar())
                {
                    afc.atualizaEstoque();
                    m.InformationMessage("Esqtoque ajustado com sucesso!", "Informação");
                    limpar();
                }
                else
                {
                    m.ErroMessage("Erro ao Ajustar Estoque!", "ERRO");
                }
        }
    }//GEN-LAST:event_btnGravarActionPerformed

    private void rbincrementStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbincrementStateChanged
        if(rbincrement.isSelected())
        {
            rbdecrement.setSelected(false);
            lbTexto.setText("Quantidade a ser colocado:");
        }
    }//GEN-LAST:event_rbincrementStateChanged

    private void rbdecrementStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbdecrementStateChanged
        if(rbdecrement.isSelected())
        {
            rbincrement.setSelected(false);
            lbTexto.setText("Quantidade a ser retirado:");
        }
    }//GEN-LAST:event_rbdecrementStateChanged

    private void btnLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarActionPerformed
    
    }//GEN-LAST:event_btnLocalizarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if(!sc.help("SGG/SGGOnlineHelp/movajustarfolha.html", "C:/"))
        {
            sc.help("SGG/SGGOnlineHelp/movajustarfolha.html", "D:/");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void limpar()
    {
        txtObs.setText("");
        txtQtdAtual.setText("");
        txtServico.setText("");
        txtTamnho.setText("");
        txtcod.setText("");
        txtqtd.setText("");
        rbincrement.setSelected(true);
        
        sc.Initialize(jPanel2.getComponents());
        sc.HabilityComponents(jPanel1.getComponents(), false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnLocalizar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnaddFolha;
    private javax.swing.JButton btnaddServico;
    private javax.swing.JButton btnlocFolha;
    private javax.swing.JButton btnlocServico;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTexto;
    private javax.swing.JRadioButton rbdecrement;
    private javax.swing.JRadioButton rbincrement;
    private br.com.marciorl.beans.DateChooser txtData;
    private javax.swing.JTextArea txtObs;
    private javax.swing.JTextField txtQtdAtual;
    private javax.swing.JTextField txtServico;
    private javax.swing.JTextField txtTamnho;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtqtd;
    // End of variables declaration//GEN-END:variables
}
