package CamadaNegocio;

import CamadaLogica.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 阿武隈
 * @author 長門
 * @author 大和
 * @author 阿賀野
 * @author 矢矧
 * @author 長良
 * @author 天野
 * @author 紅葉
 */
public class Orcamento {
    private int codigo;
    private Cliente cli;
    private Funcionario f;
    private double valorTotal;
    private FormaPagamento fp;
    private Date orcado, validade;
    private ArrayList<Orcamento_Servico> lista;

    public Orcamento(int codigo, Cliente cli, Funcionario f, double valorTotal, FormaPagamento fp, Date orcado, Date validade, ArrayList<Orcamento_Servico> lista) {
        this.codigo = codigo;
        this.cli = cli;
        this.f = f;
        this.valorTotal = valorTotal;
        this.fp = fp;
        this.orcado = orcado;
        this.validade = validade;
        this.lista = lista;
    }

    public Orcamento() {
        this.lista = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public Funcionario getF() {
        return f;
    }

    public void setF(Funcionario f) {
        this.f = f;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public FormaPagamento getFp() {
        return fp;
    }

    public void setFp(FormaPagamento fp) {
        this.fp = fp;
    }

    public Date getOrcado() {
        return orcado;
    }

    public void setOrcado(Date orcado) {
        this.orcado = orcado;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public ArrayList<Orcamento_Servico> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Orcamento_Servico> lista) {
        this.lista = lista;
    }
    
    public boolean gravar()
    {
        String sql = "";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir()
    {
        String sql = "";
        return Banco.getCon().manipular(sql); 
    }
    
    public int UltimoCodigo()
    {
        String sql = "";
        ResultSet rs=Banco.getCon().consultar(sql);
        return 0;
    }
    
    
}
