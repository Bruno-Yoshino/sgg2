package CamadaNegocio;

import CamadaLogica.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
public class Orcamento_Servico {
    private Servico serv;
    private double valor;
    private int qtd;
    private double custoPapel, custoImpre, custoAcab, custoArte, custoChapa, custoMdO, desconto;
    private String descricao;
    private int sequence;
    private ArrayList<Orcamento_Servico_Detalhe> lista;

    public Orcamento_Servico(Servico serv, double valor, int qtd, double custoPapel, double custoImpre, double custoAcab, double custoArte, double custoChapa, double custoMdO, double desconto, String descricao, int sequence, ArrayList<Orcamento_Servico_Detalhe> lista) {
        this.serv = serv;
        this.valor = valor;
        this.qtd = qtd;
        this.custoPapel = custoPapel;
        this.custoImpre = custoImpre;
        this.custoAcab = custoAcab;
        this.custoArte = custoArte;
        this.custoChapa = custoChapa;
        this.custoMdO = custoMdO;
        this.desconto = desconto;
        this.descricao = descricao;
        this.sequence = sequence;
        this.lista = lista;
    }

    public Orcamento_Servico() {
        this.lista = new ArrayList<>();
    }

    public Servico getServ() {
        return serv;
    }

    public void setServ(Servico serv) {
        this.serv = serv;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getCustoPapel() {
        return custoPapel;
    }

    public void setCustoPapel(double custoPapel) {
        this.custoPapel = custoPapel;
    }

    public double getCustoImpre() {
        return custoImpre;
    }

    public void setCustoImpre(double custoImpre) {
        this.custoImpre = custoImpre;
    }

    public double getCustoAcab() {
        return custoAcab;
    }

    public void setCustoAcab(double custoAcab) {
        this.custoAcab = custoAcab;
    }

    public double getCustoArte() {
        return custoArte;
    }

    public void setCustoArte(double custoArte) {
        this.custoArte = custoArte;
    }

    public double getCustoChapa() {
        return custoChapa;
    }

    public void setCustoChapa(double custoChapa) {
        this.custoChapa = custoChapa;
    }

    public double getCustoMdO() {
        return custoMdO;
    }

    public void setCustoMdO(double custoMdO) {
        this.custoMdO = custoMdO;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public ArrayList<Orcamento_Servico_Detalhe> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Orcamento_Servico_Detalhe> lista) {
        this.lista = lista;
    }
    
    public boolean gravar(int codigoO)
    {
        String sql =  "INSERT INTO orcamento_servico( " +
                  " orc_numero, serv_codigo, os_valor, os_qtd, os_custopapel, os_custoimpressao, os_custoacabamento, os_custoarte, os_custochapa, os_customdo, os_desconto, os_descricao, os_sequence) " +
                  " VALUES ("+codigoO+", "+serv.getCodigo()+", "+valor+", "+qtd+", "+custoPapel+", "+custoImpre+", "
                    + ""+custoAcab+", "+custoArte+", "+custoChapa+", "+custoMdO+", "+desconto+", '"+descricao+"', "+sequence+");";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(int codigoO)
    {
        String sql =  "UPDATE orcamento_servico " +
                  " SET serv_codigo="+serv.getCodigo()+", os_valor="+valor+", os_qtd="+qtd+", os_custopapel="+custoPapel+", os_custoimpressao="+custoImpre+", os_custoacabamento="+custoAcab+", os_custoarte="+custoArte+", os_custochapa="+custoChapa+", os_customdo="+custoMdO+", os_desconto="+desconto+", os_descricao='"+descricao+"' " +
                  " WHERE orc_numero="+codigoO+" and os_sequence = "+sequence+";";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigoO, int sequence)
    {
        String sql = "DELETE FROM orcamento_servico " +
                     " WHERE orc_numero="+codigoO+" and os_sequence = "+sequence+";";
        return Banco.getCon().manipular(sql); 
    }
    
    public boolean excluir(int codigoO)
    {
        String sql = "DELETE FROM orcamento_servico " +
                     " WHERE orc_numero="+codigoO+";";
        return Banco.getCon().manipular(sql); 
    }
    
    public boolean ChecarExiste(int codigoO, int sequence)
    {
        String sql;
        sql = "select * from orcamento_servico where orc_numero="+codigoO+" and os_sequence = "+sequence+"";
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
    
    public ArrayList<Orcamento_Servico> buscar(int codigo)
    {
        ArrayList<Orcamento_Servico> lista = new ArrayList<>();
        String sql;
        sql = "SELECT orc_numero, serv_codigo, os_valor, os_qtd, os_custopapel, os_custoimpressao, os_custoacabamento, os_custoarte, os_custochapa, os_customdo, os_desconto, os_descricao, os_sequence " +
              " FROM public.orcamento_servico"
            + " WHERE orc_numero = "+codigo+";";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            while (rs.next()) 
            {
                lista.add(new Orcamento_Servico(new Servico().buscarCodigo(rs.getInt(2)), rs.getDouble(3), rs.getInt(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10), rs.getDouble(11), rs.getString(12), rs.getInt(13), new Orcamento_Servico_Detalhe().buscar(rs.getInt(1), rs.getInt(13))));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return lista;
    }
}
