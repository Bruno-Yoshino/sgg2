/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaLogica.ReadOnlyTableModel;
import CamadaNegocio.Folha;
import CamadaNegocio.Funcionario;
import CamadaNegocio.Producao;
import CamadaNegocio.Producao_Folha;
import CamadaNegocio.Producao_Produto;
import CamadaNegocio.Produto;
import java.util.ArrayList;
import javax.swing.JTable;
import util.Validacao;
import util.mensagens;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 巴御前
 * @author 高村　結衣
 * @author 里川　麗奈
 * @author 橋立
 * @author 阿賀野
 * @author 矢矧
 */
public class ProducaoController {
    
    private Producao p;
    private final Validacao v;
    private final mensagens m;
    private Folha f;
    private Produto prod;

    public ProducaoController() {
        p = new Producao();
        v = new Validacao();
        m = new mensagens();
    }

    public Producao getP() {
        return p;
    }

    public void setP(Producao p) {
        this.p = p;
    }

    public Folha getF() {
        return f;
    }

    public void setF(Folha f) {
        this.f = f;
    }

    public Produto getProd() {
        return prod;
    }

    public void setProd(Produto prod) {
        this.prod = prod;
    }
    
    public int validarAddItem(String qtdP, String qtdF, JTable tabela)
    {
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        if(prod == null && f == null)
            return 1;
        
        if(prod != null)
        {
            if(v.ConverteNumeroInteiro(qtdP) <= 0)
                return 2;
            
            if(prod.getQtd() < v.ConverteNumeroInteiro(qtdP))
                return 4;
            
             //check QTD reserva return 6
            for(int i = 0; i < tabela.getRowCount(); i++)
            {
                if(v.ConverteNumeroInteiro(model.getValueAt(i, 0)) == prod.getCodigo())
                    return 8;
            }
            ArrayList<Producao_Produto> tempP = p.getListaP();
            tempP.add(new Producao_Produto(prod, v.ConverteNumeroInteiro(qtdP)));
            p.setListaP(tempP);
        }
        
        if(f != null)
        {
            if(v.ConverteNumeroInteiro(qtdF) <= 0)
                return 3;
            
            if(f.getQtd() < v.ConverteNumeroInteiro(qtdF))
                return 5;
            
            //check QTD reserva return 7
            
            for(int i = 0; i < tabela.getRowCount(); i++)
            {
                if(v.ConverteNumeroInteiro(model.getValueAt(i, 1)) == f.getCodigo())
                    return 8;
            }
            ArrayList<Producao_Folha> tempF = p.getListaF();
            tempF.add(new Producao_Folha(f, v.ConverteNumeroInteiro(qtdF)));
            p.setListaF(tempF);
        }

        return 0;
    }
    
    
    public void addTabela(JTable tabela, boolean flagF, boolean flagP)
    {
        ArrayList<Producao_Folha> tempF = p.getListaF();
        ArrayList<Producao_Produto> tempP = p.getListaP();
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        if(flagF)
        {
            model.addRow(new Object[]{
                    0,
                    tempF.get(tempF.size()-1).getF().getCodigo(),
                    tempF.get(tempF.size()-1).getF().getDescricao() +"/"+tempF.get(tempF.size()-1).getF().getTamanho(),
                    tempF.get(tempF.size()-1).getQtd()
                });
        }
        
        if(flagP)
        {
            model.addRow(new Object[]{
                    tempP.get(tempP.size()-1).getP().getCodigo(),
                    0,
                    tempP.get(tempP.size()-1).getP().getNome(),
                    tempP.get(tempP.size()-1).getQtd()
                });
        }
        prod = null;
        f = null;
    }
    
    public void removeTabela(JTable tabela, int linha)
    {
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        model.removeRow(linha);
        if(model.getValueAt(linha, 0).equals(0)) // True --> Folha
        {
            ArrayList<Producao_Folha> tempF = p.getListaF();
            tempF.remove(linha);
            p.setListaF(tempF);
        }
        else // False --> Produto
        {
            ArrayList<Producao_Produto> tempP = p.getListaP();
            tempP.remove(linha);
            p.setListaP(tempP);
        }
    }
    
    public void buscaFolha(int codigo)
    {
        f = f.buscarCodigo(codigo);
    }
    
    public void buscaProduto(int codigo)
    {
        prod = prod.buscarCodigo(codigo);
    }
    
    public void buscarFuncionario(int codigo)
    {
        p.setF(new Funcionario().buscarCodigo(codigo));
    }
    
    public void carregarListaCliente(int op)
    {
        
    }
    
    public void carregarListaPedido(int op)
    {
        
    }
    
    public void carregarListaItens(int codigoPed, int codigoP)// pedido && producao
    {
        
    }
}
