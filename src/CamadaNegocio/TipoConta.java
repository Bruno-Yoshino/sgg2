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
 * 
 * @author 弐条
 * @author 七草
 */
//insert into forma_pagamento (fpg_nome, fpg_status) values ('Dinheiro', true),('Cartão Credito', true),('Cartão Débito', true),('Cheque', true), ('Fiado', true)
public class TipoConta {
    private int codigo;
    private String tipo;

    public TipoConta(int codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }
    
    public TipoConta() {

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    //------------------------------------------------DAO---------------------------------------------------------
    
     public boolean gravar()  // using This   
    {
        String sql;
        if(this.codigo == 0)
        {
            sql = "insert into tipo_conta (tc_tipo) values ('"+this.tipo+"')";
        }
        else
        {
            sql = "update tipo_conta set tc_tipo = '"+this.tipo+"' where tc_codigo = "+this.codigo+"";
        }
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo)
    {
        String sql;
        sql = "delete from tipo_conta where tc_codigo = "+codigo+"";
        return Banco.getCon().manipular(sql);
    }
    
    public TipoConta buscarTipo(String s)
    {
        String sql;
        sql = "select * from tipo_conta where tc_tipo = '"+s+"'";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new TipoConta(rs.getInt("tc_codigo"), rs.getString("tc_tipo"));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public TipoConta buscarCodigo(int c)
    {
        String sql;
        sql = "select * from tipo_conta where tc_codigo = "+c+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new TipoConta(rs.getInt("tc_codigo"), rs.getString("tc_tipo"));
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
            query = "select * from tipo_conta order by tc_tipo";
        }
        else
        {
            switch (tipo)
            {
//                case 0:
//                {
//                    query = "select * from tipo_conta where tc_codigo = "+valor+" ";
//                    break;
//                }
                case 0:
                {
                    query = "select * from tipo_conta where tc_tipo ilike '%" + valor + "%' order by tc_tipo";
                    break;
                }
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static void configuraModel(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Código", "Tipo"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(250);
    }
    
}
