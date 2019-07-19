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
   private String motivo;

    public Cheque(int codigo, ContaReceber cr, String dono, String cpf, double valor, Date data, Date predata, String nAgencia, String nConta, String nBanco, String nCheque, String obs, Date dataComp, String motivo) {
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
   
   public boolean gravar()
   {
        String sql;
        if(this.codigo == 0)
        {
            sql = "INSERT INTO cheque (cr_codigo, c_dono, c_cpfdono, c_valor, c_datal, c_predata, c_nagencia, c_nconta, c_nbanco, c_ncheque, c_obs, c_datacomp, c_motivo) VALUES "
                    + " ("+cr == null ? null : cr.getCodigo()+", '"+dono+"', '"+cpf+"', "+valor+", '"+data+"', '"+predata+"', '"+nAgencia+"', '"+nConta+"', '"+nBanco+"', '"+nCheque+"', '"+obs+"', '"+null+"', '')";
        }
        else
        {
            sql = "UPDATE cheque set c_datacomp = '"+dataComp+"', c_motivo = '"+motivo+"' where c_codigo = "+codigo+"";
        }
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
        sql = "select c_codigo, cr_codigo, c_dono, c_cpfdono, c_valor, c_datal, c_predata, c_nagencia, c_nconta, c_nbanco, c_ncheque, c_obs, c_datacomp, c_motivo "
                + " from cheque where c_codigo = "+codigo+"";
                ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Cheque(rs.getInt(1), new ContaReceber().buscar(rs.getInt(2)), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getDate(6), rs.getDate(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getDate(13), rs.getString(14));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
   }
   
}
