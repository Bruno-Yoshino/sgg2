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
        if(codigo == 0)
        {
            sql = "INSERT INTO orcamento( " +
                  " cli_codigo, func_codigo, orc_valortotal, orc_dataorc, orc_validade, fpg_codigo) " +
                  " VALUES ("+cli.getCodigo()+", "+f.getCodigo()+", "+valorTotal+", '"+orcado+"', '"+validade+"', "+fp.getCodigo()+");";
        }
        else
        {
            sql = "UPDATE orcamento " +
                  " SET cli_codigo="+cli.getCodigo()+", func_codigo="+f.getCodigo()+", orc_valortotal="+valorTotal+", orc_dataorc='"+orcado+"', orc_validade='"+validade+"', fpg_codigo="+fp.getCodigo()+" " +
                  " WHERE orc_numero="+codigo+";";
        }
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo)
    {
        String sql = "DELETE FROM orcamento " +
                     " WHERE orc_numero="+codigo+";";
        return Banco.getCon().manipular(sql); 
    }
    
    public final int UltimoCodigo() throws SQLException
    {
        String sql = "select max(orc_numero) from orcamento";
        ResultSet rs=Banco.getCon().consultar(sql);
        if(rs.next())
        {
            return rs.getInt(1);
        }
        return 0;
    }
    
    public Orcamento buscar(int codigo) throws SQLException
    {
        Orcamento temp = new Orcamento();
        String sql = "SELECT orc_numero, cli_codigo, func_codigo, orc_valortotal, orc_dataorc, orc_validade, fpg_codigo " +
                     " FROM public.orcamento "
                    + " WHERE orc_numero = "+codigo+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        if(rs.next())
        {
            temp.setCodigo(rs.getInt(1));
            temp.setCli(new Cliente().buscarCodigo(rs.getInt(2)));
            temp.setValorTotal(rs.getDouble(4));
            temp.setOrcado(rs.getDate(5));
            temp.setValidade(rs.getDate(6));
            temp.setFp(new FormaPagamento().buscaForma(rs.getInt(7)));
        }
        temp.setLista(new Orcamento_Servico().buscar(temp.getCodigo()));
        return temp;
    }
}
