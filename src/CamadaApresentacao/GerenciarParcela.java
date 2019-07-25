/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CamadaApresentacao;

import CamadaLogica.ReadOnlyTableModel;
import CamadaNegocio.Compra;
import CamadaNegocio.ContaPagar;
import CamadaNegocio.Pedido;
import Controller.GerenciarParcelaController;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import util.SystemControl;
import util.mensagens;

/**
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 夕張
 * @author もも
 * @author 林道
 * @author 香取 
 * @author 鹿島
 * 
 * Tester 2019/07/23
 * @author 海星
 * @author 海流
 * @author 伊弉冉
 * @author 伊弉諾
 */
public class GerenciarParcela extends javax.swing.JDialog {

    private boolean flag;
    private final GerenciarParcelaController gpc = new GerenciarParcelaController();
    private final SystemControl sc = new SystemControl();
    private final mensagens m = new mensagens();
    
    public boolean isFlag() {
        return flag;
    }
    
    public GerenciarParcela(java.awt.Frame parent, boolean modal, ContaPagar cp, Compra c, Pedido p) {//Contas a Receber "Pedido"
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        flag = false;
        gpc.setC(c);
        gpc.setCp(cp);
        gpc.setP(p);
        txtQtdP.setText("1");
        txtIntervalo.setText("0");
        if(c != null)
        {
            dtData.setEditable(true);
            txtValorT.setText(""+c.getValort());
//            txtValorT.setText(""+sc.verificaValor(sc.converteString(c.getValort())));
        }
        if(cp != null)
        {
            dtData.setEditable(false);
            dtData.setData(cp.getDataV());
            txtValorT.setText(""+cp.getValorC());
            //txtValorT.setText(""+sc.verificaValor(sc.converteString(cp.getValorC())));
        }
        if(p != null)
        {
            dtData.setEditable(true);
            txtValorT.setText(""+p.getValorTotal());
            //txtValorT.setText(""+sc.verificaValor(sc.converteString(p.getValorTotal())));
        }
        GerenciarParcelaController.configuraModelItem(jTable1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLTexto = new javax.swing.JLabel();
        txtValorT = new javax.swing.JTextField();
        jLTexto1 = new javax.swing.JLabel();
        txtQtdP = new javax.swing.JTextField();
        jLTexto3 = new javax.swing.JLabel();
        txtIntervalo = new javax.swing.JTextField();
        btnGravar3 = new javax.swing.JButton();
        jLTexto4 = new javax.swing.JLabel();
        dtData = new br.com.marciorl.beans.DateChooser();
        btnGerarParcela = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnGravar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLTexto6 = new javax.swing.JLabel();
        txtValorV = new javax.swing.JTextField();
        btnAtualizar = new javax.swing.JButton();
        jLTexto7 = new javax.swing.JLabel();
        txtValorN = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLTexto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLTexto.setText("Valor Total:");

        txtValorT.setEditable(false);
        txtValorT.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtValorT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtValorTFocusGained(evt);
            }
        });

        jLTexto1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLTexto1.setText("Quantidade de Parcelas:");

        txtQtdP.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtQtdP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtQtdPFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtQtdPFocusLost(evt);
            }
        });
        txtQtdP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtdPKeyPressed(evt);
            }
        });

        jLTexto3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLTexto3.setText("Intervalo:");

        txtIntervalo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtIntervalo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIntervaloFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIntervaloFocusLost(evt);
            }
        });
        txtIntervalo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIntervaloKeyPressed(evt);
            }
        });

        btnGravar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Information16.png"))); // NOI18N
        btnGravar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravar3ActionPerformed(evt);
            }
        });

        jLTexto4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLTexto4.setText("Data de Vencimento:");

        btnGerarParcela.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnGerarParcela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Novo 16.png"))); // NOI18N
        btnGerarParcela.setText("Gerar Parcelas");
        btnGerarParcela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarParcelaActionPerformed(evt);
            }
        });

        btnAlterar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Alterar16.png"))); // NOI18N
        btnAlterar.setText("Alterar o valor da Parcela");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnGravar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Gravar16.png"))); // NOI18N
        btnGravar.setText("Gravar");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
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
                        .addComponent(jLTexto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorT, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLTexto4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnGerarParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(365, 365, 365)
                        .addComponent(btnGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLTexto1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtQtdP, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(197, 197, 197)
                                    .addComponent(dtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(34, 34, 34)
                                    .addComponent(jLTexto3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtIntervalo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnGravar3)))
                            .addGap(97, 97, 97))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(171, 171, 171))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLTexto)
                    .addComponent(txtValorT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLTexto4)
                    .addComponent(dtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLTexto3)
                        .addComponent(txtIntervalo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLTexto1)
                        .addComponent(txtQtdP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGravar3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGravar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGerarParcela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLTexto6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLTexto6.setText("Valor da Parcela Atual:");

        txtValorV.setEditable(false);
        txtValorV.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtValorV.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtValorVFocusGained(evt);
            }
        });
        txtValorV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtValorVKeyPressed(evt);
            }
        });

        btnAtualizar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Atualizar16.png"))); // NOI18N
        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        jLTexto7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLTexto7.setText("Novo Valor da Parcela:");

        txtValorN.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtValorN.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtValorNFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtValorNFocusLost(evt);
            }
        });
        txtValorN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtValorNKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLTexto6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValorV, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLTexto7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValorN, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLTexto7)
                        .addComponent(txtValorN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLTexto6)
                        .addComponent(txtValorV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void txtValorTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorTFocusGained
        
    }//GEN-LAST:event_txtValorTFocusGained

    private void txtQtdPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQtdPFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtdPFocusGained

    private void txtQtdPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtdPKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            btnGerarParcelaActionPerformed(null);
    }//GEN-LAST:event_txtQtdPKeyPressed

    private void txtIntervaloFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIntervaloFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIntervaloFocusGained

    private void txtIntervaloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIntervaloKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            btnGerarParcelaActionPerformed(null);
    }//GEN-LAST:event_txtIntervaloKeyPressed

    private void txtValorVFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorVFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVFocusGained

    private void txtValorVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorVKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVKeyPressed

    private void txtValorNFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorNFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorNFocusGained

    private void txtValorNKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorNKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            btnAtualizarActionPerformed(null);
    }//GEN-LAST:event_txtValorNKeyPressed

    private void btnGravar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravar3ActionPerformed
        m.InformationMessage("Neste campo, podera estar informando o intervalo para o vencimento. As opições são: \n Para 0: 1 mes.\n 1 ~ 31: Dia(s) \n -1 ~ -3: Semana(s) ", "Atenção");
    }//GEN-LAST:event_btnGravar3ActionPerformed

    private void btnGerarParcelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarParcelaActionPerformed
        gpc.RemoveAll(jTable1);
        switch(gpc.gerarParcelas(txtQtdP.getText(), txtIntervalo.getText(), jTable1, Double.parseDouble(txtValorT.getText()), dtData.getData()))
        {
            case 1: m.InformationMessage("A Quantidade de Parcelas precisa ser maior que 0!", "Informação"); txtQtdP.requestFocus(); break;
            case 2: m.InformationMessage("Informe o Intervalo! Mais detalhe consulte a informação", "Informação"); txtIntervalo.requestFocus(); break;
        }
    }//GEN-LAST:event_btnGerarParcelaActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if (jTable1.getSelectedRow() >= 0)
        {
            ReadOnlyTableModel model = (ReadOnlyTableModel) jTable1.getModel();
            txtValorV.setText(""+model.getValueAt(jTable1.getSelectedRow(), 1));
            txtValorN.setText("0");
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Você deve selecionar uma parcela!", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        switch(gpc.check(jTable1, txtValorT))
        {
            case 1: m.InformationMessage("O valor total das parcelas não são iguais a valor total!", "Informação"); break;
            default: 
                if(gpc.gravar(jTable1))
                {
                    flag = true;
                    m.InformationMessage("Parcelas gerados com sucesso!", "Informação");
                    dispose();
                }
                else
                {
                    m.ErroMessage("Erro ao Gerar!", "ERRO");
                }
        }
    }//GEN-LAST:event_btnGravarActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        gpc.atualizarValor(txtValorN, jTable1, jTable1.getSelectedRow());
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void txtQtdPFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQtdPFocusLost
      if(gpc.varidar(txtQtdP.getText()) == 1)
      {
          m.InformationMessage("Informe a Quantidade de Parcelas!", "Informação");
          txtQtdP.requestFocus();
      }
    }//GEN-LAST:event_txtQtdPFocusLost

    private void txtIntervaloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIntervaloFocusLost
      if(gpc.varidar(txtQtdP.getText()) == 1)
      {
          m.InformationMessage("Informe o Intervalo!", "Informação");
          txtQtdP.requestFocus();
      }
    }//GEN-LAST:event_txtIntervaloFocusLost

    private void txtValorNFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorNFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorNFocusLost

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        flag = false;
    }//GEN-LAST:event_formWindowClosing



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnGerarParcela;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnGravar3;
    private br.com.marciorl.beans.DateChooser dtData;
    private javax.swing.JLabel jLTexto;
    private javax.swing.JLabel jLTexto1;
    private javax.swing.JLabel jLTexto3;
    private javax.swing.JLabel jLTexto4;
    private javax.swing.JLabel jLTexto6;
    private javax.swing.JLabel jLTexto7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtIntervalo;
    private javax.swing.JTextField txtQtdP;
    private javax.swing.JTextField txtValorN;
    private javax.swing.JTextField txtValorT;
    private javax.swing.JTextField txtValorV;
    // End of variables declaration//GEN-END:variables
}
