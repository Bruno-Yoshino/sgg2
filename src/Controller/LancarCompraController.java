package Controller;

import CamadaLogica.ReadOnlyTableModel;
import CamadaNegocio.Compra;
import CamadaNegocio.Compra_Folha;
import CamadaNegocio.Compra_Produto;
import CamadaNegocio.ContaPagar;
import CamadaNegocio.Folha;
import CamadaNegocio.Fornecedor;
import CamadaNegocio.Producao_Folha;
import CamadaNegocio.Producao_Produto;
import CamadaNegocio.Produto;
import CamadaNegocio.TipoConta;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import util.Validacao;
import util.mensagens;

/**
 *
 * Moniter
 * @author 羽根川　翼
 * @author モニカ
 * Produto
 * @author 香取 
 * @author 鹿島
 * @author 橋立
 * Folha
 * @author 海女
 * @author 御子
 * @author 稲荷
 */
public class LancarCompraController 
{
    private Compra c;
    private Validacao v;
    private Folha f;
    private Produto p;
    private Fornecedor forn;
    private Compra_Produto cp = new Compra_Produto();
    private ContaPagar contP;
    private Compra_Folha cf = new Compra_Folha();
    private final mensagens m = new mensagens();

    public LancarCompraController() {
        c = new Compra();
        v = new Validacao();
        f = new Folha();
        p = new Produto();
        forn = new Fornecedor();
        contP = new ContaPagar();
    }

    public Compra getC() {
        return c;
    }

    public void setC(Compra c) {
        this.c = c;
    }

    public Folha getF() {
        return f;
    }

    public void setF(Folha f) {
        this.f = f;
    }

    public Produto getP() {
        return p;
    }

    public void setP(Produto p) {
        this.p = p;
    }

    public Fornecedor getForn() {
        return forn;
    }

    public void setForn(Fornecedor forn) {
        this.forn = forn;
    }
    
    // c.getLcp().add(new Compra_Produto(new Compra(), new Produto().buscarCodigo(v.ConverteNumeroInteiro(codigo)), v.ConverteNumeroInteiro(qtd), v.ConverteNumeroReal(preco)));
    public int validar(String codigo, String nome, String qtd, String preco, JTable tabela, boolean flag, String codigoC, int tipo)
    {//Adicionar mais um campo para ver se eh Folha ou Produto OK 追加済みです
        int task = 1; // Verifica se nao tem na tabela
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        if(codigo.equals(""))
        {
            return 3;
        }
        
        if(!flag)
        {
            if(tipo == 1 && buscarTabelaProduto(Integer.parseInt(codigoC), Integer.parseInt(codigo)) && verificarEstoqueProduto(Integer.parseInt(codigo), Integer.parseInt(qtd)))
            {
                return 5;
            }
            if(tipo == 2 && buscarTabelaFolha(Integer.parseInt(codigoC), Integer.parseInt(codigo)) && verificarEstoqueFolha(Integer.parseInt(codigo), Integer.parseInt(qtd)))
            {
                return 6;
            }
        }
        for (int i = 0; i < model.getRowCount() && task == 1; i++) 
        {
            if(String.valueOf(model.getValueAt(i, 0)).equals(codigo))
            {
                if(m.Pergunta("Este item ja esta inserido! Deseja somar com o item ja inserido?", "Confirmação") == JOptionPane.YES_OPTION)
                {
                    if(v.ConverteNumeroReal(model.getValueAt(i, 3)) != v.ConverteNumeroReal(preco))
                    {
                        if(m.Pergunta("Deseja manter o preço?", "Confirmação") == JOptionPane.NO_OPTION)
                        {
                            model.setValueAt(v.ConverteNumeroReal(model.getValueAt(i, 3)), i, 3);
                        }
                    }
                    model.setValueAt((v.ConverteNumeroInteiro(model.getValueAt(i, 2)) + v.ConverteNumeroInteiro(qtd)), i, 2);
                    model.setValueAt((v.ConverteNumeroReal(model.getValueAt(i, 3)) * v.ConverteNumeroInteiro(model.getValueAt(i, 2))), i, 4);
                    task = 2;
                }
                else
                {
                    task = 0;
                }
            }
        }
        switch(task)
        {
            case 1: 
                model.addRow(new Object[]
                {
                    codigo, 
                    nome, 
                    qtd,
                    preco,
                    v.ConverteNumeroInteiro(qtd)*v.ConverteNumeroReal(preco)
                });
                break;
            case 0:
                return 4;
            
            default:
                
        }
        return 0;
    }
    
