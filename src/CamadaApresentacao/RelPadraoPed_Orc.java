package CamadaApresentacao;

import CamadaLogica.Relatorio;
import CamadaNegocio.Cliente;
import Controller.OrcamentoController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import util.SystemControl;
import util.Validacao;
import util.mensagens;

/**
 *
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
public class RelPadraoPed_Orc extends javax.swing.JDialog {

    private int codigo;    
    private String[] vetOpcoes = new String[20];
    private final SystemControl  sc = new SystemControl();
    private final mensagens m = new mensagens();
    private int tl;
    private String tabela;
    private int posDefault;
    boolean jtableEditavel;
    private final Relatorio rel = new Relatorio();
    private Cliente cli;
    private final util.Validacao v = new Validacao();

    public RelPadraoPed_Orc(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        txtValor.setVisible(true);
        lbData.setVisible(true);
        dataInicio.setVisible(true);
        dataFim.setVisible(true);
        lbA.setVisible(true);
        btnlocCliente.setVisible(true);
        lbTexto.setText("Nome:");
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
        btnlocCliente = new javax.swing.JButton();
        lbTexto1 = new javax.swing.JLabel();
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

        lbTexto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbTexto.setText("Valor:");

        txtValor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbData.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbData.setText("Data:");

        lbA.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbA.setText("a");

        btnlocCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Localizar 16.png"))); // NOI18N
        btnlocCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlocClienteActionPerformed(evt);
            }
        });

        lbTexto1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTexto1.setText("Opção:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lbData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(187, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbTexto1)
                            .addComponent(lbTexto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtValor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnlocCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbOpcao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTexto1)
                    .addComponent(cbOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, 22, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnlocCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbTexto)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
    if(!txtValor.getText().equals("") || cbOpcao.getSelectedIndex()== 2)
    {    String lista[] = {"PDF","Local"};
        Object valor;
        do{
            valor = JOptionPane.showInputDialog(this, "Atenção", 
                    "A geração do Relatorio será em:", JOptionPane.INFORMATION_MESSAGE,
                    null, lista, lista[0]);
        }while(valor == null);
        switch(tabela)
        {
            case "Orçamento": Orcamento(""+valor);  break;
            case "Pedido" : Pedido(""+valor); break;
        }   
    }
    else
    {
        m.InformationMessage("Informe o Cliente ou número.", "Atenção");
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnlocClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlocClienteActionPerformed
        ConsultaPadrao consCliente = new ConsultaPadrao(null, true);
        String[] vet = new String[3];
        vet[0] = "Nome";
        vet[1] = "CPF";
        vet[2] = "CNPJ";
        consCliente.configuraOpcoes(vet, 3, 0, "Cliente", false);
        consCliente.verificaconsulta(true);
        consCliente.setVisible(true);
        if (consCliente.getCodigo() != 0)
        {
            int cod = consCliente.getCodigo();
            cli = new Cliente().buscarCodigo(cod);
            txtValor.setText(""+cli.getNome());
            cli.buscarCodigoFisica(cod);
            if(cli.getCpf() == null)
                cli.buscarCodigoJuridica(cod);
            consCliente.dispose();
        }
        else
        {
            consCliente.dispose();
        }
    }//GEN-LAST:event_btnlocClienteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        String caminho = "";
        switch(tabela)
        {
            case "Orçamento": caminho = "SGG/SGGOnlineHelp/relorc.html";  break;
            case "Pedido" : caminho = "SGG/SGGOnlineHelp/relped.html"; break;
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
        this.setTitle("Localiza " + tabela);
        for (int i = 0; i < tl; i++)
        {
            cbOpcao.addItem(vetOpcoes[i]);
        }
        cbOpcao.setSelectedIndex(posDefault);
    }
    
    private void ControlaComboBox() //alterar os campos conforme a opcao selecionada
    {
        String nome = (String) cbOpcao.getSelectedItem();
        
        if(nome.contains("Periodo e Nome"))
        {
            txtValor.setVisible(true);
            lbData.setVisible(true);
            dataInicio.setVisible(true);
            dataFim.setVisible(true);
            lbA.setVisible(true);
            btnlocCliente.setVisible(true);
            lbTexto.setText("Nome:");
            txtValor.setText("");
        }
        else
        {
            if(nome.contains("Número"))
            {
                txtValor.setVisible(true);
                lbTexto.setText("Número:");
                lbData.setVisible(false);
                dataInicio.setVisible(false);
                dataFim.setVisible(false);
                lbA.setVisible(false);
                btnlocCliente.setVisible(false);
                txtValor.setText("");
            }
            else
            {
                txtValor.setVisible(false);
                lbTexto.setText("");
                lbData.setVisible(true);
                dataInicio.setVisible(true);
                dataFim.setVisible(false);
                lbA.setVisible(false);
                btnlocCliente.setVisible(false);
                txtValor.setText("");
            }
        }
    }
    
    private void Orcamento(String op)
    {
        try {
            if(op.equals("PDF"))
            {
                switch(cbOpcao.getSelectedIndex())
                {
                    case 1:
                        rel.ImprimirRelatorioPDF(cli.getCodigo(), dataInicio.getData(), dataFim.getData(), "Relatorios\\Orcamento.jasper");
                        break;
                    case 0:
                        rel.ImprimirRelatorioPDFNumero(v.ConverteNumeroInteiro(txtValor.getText()), "Relatorios\\OrcamentoCodigo.jasper");
                        break;
                }
            }
            else
                switch(cbOpcao.getSelectedIndex())
                {
                    case 1:
                        rel.ImprimirRelatorio(cli.getCodigo(), dataInicio.getData(), dataFim.getData(), "Relatorios\\Orcamento.jasper", "Relatorio Orçamento");
                        break;
                    case 0:
                        rel.ImprimirRelatorioNumero(v.ConverteNumeroInteiro(txtValor.getText()), "Relatorios\\OrcamentoCodigo.jasper", "Relatorio Orçamento");
                        break;
                }
        } catch (JRException ex) {
            //Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, ex);
            m.InformationMessage("Relatório em Branco!", "Atenção");
        }
    }
    
    private void Pedido(String op)
    {
        try {
            if(op.equals("PDF"))
            {
                switch(cbOpcao.getSelectedIndex())
                {
                    case 1:
                        rel.ImprimirRelatorioPDF(cli.getCodigo(), dataInicio.getData(), dataFim.getData(), "Relatorios\\pedido2.jasper");
                        break;
                    case 0:
                        rel.ImprimirRelatorioPDFNumero(v.ConverteNumeroInteiro(txtValor.getText()), "Relatorios\\pedidoCodigo.jasper");
                        break;
                    case 2:
                        rel.ImprimirRelatorioPDFData(dataInicio.getData(), "Relatorios\\pedidoDia.jasper");
                        break;
                }
            }
            else
                switch(cbOpcao.getSelectedIndex())
                {
                    case 1:
                        rel.ImprimirRelatorio(cli.getCodigo(), dataInicio.getData(), dataFim.getData(), "Relatorios\\pedido2.jasper", "Relatorio Pedido");
                        break;
                    case 0:
                        rel.ImprimirRelatorioNumero(v.ConverteNumeroInteiro(txtValor.getText()), "Relatorios\\pedidoCodigo.jasper", "Relatorio Pedido");
                        break;
                    case 2:
                        rel.ImprimirRelatorioData(dataInicio.getData(), "Relatorios\\pedidoDia.jasper", "Relatorio Pedido");
                        break;
                }
        } catch (JRException ex) {
//            Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, ex);
            m.InformationMessage("Relatório em Branco!", "Atenção");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlocCliente;
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
