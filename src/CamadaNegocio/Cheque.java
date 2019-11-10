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
 * @author 巴御前
 * @author 高村　結衣
 * @author 里川　麗奈
 * @author 川波　愁子
 * @author 水川　鈴奈
 * @author 嶌田　治奈
 * @author 小枩　夏輝
 * @author 阿賀野
 * @author 矢矧
 * @author ウィリアム
 */
public class Cheque {
   private int codigo;
   private ContaReceber cr;
   private String dono;
   private String cpf;
   private double valor;
   private Date data;
   private Date predata;
   private String nAgencia;
   private String nConta;
   private String nBanco;
   private String nCheque;
   private String obs;
   private Date dataComp;
   private int motivo;
   private String cliente;

    public Cheque(int codigo, ContaReceber cr, String dono, String cpf, double valor, Date data, Date predata, String nAgencia, String nConta, String nBanco, String nCheque, String obs, Date dataComp, int motivo, String cliente) {
        this.codigo = codigo;
        this.cr = cr;
        this.dono = dono;
        this.cpf = cpf;
        this.valor = valor;
        this.data = data;
        this.predata = predata;
        this.nAgencia = nAgencia;
        this.nConta = nConta;
        this.nBanco = nBanco;
        this.nCheque = nCheque;
        this.obs = obs;
        this.dataComp = dataComp;
        this.motivo = motivo;
        this.cliente = cliente;
    }
   
    public Cheque() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ContaReceber getCr() {
        return cr;
    }