    public boolean gravar(String codigo, String valorT, JTable tabelaP, JTable tabelaF)
    {
        boolean x = true;
        c.setCodigo(v.ConverteNumeroInteiro(codigo));
        c.setValort(v.ConverteNumeroReal(valorT));
        c.setData(Date.from(Instant.now()));
        
        ReadOnlyTableModel modelP = (ReadOnlyTableModel) tabelaP.getModel();
        ReadOnlyTableModel modelF = (ReadOnlyTableModel) tabelaF.getModel();
        
//        ArrayList<Compra_Folha> lcf = new ArrayList<>();
//        ArrayList<Compra_Produto> lcp = new ArrayList<>();
        
        if(c.gravar())
        {
            c.setCodigo(c.maxCodigo());
            for (int i = 0; i < modelP.getRowCount() && x; i++) 
            {
                x = c.gravarItem(v.ConverteNumeroInteiro(modelP.getValueAt(i, 0)), v.ConverteNumeroInteiro(modelP.getValueAt(i, 2)), v.ConverteNumeroReal(modelP.getValueAt(i, 3)), true);
//                lcp.add(new Compra_Produto(c, p.buscarCodigo(v.ConverteNumeroInteiro(modelP.getValueAt(i, 0))), v.ConverteNumeroInteiro(modelP.getValueAt(i, 2)), v.ConverteNumeroReal(modelP.getValueAt(i, 3))));
                p = p.buscarCodigo(v.ConverteNumeroInteiro(modelP.getValueAt(i, 0)));
                p.setQtd(p.getQtd()+v.ConverteNumeroInteiro(modelP.getValueAt(i, 2)));
                p.atualizarEstoque();
            }

            for (int i = 0; i < modelF.getRowCount() && x; i++) 
            {
                x = c.gravarItem(v.ConverteNumeroInteiro(modelF.getValueAt(i, 0)), v.ConverteNumeroInteiro(modelF.getValueAt(i, 2)), v.ConverteNumeroReal(modelF.getValueAt(i, 3)), false);
//                lcf.add(new Compra_Folha(c, f.buscarCodigo(v.ConverteNumeroInteiro(modelF.getValueAt(i, 0))), v.ConverteNumeroInteiro(modelF.getValueAt(i, 2)), v.ConverteNumeroReal(modelF.getValueAt(i, 3))));
                f = f.buscarCodigo(v.ConverteNumeroInteiro(modelF.getValueAt(i, 0)));
                f.setQtd(f.getQtd()+v.ConverteNumeroInteiro(modelF.getValueAt(i, 2)));
                f.atualizarEstoque();
            }
            if(!x)
                c.excluir();
            return x;
        }
        return false;
    }
    
    public void buscaFolha(int codigo)
    {
        f = f.buscarCodigo(codigo);
    }
    
    public void buscProduto(int codigo)
    {
        p = p.buscarCodigo(codigo);
    }
    
    public void buscFornecedor(int codigo)
    {
        forn = new Fornecedor().buscarCodigo(codigo);
        c.setF(forn);
    }
    
    public void buscaCompra(int codigo)
    {
        c = c.buscaCompra(codigo);
        cf.setC(c);
        cp.setC(c);
        c.setLcf(cf.buscaCompraFolha(codigo));
        c.setLcp(cp.buscaCompraProduto(codigo));
    }   
    
    public int Calcula(JTextField qtd, JTextField valor, JTextField total)
    {
//        String var = valor.getText();
////        var = var.replaceAll("\\.", "");
////        var = var.replace(',', '.');
//        valor.setText(var);
        if(v.ConverteNumeroInteiro(qtd.getText()) <= 0)
        {
            return 1;
        }
        if(v.ConverteNumeroReal(valor.getText()) <= 0)
        {
            return 2;
        }
        total.setText(""+(Double)(v.ConverteNumeroInteiro(qtd.getText()) * v.ConverteNumeroReal(valor.getText())));
        return 0;
    }
    
    public void CalculaTotal(JTextField total, JTextField valorTP, JTextField valorTF)
    {
        valorTF.setText(""+(v.ConverteNumeroReal(valorTP.getText()) + v.ConverteNumeroReal(total.getText())));
    }
    
