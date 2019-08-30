package CamadaNegocio;

import CamadaLogica.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 海女
 * @author 御子
 * @author 稲荷
 */
public class ContaReceber {
    private int codigo;
    private Pedido p;
    private Date dataV;
    private String obs;
    private double valor;
    private Date dataP;
    private double valorP;
    private boolean flag;

    public ContaReceber() {
        
    }

    public ContaReceber(int codigo, Pedido p, Date dataV, String obs, double valor, Date dataP, double valorP, boolean flag) {
        this.codigo = codigo;
        this.p = p;
        this.dataV = dataV;
        this.obs = obs;
        this.valor = valor;
        this.dataP = dataP;
        this.valorP = valorP;
        this.flag = flag;
    }

    public ContaReceber(int codigo, Pedido p, Date dataV, String obs, double valor, Date dataP, double valorP) {
        this.codigo = codigo;
        this.p = p;
        this.dataV = dataV;
        this.obs = obs;
        this.valor = valor;
        this.dataP = dataP;
        this.valorP = valorP;
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

    public Date getDataV() {
        return dataV;
    }

    public void setDataV(Date dataV) {
        this.dataV = dataV;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataP() {
        return dataP;
    }

    public void setDataP(Date dataP) {
        this.dataP = dataP;
    }

    public double getValorP() {
        return valorP;
    }

    public void setValorP(double valorP) {
        this.valorP = valorP;
    }
    //--------------------------------------------------------------------------
    public boolean gravar()
    {
        String sql = "INSERT INTO conta_receber (pe_codigo, cr_datavenc, cr_obs, cr_valor, cr_datapago, cr_vlorp, cr_flag) "
                   + " values ("+p.getCodigo()+", '"+dataV+"', '', "+valor+", "+null+", "+0+", "+flag+");";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar()
    {
        String sql = "update conta_receber set cr_obs = '"+obs+"', cr_datapago = '"+dataP+"', cr_vlorp = "+valorP+" "
                   + " where cr_codigo = "+codigo+";";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterarvalorConta(int codigo)
    {
        String sql = "update conta_receber set cr_valor = "+valor+" "
                   + " where cr_codigo = "+codigo+";";
        return Banco.getCon().manipular(sql);
    }
    
    public ContaReceber buscaContaReceber(int codigo) throws SQLException
    {
        //int codigo, Pedido p, Date dataV, String obs, double valor, Date dataP, double valorP
        String sql = "SELECT cr_codigo, pe_codigo, cr_datavenc, cr_obs, cr_valor, cr_datapago, cr_vlorp, cr_flag " +
                     " FROM public.conta_receber"
                   + " WHERE cr_codigo = "+codigo+";";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new ContaReceber(rs.getInt(1), new Pedido().buscar(rs.getInt(2)), rs.getDate(3), rs.getString(4), rs.getDouble(5), rs.getDate(6), rs.getDouble(7), rs.getBoolean(8));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
        //p = new Pedido().buscar(codigo);
    }
    
    public ContaReceber buscar(int codigo)
    {
        String sql;
        sql = "select cr_codigo, pe_codigo, cr_datavenc, cr_obs, cr_valor, cr_datapago, cr_vlorp, cr_boolean "
                + " from conta_receber where cr_codigo = "+codigo+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new ContaReceber(rs.getInt(1), new Pedido().buscar(rs.getInt(2)), rs.getDate(3), rs.getString(4), rs.getDouble(5), rs.getDate(6), rs.getDouble(7), rs.getBoolean(8));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public ArrayList<Integer> CodigoContaReceber(int codigoP)
    {
        String sql;
        ArrayList<Integer> lista = new ArrayList<>();
        sql = "select cr_codigo "
                + " from conta_receber where pe_codigo = "+codigoP+" order by cr_codigo";
                ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            while (rs.next()) 
            {
                lista.add(rs.getInt(1));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    public ArrayList<ContaReceber> ListaContaReceber(int codigoP)
    {
        String sql;
        ArrayList<ContaReceber> lista = new ArrayList<>();
        sql = "select cr_codigo, pe_codigo, cr_datavenc, cr_obs, cr_valor, cr_datapago, cr_vlorp, cr_boolean "
                + " from conta_receber where pe_codigo = "+codigoP+" order by cr_codigo";
                ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            while (rs.next()) 
            {
                lista.add(new ContaReceber(rs.getInt(1), new Pedido().buscar(rs.getInt(2)), rs.getDate(3), rs.getString(4), rs.getDouble(5), rs.getDate(6), rs.getDouble(7), rs.getBoolean(8)));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    public int QtdParcela()
    {
        String sql;
        sql = "select count(*) "
                + " from conta_receber where pe_codigo = "+p.getCodigo()+"";
                ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return rs.getInt(1);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    public static ResultSet buscarDados(int op)
    {
        String sql;
        switch(op)
        {//"Cliente", "Número Pedido", "Valor a ser Cobrado", "Data de Vencimento", "Data do Peido", "Numero da conta"
            case 1: // nome
                sql = "select c.cli_nome, cr.pe_codigo, cr.cr_valor, cr.cr_datavenc, p.pe_datapedido, cr.cr_codigo "
                    + " from conta_receber cr, pedido p, cliente c"
                    + " where cr.cr_datapago is null and p.pe_codigo = cr.pe_codigo and p.cli_codigo = c.cli_codigo "
                    + " order by c.cli_nome;";
                break;
            case 2: // data vencimento
                //sql = "select cr.cr_codigo, cr.pe_codigo, cr.cr_datavenc, cr.cr_obs, cr.cr_valor, cr.cr_datapago, cr.cr_vlorp "
                sql = "select c.cli_nome, cr.pe_codigo, cr.cr_valor, cr.cr_datavenc, p.pe_datapedido, cr.cr_codigo "
                    + " from conta_receber cr "
                    + " where cr.cr_datapago is null"
                    + " order by cr.cr_datavenc;";
                break;
            default:
                sql = null;
        }
        return Banco.getCon().retornaResultSet(sql);
    }
}
