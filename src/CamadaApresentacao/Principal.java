/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CamadaApresentacao;

import CamadaLogica.ReadOnlyTableModel;
import CamadaNegocio.Cheque;
import CamadaNegocio.Empresa;
import CamadaNegocio.Funcionario;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import util.ClassHome;

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

    private final Funcionario funcL;
    private final Empresa emp;
    private final java.awt.Frame log;
    private ClassHome ch;
    private Clip clip;
    
    public Principal(java.awt.Frame parent, boolean modal, Funcionario func, Empresa emp) {
        //super(parent, modal);
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //setSize(screenSize);
        setLocationRelativeTo(null);
        funcL = func;
        log = parent;
        lbUser.setText(funcL.getNome());
        Cadastro.setVisible(false);
        Consulta.setVisible(false);
        Servico.setVisible(false);
        Relatorio.setVisible(false);
        Configuracao.setVisible(false);
        AccessLevel(func.getNivel());
        ImageIcon icon = new ImageIcon(func.getCaminho());
        jlFoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(jlFoto.getWidth(), jlFoto.getHeight(), Image.SCALE_DEFAULT))); // Utilizado para recuperar imagem.
        this.emp = emp;
        ImageIcon icon2 = new ImageIcon(emp.getCaminho());
        jlLogo.setIcon(new ImageIcon(icon2.getImage().getScaledInstance(jlLogo.getWidth(), jlLogo.getHeight(), Image.SCALE_DEFAULT))); // Utilizado para recuperar imagem.
        
        ch.configuraModel(jTable1);
        ReadOnlyTableModel model = (ReadOnlyTableModel) jTable1.getModel();
        //ここで最初のリロード、残りはそれぞれの処理を終えた後。
       
//        try {
//            //AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("D:/SGG/Rekotyoku.wav").getAbsoluteFile());
//            //AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("D:/SGG/05 - Eight Minutes.wav").getAbsoluteFile());
//            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("D:/SGG/Oda Nobuna no Yabou OST - Kishuu.wav").getAbsoluteFile());
//            clip = AudioSystem.getClip();
//            clip.open(audioInputStream);
//            clip.start();
//            clip.loop(Clip.LOOP_CONTINUOUSLY);
//        } catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
//            System.out.println("Error with playing sound.");
//        }
        
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
        Relatorio = new javax.swing.JMenu();
        Configuracao = new javax.swing.JMenu();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Home");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/AbrirCaixa323.jpg"))); // NOI18N
        btn1.setName("btn1"); // NOI18N
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/MovimentarCaixa2 32.jpg"))); // NOI18N
        btn2.setName("btn1"); // NOI18N
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/MovimentarCaixa32.jpg"))); // NOI18N
        btn3.setName("btn1"); // NOI18N
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Folha32.jpg"))); // NOI18N
        btn4.setName("btn1"); // NOI18N
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btn5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Produto32.jpg"))); // NOI18N
        btn5.setName("btn1"); // NOI18N
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Orcamento.jpg"))); // NOI18N
        btn6.setName("btn1"); // NOI18N
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        btn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Pedido32.jpg"))); // NOI18N
        btn7.setName("btn1"); // NOI18N
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });

        btn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Novo Pessoa.png"))); // NOI18N
        btn8.setName("btn1"); // NOI18N
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });

        btn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Funcionario32.jpg"))); // NOI18N
        btn9.setName("btn1"); // NOI18N
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });

        btn10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/PagarContas32.jpg"))); // NOI18N
        btn10.setName("btn1"); // NOI18N
        btn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn10ActionPerformed(evt);
            }
        });

        btn11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Sair.png"))); // NOI18N
        btn11.setName("btn1"); // NOI18N
        btn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(167, 167, 167)
                .addComponent(btn11, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(btn1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addComponent(btn2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btn6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jComboBox1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox1FocusLost(evt);
            }
        });
        jComboBox1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox1PropertyChange(evt);
            }
        });

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
                        .addGap(165, 165, 165)
                        .addComponent(relogio1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 13, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(calendario1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(relogio1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        jMenuItem26.setText("Recebaer Conta");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem26);

        jMenuItem10.setText("Compençar Cheque");
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

        jMenuBar1.add(Consulta);

        Relatorio.setText("Relatorio");
        jMenuBar1.add(Relatorio);

        Configuracao.setText("Configuração");

        jMenuItem29.setText("Gerar Backup");
        Configuracao.add(jMenuItem29);

        jMenuBar1.add(Configuracao);

        jMenu7.setText("Sobre");
        jMenu7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu7ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu7);

        jMenu6.setText("Sair");
        jMenu6.setToolTipText("");

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

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        log.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
      System.exit(0);
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
//        clip.stop();
        log.setVisible(true);
        dispose();
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

    private void jComboBox1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1PropertyChange

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        MovCaixaAbertura frmCA  = new MovCaixaAbertura(this, true, funcL);
        frmCA.setTitle("Abrir Caixa");
        frmCA.setVisible(true);
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
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        MovLancarCompras frm = new MovLancarCompras(this, true, funcL);
        frm.setTitle("Lançar Compras");
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        try {
            MovDespesa frm = new MovDespesa(this, true, funcL);
            frm.setTitle("Lançar Despesa");
            frm.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        MovPagarContas frm = new MovPagarContas(this, true, funcL);
        frm.setTitle("Pagar Conta");
        frm.setVisible(true);
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
            frm.setTitle("Oraçamento");
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
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        MovProducao frm = new MovProducao(this, true, funcL);
        frm.setTitle("Produção");
        frm.setVisible(true);
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
        MovCheque frm = new MovCheque(this, true, 0);
        frm.setTitle("Cheque");
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenu7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu7ActionPerformed
        Sobre s = new Sobre(this, true);
        s.setTitle("Sobre");
        s.setVisible(true);
    }//GEN-LAST:event_jMenu7ActionPerformed

    private void jComboBox1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1FocusLost
        
    }//GEN-LAST:event_jComboBox1FocusLost

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        MovCaixaBanco frm = new MovCaixaBanco(this, true, funcL);
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        MovAjustarCaixaBanco frm = new MovAjustarCaixaBanco(this, true, funcL);
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem31ActionPerformed


    private void AccessLevel(int soma)
    {  
        if(soma == 64)
        {
            Cadastro.setVisible(true);
            Consulta.setVisible(true);
            Servico.setVisible(true);
            Relatorio.setVisible(true);
            Configuracao.setVisible(true);
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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Cadastro;
    private javax.swing.JMenu Configuracao;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
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
    private javax.swing.JMenuItem jMenuItem4;
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
    private javax.swing.JLabel lbUser;
    private br.com.marciorl.beans.Relogio relogio1;
    // End of variables declaration//GEN-END:variables
}
