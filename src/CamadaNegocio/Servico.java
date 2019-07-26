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
import javax.swing.JTable;

/**
 *
 * @author 吉野　廉
 * @author モニカ
 * 
 * @author 磐手
 * @author イントレピッド
 * @author 弐条
 * @author 七草
 */
public class Servico 
{
    private int codigo;
    private String nome;
    private Boolean status;

    public Servico(int codigo, String nome, Boolean status) {
        this.codigo = codigo;
        this.nome = nome;
        this.status = status;
    }

    public Servico() {
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    //--------------------------------------------------------------データベース------------------------
    
    public boolean gravar()  // using This
    {
        String sql;
        if(this.codigo == 0)
        {
            sql = "insert into servico (serv_nome, serv_status) values ('"+this.nome+"',"+this.status+")";
        }
        else
        {
            sql = "update servico set serv_nome = '"+this.nome+"',  serv_status = "+this.status+"  where serv_codigo = "+this.codigo+"";
        }
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo)
    {
        String sql;
        sql = "delete from servico where serv_codigo = "+codigo+"";
        return Banco.getCon().manipular(sql);
    }
    
    public Servico buscarDescricao(String s)
    {
        String sql;
        sql = "select serv_codigo, serv_nome, serv_status from servico where serv_nome like '%"+s+"%'";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Servico(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public Servico buscarCodigo(int c)
    {
        String sql;
        sql = "select serv_codigo, serv_nome,  serv_status from servico where serv_codigo = "+c+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Servico(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public boolean VerificaServico(String nome)
    {
        String sql;
        sql = "select * from servico where serv_nome = '"+nome+"'";
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
    
    
    public static ResultSet buscarDados(String valor, int tipo)//Para consulta
    {
      
        String query = null;
        if (valor.equals(""))
        {
            query = "select serv_codigo, serv_nome, serv_status from servico order by serv_nome";
        }
        else
        {
            switch (tipo)
            {
//                case 0:
//                {
//                    query = "select serv_codigo, serv_nome, serv_status from servico where prod_codigo = " + Integer.parseInt(valor) + " order by serv_nome";
//                    break;
//                }
                case 0:
                {
                    query = "select serv_codigo, serv_nome, serv_status from servico where serv_nome ilike '%" + valor + "%' order by serv_nome";
                    break;
                }
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static ResultSet buscarDados2(String valor, int tipo)//Para consulta
    {
      
        String query = null;
        if (valor.equals(""))
        {
            query = "select serv_codigo, serv_nome, serv_status from servico where serv_status = true order by serv_nome";
        }
        else
        {
            switch (tipo)
            {
//                case 0:
//                {
//                    query = "select serv_codigo, serv_nome, serv_status from servico where prod_codigo = " + Integer.parseInt(valor) + " order by serv_nome";
//                    break;
//                }
                case 0:
                {
                    query = "select serv_codigo, serv_nome, serv_status from servico where serv_nome ilike '%" + valor + "%' and serv_status = true order by serv_nome";
                    break;
                }
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static void configuraModel(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Código", "Nome", "Status"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
    }
}
