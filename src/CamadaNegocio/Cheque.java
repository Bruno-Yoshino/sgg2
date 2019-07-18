package CamadaNegocio;

import CamadaLogica.Banco;
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
        if(this.codigo == 0) // Abrir
        {
            sql = "";
        }
        else
        {
            sql = "";
        }
        return Banco.getCon().manipular(sql);
   }
   
   
   
}
