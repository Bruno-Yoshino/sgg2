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
public class Orcamento_Servico_Detalhe {
    private DetalheServico ds;
    private int numeracaoI, numeracaoF, vias;
    private String outros;
    private int sequence;

    public Orcamento_Servico_Detalhe(DetalheServico ds, int numeracaoI, int numeracaoF, int vias, String outros, int sequence) {
        this.ds = ds;
        this.numeracaoI = numeracaoI;
        this.numeracaoF = numeracaoF;
        this.vias = vias;
        this.outros = outros;
        this.sequence = sequence;
    }

    public Orcamento_Servico_Detalhe() {
    }

    public DetalheServico getDs() {
        return ds;
    }

    public void setDs(DetalheServico ds) {
        this.ds = ds;
    }

    public int getNumeracaoI() {
        return numeracaoI;
    }

    public void setNumeracaoI(int numeracaoI) {
        this.numeracaoI = numeracaoI;
    }

    public int getNumeracaoF() {
        return numeracaoF;
    }

    public void setNumeracaoF(int numeracaoF) {
        this.numeracaoF = numeracaoF;
    }

    public int getVias() {
        return vias;
    }

    public void setVias(int vias) {
        this.vias = vias;
    }

    public String getOutros() {
        return outros;
    }

    public void setOutros(String outros) {
        this.outros = outros;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
    
    public String CreatingDeleteSQLComand(int codigoO, int codigoS, int codigoD, int sequencia)
    {
        String sql = "delete form orcamento_servico_detalhe where orc_codigo = "+codigoO+" and serv_codigo = "+codigoS+" and ds_codigo = "+codigoD+" and os_sequence = "+sequencia+"";
        return sql;
    }
    
    public boolean gravar(int codigoO, int codigoS, int codigoOS)
    {
        String sql =  "INSERT INTO orcamento_servico_detalhe( " +
                    " orc_numero, serv_codigo, ds_codigo, osd_numeracaoini, osd_numeracaofim, osd_vias, osd_outros, os_sequence) " +
                    " VALUES ("+codigoO+", "+codigoS+", "+ds.getCodigo()+", "+numeracaoI+", "+numeracaoF+", "+vias+", '"+outros+"', "+sequence+");";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(int codigoO, int codigoS)
    {
        String sql =  "UPDATE orcamento_servico_detalhe" +
                      " SET serv_codigo="+codigoS+", ds_codigo="+ds.getCodigo()+", osd_numeracaoini="+numeracaoI+", osd_numeracaofim="+numeracaoF+", osd_vias="+vias+", osd_outros='"+outros+"' " +
                      " WHERE orc_numero="+codigoO+" and os_sequence="+sequence+";";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigoO, int sequence)
    {
        String sql = "DELETE FROM orcamento_servico_detalhe " +
                     " WHERE orc_numero="+codigoO+" and os_sequence = "+sequence+";";
        return Banco.getCon().manipular(sql); 
    }
    
    public boolean excluir(int codigoO, int sequence, int codigoD)
    {
        String sql = "DELETE FROM orcamento_servico_detalhe " +
                     " WHERE orc_numero="+codigoO+" and os_sequence = "+sequence+" and ds_codigo = "+codigoD+";";
        return Banco.getCon().manipular(sql); 
    }
    
    public boolean excluir(int codigoO)
    {
        String sql = "DELETE FROM orcamento_servico_detalhe " +
                     " WHERE orc_numero="+codigoO+";";
        return Banco.getCon().manipular(sql); 
    }
    
    public boolean executeDelete(String comand)
    {
        return Banco.getCon().manipular(comand);  
    }
    
    public boolean ChecarExiste(int codigoO, int sequence, int descCodigo)
    {
        String sql;
        sql = "select * from orcamento_servico_detalhe where orc_numero="+codigoO+" and os_sequence = "+sequence+" and ds_codigo = "+descCodigo+"";
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
    
    public ArrayList<Orcamento_Servico_Detalhe> buscar (int codigo, int sequence)
    {
        ArrayList<Orcamento_Servico_Detalhe> lista = new ArrayList<>();
        String sql;
        sql = "SELECT orc_numero, serv_codigo, ds_codigo, osd_numeracaoini, osd_numeracaofim, osd_vias, osd_outros, os_sequence " +
              " FROM orcamento_servico_detalhe"
            + " WHERE orc_numero = "+codigo+" and os_sequence = "+sequence+";";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            while (rs.next()) 
            {
                lista.add(new Orcamento_Servico_Detalhe(new DetalheServico().buscarCodigo(rs.getInt(3)), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8)));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return lista;
    }
}
