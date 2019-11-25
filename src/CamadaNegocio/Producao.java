package CamadaNegocio;

import CamadaLogica.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private int status;
    private Date data;
    private int sequence;
    private ArrayList<Producao_Folha> listaF;
    private ArrayList<Producao_Produto> listaP;

    public Producao(int codigo, Pedido p, Servico s, Funcionario f, int status, Date data, int sequence, ArrayList<Producao_Folha> listaF, ArrayList<Producao_Produto> listaP) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
    
    public boolean gravar(Pedido_Servico ps)
    {
        String sql = "INSERT INTO producao (pe_codigo, serv_codigo, prod_status, ps_sequence) VALUES ("+p.getCodigo()+", "+ps.getServ().getCodigo()+", 4, "+ps.getSequence()+")";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(int codigo)
    {
        String sql = "update producao set func_codigo = "+f.getCodigo()+", prod_status = "+status+", prod_data = "+(status==1 ? Date.from(Instant.now()) : null)+" where prod_codigo = "+codigo+"";
        if(status==1)
        {
            sql = "update producao set func_codigo = "+f.getCodigo()+", prod_status = "+status+", prod_data = '"+Date.from(Instant.now())+"' where prod_codigo = "+codigo+"";
        }
        else
        {
            sql = "update producao set func_codigo = "+f.getCodigo()+", prod_status = "+status+", prod_data = null where prod_codigo = "+codigo+"";
        }
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo, int sequencia)
    {
        String sql = "delete from producao where pe_codigo = "+codigo+" and ps_sequence = "+sequencia+"";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo)
    {
        String sql = "delete from producao where pe_codigo = "+codigo+"";
        return Banco.getCon().manipular(sql);
    }
    
    public static ResultSet BuscarPedidoHaFazer(String valor, int tipo)
    {
        String query = null;
        if (valor.equals(""))
        {
            query = "SELECT c.cli_codigo, c.cli_nome, p.pe_codigo, p.pe_datapedido, p.pe_dataentrega "
                + " FROM pedido p, cliente c "
                + " WHERE p.cli_codigo = c.cli_codigo and p.pe_datapedido > p.pe_dataentrega and (select count(*) from producao prod where p.pe_codigo = prod.pe_codigo and prod.prod_status != 1) != 0";
        }
        else
        {
            switch (tipo)
            {
                case 1:// Nome
                {
                    query = "SELECT c.cli_codigo, c.cli_nome, p.pe_codigo, p.pe_datapedido, p.pe_dataentrega "
                          + " FROM pedido p, cliente c "
                          + " WHERE c.cli_nome ilike '%"+valor+"%' and p.cli_codigo = c.cli_codigo and p.pe_datapedido > p.pe_dataentrega and (select count(*) from producao prod where p.pe_codigo = prod.pe_codigo and prod.prod_status != 1) != 0";
                    break;
                }
                case 2:// Numero
                {
                    query = "SELECT c.cli_codigo, c.cli_nome, p.pe_codigo, p.pe_datapedido, p.pe_dataentrega "
                             + " FROM pedido p, cliente c "
                            + "where p.pe_codigo = '"+valor+"' and p.cli_codigo = c.cli_codigo and p.pe_datapedido > p.pe_dataentrega and (select count(*) from producao prod where p.pe_codigo = prod.pe_codigo and prod.prod_status != 1) != 0";
                    break;
                }
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static ResultSet BuscarPedidoNaoEntregue(String valor, int tipo)
    {
        String query = null;
        if (valor.equals(""))
        {
            query = "SELECT c.cli_codigo, c.cli_nome, p.pe_codigo, p.pe_datapedido, p.pe_dataentrega "
                + " FROM pedido p, cliente c "
                + " WHERE p.cli_codigo = c.cli_codigo and p.pe_datapedido > p.pe_dataentrega and (select count(*) from producao prod where p.pe_codigo = prod.pe_codigo and prod.prod_status = 1) != 0";
        }
        else
        {
            switch (tipo)
            {
                case 1:// Nome
                {
                    query = "SELECT c.cli_codigo, c.cli_nome, p.pe_codigo, p.pe_datapedido, p.pe_dataentrega "
                          + " FROM pedido p, cliente c "
                          + " WHERE c.cli_nome ilike '%"+valor+"%' and p.cli_codigo = c.cli_codigo and p.pe_datapedido > p.pe_dataentrega and (select count(*) from producao prod where p.pe_codigo = prod.pe_codigo and prod.prod_status = 1) != 0";
                    break;
                }
                case 2:// Numero
                {
                    query = "SELECT c.cli_codigo, c.cli_nome, p.pe_codigo, p.pe_datapedido, p.pe_dataentrega "
                             + " FROM pedido p, cliente c "
                            + "where p.pe_codigo = '"+valor+"' and p.cli_codigo = c.cli_codigo and p.pe_datapedido > p.pe_dataentrega and (select count(*) from producao prod where p.pe_codigo = prod.pe_codigo and prod.prod_status = 1) != 0";
                    break;
                }
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static ResultSet BuscarProducao(int flag, int codigo)
    {
        String query = null;
        if(flag == 1)
            query = "SELECT p.prod_codigo, s.serv_nome, p.prod_status "
                + " FROM producao p, servico s "
                + " WHERE p.pe_codigo = "+codigo+" and p.serv_codigo = s.serv_codigo and p.prod_status = 1";
        else
            query = "SELECT p.prod_codigo, s.serv_nome, p.prod_status "
                + " FROM producao p, servico s "
                + " WHERE p.pe_codigo = "+codigo+" and p.serv_codigo = s.serv_codigo and p.prod_status != 1";
        
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
        String query = "SELECT p.pro_codigo, p.pro_nome, pp.pp_qtd "
                     + " FROM producao_produto pp, produto p "
                     + " WHERE pp.prod_codigo = "+codigoP+" and pp.pro_codigo = p.pro_codigo ";

        return Banco.getCon().retornaResultSet(query);
    }
    
    public int buscarQTD()//Pedidos em andamento
    {
        String sql = "SELECT count(*) "
                     + " FROM producao "
                     + " WHERE prod_status != 1 ";

        ResultSet rs=Banco.getCon().consultar(sql);
        try {
            if(rs.next())
            {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int buscarQTD2()//Pedidos nao entregue
    {
        String sql = "SELECT count(*) "
                   +" FROM pedido p, cliente c " 
                   +" WHERE p.cli_codigo = c.cli_codigo and p.pe_datapedido > p.pe_dataentrega and (select count(*) from producao prod where p.pe_codigo = prod.pe_codigo and prod.prod_status = 1) != 0";

        ResultSet rs=Banco.getCon().consultar(sql);
        try {
            if(rs.next())
            {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public boolean VerificaItensP(int codigo)
    {
        String sql = "select pp.pp_qtd from producao_produto pp where pp.prod_codigo = "+codigo+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        try {
            if(rs.next())
            {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean VerificaItensF(int codigo)
    {
        String sql = "select pf.pf_qtd from producao_folha pf where pf.prod_codigo = "+codigo+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        try {
            if(rs.next())
            {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int MaxCodigo()
    {
        String sql = "select max(prod_codigo) from producao";
        ResultSet rs=Banco.getCon().consultar(sql);
        try {
            if(rs.next())
            {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static ResultSet carregarServico(int codigoP)
    {
        String query = "SELECT s.serv_nome, p.pe_codigo, p.ps_sequence, ps.ps_descricao "
                     + " FROM producao p, servico s, pedido_servico ps "
                     + " WHERE p.prod_codigo = "+codigoP+" and p.serv_codigo = s.serv_codigo "
                     + " and p.pe_codigo = ps.pe_codigo and p.serv_codigo = ps.serv_codigo ";
        return Banco.getCon().retornaResultSet(query);
    }
}
