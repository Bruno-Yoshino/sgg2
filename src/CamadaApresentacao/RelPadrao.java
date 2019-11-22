package CamadaApresentacao;

import CamadaLogica.Relatorio;
import CamadaNegocio.AjustarFolha;
import CamadaNegocio.AjustarProduto;
import CamadaNegocio.Cliente;
import CamadaNegocio.Pedido;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import util.SystemControl;

/**
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 稲荷
 * @author 天野
 * @author 紅葉
 * @author 川内
 * @author 神通
 * @author 那珂
 */
public class RelPadrao extends javax.swing.JDialog {

    private int codigo;    
    private String[] vetOpcoes = new String[20];
    private final SystemControl  sc = new SystemControl();
    private int tl;
    private String tabela;
    private int posDefault;
    boolean jtableEditavel;
    private final Relatorio rel = new Relatorio();
    private Pedido ped;

    public RelPadrao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        txtValor.setVisible(true);
        lbData.setVisible(false);
        dataInicio.setVisible(false);
        dataFim.setVisible(false);
        lbA.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cbOpcao = new javax.swing.JComboBox<>();
        lbTexto = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        lbData = new javax.swing.JLabel();
        dataInicio = new br.com.marciorl.beans.DateChooser();
        lbA = new javax.swing.JLabel();
        dataFim = new br.com.marciorl.beans.DateChooser();
        lbTexto1 = new javax.swing.JLabel();
        btnloc = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        cbOpcao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbOpcao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbOpcaoItemStateChanged(evt);
            }
        });

        lbTexto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTexto.setText("Valor:");

        txtValor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbData.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbData.setText("Data:");

        lbA.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbA.setText("a");

        lbTexto1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTexto1.setText("Opção:");

        btnloc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Localizar 16.png"))); // NOI18N
        btnloc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlocActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbTexto)
                            .addComponent(lbTexto1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnloc, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lbData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTexto1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbTexto)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnloc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbData)
                    .addComponent(dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbA)
                    .addComponent(dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Sair.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/relatorio.png"))); // NOI18N
        jButton2.setText("Gerar Relatorio");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbOpcaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbOpcaoItemStateChanged
        ControlaComboBox();
    }//GEN-LAST:event_cbOpcaoItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String lista[] = {"PDF","Local"};
        Object valor;
        do{
            valor = JOptionPane.showInputDialog(this, "Atenção", 
                    "A geração do Relatorio será em:", JOptionPane.INFORMATION_MESSAGE,
                    null, lista, lista[0]);
        }while(valor == null);

        switch(tabela)
        {
            case "AjusteEstoqueProduto": AjusteProduto(""+valor); break;
            case "AjusteEstoqueFolha": AjusteFolha(""+valor); break;
            case "Nota NF": NNF(""+valor); break;
            case "Comprovante": Comprovante(""+valor); break;
        }        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnlocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlocActionPerformed
        switch(tabela)
        {
            case "Nota NF": consultaPedido(); break;
            default: btnloc.setVisible(false);
        }
    }//GEN-LAST:event_btnlocActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        String caminho = "";
        switch(tabela)
        {
            case "AjusteEstoqueProduto": caminho = "SGG/SGGOnlineHelp/relaep.html"; break;
            case "AjusteEstoqueFolha":caminho = "SGG/SGGOnlineHelp/relaef.html"; break;
            case "Nota NF": caminho = "SGG/SGGOnlineHelp/relnnf.html"; break;
            case "Comprovante": caminho = "SGG/SGGOnlineHelp/relcomprovante.html"; break;
        }
        if(!sc.help(caminho, "C:/"))
        {
            sc.help(caminho, "D:/");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public void configuraOpcoes(String[] vetOpcoes, int tl, int posDefault, String tabela)
    {
        this.tl = tl;
        this.vetOpcoes = vetOpcoes;
        this.posDefault = posDefault;
        this.tabela = tabela;
        this.setTitle(tabela);
        for (int i = 0; i < tl; i++)
        {
            cbOpcao.addItem(vetOpcoes[i]);
        }
        cbOpcao.setSelectedIndex(posDefault);
    }
    
    private void ControlaComboBox() //alterar os campos conforme a opcao selecionada
    {
        String nome = (String) cbOpcao.getSelectedItem();
        
        if(nome.contains("Data"))
        {
            txtValor.setVisible(false);
            lbData.setVisible(true);
            dataInicio.setVisible(true);
            dataFim.setVisible(false);
            lbA.setVisible(false);
        }
        else
        {
            if(nome.contains("Periodo"))
            {
                txtValor.setVisible(false);
                lbData.setVisible(true);
                dataInicio.setVisible(true);
                dataFim.setVisible(true);
                lbA.setVisible(true);

            }
            else
            {
                txtValor.setVisible(true);
                lbData.setVisible(false);
                dataInicio.setVisible(false);
                dataFim.setVisible(false);
                lbA.setVisible(false);
            }
        }
        controlaTabela();
    }
    
    private void controlaTabela()
    {
        switch(tabela)
        {
            case "Nota NF": btnloc.setVisible(true); break;
            default: btnloc.setVisible(false);
        }
    }
    
    private void consultaPedido()
    {
        ConsultaMov consPedido = new ConsultaMov(null, true);
        String[] vet = new String[4];
        vet[0] = "Tudo";
        vet[1] = "Data";
        vet[2] = "Periodo";
        vet[3] = "Numero";
        consPedido.configuraOpcoes(vet, 4, 0, "Pedido", false);
        consPedido.verificaconsulta(true);
        consPedido.setVisible(true);
        if (consPedido.getCodigo() != 0)
        {
            Pedido p;
            Cliente cli;
            int cod = consPedido.getCodigo();
            try {
                p = new Pedido().buscar(cod);
                txtValor.setText(""+p.getCodigo());
                cli = p.getCli();
                cli.buscarCodigoFisica(p.getCli().getCodigo());
                if(cli.getCpf() == null)
                    cli.buscarCodigoJuridica(p.getCli().getCodigo());
                p.setCli(cli);
                ped = p;
                txtValor.setText("Número Pedido: "+p.getCodigo());
            } catch (SQLException ex) {
                Logger.getLogger(RelPadrao.class.getName()).log(Level.SEVERE, null, ex);
            }
            consPedido.dispose();
        }
        else
        {
            consPedido.dispose();
        }
    }
    
    private void AjusteProduto(String op)
    {
        
        try {
            if(op.equals("PDF"))
                rel.ImprimirRelatorioPDF(AjustarProduto.Relatorio(txtValor.getText(), cbOpcao.getSelectedIndex(), dataInicio.getData(), dataFim.getData()), "Relatorios\\AjusteProduto.jasper");
            else
                rel.ImprimirRelatorio(AjustarProduto.Relatorio(txtValor.getText(), cbOpcao.getSelectedIndex(), dataInicio.getData(), dataFim.getData()), "Relatorios\\AjusteProduto.jasper", "Relatorio Ajuste Produto");
        } catch (JRException ex) {
            System.out.println(""+ex.toString());
        }
        
    }
    
    private void AjusteFolha(String op)
    {
        try {
            if(op.equals("PDF"))
                rel.ImprimirRelatorioPDF(AjustarFolha.Relatorio(txtValor.getText(), cbOpcao.getSelectedIndex(), dataInicio.getData(), dataFim.getData()), "Relatorios\\ajustefolha.jasper");
            else
                rel.ImprimirRelatorio(AjustarFolha.Relatorio(txtValor.getText(), cbOpcao.getSelectedIndex(), dataInicio.getData(), dataFim.getData()), "Relatorios\\ajustefolha.jasper", "Relatorio Ajuste Folha");
        } catch (JRException ex) {
            System.out.println(""+ex.toString());
        }
    }
    
    private void Comprovante(String op)
    {
        try {
            if(op.equals("PDF"))
                rel.ImprimirRelatorioPDF(AjustarFolha.Relatorio(txtValor.getText(), cbOpcao.getSelectedIndex(), dataInicio.getData(), dataFim.getData()), "Relatorios\\comprovantep.jasper");
            else
                rel.ImprimirRelatorio(AjustarFolha.Relatorio(txtValor.getText(), cbOpcao.getSelectedIndex(), dataInicio.getData(), dataFim.getData()), "Relatorios\\comprovantep.jasper", "Relatorio Ajuste Folha");
        } catch (JRException ex) {
            System.out.println(""+ex.toString());
        }
    }
    
    private void NNF(String op)//Nota Nao Fiscal
    {
        if(!txtValor.getText().equals(""))
        {
            try {
                if(op.equals("PDF"))
                    rel.ImprimirRelatorioPDFNNF(ped, "Relatorios\\notaNF.jasper");
                else
                    rel.ImprimirRelatorioNNF(ped, "Relatorios\\notaNF.jasper", "Nota Não Fiscal");
            } catch (JRException ex) {
                System.out.println(""+ex.toString());
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnloc;
    private javax.swing.JComboBox<String> cbOpcao;
    private br.com.marciorl.beans.DateChooser dataFim;
    private br.com.marciorl.beans.DateChooser dataInicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbA;
    private javax.swing.JLabel lbData;
    private javax.swing.JLabel lbTexto;
    private javax.swing.JLabel lbTexto1;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
