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
 * @author 鳳翔
 * @author 利根
 */
public class Produto {
    private int codigo;
    private String nome;
    private String caminho;
    private Boolean status;
    private char tipo;
    private int qtd;

    public Produto(int codigo, String nome, String caminho, Boolean status, char tipo, int qtd) {
        this.codigo = codigo;
        this.nome = nome;
        this.caminho = caminho;
        this.status = status;
        this.tipo = tipo;
        this.qtd = qtd;
    }

    public Produto() {
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

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    
    //--------------------------------------------------------------------データベース--------------------------------------
     public boolean gravar()  // using This
    {
        String sql;
        if(this.codigo == 0)
        {
            sql = "insert into produto (pro_nome, pro_tipo, pro_status, pro_caminho) values ('"+this.nome.toUpperCase()+"', '"+this.tipo+"', "+this.status+", '"+this.caminho+"')";
        }
        else
        {
            sql = "update produto set pro_nome = '"+this.nome.toUpperCase()+"', pro_tipo = '"+this.tipo+"', pro_status = "+this.status+", pro_caminho = '"+this.caminho+"' where pro_codigo = "+this.codigo+"";
        }
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo)
    {
        String sql;
        sql = "delete from produto where pro_codigo = "+codigo+"";
        return Banco.getCon().manipular(sql);
    }
    
    public Produto buscarNome(String s)
    {
        String sql;
        sql = "select pro_codigo, pro_nome, pro_tipo, pro_status, pro_caminho from produto where pro_nome like '%"+s.toUpperCase()+"%'";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Produto(rs.getInt(1), rs.getString(2), rs.getString(5), rs.getBoolean(4), rs.getString(3).charAt(0), 0);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public Produto buscarCodigo(int c)
    {
        String sql;
        sql = "select pro_codigo, pro_nome, pro_tipo, pro_status, pro_caminho from produto where pro_codigo = "+c+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Produto(rs.getInt(1), rs.getString(2), rs.getString(5), rs.getBoolean(4), rs.getString(3).charAt(0), 0);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public boolean VerificaProduto(String nome)
    {
        String sql;
        sql = "select * from prouto where pro_nome = '"+nome.toUpperCase()+"'";
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
            query = "select pro_codigo, pro_nome, pro_tipo, pro_status, pro_caminho from produto order by pro_nome";
        }
        else
        {
            switch (tipo)
            {
//                case 0:
//                {
//                    query = "select pro_codigo, pro_nome, pro_tipo, pro_status, pro_caminho from produto where prod_codigo = " + Integer.parseInt(valor) + " order by pro_nome";
//                    break;
//                }
                case 0:
                {
                    query = "select pro_codigo, pro_nome, pro_tipo, pro_status, pro_caminho from produto where pro_nome ilike '%" + valor + "%' order by pro_nome";
                    break;
                }
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static void configuraModel(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Código", "Nome", "Tipo Consumo", "Status"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(100);
    }
    //--------------------------------------------------------------  ↑ Para Cadastro
    // * @author 速吸
    // * @author 神威
    //-------------------------------------------------------------   ↓ Para Atualizar Estoque
    
    public boolean atualizarEstoque()
    {
        String sql = "update produto set pro_estoque = "+this.qtd+" where pro_codigo = "+this.codigo+"";
        return Banco.getCon().manipular(sql);
    }
}