    public void CalculaTotalI(JTextField valorT, JTable tabela)
    {
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        double tot = 0;
        for(int i = 0; i < model.getRowCount(); i++)
        {
            tot += v.ConverteNumeroReal(model.getValueAt(i, 4));
        }
        valorT.setText(""+tot);
    }
    
    public void ExcluirLinha(JTable tabela, JTextField valorTM, JTextField valorTF)
    {
        valorTM.setText(""+(v.ConverteNumeroReal(valorTM.getText()) - v.ConverteNumeroReal(String.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 4)))));
        valorTF.setText(""+(v.ConverteNumeroReal(valorTF.getText()) - v.ConverteNumeroReal(valorTM.getText())));
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        model.removeRow(tabela.getSelectedRow());
    }
    
    public int excluir()
    {
        //Verificar se existe Parcela paga!
        if(c.buscaQtdParcelas(c.getCodigo()) > 0)
        {
            return 1;
        }
        
        if(!c.Function())
        {
            return 2;
        }
        ContaPagar.excluirParcelasCompra(c.getCodigo());
        //voltar o estoque ->> O trigger esta cuidando dess parte
        //if(c.excluirItens())
        return c.excluir() ? 0 : 3;
        //return false;
    }
    
    public boolean verificarParcelars(int codigo)
    {
        return c.buscaQtdParcelas(codigo) == 0;
    }
    
    public void addItens(JTable tabelaF, JTable tabelaP, JTextField valorTF, JTextField valorTP)
    {
        double valort = 0;
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabelaF.getModel();
        for (Compra_Folha lcf : c.getLcf()) 
        {
            model.addRow(new Object[]
            {
                lcf.getF().getCodigo(), 
                lcf.getF().getTamanho()+"/"+lcf.getF().getDescricao(), 
                lcf.getQtd(),
                lcf.getPreco(),
                lcf.getQtd()*lcf.getPreco()
            });
            valort = valort + lcf.getQtd()*lcf.getPreco();
        }
        valorTF.setText(""+valort);
        
        model = (ReadOnlyTableModel) tabelaP.getModel();
        valort = 0;
        for (Compra_Produto lcp : c.getLcp()) 
        {
            model.addRow(new Object[]
            {
                lcp.getP().getCodigo(), 
                lcp.getP().getNome(), 
                lcp.getQtd(),
                lcp.getPreco(),
                lcp.getQtd()*lcp.getPreco()
            });
            valort = valort + lcp.getQtd()*lcp.getPreco();
        }
        valorTP.setText(""+valort);
    }
    
    public boolean verificarEstoqueProduto(int codigoP, int qtd)
    {
        try {
            Produto temp = new Produto().buscarCodigo(codigoP);
            Producao_Produto pp = new Producao_Produto();
            return temp.getQtd() - qtd - pp.qtdReserva(codigoP) >= 0;
        } catch (SQLException ex) {
            Logger.getLogger(LancarCompraController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean buscarTabelaProduto(int codigoC, int codigoP)
    {
        return c.verificaTabelaProduto(codigoP, codigoC);
    }
    
    public boolean buscarTabelaFolha(int codigoC, int codigoF)
    {
        return c.verificaTabelaFolha(codigoF, codigoC);
    }
    
    public boolean verificarEstoqueFolha(int codigoF, int qtd)
    {
        try {
            Folha temp = new Folha().buscarCodigo(codigoF);
            Producao_Folha pf = new Producao_Folha();
            
            return temp.getQtd() - qtd - pf.qtdReserva(codigoF) >= 0;
        } catch (SQLException ex) {
            Logger.getLogger(LancarCompraController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean lancarContaPagar(Date data)
    {
        contP.setCodigo(0);
        contP.setTc(null);
        contP.setValorC(c.getValort());
        contP.setDataV(data);
        contP.setComp(c);
        contP.setLocal("");
        contP.setValorP(0);
        contP.setDataP(null);
        contP.setObs("Compra");
        contP.setDataL(Date.from(Instant.now()));
        contP.setParcela(0);
        contP.setFunc(c.getFunc());
        return contP.gravar();
    }
    
    public static void configuraModelItem(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Codigo", "Nome", "Quantidade", "Valor Unitario", "Valor Total"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(350);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(150);
    }

}
