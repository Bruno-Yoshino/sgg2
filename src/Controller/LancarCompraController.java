/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaLogica.ReadOnlyTableModel;
import CamadaNegocio.Compra;
import CamadaNegocio.Compra_Produto;
import CamadaNegocio.Folha;
import CamadaNegocio.Fornecedor;
import CamadaNegocio.Produto;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import util.Validacao;

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
    public int validar(String codigo, String nome, String qtd, String preco, JTable tabela, boolean flag)
    {
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        if(codigo.equals(""))
        {
            return 3;
        }
        if(flag)//true => Produto
        {
            model.addRow(new Object[]
            {
                codigo, 
                nome, 
                qtd,
                preco,
                v.ConverteNumeroInteiro(qtd)*v.ConverteNumeroReal(preco)
            });
        }
        else // false => Folha
        {
            model.addRow(new Object[]
            {
                codigo, 
                nome, 
                qtd,
                preco,
                v.ConverteNumeroInteiro(qtd)*v.ConverteNumeroReal(preco)
            });
        }
        return 0;
    }
    
    public boolean gravar()
    {
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
}
