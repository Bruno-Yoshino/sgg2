package CamadaApresentacao;

import CamadaLogica.ReadOnlyTableModel;
import CamadaNegocio.Caixa;
import CamadaNegocio.ContaPagar;
import CamadaNegocio.ContaReceber;
import CamadaNegocio.Empresa;
import CamadaNegocio.Funcionario;
import CamadaNegocio.Producao;
import CamadaNegocio.TipoConta;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import util.ClassHome;
import util.SystemControl;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import javax.swing.JOptionPane;
import util.mensagens;

/*
Important Data Base
Add data base (2019/06/08): Status: Not Aplication
desp_datapagamento   date
desp_valorp          Double Precision
--------------------------------------------------------------------------------------------------------------
Type chenge necessary date -> TimeStamp (2019/06/09): Status: Cancel
--------------------------------------------------------------------------------------------------------------
Ajusting (2019/06/13): Status: Not Aplication
1- Deleting Table "despesa"; Fase Status: Not Aplication
2- Conecting "Tipo_Conta" in "Conta_pagar" and creating FK tc_codigo in "Conta_pagar"; Fase Status: Not Aplication
3- Conecting "Funcionario" in "Conta_pagar" and creating FK func_codigo in "Conta_pagar"; Fase Status: Not Aplication
4- Conecting "Caixa" in "Conta_pagar" and Creating FK caixa_codigo in "Conta_pagar"; Fase Status: Not Aplication
5- Deleting Field "cp_nparcelat" in Table "Conta_pagar"; Fase Status: Not Aplication
6- Creating mew Camp "cp_datavencimento" in "Conta_pagar" is type Date; Fase Status: Not Aplication
7- Creating mew Camp "cp_obs" in "Conta_pagar" is type Character Varying[100];  Fase Status: Not Aplication

8- Deleting Table "movi_caixa" however in programing its released automatically in Table "Conta_pagar"; Fase Status: Not Aplication

9- Creating new Field in Table "Funcionario" with "forn_status" of type Boolean; Fase Status: Not Aplication

10- Creating new Conection in Table "Producao" of "Funcionario" and Creating FK func_codigo in "Producao"; Fase Status: Not Aplication

11- Creating new Table "faixa_preco" and conecting with Table "servico", view detals in create script or Dezing Data Base; Fase Status: Not Aplication

12- Creating new Table "forma_pagamento" and conecting with Table "orcamento" and "pedido", view detals in create script or Dezing Data Base; Fase Status: Not Aplication
--------------------------------------------------------------------------------------------------------------
Ajusting (2019/06/18): Status: Not Aplication
Ajusting in Table "Conta_Pagar", atribute "cp_vlorp" -> "cp_valorp"
--------------------------------------------------------------------------------------------------------------
Ajusting (2019/06/22): Status: Not Aplication
Exclusing Coluns "pe_formaPago" and "orc_fomaPago" in Table "Pedido" and "Orcamento"
--------------------------------------------------------------------------------------------------------------
Ajusting (2019/07/01): Status: Not Aplication
Add new colum in Producao: "ps_sequence" type Integer
Add new colum in Producao: "func_codigo" type Integer
--------------------------------------------------------------------------------------------------------------
Ajusting (2019/07/04): Status: Not Aplication
Alter type prod_status for Integer
Creating colum func_codigo and your conection
--------------------------------------------------------------------------------------------------------------
Ajusting (2019/07/17): Status: Not Aplication
Alter CharacterVarying for cheque_numero 25 -> 40
Alter Type nAgencia for CharacterVarying size 15
--------------------------------------------------------------------------------------------------------------
Ajusting (2019/07/18): Status: Not Aplication
Add conection Caixa -- Pedido and constraint FK caixa_codigo in Pedido
*/

/**
 * Programer
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 川波
 * @author 水川
 * @author 巴御前
 * @author アナスタシア
 * @author 早川　茜
 * @author 高村　結衣
 * @author 里川　麗奈
 * @author 川波　愁子
 * @author 水川　鈴奈
 * @author 嶌田　治奈
 * @author 小枩　夏輝
 * @author 海女
 * @author 御子
 * @author 稲荷
 * @author 天野
 * @author 紅葉
 * @author 速吸
 * @author 神威
 * @author 橋立
 * @author 阿賀野
 * @author 矢矧
 * @author 長良
 * @author 阿武隈
 * @author 長門
 * @author 大和
 * @author 鳳翔
 * @author 利根
 * @author 夕張
 * @author もも
 * @author 林道
 * @author 香取 
 * @author 鹿島
 * @author 天城
 * @author 里穂
 * @author 津穂
 * @author 七海
 * @author 実里
 * @author 京介
 * @author 吉広
 * @author 光秀
 * @author 川村
 * @author 磐手
 * @author イントレピッド
 * @author ウィリアム
 * @author アルトリア
 * @author ミシェル
 * @author レア
 * @author レレイナ
 * @author レオナ
 * @author 明石
 * @author 川内
 * @author 神通
 * @author 那珂
 * 
 * Tester
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 川波
 * @author 水川
 * @author アナスタシア
 * @author 早川　茜
 * @author 高村　結衣
 * @author 里川　麗奈
 * @author 伊吹
 * @author 伊勢
 * @author 日向
 * @author 吹雪
 * @author 白雪
 * @author 東雲
 * @author 曙
 * @author 漣
 * @author 能代
 * @author 飛龍
 * @author 蒼龍
 * @author 海雪
 * @author 海春
 * @author 海星
 * @author 海流
 * @author 橿原
 * @author 香椎
 * @author 弐条
 * @author 七草
 * @author 雲龍
 * @author 天城
 * @author 志穂
 * @author 壱条
 * @author 織田
 * @author 真田
 * @author 服部
 * @author 彩斗
 * @author 高広
 * @author 八条
 * @author 伊弉冉
 * @author 伊弉諾
 * @author 瀬戸雪
 * @author 島雪
 * @author 山雪
 * @author 明石
 */
