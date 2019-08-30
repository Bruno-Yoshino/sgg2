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
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * 
 * @author 弐条
 * @author 七草
 */
public class Folha {
    private int codigo;
    private String descricao;
    private double altura;
    private double largura;
    private boolean status;
    private int qtd;
    private String tamanho;
                        //fo_codigo, fo_tamanho, fo_status, fo_qtd, fo_altura, fo_largura fo_descricao from folha
    public Folha(int codigo, String tamanho, boolean status,  int qtd, double altura, double largura, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.altura = altura;
        this.largura = largura;
        this.status = status;
        this.qtd = qtd;
        this.tamanho = tamanho;
    }

    public Folha() {
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

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    
    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
    
    //--------------------------------------------------DAO-------------------------------------------------
    
    public boolean gravar()
    {
        String sql;
        if(this.codigo == 0)
        {
            sql = "insert into folha (fo_tamanho, fo_status, fo_qtd, fo_altura, fo_largura, fo_descricao) "
                    + "values ('"+this.getTamanho()+"', '"+this.status+"', 0, "+this.altura+", "+this.largura+", '"+this.descricao+"')";
        }
        else
        {
            sql = "update folha set fo_tamanho = '"+this.getTamanho()+"', fo_status = '"+this.status+"', fo_altura = "+this.altura+",  fo_largura = "+this.largura+", fo_descricao = '"+this.descricao+"' where fo_codigo = "+codigo+"";
        }
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo)
    {
        String sql = "delete from folha where fo_codigo = "+codigo+"";
        return Banco.getCon().manipular(sql);
    }
    
    public Folha buscarCodigo(int codigo)
    {
        String sql = "select fo_codigo, fo_tamanho, fo_status, fo_qtd, fo_altura, fo_largura, fo_descricao from folha where fo_codigo = "+codigo+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Folha(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getInt(4), rs.getDouble(5), rs.getDouble(6), rs.getString(7));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public boolean CheckExist(String tamanho, String descricao)
    {
        String sql = "select * from folha where fo_tamanho = '"+tamanho+"' and fo_descricao = '"+descricao+"'";
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
            query = "select fo_codigo, fo_tamanho, fo_status, fo_qtd, fo_altura, fo_largura, fo_descricao from folha";
        }
        else
        {
            switch (tipo)
            {
//                case 0:
//                {
//                    query = "select fo_codigo, fo_tamanho, fo_status, fo_qtd, fo_altura, fo_largura, fo_descricao from folha where fo_codigo = "+valor+"";
//                    break;
//                }
                case 0:
                {
                    query = "select fo_codigo, fo_tamanho, fo_status, fo_qtd, fo_altura, fo_largura, fo_descricao from folha where fo_tamanho ilike '%"+valor+"%'";
                    break;
                }
                case 1:
                {
                    query = "select fo_codigo, fo_tamanho, fo_status, fo_qtd, fo_altura, fo_largura, fo_descricao from folha where fo_descricao ilike '%"+valor+"%'";
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
            query = "select fo_codigo, fo_tamanho, fo_status, fo_qtd, fo_altura, fo_largura, fo_descricao from folha where fo_status = true";
        }
        else
        {
            switch (tipo)
            {
//                case 0:
//                {
//                    query = "select fo_codigo, fo_tamanho, fo_status, fo_qtd, fo_altura, fo_largura, fo_descricao from folha where fo_codigo = "+valor+"";
//                    break;
//                }
                case 0:
                {
                    query = "select fo_codigo, fo_tamanho, fo_status, fo_qtd, fo_altura, fo_largura, fo_descricao from folha where fo_tamanho ilike '%"+valor+"%' and where fo_status = true";
                    break;
                }
                case 1:
                {
                    query = "select fo_codigo, fo_tamanho, fo_status, fo_qtd, fo_altura, fo_largura, fo_descricao from folha where fo_descricao ilike '%"+valor+"%' and where fo_status = true";
                    break;
                }
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static void configuraModel(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Código", "Tamanho", "Descrição", "Altura", "Largura", "Ativo"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(250);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(90);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(90);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(50);
    }
    
    //--------------------------------------------------------------  ↑ Para Cadastro
    // * @author 速吸
    // * @author 神威
    //-------------------------------------------------------------   ↓ Para Atualizar Estoque
    public boolean atualizarEstoque()
    {
        String sql = "update folha set fo_qtd = "+this.qtd+" where fo_codigo = "+codigo+"";
        return Banco.getCon().manipular(sql);
    }
}
