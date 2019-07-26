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
 * @author 磐手
 * @author イントレピッド
 * 
 * @author 弐条
 * @author 七草
 */

public class DetalheServico {
    private int codigo;
    private String descricao;
    private Boolean status;

    public DetalheServico(int codigo, String descricao, Boolean status) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.status = status;
    }

    public DetalheServico() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    //----------------------------------------------------------データベース-------------------------------------------------
    public boolean gravar()  // using This
    {
        String sql;
        if(this.codigo == 0)
        {
            sql = "insert into detalhe_serv (ds_descricao, ds_status) values ('"+this.descricao+"', "+this.status+" )";
        }
        else
        {
            sql = "update detalhe_serv set ds_descricao = '"+this.descricao+"',  ds_status = "+this.status+"   where ds_codigo = "+this.codigo+"";
        }
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo)
    {
        String sql;
        sql = "delete from detalhe_serv where ds_codigo = "+codigo+"";
        return Banco.getCon().manipular(sql);
    }
    
    public DetalheServico buscarDescricao(String s)
    {
        String sql;
        sql = "select ds_codigo, ds_descricao, ds_status from detalhe_serv where ds_descricao ilike '%"+s+"%'";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new DetalheServico(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public DetalheServico buscarCodigo(int c)
    {
        String sql;
        sql = "select ds_codigo, ds_descricao,  ds_status from detalhe_serv where ds_codigo = "+c+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new DetalheServico(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public boolean VerificaDescricaoServico(String descricao)
    {
        String sql;
        sql = "select * from detalhe_serv where ds_descricao = '"+descricao+"'";
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
            query = "select ds_codigo, ds_descricao, ds_status from detalhe_serv order by ds_descricao";
        }
        else
        {
            switch (tipo)
            {
//                case 0:
//                {
//                    query = "select ds_codigo, ds_descricao, ds_status from detalhe_serv where prod_codigo = " + Integer.parseInt(valor) + " order by ds_descricao";
//                    break;
//                }
                case 0:
                {
                    query = "select ds_codigo, ds_descricao, ds_status from detalhe_serv where ds_descricao ilike '%" + valor + "%' order by ds_descricao";
                    break;
                }
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public ResultSet buscarDetalhes()
    {
      
        String query = "select ds_codigo, ds_descricao, ds_status from detalhe_serv order by ds_descricao";
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static void configuraModel(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Código", "Descrição", "Status"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
    }
    
}