public class Principal extends javax.swing.JFrame {

    private Funcionario funcL;
    private Empresa emp;
    private final SystemControl sc = new SystemControl();
    private final java.awt.Frame log;
    private ClassHome ch;
    private Clip clip;
    private mensagens m = new mensagens();
    
    public Principal(java.awt.Frame parent, boolean modal, Funcionario func, Empresa emp) {
        //super(parent, modal);
        initComponents();
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        setSize(screenSize);
        setLocationRelativeTo(null);
        funcL = func;
        log = parent;
        
        loadQtdProducao();
//        Configuracao.setVisible(false);
        StatusCaixa();
        this.emp = emp;
        InitSystem();
        EmpresaSystem();
        
        ClassHome.configuraModel(jTable1);
        ReadOnlyTableModel model = (ReadOnlyTableModel) jTable1.getModel();
        carregaTabela();
        
        //ここで最初のリロード、残りはそれぞれの処理を終えた後。
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        relogio1 = new br.com.marciorl.beans.Relogio();
        calendario1 = new br.com.marciorl.beans.Calendario();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jlFoto = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jlLogo = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelqtdnEntregue = new javax.swing.JLabel();
        labelservicoFazer = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Cadastro = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        cadEstado = new javax.swing.JMenuItem();
        cadCidade = new javax.swing.JMenuItem();
        cadCliente = new javax.swing.JMenuItem();
        cadFuncionario = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        Servico = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        Consulta = new javax.swing.JMenu();
        cadEstado1 = new javax.swing.JMenuItem();
        cadCidade1 = new javax.swing.JMenuItem();
        cadCliente1 = new javax.swing.JMenuItem();
        cadFuncionario1 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem37 = new javax.swing.JMenuItem();
        jMenuItem38 = new javax.swing.JMenuItem();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenuItem40 = new javax.swing.JMenuItem();
        jMenuItem41 = new javax.swing.JMenuItem();
        jMenuItem42 = new javax.swing.JMenuItem();
        jMenuItem43 = new javax.swing.JMenuItem();
        Relatorio = new javax.swing.JMenu();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenuItem44 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem45 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem33 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Home");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/AbrirCaixa323.jpg"))); // NOI18N
        btn1.setToolTipText("Abrir Caixa");
        btn1.setName("btn1"); // NOI18N
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/MovimentarCaixa2 32.jpg"))); // NOI18N
        btn2.setToolTipText("Retirar dinheiro");
        btn2.setName("btn1"); // NOI18N
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/MovimentarCaixa32.jpg"))); // NOI18N
        btn3.setToolTipText("Fechar Caixa");
        btn3.setName("btn1"); // NOI18N
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Folha32.jpg"))); // NOI18N
        btn4.setToolTipText("Ajuste Folha");
        btn4.setName("btn1"); // NOI18N
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btn5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Produto32.jpg"))); // NOI18N
        btn5.setToolTipText("Ajuste Produto");
        btn5.setName("btn1"); // NOI18N
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Orcamento.jpg"))); // NOI18N
        btn6.setToolTipText("Orçamento");
        btn6.setName("btn1"); // NOI18N
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        btn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Pedido32.jpg"))); // NOI18N
        btn7.setToolTipText("Pedido");
        btn7.setName("btn1"); // NOI18N
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });

        btn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Novo Pessoa.png"))); // NOI18N
        btn8.setToolTipText("Cliente");
        btn8.setName("btn1"); // NOI18N
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });

        btn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Funcionario32.jpg"))); // NOI18N
        btn9.setToolTipText("Funcionário");
        btn9.setName("btn1"); // NOI18N
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });

        btn10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/PagarContas32.jpg"))); // NOI18N
        btn10.setToolTipText("Pagar Conta");
        btn10.setName("btn1"); // NOI18N
        btn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn10ActionPerformed(evt);
            }
        });

        btn11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Sair.png"))); // NOI18N
        btn11.setToolTipText("Log Out");
        btn11.setName("btn1"); // NOI18N
        btn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn11ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Caixa Status: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(122, 122, 122)
                .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn11, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(btn2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(btn6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        relogio1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        calendario1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Usuario:");

        lbUser.setBackground(new java.awt.Color(255, 255, 255));
        lbUser.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbUser.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );

        jComboBox1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Contas a Pagar", "Contas a Receber" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Quantidade de Serviço a fazer:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Quantidade de Serviços prontos não entregue:");

        labelqtdnEntregue.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelqtdnEntregue.setText(" ");

        labelservicoFazer.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelservicoFazer.setText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(calendario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(165, 165, 165)
                                .addComponent(relogio1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelservicoFazer, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelqtdnEntregue, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(lbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(labelservicoFazer))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(labelqtdnEntregue))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(relogio1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(calendario1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        Cadastro.setText("Cadastro");

        jMenuItem1.setText("Empresa");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        Cadastro.add(jMenuItem1);

        cadEstado.setText("Estado");
        cadEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadEstadoActionPerformed(evt);
            }
        });
        Cadastro.add(cadEstado);

        cadCidade.setText("Cidade");
        cadCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadCidadeActionPerformed(evt);
            }
        });
        Cadastro.add(cadCidade);

        cadCliente.setText("Cliente");
        cadCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadClienteActionPerformed(evt);
            }
        });
        Cadastro.add(cadCliente);

        cadFuncionario.setText("Funcionario");
        cadFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadFuncionarioActionPerformed(evt);
            }
        });
        Cadastro.add(cadFuncionario);

        jMenuItem6.setText("Fornecedor");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        Cadastro.add(jMenuItem6);

        jMenuItem4.setText("Serviço");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        Cadastro.add(jMenuItem4);

        jMenuItem5.setText("Detalhe do Serviço");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        Cadastro.add(jMenuItem5);

        jMenuItem2.setText("Produto");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        Cadastro.add(jMenuItem2);

        jMenuItem3.setText("Folha");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        Cadastro.add(jMenuItem3);

        jMenuItem15.setText("Tipo de Conta");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        Cadastro.add(jMenuItem15);

        jMenuBar1.add(Cadastro);

        Servico.setText("Serviços");

        jMenu1.setText("Caixa");

        jMenuItem16.setText("Abrir Caixa");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem16);

        jMenuItem30.setText("Abrir Caixa Banco");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem30);

        jMenuItem24.setText("Retirar Caixa");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem24);

        jMenuItem31.setText("Ajustar Caixa Banco");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem31);

        jMenuItem25.setText("Fechar Caixa");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem25);

        Servico.add(jMenu1);

        jMenu3.setText("Despesa");

        jMenuItem11.setText("Lançar Compra");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem11);

        jMenuItem28.setText("Lançar Despesa");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem28);

        jMenuItem27.setText("Pagar Conta");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem27);

        Servico.add(jMenu3);

        jMenu4.setText("Movimentar Estoque");

        jMenuItem8.setText("Atualizar Estoque Folha");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        jMenuItem7.setText("Atualizar Estoque Produto");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        Servico.add(jMenu4);

        jMenu2.setText("Servicos");

        jMenuItem9.setText("Orçamento");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem23.setText("Pedido");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem23);

        jMenuItem12.setText("Produção");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        Servico.add(jMenu2);

        jMenu5.setText("Receber");

        jMenuItem26.setText("Receber Conta");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem26);

        jMenuItem10.setText("Compensar Cheque");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem10);

        Servico.add(jMenu5);

        jMenuBar1.add(Servico);

        Consulta.setText("Consulta");

        cadEstado1.setText("Estado");
        cadEstado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadEstado1ActionPerformed(evt);
            }
        });
        Consulta.add(cadEstado1);

        cadCidade1.setText("Cidade");
        cadCidade1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadCidade1ActionPerformed(evt);
            }
        });
        Consulta.add(cadCidade1);

        cadCliente1.setText("Cliente");
        cadCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadCliente1ActionPerformed(evt);
            }
        });
        Consulta.add(cadCliente1);

        cadFuncionario1.setText("Funcionario");
        cadFuncionario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadFuncionario1ActionPerformed(evt);
            }
        });
        Consulta.add(cadFuncionario1);

        jMenuItem21.setText("Fornecedor");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        Consulta.add(jMenuItem21);

        jMenuItem19.setText("Serviço");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        Consulta.add(jMenuItem19);

        jMenuItem20.setText("Detalhe do Serviço");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        Consulta.add(jMenuItem20);

        jMenuItem17.setText("Produto");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        Consulta.add(jMenuItem17);

        jMenuItem18.setText("Folha");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        Consulta.add(jMenuItem18);

        jMenuItem22.setText("Tipo de Conta");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        Consulta.add(jMenuItem22);

        jMenuItem37.setText("Ajuste Estoque Folha");
        jMenuItem37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem37ActionPerformed(evt);
            }
        });
        Consulta.add(jMenuItem37);

        jMenuItem38.setText("Ajuste Estoque Produto");
        jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem38ActionPerformed(evt);
            }
        });
        Consulta.add(jMenuItem38);

        jMenuItem39.setText("Caixa Retirada");
        jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem39ActionPerformed(evt);
            }
        });
        Consulta.add(jMenuItem39);

        jMenuItem40.setText("Cheque");
        jMenuItem40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem40ActionPerformed(evt);
            }
        });
        Consulta.add(jMenuItem40);

        jMenuItem41.setText("Compra");
        jMenuItem41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem41ActionPerformed(evt);
            }
        });
        Consulta.add(jMenuItem41);

        jMenuItem42.setText("Orçamento");
        jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem42ActionPerformed(evt);
            }
        });
        Consulta.add(jMenuItem42);

        jMenuItem43.setText("Pedido");
        jMenuItem43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem43ActionPerformed(evt);
            }
        });
        Consulta.add(jMenuItem43);

        jMenuBar1.add(Consulta);

        Relatorio.setText("Relatorio");

        jMenuItem32.setText("Ajuste Folha");
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        Relatorio.add(jMenuItem32);

        jMenuItem36.setText("Ajuste Produto");
        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        Relatorio.add(jMenuItem36);

        jMenuItem34.setText("Orçamento");
        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        Relatorio.add(jMenuItem34);

        jMenuItem35.setText("Pedido");
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        Relatorio.add(jMenuItem35);

        jMenuItem44.setText("Nota Não Fiscal");
        jMenuItem44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem44ActionPerformed(evt);
            }
        });
        Relatorio.add(jMenuItem44);

        jMenuBar1.add(Relatorio);

        jMenu7.setText("Sobre");
        jMenu7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu7ActionPerformed(evt);
            }
        });

        jMenuItem29.setText("Sobre");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem29);

        jMenuBar1.add(jMenu7);

        jMenu6.setText("Sair");

        jMenuItem45.setText("Trocar Usuario");
        jMenuItem45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem45ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem45);

        jMenuItem13.setText("Log out");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem13);

        jMenuItem14.setText("Fechar o Sistema");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem14);

        jMenuBar1.add(jMenu6);

        jMenu8.setText("Help");

        jMenuItem33.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem33.setText("Ajuda");
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem33);

        jMenuBar1.add(jMenu8);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cadEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadEstadoActionPerformed
        CadastroEstado frm = new CadastroEstado(this, true);
        frm.setTitle("Cadastro Estado");
        frm.setVisible(true);
    }//GEN-LAST:event_cadEstadoActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
