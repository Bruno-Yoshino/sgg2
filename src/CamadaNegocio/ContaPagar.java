package CamadaNegocio;

import CamadaLogica.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 夕張
 * @author もも
 * @author 林道
 * @author 香取 
 * @author 鹿島
 */
public class ContaPagar {
    
    private int codigo;
    private Compra comp;
    private TipoConta tc;
    private Funcionario func;
    private Caixa c;
    private Date dataL;
    private String local;
    private double valorC;
    private Date dataP;
    private double valorP;
    private int parcela;
    private Date dataV;
    private String obs;

    public ContaPagar(int codigo, Compra comp, TipoConta tc, Funcionario func, Caixa c, Date dataL, String local, double valorC, Date dataP, double valorP, int parcela, Date dataV, String obs) {
        this.codigo = codigo;
        this.comp = comp;
        this.tc = tc;
        this.func = func;
        this.c = c;
        this.dataL = dataL;
        this.local = local;
        this.valorC = valorC;
        this.dataP = dataP;
        this.valorP = valorP;
        this.parcela = parcela;
        this.dataV = dataV;
        this.obs = obs;
    }

    public ContaPagar() {
        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Compra getComp() {
        return comp;
    }

    public void setComp(Compra comp) {
        this.comp = comp;
    }

    public TipoConta getTc() {
        return tc;
    }

    public void setTc(TipoConta tc) {
        this.tc = tc;
    }

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
    }

    public Caixa getC() {
        return c;
    }

    public void setC(Caixa c) {
        this.c = c;
    }

    public Date getDataL() {
        return dataL;
    }

    public void setDataL(Date dataL) {
        this.dataL = dataL;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public double getValorC() {
        return valorC;
    }

    public void setValorC(double valorC) {
        this.valorC = valorC;
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

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
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
    
    public boolean gravar()
    {
        String sql;
        if(dataP == null)
        {
            sql = "INSERT INTO conta_pagar(comp_codigo, cp_data, cp_local, cp_valorc, cp_dtpago, cp_valorp, cp_nparcela, tc_codigo, func_codigo, caixa_codigo, cp_datavencimento, cp_obs) " +
                     " VALUES ("+(comp==null ? null:comp.getCodigo())+", "
                    + "'"+dataL+"', '"+local+"', "+valorC+", null, "+valorP+", "+parcela+", "
                    + ""+(tc==null ? null:tc.getCodigo())+", "
                    + ""+func.getCodigo()+", "
                    + ""+(c==null ? null:c.getCodigo())+", '"+dataV+"', '"+obs+"');";
        }
        else
        {
             sql = "INSERT INTO conta_pagar(comp_codigo, cp_data, cp_local, cp_valorc, cp_dtpago, cp_valorp, cp_nparcela, tc_codigo, func_codigo, caixa_codigo, cp_datavencimento, cp_obs) " +
                     " VALUES ("+(comp==null ? null:comp.getCodigo())+", '"+dataL+"', '"+local+"', "+valorC+", '"+dataP+"', "+valorP+", "+parcela+", "+(tc==null ? null:tc.getCodigo())+", "+func.getCodigo()+", "+(c==null ? null:c.getCodigo())+", '"+dataV+"', '"+obs+"');";
        }
        return Banco.getCon().manipular(sql);
    }
    
    public int maxCodigo()
    {
        String sql = "select max(cp_codigo) from conta_pagar";
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
    
    public boolean alterar()
    {
        String sql = "update conta_pagar set cp_local = '"+local+"', cp_dtpago = '"+dataP+"', cp_valorp = "+valorP+" where cp_codigo = "+codigo+";";
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean despesa()
    {
        String sql = "update conta_pagar set cp_local = '"+local+"', cp_dtpago = '"+dataP+"', cp_valorp = "+valorP+" where cp_codigo = "+codigo+";";
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo)
    {
        String sql = "delete form conta_pagar where cp_codigo = "+codigo+";";
        
        return Banco.getCon().manipular(sql);
    }
    
    public static ResultSet buscarDados()
    {
        String sql = "SELECT cp_codigo, comp_codigo, cp_data, cp_local, cp_valorc, cp_dtpago, cp_valorp, cp_nparcela, tc_codigo, func_codigo, caixa_codigo, cp_datavencimento, cp_obs "
                    + "FROM conta_pagar "
                    + "WHERE cp_dtpago is null and caixa_codigo is null "
                    + "Order by cp_datavencimento;";
        return Banco.getCon().retornaResultSet(sql);
    }
    
    public static ResultSet buscarDados(int codigo)
    {
        String sql = "SELECT cp_codigo, comp_codigo, cp_data, cp_local, cp_valorc, cp_dtpago, cp_valorp, cp_nparcela, tc_codigo, func_codigo, caixa_codigo, cp_datavencimento, cp_obs "
                    + "FROM conta_pagar "
                    + "WHERE cp_codigo = "+codigo+";";
        return Banco.getCon().retornaResultSet(sql);
    }
    
    public double saldoRetirado(int codigo)
    {
        String sql = "select sum(cp_valorc) from conta_pagar "
                   + " where caixa_codigo = "+codigo+"";
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
            return 0;
        }
        return 0;
    }
    
    
}
