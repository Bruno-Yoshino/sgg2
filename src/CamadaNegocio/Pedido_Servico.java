package CamadaNegocio;

import CamadaLogica.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 巴御前
 * @author 高村　結衣
 * @author 里川　麗奈
 * @author 鳳翔
 * @author 川村
 * @author 磐手
 * @author イントレピッド
 * @author 七海
 * @author 海女
 * @author 御子
 * @author 稲荷
 */
public class Pedido_Servico {
    private Servico serv;
    private double valor;
    private int qtd;
    private double desconto;
    private String descricao;
    private int sequence;
    private ArrayList<Pedido_Servico_Detalhe> lista;

    public Pedido_Servico(Servico serv, double valor, int qtd, double desconto, String descricao, int sequence, ArrayList<Pedido_Servico_Detalhe> lista) {
        this.serv = serv;
        this.valor = valor;
        this.qtd = qtd;
        this.desconto = desconto;
        this.descricao = descricao;
        this.sequence = sequence;
        this.lista = lista;
    }

    public Pedido_Servico() {
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

    public ArrayList<Pedido_Servico_Detalhe> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Pedido_Servico_Detalhe> lista) {
        this.lista = lista;
    }
    
    public boolean gravar(int codigoP)
    {
        String sql =  "INSERT INTO pedido_servico( " +
                  " pe_codigo, serv_codigo, pe_valor, pe_qtd, pe_desconto, pe_descricao, pe_sequence) " +
                  " VALUES ("+codigoP+", "+serv.getCodigo()+", "+valor+", "+qtd+", "+desconto+", '"+descricao+"', "+sequence+");";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(int codigoP)
    {
        String sql =  "UPDATE pedido_servico " +
                  " SET serv_codigo="+serv.getCodigo()+", pe_valor="+valor+", pe_qtd="+qtd+", pe_desconto="+desconto+", pe_descricao='"+descricao+"' " +
                  " WHERE pe_codigo="+codigoP+" and pe_sequence = "+sequence+";";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigoP, int sequence)
    {
        String sql = "DELETE FROM pedido_servico " +
                     " WHERE pe_codigo="+codigoP+" and pe_sequence = "+sequence+";";
        return Banco.getCon().manipular(sql); 
    }
    
    public boolean excluir(int codigoP)
    {
        String sql = "DELETE FROM pedido_servico " +
                     " WHERE pe_codigo="+codigoP+";";
        return Banco.getCon().manipular(sql); 
    }
    
    public boolean ChecarExiste(int codigoP, int sequence)
    {
        String sql;
        sql = "select * from pedido_servico where pe_codigo="+codigoP+" and pe_sequence = "+sequence+"";
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
    
    public ArrayList<Pedido_Servico> buscar(int codigo)
    {
        ArrayList<Pedido_Servico> lista = new ArrayList<>();
        String sql;
        sql = "SELECT pe_codigo, serv_codigo, pe_valor, pe_qtd, pe_desconto, pe_descricao, pe_sequence " +
              " FROM public.pedido_servico"
            + " WHERE pe_codigo = "+codigo+";";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            while (rs.next()) 
            {
                lista.add(new Pedido_Servico(new Servico().buscarCodigo(rs.getInt(2)), rs.getDouble(3), rs.getInt(4), rs.getDouble(5), rs.getString(6), rs.getInt(7), new Pedido_Servico_Detalhe().buscar(rs.getInt(1), rs.getInt(7))));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return lista;
    }
}
