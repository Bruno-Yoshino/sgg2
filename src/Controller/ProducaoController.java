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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    //-------------------------------------------------------------------------------------------------------------------
    
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
        f = new Folha().buscarCodigo(codigo);
    }
    
    public void buscaProduto(int codigo)
    {
        prod = new Produto().buscarCodigo(codigo);
    }
    
    public void buscarFuncionario(int codigo)
    {
        p.setF(new Funcionario().buscarCodigo(codigo));
    }
    
    public void carregarListaCliente(int op, String valor, JTable tabela)
    {
        ResultSet rs;
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        switch(op)
        {
            case 1: 
                if(valor.equals("Nome Cliente ou Numero pedido"))
                {
                    rs =  Producao.BuscarPedidoNaoEntregue("", 0);
                }
                else
                    if(v.ConverteNumeroInteiro(valor) > 0)
                    {
                        rs =  Producao.BuscarPedidoNaoEntregue(valor, 2);
                    }
                    else
                    {
                        rs =  Producao.BuscarPedidoNaoEntregue(valor, 1);
                    }
                break;
            case 2: 
                if(valor.equals("Nome Cliente ou Numero pedido"))
                {
                    rs =  Producao.BuscarPedidoEntregue("", 0);
                }
                else
                    if(v.ConverteNumeroInteiro(valor) > 0)
                    {
                        rs =  Producao.BuscarPedidoEntregue(valor, 2);
                    }
                    else
                    {
                        rs =  Producao.BuscarPedidoEntregue(valor, 1);
                    }
                break;
                
            default: rs =  Producao.BuscarPedidoNaoEntregue("", 0);
        }
        try {
            while(rs.next())
            {
                model.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getDate(4)
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProducaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void carregarListaPedido(int op, JTable tabela, int codigo)
    {
        ResultSet rs;
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        rs =  Producao.BuscarProducao(op, codigo);
        try {
            while(rs.next())
            {
                model.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3) == 1 ? "Pronto" : rs.getInt(3) == 2 ? "Pausado" : rs.getInt(3) == 3 ? "Desenvolvimento" : "Aguardando"
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProducaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void carregarListaItens(int codigoP, JTable tabela)// pedido && producao
    {
        ResultSet rs;
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        try {
            rs = Producao.BuscarProducaoItemFolha(codigoP);
            while(rs.next())
            {
                model.addRow(new Object[]{
                    0,
                    rs.getInt(1),
                    rs.getString(2) +"/"+ rs.getString(3),
                    rs.getInt(4)
                });
            }
            rs = Producao.BuscarProducaoItemProduto(codigoP);
            while(rs.next())
            {
                model.addRow(new Object[]{
                    0,
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3)
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProducaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int varidarAddItem(String produto, String folha, String qtdP, String qtdF, String reservaP, String reservaF, JTable tabela)
    {
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        if(produto.equals("") && folha.equals(""))
            return 1;
        if(!produto.equals(""))
        {
            if(v.ConverteNumeroInteiro(qtdP) <= 0)
                return 2;
            if(v.ConverteNumeroInteiro(qtdP) > prod.getQtd()+v.ConverteNumeroInteiro(reservaP))
                return 3;
            for (int i = 0; i < model.getRowCount(); i++) 
            {
                if(v.ConverteNumeroInteiro(model.getValueAt(i, 0)) == prod.getCodigo())
                    return 6;
            }
            p.getListaP().add(new Producao_Produto(prod, v.ConverteNumeroInteiro(qtdP)));
        }
        
        if(!folha.equals(""))
        {
            if(v.ConverteNumeroInteiro(qtdF) <= 0)
                return 4;
            if(v.ConverteNumeroInteiro(qtdF) > f.getQtd()+v.ConverteNumeroInteiro(reservaF))
                return 5;
            for (int i = 0; i < model.getRowCount(); i++) 
            {
                if(v.ConverteNumeroInteiro(model.getValueAt(i, 1)) == f.getCodigo())
                    return 7;
            }
            p.getListaF().add(new Producao_Folha(f, v.ConverteNumeroInteiro(qtdF)));
        }
        
        return 0;
    }
    
    public int ReturnIndexComboBox(String texto)
    {
        switch(texto)
        {
            case "Pronto": return 0;
            case "Pausado": return 1;
            case "Desenvolvimento": return 2;
            case "Aguardando": return 3;
            default: return 0;
        }
    }
    
    public int qtdReservaP()
    {
        try {
            return new Producao_Produto().qtdReserva(prod.getCodigo());
        } catch (SQLException ex) {
            Logger.getLogger(Producao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int qtdReservaF()
    {
        try {
            return new Producao_Folha().qtdReserva(f.getCodigo());
        } catch (SQLException ex) {
            Logger.getLogger(Producao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public void limpaLista()
    {
        p.getListaF().clear();
        p.getListaP().clear();
    }
    
    public int atualizaProduto(int status)
    {
        if(status == 4 && p.VerificaItens(p.getCodigo()))
            return 1;
        p.setStatus(status);
        return 0;
    }
    
    public boolean Atualizar()
    {
        if(p.getStatus() == 4)
        {
            //insere os itens
            for(int i = 0; i < p.getListaF().size(); i++)//Folha
            {
                p.getListaF().get(i).gravar(p.getCodigo());
            }
            for(int i = 0; i < p.getListaP().size(); i++)//Produto
            {
                p.getListaP().get(i).gravar(p.getCodigo());
            }
        }
        return p.alterar();
    }
}
