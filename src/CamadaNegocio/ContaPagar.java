package CamadaNegocio;

import CamadaLogica.Banco;
import CamadaLogica.ReadOnlyTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;

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
            {//
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
        String sql = "update conta_pagar set cp_local = '"+local+"', cp_dtpago = '"+dataP+"', cp_valorp = "+valorP+", caixa_codigo = "+c.getCodigo()+" where cp_codigo = "+codigo+";";
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterarPEstorno(int codigo)
    {
        String sql = "update conta_pagar set cp_local = '', cp_dtpago = null, cp_valorp = 0, caixa_codigo = null where cp_codigo = "+codigo+";";
        
        return Banco.getCon().manipular(sql);
    }
        
    public boolean alterarDespesa()
    {
        String sql = "update conta_pagar set cp_datavencimento = '"+dataV+"', cp_valorc = "+valorC+", tc_codigo = "+tc.getCodigo()+", func_codigo = "+func.getCodigo()+", cp_obs = '"+obs+"' "
                   + "where cp_codigo = "+codigo+";";
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo)
    {
        String sql = "delete from conta_pagar where cp_codigo = "+codigo+";";
        
        return Banco.getCon().manipular(sql);
    }
    
    public ContaPagar buscarDado(String codigo)
    {
        String sql = "SELECT cp_codigo, comp_codigo, tc_codigo, func_codigo, caixa_codigo, cp_data, cp_local, cp_valorc, cp_dtpago, cp_valorp, cp_nparcela, cp_datavencimento, cp_obs "
                    + "FROM conta_pagar "
                    + "WHERE cp_codigo = "+codigo+" "
                    + "Order by cp_datavencimento;";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {//int codigo, Compra comp, TipoConta tc, Funcionario func, Caixa c, Date dataL, String local, double valorC, Date dataP, double valorP, int parcela, Date dataV, String obs
                return new ContaPagar(rs.getInt(1), new Compra().buscaCompra(rs.getInt(2)), new TipoConta().buscarCodigo(rs.getInt(3)), new Funcionario().buscarCodigo(rs.getInt(4)), new Caixa().buscaCaixa(rs.getInt(5)), rs.getDate(6), rs.getString(7), rs.getDouble(8), rs.getDate(9), rs.getDouble(10), rs.getInt(11), rs.getDate(12), rs.getString(13));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public ArrayList<ContaPagar> retornaLista(String codigo)//Por Nparcela
    {
        ArrayList<ContaPagar> lista = new ArrayList<>();
        String sql = "SELECT cp_codigo, comp_codigo, tc_codigo, func_codigo, caixa_codigo, cp_data, cp_local, cp_valorc, cp_dtpago, cp_valorp, cp_nparcela, cp_datavencimento, cp_obs "
                    + "FROM conta_pagar "
                    + "WHERE (cp_nparcela = "+codigo+" or cp_codigo = "+codigo+") and cp_dtpago is null "
                    + "Order by cp_datavencimento;";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next()) 
            {//int codigo, Compra comp, TipoConta tc, Funcionario func, Caixa c, Date dataL, String local, double valorC, Date dataP, double valorP, int parcela, Date dataV, String obs
                 lista.add(new ContaPagar(rs.getInt(1), new Compra().buscaCompra(rs.getInt(2)), new TipoConta().buscarCodigo(rs.getInt(3)), new Funcionario().buscarCodigo(rs.getInt(4)), new Caixa().buscaCaixa(rs.getInt(5)), rs.getDate(6), rs.getString(7), rs.getDouble(8), rs.getDate(9), rs.getDouble(10), rs.getInt(11), rs.getDate(12), rs.getString(13)));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    public ArrayList<ContaPagar> retornaListaSPago(String codigo)//Por Nparcela Somente Pago
    {
        ArrayList<ContaPagar> lista = new ArrayList<>();
        String sql = "SELECT cp_codigo, comp_codigo, tc_codigo, func_codigo, caixa_codigo, cp_data, cp_local, cp_valorc, cp_dtpago, cp_valorp, cp_nparcela, cp_datavencimento, cp_obs "
                    + "FROM conta_pagar "
                    + "WHERE (cp_codigo = "+codigo+" or cp_nparcela = "+codigo+") and cp_dtpago is not null "
                    + "Order by cp_datavencimento asc;";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next()) 
            {//int codigo, Compra comp, TipoConta tc, Funcionario func, Caixa c, Date dataL, String local, double valorC, Date dataP, double valorP, int parcela, Date dataV, String obs
                 lista.add(new ContaPagar(rs.getInt(1), new Compra().buscaCompra(rs.getInt(2)), new TipoConta().buscarCodigo(rs.getInt(3)), new Funcionario().buscarCodigo(rs.getInt(4)), new Caixa().buscaCaixa(rs.getInt(5)), rs.getDate(6), rs.getString(7), rs.getDouble(8), rs.getDate(9), rs.getDouble(10), rs.getInt(11), rs.getDate(12), rs.getString(13)));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    public int buscarParcelas(int codigoI, int codigoF)
    {
         
        String sql = "SELECT count(*) "
                    + "FROM conta_pagar "
                    + "WHERE cp_codigo >= "+codigoI+" and cp_codigo < "+codigoF+" and cp_dtpago is not null ";
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
    
    public int buscarCodigoMaxParcela(int codigo)//referente a parcela 
    {
         
        String sql = "SELECT max(cp_codigo) "
                    + "FROM conta_pagar "
                    + "WHERE cp_nparcela = "+codigo+" ";
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
    
    public int buscarCodigoMaxParcelaCDtV(int parcela, Date dataV, int codigo)
    {
         
        String sql = "SELECT max(cp_codigo) "
                    + "FROM conta_pagar "
                    + "WHERE (cp_nparcela = "+parcela+" or cp_nparcela = "+codigo+") and cp_datavencimento = '"+dataV+"' and cp_dtpago is null ";
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
    
    public int buscarCodigoMaxParcelaPaga(int parcela, Date dataV, int codigo)
    {
         
        String sql = "SELECT max(cp_codigo) "
                    + "FROM conta_pagar "
                    + "WHERE (cp_nparcela = "+parcela+" or cp_nparcela = "+codigo+") and cp_datavencimento = '"+dataV+"' and cp_dtpago is not null ";
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
    
    public int buscarCodigoMaxNaoPago(int codigo)
    {
         
        String sql = "SELECT max(cp_codigo) "
                    + "FROM conta_pagar "
                    + "WHERE cp_nparcela = "+codigo+" and cp_dtpago is null ";
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
    
    public int buscarNParcela(String codigo)// busca somente o numero da parcela para manter na hora de gerar uma nova parcela
    {
        String sql = "SELECT cp_nparcela "
                    + "FROM conta_pagar "
                    + "WHERE cp_codigo = "+codigo+" ";
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
    
    public int buscarQTDParcela(String codigo)
    {
        String sql = "SELECT count(*) "
                    + "FROM conta_pagar "
                    + "WHERE cp_nparcela = "+codigo+" ";
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
    
    public static ResultSet buscarDados()
    {
        String sql = "SELECT cp_codigo, comp_codigo, cp_data, cp_local, cp_valorc, cp_dtpago, cp_valorp, cp_nparcela, tc_codigo, func_codigo, caixa_codigo, cp_datavencimento, cp_obs "
                    + "FROM conta_pagar "
                    + "WHERE cp_dtpago is null and caixa_codigo is null "
                    + "Order by cp_datavencimento;";
        return Banco.getCon().retornaResultSet(sql);
    }
    
    public boolean buscaCaixa(int codigo)
    {
        //int codigo, Funcionario funcI, Funcionario funcF, double saldoI, double saldoF, double valorR, LocalDateTime data
        String sql;
        sql = "select * "
                + " from caixa "
                + " where caixa_codigo = "+codigo+" and func_fechar is null "; 
                ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return true;
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return false;
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
        String sql = "select sum(cp_valorp) from conta_pagar "
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
        }
        return 0;
    }
    
    public boolean verificaPago()
    {
        String sql = "select cp_codigo from conta_pagar "
                   + " where cp_codigo = "+codigo+" and cp_dtpago is null";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return true;
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public int verificaPago(int codigo)
    {
        String sql = "select max(cp_codigo) from conta_pagar "
                   + " where cp_nparcela = "+codigo+" and cp_dtpago is null";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return 0;
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    public static ResultSet buscarDadosCLD(Date Inicio, Date Fim, int tipo)//CLancarDespesa
    {
        String query = null;
        switch (tipo)
        {
            case 0:
            {
                query = "SELECT cp_codigo, comp_codigo, cp_data, cp_local, cp_valorc, cp_dtpago, cp_valorp, cp_nparcela, tc_codigo, func_codigo, caixa_codigo, cp_datavencimento, cp_obs "
                      + "FROM conta_pagar "
                      + "WHERE cp_dtpago is null and cp_datavencimento = '"+Inicio+"' "
                      + "Order by cp_datavencimento;";                
                break;
            }
            case 1:
            {
                query = "SELECT cp_codigo, comp_codigo, cp_data, cp_local, cp_valorc, cp_dtpago, cp_valorp, cp_nparcela, tc_codigo, func_codigo, caixa_codigo, cp_datavencimento, cp_obs "
                      + "FROM conta_pagar "
                      + "WHERE cp_dtpago is null and cp_datavencimento BETWEEN '"+Inicio+"' and '"+Fim+"' "
                      + "Order by cp_datavencimento;";                
                break;
            }
            default:
                    query = "SELECT cp_codigo, comp_codigo, cp_data, cp_local, cp_valorc, cp_dtpago, cp_valorp, cp_nparcela, tc_codigo, func_codigo, caixa_codigo, cp_datavencimento, cp_obs "
                    + "FROM conta_pagar "
                    + "WHERE cp_dtpago is null "
                    + "Order by cp_datavencimento;";    
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static ResultSet buscarDadosCEstornoCP(Date Inicio, Date Fim, int tipo)//CLancarDespesa
    {
        String query = null;
        switch (tipo)
        {
            case 0: //Data vencimento
            {
                query = "SELECT cp_codigo, cp_valorc, cp_valorp, cp_datavencimento, cp_dtpago, cp_obs "
                      + "FROM conta_pagar "
                      + "WHERE cp_dtpago is not null and cp_datavencimento = '"+Inicio+"' "
                      + "Order by cp_datavencimento;";                
                break;
            }
            case 1://Periodo vencimento
            {
                query = "SELECT cp_codigo, cp_valorc, cp_valorp, cp_datavencimento, cp_dtpago, cp_obs "
                      + "FROM conta_pagar "
                      + "WHERE cp_dtpago is not null and cp_datavencimento BETWEEN '"+Inicio+"' and '"+Fim+"' "
                      + "Order by cp_datavencimento;";                
                break;
            }
            case 2://Data Pagamento
            {
                query = "SELECT cp_codigo, cp_valorc, cp_valorp, cp_datavencimento, cp_dtpago, cp_obs "
                      + "FROM conta_pagar "
                      + "WHERE cp_dtpago is not null and cp_dtpago BETWEEN '"+Inicio+"' and '"+Fim+"' "
                      + "Order by cp_datavencimento;";                
                break;
            }
            case 3://Periodo Pagamento
            {
                query = "SELECT cp_codigo, cp_valorc, cp_valorp, cp_datavencimento, cp_dtpago, cp_obs "
                      + "FROM conta_pagar "
                      + "WHERE cp_dtpago is not null and cp_dtpago BETWEEN '"+Inicio+"' and '"+Fim+"' "
                      + "Order by cp_datavencimento;";                
                break;
            }
            default:
                    query = "SELECT cp_codigo, cp_valorc, cp_valorp, cp_datavencimento, cp_dtpago, cp_obs "
                    + "FROM conta_pagar "
                    + "WHERE cp_dtpago is not null "
                    + "Order by cp_datavencimento;";    
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static void configuraModelCLD(JTable jTable) // CLancarDespesa
    {
        String colunas[] = new String [] {"Código", "Valor da Conta", "Data Vencimento", "Obs"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(300);
    }
    
    public static void configuraModelCEstornoCP(JTable jTable) 
    {
        String colunas[] = new String [] {"Código", "Valor da Conta", "Valor Pago", "Data Vencimento", "Data Pagamento", "Obs"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(300);
    }
    
}
