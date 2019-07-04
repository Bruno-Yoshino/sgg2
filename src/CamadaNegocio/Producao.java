/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CamadaNegocio;

import CamadaLogica.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

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
public class Producao 
{
    private int codigo;
    private Pedido p;
    private Servico s;
    private Funcionario f;
    private boolean status;
    private Date data;
    private int sequence;
    private ArrayList<Producao_Folha> listaF;
    private ArrayList<Producao_Produto> listaP;

    public Producao(int codigo, Pedido p, Servico s, Funcionario f, boolean status, Date data, int sequence, ArrayList<Producao_Folha> listaF, ArrayList<Producao_Produto> listaP) {
        this.codigo = codigo;
        this.p = p;
        this.s = s;
        this.f = f;
        this.status = status;
        this.data = data;
        this.sequence = sequence;
        this.listaF = listaF;
        this.listaP = listaP;
    }

    public Producao() {
        listaF = new ArrayList<>();
        listaP = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Pedido getP() {
        return p;
    }

    public void setP(Pedido p) {
        this.p = p;
    }

    public Servico getS() {
        return s;
    }

    public void setS(Servico s) {
        this.s = s;
    }

    public Funcionario getF() {
        return f;
    }

    public void setF(Funcionario f) {
        this.f = f;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public ArrayList<Producao_Folha> getListaF() {
        return listaF;
    }

    public void setListaF(ArrayList<Producao_Folha> listaF) {
        this.listaF = listaF;
    }

    public ArrayList<Producao_Produto> getListaP() {
        return listaP;
    }

    public void setListaP(ArrayList<Producao_Produto> listaP) {
        this.listaP = listaP;
    }
    
    //--------------------------------------------------------------------------
    
    public int qtdReservaF()
    {
        return 0;
    }
    
    public int qtdReservaP()
    {
        return 0;
    }
    
    public static ResultSet BuscarPedidoNaoEntregue(String valor, int tipo)
    {
        String query = null;
        if (valor.equals(""))
        {
            query = "SELECT c.cli_codigo, c.cli_nome, p.pe_codigo, p.pe_datapedido, p.pe_entrega "
                + " FROM pedido p, cliente c "
                + " WHERE p.cli_codigo = c.cli_codigo and p.pe_datapedido > p.pe_entrega";
        }
        else
        {
            switch (tipo)
            {
                case 1:// Mome
                {
                    query = "SELECT c.cli_codigo, c.cli_nome, p.pe_codigo, p.pe_datapedido, p.pe_entrega "
                          + " FROM pedido p, cliente c "
                          + " WHERE c.cli_nome ilike '"+valor+"' and p.cli_codigo = c.cli_codigo and p.pe_datapedido > p.pe_entrega";
                    break;
                }
                case 2:// Numero
                {
                    query = "SELECT c.cli_codigo, c.cli_nome, p.pe_codigo, p.pe_datapedido, p.pe_entrega "
                             + " FROM pedido p, cliente c "
                            + "where p.pe_codigo = '"+valor+"' and p.cli_codigo = c.cli_codigo and p.pe_datapedido > p.pe_entrega";
                    break;
                }
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static ResultSet BuscarPedidoEntregue(String valor, int tipo)
    {
        String query = null;
        if (valor.equals(""))
        {
            query = "SELECT c.cli_codigo, c.cli_nome, p.pe_codigo, p.pe_datapedido, p.pe_entrega "
                + " FROM pedido p, cliente c "
                + " WHERE p.cli_codigo = c.cli_codigo and p.pe_datapedido < p.pe_entrega";
        }
        else
        {
            switch (tipo)
            {
                case 1:// Mome
                {
                    query = "SELECT c.cli_codigo, c.cli_nome, p.pe_codigo, p.pe_datapedido, p.pe_entrega "
                          + " FROM pedido p, cliente c "
                          + " WHERE c.cli_nome ilike '"+valor+"' and p.cli_codigo = c.cli_codigo and p.pe_datapedido < p.pe_entrega";
                    break;
                }
                case 2:// Numero
                {
                    query = "SELECT c.cli_codigo, c.cli_nome, p.pe_codigo, p.pe_datapedido, p.pe_entrega "
                             + " FROM pedido p, cliente c "
                            + "where p.pe_codigo = '"+valor+"' and p.cli_codigo = c.cli_codigo and p.pe_datapedido < p.pe_entrega";
                    break;
                }
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static ResultSet BuscarProducao(String valor, int tipo, boolean flag)
    {
        String query = null;
        if (valor.equals(""))
        {
            query = "SELECT p.prod_codigo, s.serv_nome, p.prod_status "
                + " FROM producao p, servico s "
                + " WHERE p.serv_codigo = s.serv_codigo and p.prod_status = "+flag+"";
        }
        else
        {
            switch (tipo)
            {
                case 1:// Numero
                {
                    query = "SELECT p.prod_codigo, s.serv_nome, p.prod_status "
                          + " FROM producao p, servico s "
                          + " WHERE p.prod_codigo = "+valor+" and p.serv_codigo = s.serv_codigo and p.prod_status = "+flag+"";
                    break;
                }
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static ResultSet BuscarProducaoItemFolha(int codigoP)
    {
        String query = "SELECT f.fo_codigo, f.fo_tamanho, f.fo_descricao, pf.pf_qtd "
                        + " FROM producao_folha pf, folha f "
                        + " WHERE pf.prod_codigo = "+codigoP+" and pf.fo_codigo = f.fo_codigo ";

        return Banco.getCon().retornaResultSet(query);
    }
    
    public static ResultSet BuscarProducaoItemProduto(int codigoP)
    {
        String query = "SELECT p.pro_codigo, p.pro_nome, pp.prod_qtd "
                     + " FROM producao_produto pp, produto p "
                     + " WHERE pp.prod_codigo = "+codigoP+" and pp.pro_codigo = p.pro_qtd ";

        return Banco.getCon().retornaResultSet(query);
    }
}
