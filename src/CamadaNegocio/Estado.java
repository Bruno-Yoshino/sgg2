/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CamadaNegocio;

import CamadaLogica.Banco;
import CamadaLogica.ReadOnlyTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 * Programer
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 */
public class Estado 
{
    private int codigo;
    private String sigla;

    public Estado(int codigo, String sigla) {
        this.codigo = codigo;
        this.sigla = sigla;
    }

    public Estado() {
        this.codigo = 0;
        this.sigla = "";
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
//    ------------------------------------------DAO----------------------------------------------
    
    public boolean gravar()  // using This
    {
        String sql;
        if(this.codigo == 0)
        {
            sql = "insert into estado (uf_sigla) values ('"+this.sigla.toUpperCase()+"')";
        }
        else
        {
            sql = "update estado set uf_sigla = '"+this.sigla.toUpperCase()+"' where uf_codigo = "+this.codigo+"";
        }
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo)
    {
        String sql;
        sql = "delete from estado where uf_codigo = "+codigo+"";
        return Banco.getCon().manipular(sql);
    }
    
    public Estado buscarSigla(String s)
    {
        String sql;
        Estado es = null;
        sql = "select * from estado where uf_sigla = '"+s+"'";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                es = new Estado(rs.getInt("uf_codigo"),rs.getString("uf_sigla"));
            }
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
        return es;
    }
    
    public Estado buscarCodigo(int c)
    {
        String sql;
        Estado es = null;
        sql = "select * from estado where uf_codigo = "+c+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                es = new Estado(rs.getInt("uf_codigo"),rs.getString("uf_sigla"));
            }
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
        return es;
    }
    
    public ArrayList<Estado> buscarALL()
    {
        String sql;
        ArrayList<Estado> es = new ArrayList<>();
        sql = "select * from estado";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            while (rs.next()) 
            {
               es.add(new Estado(rs.getInt("uf_codigo"),rs.getString("uf_sigla")));
            }
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
        return es;
    }
    
    public boolean CheckEstado(String sigla)
    {
        String sql;
        Estado es = null;
        sql = "select * from estado where uf_sigla = '"+sigla+"'";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return true;
            }
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public static ResultSet buscarDados(String valor, int tipo)
    {
      
        String sql = null;
        if (valor.equals(""))
        {
            sql = "select * from estado order by uf_sigla";
        }
        else
        {
            switch (tipo)
            {
//                case 0:
//                {
//                    sql = "select * from estado where uf_codigo = " + Integer.parseInt(valor) + " order by uf_sigla";
//                    break;
//                }
                case 0:
                {
                    sql = "select * from estado where uf_sigla ilike '%" + valor + "%' order by uf_sigla";
                    break;
                }
            }
        }
        return Banco.getCon().retornaResultSet(sql);
    }
    
    public static void configuraModel(JTable jTable) // Configurar Tabela para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Código", "Estado"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
    }
   
    public boolean InsereDadosESTADOS()
       {
           try
           {
               String  sql = "select * from estado";
               ResultSet rs = Banco.getCon().retornaResultSet(sql);
               rs.next();
               if (rs.getRow() > 0)
               {
                   return true;
               }
               else
               {
                   sql = "insert into estado (uf_sigla) values ('AC')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('AL')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('AM')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('AP')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('BA')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('CE')"; Banco.getCon().manipular(sql); 
                   sql = "insert into estado (uf_sigla) values ('DF')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('ES')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('GO')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('MA')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('MG')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('MS')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('MT')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('PA')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('PB')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('PE')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('PI')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('PR')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('RJ')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('RO')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('RN')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('RR')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('RS')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('SC')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('SE')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('SP')"; Banco.getCon().manipular(sql);
                   sql = "insert into estado (uf_sigla) values ('TO')"; Banco.getCon().manipular(sql);
                   return false;
               }
           }
           catch (SQLException sqlex)
           {
               System.out.println("Erro: \n" + sqlex.toString()); 
           }
           return false;
       }
}
