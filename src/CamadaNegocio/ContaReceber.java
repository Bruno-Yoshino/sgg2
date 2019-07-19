package CamadaNegocio;

import CamadaLogica.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public ContaReceber() {
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
        String sql = "INSERT INTO conta_receber (pe_codigo, cr_datavenc, cr_obs, cr_valor, cr_datapago, cr_vlorp) "
                   + " values ("+p.getCodigo()+", '"+dataV+"', '', "+valor+", '"+null+"', "+0+");";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar()
    {
        String sql = "update conta_receber set  cr_obs = '"+obs+"', cr_datapago = "+dataP+", cr_vlorp = "+valorP+" "
                   + " where cr_codigo = "+codigo+";";
        return Banco.getCon().manipular(sql);
    }
    
    public void buscaContaReceber(int codigo) throws SQLException
    {
        p = new Pedido().buscar(codigo);
    }
    
    public ContaReceber buscar(int codigo)
    {
        String sql;
        sql = "select cr_codigo, pe_codigo, cr_datavenc, cr_obs, cr_valor, cr_datapago, cr_vlorp "
                + " from conta_receber where cr_codigo = "+codigo+"";
                ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new ContaReceber(rs.getInt(1), new Pedido().buscar(rs.getInt(2)), rs.getDate(3), rs.getString(4), rs.getDouble(5), rs.getDate(6), rs.getDouble(7));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public static ResultSet buscarDados(int op)
    {
        String sql;
        switch(op)
        {
            case 1:
                sql = "";
                break;
            case 2:
                sql = "";
                break;
            default:
                sql = null;
        }
        return Banco.getCon().retornaResultSet(sql);
    }
}