    public void setCr(ContaReceber cr) {
        this.cr = cr;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getPredata() {
        return predata;
    }

    public void setPredata(Date predata) {
        this.predata = predata;
    }

    public String getnAgencia() {
        return nAgencia;
    }

    public void setnAgencia(String nAgencia) {
        this.nAgencia = nAgencia;
    }

    public String getnConta() {
        return nConta;
    }

    public void setnConta(String nConta) {
        this.nConta = nConta;
    }

    public String getnBanco() {
        return nBanco;
    }

    public void setnBanco(String nBanco) {
        this.nBanco = nBanco;
    }

    public String getnCheque() {
        return nCheque;
    }

    public void setnCheque(String nCheque) {
        this.nCheque = nCheque;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Date getDataComp() {
        return dataComp;
    }

    public void setDataComp(Date dataComp) {
        this.dataComp = dataComp;
    }

    public int getMotivo() {
        return motivo;
    }

    public void setMotivo(int motivo) {
        this.motivo = motivo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
       
   public boolean gravar()
   {
        String sql;
        if(this.codigo == 0)
        {
            sql = "INSERT INTO cheque (cr_codigo, c_dono, c_cpfdono, c_valor, c_datal, c_predata, c_nagencia, c_nconta, c_nbanco, c_ncheque, c_obs, c_datacomp, c_motivo, c_cliente) VALUES "
                    + " ("+(cr == null ? null : cr.getCodigo())+", '"+dono+"', '"+cpf+"', "+valor+", '"+data+"', '"+predata+"', '"+nAgencia+"', '"+nConta+"', '"+nBanco+"', '"+nCheque+"', '"+obs+"', "+null+", 0, '"+cliente+"')";
        }
        else
        {
             sql = "UPDATE cheque set cr_codigo = "+(cr == null ? null : cr.getCodigo())+", c_dono = '"+dono+"', c_cpfdono = '"+cpf+"', "
                     + " c_valor = "+valor+", c_datal = '"+data+"', c_predata = '"+predata+"', "
                     + " c_nagencia = '"+nAgencia+"', c_nconta = '"+nConta+"', c_nbanco = '"+nBanco+"', c_ncheque = '"+nCheque+"',"
                     + " c_obs = '"+obs+"', c_datacomp = "+null+", c_motivo = 0, c_cliente = '"+cliente+"' where c_codigo = "+codigo+" ";
        }
        return Banco.getCon().manipular(sql);
   }
   
   public boolean compensar()
   {
       String sql = "UPDATE cheque set c_datacomp = '"+dataComp+"', c_motivo = "+motivo+" where c_codigo = "+codigo+"";
       return Banco.getCon().manipular(sql);
   }
   
   public boolean excluir()
   {
        String sql = "delete from cheque where c_codigo = "+codigo+"";
        return Banco.getCon().manipular(sql);
   }
   
   public Cheque buscar(int codigo)
   {
        String sql;
        sql = "select c_codigo, cr_codigo, c_dono, c_cpfdono, c_valor, c_datal, c_predata, c_nagencia, c_nconta, c_nbanco, c_ncheque, c_obs, c_datacomp, c_motivo, c_cliente "
                + " from cheque where c_codigo = "+codigo+"";
                ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Cheque(rs.getInt(1), new ContaReceber().buscar(rs.getInt(2)), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getDate(6), rs.getDate(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getDate(13), rs.getInt(14), rs.getString(15));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
   }
   
   public ArrayList<Cheque> buscarCheques(int codigoCR)
   {
        String sql;
        ArrayList<Cheque> lista = new ArrayList<>();
        sql = "select c.c_codigo, c.cr_codigo, c.c_dono, c.c_cpfdono, c.c_valor, c_datal, c.c_predata, c.c_nagencia, c.c_nconta, c.c_nbanco, c.c_ncheque, c.c_obs, c.c_datacomp, c.c_motivo, c.c_cliente "
                + " from cheque c where c.cr_codigo = "+codigoCR+"";
                ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            while (rs.next()) 
            {
                lista.add(new Cheque(rs.getInt(1), new ContaReceber().buscar(rs.getInt(2)), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getDate(6), rs.getDate(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getDate(13), rs.getInt(14), rs.getString(15)));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return lista;
   }
   
   public Cheque buscarMax()
   {
        String sql;
        sql = "select c_codigo, cr_codigo, c_dono, c_cpfdono, c_valor, c_datal, c_predata, c_nagencia, c_nconta, c_nbanco, c_ncheque, c_obs, c_datacomp, c_motivo, c_cliente "
                + " from cheque "
                + " where c_codigo = (select max(c_codigo)from cheque )";
//                + " GROUP BY c_codigo, cr_codigo, c_dono, c_cpfdono, c_valor, c_datal, c_predata, c_nagencia, c_nconta, c_nbanco, c_ncheque, c_obs, c_datacomp, c_motivo, c_cliente " +
//                  " having c_codigo = max(c_codigo);";
                ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Cheque(rs.getInt(1), new ContaReceber().buscar(rs.getInt(2)), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getDate(6), rs.getDate(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getDate(13), rs.getInt(14), rs.getString(15));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
   }
   
    public static ResultSet buscarDados(String valor, int tipo)//Para consulta
    {
      
        String query = null;
        if (valor.equals(""))
        {
            query = "SELECT c_codigo, cr_codigo, c_dono, c_cpfdono, c_valor, c_datal, c_predata, c_nagencia, c_nconta, c_nbanco, c_ncheque, c_obs, c_datacomp, c_motivo, c_cliente\n" +
                    " FROM cheque "
                  + " WHERE c_datacomp is null"
                  + " ORDER BY c_dono;";
        }
        else
        {
            switch (tipo)
            {
                case 0: //Dono
                {
                    query = "SELECT c_codigo, cr_codigo, c_dono, c_cpfdono, c_valor, c_datal, c_predata, c_nagencia, c_nconta, c_nbanco, c_ncheque, c_obs, c_datacomp, c_motivo, c_cliente\n" +
                            " FROM cheque "
                          + " Where c_dono ilike '"+valor+"' and c_datacomp is null"
                          + " ORDER BY c_dono;"; 
                    break;
                }
                case 1: //Data Lancado
                {
                    query = "SELECT c_codigo, cr_codigo, c_dono, c_cpfdono, c_valor, c_datal, c_predata, c_nagencia, c_nconta, c_nbanco, c_ncheque, c_obs, c_datacomp, c_motivo, c_cliente\n" +
                            " FROM cheque "
                          + " Where c_datal = '"+valor+"' and c_datacomp is null "
                          + " ORDER BY c_dono;";
                    break;
                }
                default:
                     query = "SELECT c_codigo, cr_codigo, c_dono, c_cpfdono, c_valor, c_datal, c_predata, c_nagencia, c_nconta, c_nbanco, c_ncheque, c_obs, c_datacomp, c_motivo, c_cliente\n" +
                    " FROM cheque "
                  + " WHERE c_datacomp is null"
                  + " ORDER BY c_dono;";
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
   
   public static void configuraModel(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Código", "Dono", "CPF", "Valor", "Data Lançado", "Pré Data"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(150);
    }
   
}