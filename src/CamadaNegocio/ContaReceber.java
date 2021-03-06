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
    private Caixa caixa;

    public ContaReceber() {
        
    }

    public ContaReceber(int codigo, Pedido p, Date dataV, String obs, double valor, Date dataP, double valorP, boolean flag, Caixa caixa) {
        this.codigo = codigo;
        this.p = p;
        this.dataV = dataV;
        this.obs = obs;
        this.valor = valor;
        this.dataP = dataP;
        this.valorP = valorP;
        this.flag = flag;
        this.caixa = caixa;
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
    
        public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }
    //--------------------------------------------------------------------------
    public boolean gravar()
    {
        String sql = "INSERT INTO conta_receber (pe_codigo, cr_datavenc, cr_obs, cr_valor, cr_datapago, cr_vlorp, cr_flag, caixa_codigo) "
                   + " values ("+p.getCodigo()+", '"+dataV+"', '', "+valor+", "+null+", "+0+", "+flag+", "+new Caixa().buscaCaixa().getCodigo()+");";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar()
    {
        String sql = "update conta_receber set cr_obs = '"+obs+"', cr_datapago = '"+dataP+"', cr_vlorp = "+valorP+" "
                   + " where cr_codigo = "+codigo+";";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluirContaReceberPedido(int codigoP)
    {
        String sql = "delete from conta_receber where pe_codigo = "+codigoP+";";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterarvalorConta(int codigo)
    {
        String sql = "update conta_receber set cr_valor = "+valor+" "
                   + " where cr_codigo = "+codigo+";";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean estornarValor(int codigo)
    {
        String sql = "update conta_receber set cr_obs = '', cr_datapago = null, cr_vlorp = 0 "
                   + " where cr_codigo = "+codigo+";";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir()
    {
        String sql = "delete from conta_receber where cr_codigo = "+codigo+";";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluirCheques(int codigo)
    {
        String sql = "delete from cheque where cr_codigo = "+codigo+";";
        return Banco.getCon().manipular(sql);
    }
    
    public ContaReceber buscaContaReceber(int codigo) throws SQLException
    {
        //int codigo, Pedido p, Date dataV, String obs, double valor, Date dataP, double valorP
        String sql = "SELECT cr_codigo, pe_codigo, cr_datavenc, cr_obs, cr_valor, cr_datapago, cr_vlorp, cr_flag, caixa_codigo " +
                     " FROM public.conta_receber"
                   + " WHERE cr_codigo = "+codigo+";";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new ContaReceber(rs.getInt(1), new Pedido().buscar(rs.getInt(2)), rs.getDate(3), rs.getString(4), rs.getDouble(5), rs.getDate(6), rs.getDouble(7), rs.getBoolean(8), new Caixa().buscaCaixa(rs.getInt(9)));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
        //p = new Pedido().buscar(codigo);
    }
    
    public int buscarForma(int codigo) throws SQLException
    {
        Pedido temp = new Pedido();
        String sql = "SELECT fpg_codigo " +
                     " FROM pedido "
                    +" WHERE pe_codigo = "+codigo+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        if(rs.next())
        {
            return rs.getInt(1);
        }
        return 0;
    }
    
    public ContaReceber buscar(int codigo)
    {
        String sql;
        sql = "select cr_codigo, pe_codigo, cr_datavenc, cr_obs, cr_valor, cr_datapago, cr_vlorp, cr_flag, caixa_codigo "
                + " from conta_receber where cr_codigo = "+codigo+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new ContaReceber(rs.getInt(1), new Pedido().buscar(rs.getInt(2)), rs.getDate(3), rs.getString(4), rs.getDouble(5), rs.getDate(6), rs.getDouble(7), rs.getBoolean(8), new Caixa().buscaCaixa(rs.getInt(9)));
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
        sql = "select cr_codigo, pe_codigo, cr_datavenc, cr_obs, cr_valor, cr_datapago, cr_vlorp, cr_flag, caixa_codigo "
                + " from conta_receber where pe_codigo = "+codigoP+" order by cr_datavenc";
                ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            while (rs.next()) 
            {
                lista.add(new ContaReceber(rs.getInt(1), new Pedido().buscar(rs.getInt(2)), rs.getDate(3), rs.getString(4), rs.getDouble(5), rs.getDate(6), rs.getDouble(7), rs.getBoolean(8), new Caixa().buscaCaixa(rs.getInt(9))));
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
    
    public int QtdParcela(int codigo)
    {
        String sql;
        sql = "select count(*) "
                + " from conta_receber where pe_codigo = "+codigo+"";
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
    
    public int minCodioParcela(int codigo)
    {
        String sql;
        sql = "select min(cr_codigo) "
                + " from conta_receber where pe_codigo = "+codigo+" and cr_datapago is null;";
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
                sql = "select c.cli_nome, cr.pe_codigo, cr.cr_valor, cr.cr_datavenc, p.pe_datapedido, cr.cr_codigo, fp.fpg_nome "
                    + " from conta_receber cr, pedido p, cliente c, forma_pagamento fp "
                    + " where cr.cr_datapago is null and p.pe_codigo = cr.pe_codigo and p.cli_codigo = c.cli_codigo and fp.fpg_codigo = p.fpg_codigo "
                    + " order by c.cli_nome;";
                break;
            case 2: // data vencimento
                //sql = "select cr.cr_codigo, cr.pe_codigo, cr.cr_datavenc, cr.cr_obs, cr.cr_valor, cr.cr_datapago, cr.cr_vlorp "
                sql = "select c.cli_nome, cr.pe_codigo, cr.cr_valor, cr.cr_datavenc, p.pe_datapedido, cr.cr_codigo, fp.fpg_nome "
                    + " from conta_receber cr, pedido p, cliente c, forma_pagamento fp "
                    + " where cr.cr_datapago is null and p.pe_codigo = cr.pe_codigo and p.cli_codigo = c.cli_codigo and fp.fpg_codigo = p.fpg_codigo "
                    + " order by cr.cr_datavenc;";
                break;
            default:
                sql = null;
        }
        return Banco.getCon().retornaResultSet(sql);
    }
    
    public static ResultSet buscarDadosHome()
    {
        String sql = "select c.cli_nome, cr.cr_valor, cr.cr_datavenc "
                    + " from conta_receber cr, pedido p, cliente c "
                    + " where cr.cr_datapago is null and p.pe_codigo = cr.pe_codigo and p.cli_codigo = c.cli_codigo "
                    + " order by c.cli_nome;";
        return Banco.getCon().retornaResultSet(sql);
    }
    
    public static ResultSet buscarDadosRel(int codigo)//Para Emitir Comprovante
    {
        String sql = "select cli.cli_nome, c.cr_codigo, c.cr_valor, c.cr_vlorp, c.cr_datapago, e.emp_caminho " +
                    "from cliente cli, conta_receber c, pedido p, empresa e " +
                    "where c.cr_codigo = "+codigo+" and " +
                    "cli.cli_codigo = p.cli_codigo and " +
                    "p.pe_codigo = c.pe_codigo";
        return Banco.getCon().retornaResultSet(sql);
    }
    
    public static ResultSet buscarDadosCEstornoCR(Date Inicio, Date Fim, int tipo)//CLancarDespesa
    {
        String query = null; /// falta alterar o sql
        switch (tipo)
        {
            case 0: //Data vencimento
            {
                query = "SELECT cr_codigo, cr_valor, cr_vlorp, cr_datavenc, cr_datapago, cr_obs "
                      + "FROM conta_receber "
                      + "WHERE cr_datapago is not null and cr_datavenc = '"+Inicio+"' "
                      + "Order by cr_datavenc;";                
                break;
            }
            case 1://Periodo vencimento
            {
                query = "SELECT cr_codigo, cr_valor, cr_vlorp, cr_datavenc, cr_datapago, cr_obs "
                      + "FROM conta_receber "
                      + "WHERE cr_datapago is not null and cr_datavenc BETWEEN '"+Inicio+"' and '"+Fim+"' "
                      + "Order by cr_datavenc;";                
                break;
            }
            case 2://Data Pagamento
            {
                query = "SELECT cr_codigo, cr_valor, cr_vlorp, cr_datavenc, cr_datapago, cr_obs "
                      + "FROM conta_receber "
                      + "WHERE cr_datapago is not null and cr_datapago BETWEEN '"+Inicio+"' and '"+Fim+"' "
                      + "Order by cr_datavenc;";                
                break;
            }
            case 3://Periodo Pagamento
            {
                query = "SELECT cr_codigo, cr_valor, cr_vlorp, cr_datavenc, cr_datapago, cr_obs "
                      + "FROM conta_receber "
                      + "WHERE cr_datapago is not null and cr_datapago BETWEEN '"+Inicio+"' and '"+Fim+"' "
                      + "Order by cr_datavenc;";                
                break;
            }
            default:
                    query = "SELECT cr_codigo, cr_valor, cr_vlorp, cr_datavenc, cr_datapago, cr_obs "
                    + "FROM conta_receber "
                    + "WHERE cr_datapago is not null "
                    + "Order by cr_datavenc;";    
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static void configuraModelCEstornoCR(JTable jTable) 
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
