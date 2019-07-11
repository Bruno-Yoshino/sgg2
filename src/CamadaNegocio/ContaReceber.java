package CamadaNegocio;

import CamadaLogica.Banco;
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
        String sql = "INSERT INTO  " +
                     " VALUES ();";
        
        return Banco.getCon().manipular(sql);
    }
    
}