//        clip.stop();
        ShowImputPassword sip = new ShowImputPassword(this, true, "Informe a Senha:");
        sip.setTitle("Informe");
        sip.setVisible(true);
        if(sip.getSenha().equals(funcL.getSenha()))
        {
            sip.dispose();
            if(new Caixa().VerificaCaixaAberto())
            {
                MovCaixaFechar frm = new MovCaixaFechar(this, true, funcL);
                frm.setTitle("Fechar Caixa");
                frm.setVisible(true);
            }
            log.setVisible(true);
            dispose();
        }
        else
        {
            m.InformationMessage("Senha incorreta!", "Atenção");
        }
        sip.dispose();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        ShowImputPassword sip = new ShowImputPassword(this, true, "Informe a Senha:");
        sip.setTitle("Informe");
        sip.setVisible(true);
        if(sip.getSenha().equals(funcL.getSenha()))
        {
            sip.dispose();
            if(new Caixa().VerificaCaixaAberto())
            {
                MovCaixaFechar frm = new MovCaixaFechar(this, true, funcL);
                frm.setTitle("Fechar Caixa");
                frm.setVisible(true);
            }
            System.exit(0);
        }
        else
        {
            m.InformationMessage("Senha incorreta!", "Atenção");
        }
        sip.dispose();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        MovCaixaAbertura frmCA  = new MovCaixaAbertura(this, true, funcL);
        frmCA.setTitle("Abrir Caixa");
        frmCA.setVisible(true);
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        MovCaixaRetirada frm = new MovCaixaRetirada(this, true, funcL);
        frm.setTitle("Retirar Dinheiro");
        
        frm.setVisible(true);
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        MovCaixaFechar frm = new MovCaixaFechar(this, true, funcL);
        frm.setTitle("Fechar Caixa");
        frm.setVisible(true);
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        MovAjusteFolha frm = new MovAjusteFolha(this, true, funcL);
        frm.setTitle("Ajustar Estoque Folha");
        frm.setVisible(true);
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        MovAjusteProduto frm = new MovAjusteProduto(this, true, funcL);
        frm.setTitle("Ajustar Estoque Produto");
        frm.setVisible(true);
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        try {
            MovOrcamneto frm = new MovOrcamneto(this, true, funcL);
            frm.setTitle("Orçamento");
            frm.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        try {
            MovPedido frm = new MovPedido(this, true, funcL);
            frm.setTitle("Pedido");
            frm.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
       CadastroCliente frm = new CadastroCliente(this, true);
       frm.setTitle("Cadastro Cliente");
       frm.setVisible(true);
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        CadastroFuncionario frm = new CadastroFuncionario(this, true, false);
        frm.setTitle("Cadastro Funcionario");
        frm.setVisible(true);
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn10ActionPerformed
        MovPagarContas frm = new MovPagarContas(this, true, funcL);
        frm.setTitle("Pagar Conta");
        frm.setVisible(true);
    }//GEN-LAST:event_btn10ActionPerformed

    private void btn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn11ActionPerformed
        ShowImputPassword sip = new ShowImputPassword(this, true, "Informe a Senha:");
        sip.setTitle("Informe");
        sip.setVisible(true);
        if(sip.getSenha().equals(funcL.getSenha()))
        {
            sip.dispose();
            if(new Caixa().VerificaCaixaAberto())
            {
                MovCaixaFechar frm = new MovCaixaFechar(this, true, funcL);
                frm.setTitle("Fechar Caixa");
                frm.setVisible(true);
            }
            log.setVisible(true);
            dispose();
        }
        else
        {
            m.InformationMessage("Senha incorreta!", "Atenção");
        }
        sip.dispose();
    }//GEN-LAST:event_btn11ActionPerformed

    private void cadFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadFuncionarioActionPerformed
        CadastroFuncionario frm = new CadastroFuncionario(this, true, false);
        frm.setTitle("Cadastro Funcionario");
        frm.setVisible(true);
    }//GEN-LAST:event_cadFuncionarioActionPerformed

    private void cadCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadCidadeActionPerformed
        CadastroCidade frm = new CadastroCidade(this, true);
        frm.setTitle("Cadastro Cidade");
        frm.setVisible(true);
    }//GEN-LAST:event_cadCidadeActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        CadastroEmpresa frm = new CadastroEmpresa(this, true, false);
        frm.setTitle("Cadastro Empresa");
        frm.setVisible(true);
        emp = new Empresa().buscarEmpresa();
        EmpresaSystem();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void cadClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadClienteActionPerformed
       CadastroCliente frm = new CadastroCliente(this, true);
       frm.setTitle("Cadastro Cliente");
       frm.setVisible(true);
    }//GEN-LAST:event_cadClienteActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       CadastroProduto frm = new CadastroProduto(this, true);
       frm.setTitle("Cadastro Produto");
       frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       CadastroFolha frm = new CadastroFolha(this, true);
       frm.setTitle("Cadastro Folha");
       frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
       CadastroServico frm = new CadastroServico(this, true);
       frm.setTitle("Cadastro Serviço");
       frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
       CadastroDetalheServico frm = new CadastroDetalheServico(this, true);
       frm.setTitle("Cadastro Detalhe Serviço");
       frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
       CadastroFornecedor frm = new CadastroFornecedor(this, true);
       frm.setTitle("Cadastro Fornecedor");
       frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
       CadastroTipoConta frm = new CadastroTipoConta(this, true);
       frm.setTitle("Cadastro Tipo de Conta");
       frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void cadFuncionario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadFuncionario1ActionPerformed
        ConsultaPadrao consFuncionario = new ConsultaPadrao(null, true);
        String[] vet = new String[2];
        vet[0] = "Nome";
        vet[1] = "CPF";
        consFuncionario.configuraOpcoes(vet, 2, 1, "Funcionario", false);
        consFuncionario.verificaconsulta(false);
        consFuncionario.setVisible(true);
    }//GEN-LAST:event_cadFuncionario1ActionPerformed

    private void cadCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadCliente1ActionPerformed
        ConsultaPadrao consFuncionario = new ConsultaPadrao(null, true);
        String[] vet = new String[3];
        vet[0] = "Nome";
        vet[1] = "CPF";
        vet[2] = "CNPJ";
        consFuncionario.configuraOpcoes(vet, 3, 0, "Cliente", false);
        consFuncionario.verificaconsulta(false);
        consFuncionario.setVisible(true);
    }//GEN-LAST:event_cadCliente1ActionPerformed

    private void cadCidade1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadCidade1ActionPerformed
        ConsultaPadrao consEstado = new ConsultaPadrao(null, true);
        String[] vet = new String[2];
        vet[0] = "UF";
        vet[1] = "Cidade";
        consEstado.configuraOpcoes(vet, 2, 1, "Cidade", false);
        consEstado.verificaconsulta(false);
        consEstado.setVisible(true);
    }//GEN-LAST:event_cadCidade1ActionPerformed

    private void cadEstado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadEstado1ActionPerformed
        ConsultaPadrao consEstado = new ConsultaPadrao(null, true);
        String[] vet = new String[1];
        vet[0] = "Sigla do Estado";
        consEstado.configuraOpcoes(vet, 1, 0, "Estado", false);
        consEstado.verificaconsulta(false);
        consEstado.setVisible(true);
    }//GEN-LAST:event_cadEstado1ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        ConsultaPadrao consProduto = new ConsultaPadrao(null, true);
        String[] vet = new String[1];
        vet[0] = "Nome";
        consProduto.configuraOpcoes(vet, 1, 0, "Produto", false);
        consProduto.verificaconsulta(false);
        consProduto.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        ConsultaPadrao consFolha = new ConsultaPadrao(null, true);
        String[] vet = new String[2];
        vet[0] = "Tamanho";
        vet[1] = "Descrição";
        consFolha.configuraOpcoes(vet, 2, 0, "Folha", false);
        consFolha.verificaconsulta(false);
        consFolha.setVisible(true);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        ConsultaPadrao consDS = new ConsultaPadrao(null, true);
        String[] vet = new String[1];
        vet[0] = "Nome";
        consDS.configuraOpcoes(vet, 1, 0, "Servico", false);
        consDS.verificaconsulta(false);
        consDS.setVisible(true);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        ConsultaPadrao consDS = new ConsultaPadrao(null, true);
        String[] vet = new String[1];
        vet[0] = "Detalhe";
        consDS.configuraOpcoes(vet, 1, 0, "DetalheServico", false);
        consDS.verificaconsulta(false);
        consDS.setVisible(true);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        ConsultaPadrao consFornecedor = new ConsultaPadrao(null, true);
        String[] vet = new String[1];
        vet[0] = "Nome";
        consFornecedor.configuraOpcoes(vet, 1, 0, "Fornecedor", false);
        consFornecedor.verificaconsulta(false);
        consFornecedor.setVisible(true);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        ConsultaPadrao consTC = new ConsultaPadrao(null, true);
        String[] vet = new String[1];
        vet[0] = "Tipo";
        consTC.configuraOpcoes(vet, 1, 0, "TipoConta", false);
        consTC.verificaconsulta(false);
        consTC.setVisible(true);
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        MovCaixaAbertura frmCA  = new MovCaixaAbertura(this, true, funcL);
        frmCA.setTitle("Abrir Caixa");
        frmCA.setVisible(true);
        StatusCaixa();
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        MovCaixaRetirada frm = new MovCaixaRetirada(this, true, funcL);
        frm.setTitle("Retirar Dinheiro");
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        MovCaixaFechar frm = new MovCaixaFechar(this, true, funcL);
        frm.setTitle("Fecher Caixa");
        frm.setVisible(true);
        StatusCaixa();
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        MovLancarCompras frm = new MovLancarCompras(this, true, funcL);
        frm.setTitle("Lançar Compras");
        frm.setVisible(true);
        carregaTabela(); 
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        try {
            MovDespesa frm = new MovDespesa(this, true, funcL);
            frm.setTitle("Lançar Despesa");
            frm.setVisible(true);
            carregaTabela(); 
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        MovPagarContas frm = new MovPagarContas(this, true, funcL);
        frm.setTitle("Pagar Conta");
        frm.setVisible(true);
        carregaTabela(); 
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        MovAjusteFolha frm = new MovAjusteFolha(this, true, funcL);
        frm.setTitle("Ajustar Estoque Folha");
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        MovAjusteProduto frm = new MovAjusteProduto(this, true, funcL);
        frm.setTitle("Ajustar Estoque Produto");
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        try {
            MovOrcamneto frm = new MovOrcamneto(this, true, funcL);
            frm.setTitle("Orçamento");
            frm.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        try {
            MovPedido frm = new MovPedido(this, true, funcL);
            frm.setTitle("Pedido");
            frm.setVisible(true);
            carregaTabela(); 
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        MovProducao frm = new MovProducao(this, true, funcL);
        frm.setTitle("Produção");
        frm.setVisible(true);
        loadQtdProducao();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        try {
            MovReceberContas frm = new MovReceberContas(this, true);
            frm.setTitle("Receber Conta");
            frm.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        MovCheque frm = new MovCheque(this, true, 0, false);
        frm.setTitle("Cheque");
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenu7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu7ActionPerformed
        Sobre s = new Sobre(this, true);
        s.setTitle("Sobre");
        s.setVisible(true);
    }//GEN-LAST:event_jMenu7ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        MovCaixaBanco frm = new MovCaixaBanco(this, true, funcL);
        frm.setTitle("Abrir Caixa Conta Banco"); 
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        MovAjustarCaixaBanco frm = new MovAjustarCaixaBanco(this, true, funcL);
        frm.setTitle("Ajustar Caixa Banco"); 
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        sc.limparTabela(jTable1);
        carregaTabela();     
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
        if(!sc.help("SGG/SGGOnlineHelp/principal.html", "C:/"))
        {
            sc.help("SGG/SGGOnlineHelp/principal.html", "D:/");
        }
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
        RelPadraoPed_Orc frm = new RelPadraoPed_Orc(this, false);
        String[] vet = new String[2];
        vet[0] = "Número";
        vet[1] = "Periodo e Nome";
        frm.configuraOpcoes(vet, 2, 0, "Orçamento");
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem34ActionPerformed

    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
        RelPadraoPed_Orc frm = new RelPadraoPed_Orc(this, false);
        String[] vet = new String[3];
        vet[0] = "Número";
        vet[1] = "Periodo e Nome";
        vet[2] = "Do Dia";
        frm.configuraOpcoes(vet, 3, 0, "Pedido");
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
        RelPadrao frm = new RelPadrao(this, false);
        String[] vet = new String[2];
        vet[0] = "Periodo";
        vet[1] = "Data";
        frm.configuraOpcoes(vet, 2, 0, "AjusteEstoqueFolha");
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
        RelPadrao frm = new RelPadrao(this, false);
        String[] vet = new String[2];
        vet[0] = "Periodo";
        vet[1] = "Data";
        frm.configuraOpcoes(vet, 2, 0, "AjusteEstoqueProduto");
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem36ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        m.InformationMessage("Ola "+funcL.getNome()+".\n Existe no total "+new Producao().buscarQTD()+" pedido(s) que estão no modo de Aguardando, Produção, Pausado ou Não Entrgue." , "Informação");
    }//GEN-LAST:event_formWindowOpened

    private void jMenuItem37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem37ActionPerformed
        ConsultaPadrao frm = new ConsultaPadrao(null, true);
        String[] vet = new String[3];
        vet[0] = "Funcionario";
        vet[1] = "Data";
        vet[2] = "Periodo";
        frm.configuraOpcoes(vet, 3, 0, "Ajuste Folha", false);
        frm.verificaconsulta(false);
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem37ActionPerformed

    private void jMenuItem38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem38ActionPerformed
        ConsultaPadrao frm = new ConsultaPadrao(null, true);
        String[] vet = new String[3];
        vet[0] = "Funcionario";
        vet[1] = "Data";
        vet[2] = "Periodo";
        frm.configuraOpcoes(vet, 3, 0, "Ajuste Produto", false);
        frm.verificaconsulta(false);
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem38ActionPerformed

    private void jMenuItem39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem39ActionPerformed
        ConsultaPadrao frm = new ConsultaPadrao(null, true);
        String[] vet = new String[4];
        vet[0] = "Caixa Atual";
        vet[1] = "Periodo";
        vet[2] = "Data";
        vet[3] = "Caixa";
        frm.configuraOpcoes(vet, 3, 0, "Caixa Retirada", false);
        frm.verificaconsulta(false);
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem39ActionPerformed

    private void jMenuItem40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem40ActionPerformed
        ConsultaPadrao consCheque = new ConsultaPadrao(null, true);
        String[] vet = new String[3];
        vet[0] = "Dono";
        vet[1] = "Data";
        vet[2] = "Tudo";
        consCheque.configuraOpcoes(vet, 2, 0, "Cheque", false);
        consCheque.verificaconsulta(false);
        consCheque.setVisible(true);
    }//GEN-LAST:event_jMenuItem40ActionPerformed

    private void jMenuItem41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem41ActionPerformed
        ConsultaMov consCompra = new ConsultaMov(null, true);
        String[] vet = new String[3];
        vet[0] = "Tudo";
        vet[1] = "Data";
        vet[2] = "Periodo";
        consCompra.configuraOpcoes(vet, 3, 0, "CCompra", false);
        consCompra.verificaconsulta(false);
        consCompra.setVisible(true);
    }//GEN-LAST:event_jMenuItem41ActionPerformed

    private void jMenuItem42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem42ActionPerformed
        ConsultaMov consOrcamento = new ConsultaMov(null, true);
        String[] vet = new String[4];
        vet[0] = "Tudo";
        vet[1] = "Data";
        vet[2] = "Periodo";
        vet[3] = "Numero";
        consOrcamento.configuraOpcoes(vet, 4, 0, "Orçamento", false);
        consOrcamento.verificaconsulta(false);
        consOrcamento.setVisible(true);
    }//GEN-LAST:event_jMenuItem42ActionPerformed

    private void jMenuItem43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem43ActionPerformed
        ConsultaMov consPedido = new ConsultaMov(null, true);
        String[] vet = new String[5];
        vet[0] = "Tudo";
        vet[1] = "Data";
        vet[2] = "Periodo";
        vet[3] = "Numero";
        vet[4] = "Nome";
        consPedido.configuraOpcoes(vet, 5, 0, "Pedido", false);
        consPedido.verificaconsulta(false);
        consPedido.setVisible(true);
    }//GEN-LAST:event_jMenuItem43ActionPerformed

    private void jMenuItem44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem44ActionPerformed
        RelPadrao frm = new RelPadrao(this, false);
        String[] vet = new String[1];
        vet[0] = "Numero Pedido";
        frm.configuraOpcoes(vet, 1, 0, "Nota NF");
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem44ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        Sobre frm = new Sobre(this, false);
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem45ActionPerformed
        String Senha, User;
        ShowImputPassword sip = new ShowImputPassword(this, true, "Informe a Senha:");
        sip.setTitle("Informe");
        sip.setVisible(true);
        if(sip.getSenha().equals(funcL.getSenha()))
        {
            sip.dispose();
            if(new Caixa().VerificaCaixaAberto())
            {
                MovCaixaFechar frm = new MovCaixaFechar(this, true, funcL);
                frm.setTitle("Fechar Caixa");
                frm.setVisible(true);
                User = JOptionPane.showInputDialog(null, "Informe o Usuário:", "Informe", JOptionPane.INFORMATION_MESSAGE);
                ShowImputPassword sip2 = new ShowImputPassword(this, true, "Informe a Senha:");
                sip2.setTitle("Informe");
                sip2.setVisible(true);
                Senha = sip2.getSenha();
                Funcionario temp  = new Funcionario().logar(User, Senha);
                if(temp != null)
                {
                    funcL = temp;
                    InitSystem();
                    m.InformationMessage("Funcionario trocado com sucesso!", "Informação");
                }
                else
                {
                    m.WarmingMessage("Usuario ou Senha Incorreto", "Atenção");
                }
                sip2.dispose();
            }
        }
        else
        {
            m.InformationMessage("Senha incorreta!", "Atenção");
        }
        sip.dispose();
        
        
    }//GEN-LAST:event_jMenuItem45ActionPerformed

    private void AccessLevel(int soma)
    {  
        if(soma == 64)
        {
            Cadastro.setVisible(true);
            Consulta.setVisible(true);
            Servico.setVisible(true);
            Relatorio.setVisible(true);
//            Configuracao.setVisible(true);
        }
        else
        {
            if(soma >= 16)
            {
                Servico.setVisible(true);
                soma -= 16;
            }
            if(soma >= 8)
            {
                Relatorio.setVisible(true);
                soma -= 8;
            }
            if(soma >= 4)
            {
                Consulta.setVisible(true);
                soma -= 4;
            }
            if(soma >= 2)
            {
                Cadastro.setVisible(true);
                soma = 0;
            }
        }
    }
    
    private void carregaTabela()
    {
        sc.limparTabela(jTable1);
        Date data;
        String mes = "00", temp;
        int x = 0;
        TipoConta tempTC = new TipoConta(), temp2;
        switch(jComboBox1.getSelectedIndex())
        {
            case 0:
                ResultSet rs = ContaPagar.buscarDadosHomeContaP();
                ReadOnlyTableModel model = (ReadOnlyTableModel) jTable1.getModel();
                try 
                {//"Nome", "Valor", "Data Vencimento"
                    while(rs.next())
                    {
                        data = rs.getDate(3);
                        
                        if(!mes.equals(sc.getMes(data)))
                        {
                            if(x != 0)
                            {
                                model.addRow(new Object[]{"", "  ", ""});
                            }
                            mes = sc.getMes(data);
                            model.addRow(new Object[]{buscaMes(mes), " ---------------- ", sc.getAno(data)});
                            x = 1;
                        }
                        temp2 = tempTC.buscarCodigo(rs.getInt(1));
                        if(temp2 != null)
                        {
                            temp = temp2.getTipo();
                        }
                        else
                        {
                            temp = "Compra";
                        }
                        model.addRow(new Object[]{//tc.tc_codigo, cp_valorc, cp_datavencimento
                        temp ,//Nome Clietne
                        rs.getDouble(2),
                        rs.getDate(3)
                        });
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case 1:
                ResultSet rst = ContaReceber.buscarDadosHome();
                ReadOnlyTableModel model2 = (ReadOnlyTableModel) jTable1.getModel();
                try 
                {//"Nome", "Valor", "Data Vencimento"
                    while(rst.next())
                    {
                        model2.addRow(new Object[]{
                        rst.getString(1),//Nome Clietne
                        rst.getDouble(2),
                        rst.getDate(3)
                        });
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }
    
    private String buscaMes(String mes)
    {
        switch(mes)
        {
            case "01": return "Janeiro"; 
            case "02": return "Fevereiro"; 
            case "03": return "Março"; 
            case "04": return "Abriu"; 
            case "05": return "Maio"; 
            case "06": return "Julho"; 
            case "07": return "Junho"; 
            case "08": return "Agosto"; 
            case "09": return "Setembro"; 
            case "10": return "Outubro"; 
            case "11": return "Novembro"; 
            default: return "Dezenmbro"; 
        }
    }
    
    private void InitSystem()
    {
        Cadastro.setVisible(false);
        Consulta.setVisible(false);
        Servico.setVisible(false);
        Relatorio.setVisible(false);
        AccessLevel(funcL.getNivel());
        lbUser.setText(funcL.getNome());
        ImageIcon icon = new ImageIcon(funcL.getCaminho());
        jlFoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(jlFoto.getWidth(), jlFoto.getHeight(), Image.SCALE_DEFAULT))); // Utilizado para recuperar imagem.
    }
    
    private void EmpresaSystem()
    {
        ImageIcon icon2 = new ImageIcon(emp.getCaminho());
        jlLogo.setIcon(new ImageIcon(icon2.getImage().getScaledInstance(jlLogo.getWidth(), jlLogo.getHeight(), Image.SCALE_DEFAULT))); // Utilizado para recuperar imagem.
    }
    
    private void loadQtdProducao()
    {
        //new Producao().buscarQTD()[
        int qtd = new Producao().buscarQTD();
        labelservicoFazer.setText(""+(qtd == 0 ? 0 : qtd-1));
        labelqtdnEntregue.setText(""+new Producao().buscarQTD2());
    }
    
    private void StatusCaixa()
    {
        if(new Caixa().VerificaCaixaAberto())
        {
            jLabel4.setText("Caixa Status: Aberto");
        }
        else
        {
            jLabel4.setText("Caixa Status: Fechado");
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Cadastro;
    private javax.swing.JMenu Consulta;
    private javax.swing.JMenu Relatorio;
    private javax.swing.JMenu Servico;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JMenuItem cadCidade;
    private javax.swing.JMenuItem cadCidade1;
    private javax.swing.JMenuItem cadCliente;
    private javax.swing.JMenuItem cadCliente1;
    private javax.swing.JMenuItem cadEstado;
    private javax.swing.JMenuItem cadEstado1;
    private javax.swing.JMenuItem cadFuncionario;
    private javax.swing.JMenuItem cadFuncionario1;
    private br.com.marciorl.beans.Calendario calendario1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem41;
    private javax.swing.JMenuItem jMenuItem42;
    private javax.swing.JMenuItem jMenuItem43;
    private javax.swing.JMenuItem jMenuItem44;
    private javax.swing.JMenuItem jMenuItem45;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlFoto;
    private javax.swing.JLabel jlLogo;
    private javax.swing.JLabel labelqtdnEntregue;
    private javax.swing.JLabel labelservicoFazer;
    private javax.swing.JLabel lbUser;
    private br.com.marciorl.beans.Relogio relogio1;
    // End of variables declaration//GEN-END:variables
}
