/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaLogica.ReadOnlyTableModel;
import CamadaNegocio.Compra;
import CamadaNegocio.Compra_Folha;
import CamadaNegocio.Compra_Produto;
import CamadaNegocio.Folha;
import CamadaNegocio.Fornecedor;
import CamadaNegocio.Produto;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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
    private final mensagens m = new mensagens();

    public LancarCompraController() {
        c = new Compra();
        v = new Validacao();
        f = new Folha();
        p = new Produto();
        forn = new Fornecedor();
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
    public int validar(String codigo, String nome, String qtd, String preco, JTable tabela)
    {
        int task = 1; // Verifica se nao tem na tabela
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        if(codigo.equals(""))
        {
            return 3;
        }

        for (int i = 0; i < model.getRowCount() && task == 1; i++) 
        {
            if(String.valueOf(model.getValueAt(i, 0)).equals(codigo))
            {
                if(m.Pergunta("Este item ja esta inserido! Deseja somar com o item ja inserido?", "Confirmação") == JOptionPane.YES_OPTION)
                {
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
        }
        return 0;
    }
    
    public boolean gravar(String codigo, String valorT, JTable tabelaP, JTable tabelaF)
    {
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
            for (int i = 0; i < modelP.getRowCount(); i++) 
            {
                c.gravarItem(v.ConverteNumeroInteiro(modelP.getValueAt(i, 0)), v.ConverteNumeroInteiro(modelP.getValueAt(i, 2)), v.ConverteNumeroReal(modelP.getValueAt(i, 3)), true);
//                lcp.add(new Compra_Produto(c, p.buscarCodigo(v.ConverteNumeroInteiro(modelP.getValueAt(i, 0))), v.ConverteNumeroInteiro(modelP.getValueAt(i, 2)), v.ConverteNumeroReal(modelP.getValueAt(i, 3))));
            }

            for (int i = 0; i < modelF.getRowCount(); i++) 
            {
                c.gravarItem(v.ConverteNumeroInteiro(modelF.getValueAt(i, 0)), v.ConverteNumeroInteiro(modelF.getValueAt(i, 2)), v.ConverteNumeroReal(modelF.getValueAt(i, 3)), false);
//                lcf.add(new Compra_Folha(c, f.buscarCodigo(v.ConverteNumeroInteiro(modelF.getValueAt(i, 0))), v.ConverteNumeroInteiro(modelF.getValueAt(i, 2)), v.ConverteNumeroReal(modelF.getValueAt(i, 3))));
            }

//            c.setLcp(lcp);
//            c.setLcf(lcf);
            return true;
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
    } 
    
    public int Calcula(JTextField qtd, JTextField valor, JTextField total)
    {
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
    
    public void CalculaTotal(JTextField total, JTextField valorTM, JTextField valorTF)
    {
        valorTF.setText(""+(v.ConverteNumeroReal(valorTM.getText()) + v.ConverteNumeroReal(total.getText())));
    }
    
    public void ExcluirLinha(JTable tabela, JTextField valorTM, JTextField valorTF)
    {
        valorTM.setText(""+(v.ConverteNumeroReal(valorTM.getText()) - v.ConverteNumeroReal(String.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 4)))));
        valorTF.setText(""+(v.ConverteNumeroReal(valorTF.getText()) - v.ConverteNumeroReal(valorTM.getText())));
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        model.removeRow(tabela.getSelectedRow());
    }
    
    public boolean excluir()
    {
        if(c.excluirItens())
            return c.excluir();
        return false;
    }
}
