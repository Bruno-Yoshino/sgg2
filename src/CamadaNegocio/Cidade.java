/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CamadaNegocio;

import CamadaLogica.Banco;
import CamadaLogica.ReadOnlyTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author 阿武隈
 * @author 長門
 * @author 大和
 */
public class Cidade {
    private int codigo;
    private String nome;
    private Estado uf;

    public Cidade(int codigo, String nome, Estado uf) {
        this.codigo = codigo;
        this.nome = nome;
        this.uf = uf;
    }

    public Cidade() {
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getE() {
        return uf;
    }

    public void setE(Estado uf) {
        this.uf = uf;
    }
    
    //------------------------------------DAO------------------------------------
    
    public boolean gravar()  // using This
    {
        String sql;
        if(this.codigo == 0)
        {
            sql = "insert into cidade (cid_nome, uf_codigo) values ('"+this.nome+"', "+this.uf.getCodigo()+")";
        }
        else
        {
            sql = "update cidade set cid_nome = '"+this.nome+"', uf_codigo = "+this.uf.getCodigo()+" where cid_codigo = "+this.codigo+"";
        }
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo)
    {
        String sql;
        sql = "delete from cidade where cid_codigo = "+codigo+"";
        return Banco.getCon().manipular(sql);
    }
    
    public Cidade buscarNome(String s)
    {
        String sql;
        sql = "select * from cidade where cid_nome like '%"+s+"%'";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Cidade(rs.getInt("cid_codigo"), rs.getString("cid_nome"), new Estado().buscarCodigo(rs.getInt("uf_codigo")));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public Cidade buscarCodigo(int c)
    {
        String sql;
        sql = "select * from cidade where cid_codigo = "+c+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Cidade(rs.getInt("cid_codigo"), rs.getString("cid_nome"), new Estado().buscarCodigo(rs.getInt("uf_codigo")));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public ArrayList<Cidade> buscarALL()
    {
        ArrayList<Cidade> lista = new ArrayList<>();
        String sql;
        sql = "select cid.cid_codigo, cid.cid_nome, uf.uf_codigo, uf.uf_sigla"
                + " from cidade cid, estado uf ";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            while (rs.next()) 
            {
                lista.add(new Cidade(rs.getInt("cid_codigo"), rs.getString("cid_nome"), new Estado().buscarCodigo(rs.getInt("uf_codigo"))));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    public ArrayList<Cidade> buscarPEstado(String uf)
    {
        ArrayList<Cidade> lista;
        lista = new ArrayList<>();
        String sql;
        sql = "select cid.cid_codigo, cid.cid_nome, uf.uf_codigo, uf.uf_sigla"
                + " from cidade cid, estado uf "
                + " where cid.uf_codigo = uf.uf_codigo and uf.uf_sigla = '"+uf+"'";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            while (rs.next()) 
            {
                lista.add(new Cidade(rs.getInt("cid_codigo"), rs.getString("cid_nome"), new Estado().buscarCodigo(rs.getInt("uf_codigo"))));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    public boolean VerificaCidade(String nome, int codigoUF)
    {
        String sql;
        sql = "select * from cidade where cid_nome = '"+nome+"' and uf_codigo = "+codigoUF+"";
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
    
    
    public Cidade buscarPCidadeEstado(String cidade, String uf)
    {
        ArrayList<Cidade> lista;
        lista = new ArrayList<>();
        
        String sql;
        sql = "select cid.cid_codigo, cid.cid_nome, uf.uf_codigo, uf.uf_sigla"
                + " from cidade cid, estado uf "
                + " where cid.uf_codigo = uf.uf_codigo and uf.uf_sigla = '"+uf+"' and cid.cid_nome = '"+cidade+"'";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
               return new Cidade(rs.getInt("cid_codigo"), rs.getString("cid_nome"), new Estado().buscarCodigo(rs.getInt("uf_codigo")));
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
            query = "select cid.cid_codigo, cid.cid_nome, uf.uf_codigo, uf.uf_sigla"
                + " from cidade cid, estado uf where cid.uf_codigo = uf.uf_codigo order by cid.cid_nome";
        }
        else
        {
            switch (tipo)
            {
                case 0:
                {
                    query = "select cid.cid_codigo, cid.cid_nome, uf.uf_codigo, uf.uf_sigla"
                             + " from cidade cid, estado uf "
                            + "where cuf.uf_sigla = '" + valor + "' and cid.uf_codigo = uf.uf_codigo order by cid.cid_nome";
                    break;
                }
                case 1:
                {
                    query = "select cid.cid_codigo, cid.cid_nome, uf.uf_codigo, uf.uf_sigla"
                             + " from cidade cid, estado uf "
                            + "where cid.cid_nome ilike '%" + valor + "%' and cid.uf_codigo = uf.uf_codigo  order by cid.cid_nome";
                    break;
                }
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static void configuraModel(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Código", "Cidade", "Estado"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(50);
    }
    
}
