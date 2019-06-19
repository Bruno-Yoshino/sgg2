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
import java.util.Date;
import javax.swing.JTable;

/**
 *
 * Moniter
 * @author 羽根川　翼
 * @author モニカ
 * Produto
 * @author 香取 
 * @author 鹿島
 * @author 橋立
 * Folha
 * @author 海女
 * @author 御子
 * @author 稲荷
 */
public class Compra 
{
    private int codigo; 
    private Fornecedor f;
    private Funcionario func;
    private double valort;
    private Date data;
    
    ArrayList<Compra_Folha> lcf;
    ArrayList<Compra_Produto> lcp;

    public Compra(int codigo, Fornecedor f, Funcionario func, double valort, Date data, ArrayList<Compra_Folha> lcf, ArrayList<Compra_Produto> lcp) {
        this.codigo = codigo;
        this.f = f;
        this.func = func;
        this.valort = valort;
        this.data = data;
        this.lcf = lcf;
        this.lcp = lcp;
    }
    
    public Compra(int codigo, Fornecedor f, Funcionario func, double valort, Date data) {
        this.codigo = codigo;
        this.f = f;
        this.func = func;
        this.valort = valort;
        this.data = data;
    }

    public Compra() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Fornecedor getF() {
        return f;
    }

    public void setF(Fornecedor f) {
        this.f = f;
    }

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
    }

    public double getValort() {
        return valort;
    }

    public void setValort(double valort) {
        this.valort = valort;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public ArrayList<Compra_Folha> getLcf() {
        return lcf;
    }

    public void setLcf(ArrayList<Compra_Folha> lcf) {
        this.lcf = lcf;
    }

    public ArrayList<Compra_Produto> getLcp() {
        return lcp;
    }

    public void setLcp(ArrayList<Compra_Produto> lcp) {
        this.lcp = lcp;
    }
    
    public boolean gravar()
    {
        String sql;
        if(codigo == 0)
            sql = "INSERT INTO compra( " +
              " forn_codigo, comp_valortotal, comp_data, func_codigo) " +
              " VALUES ("+(f.getCodigo() == 0 ? "" : f.getCodigo())+", "+valort+", '"+data+"', "+func.getCodigo()+");";
        else
            sql = "UPDATE compra\n" +
                  "SET forn_codigo="+f == null ? null : f.getCodigo()+", comp_valortotal="+valort+", comp_data='"+data+"', func_codigo="+func.getCodigo()+"\n" +
                  "WHERE comp_codigo="+codigo+";";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir()
    {
        String sql = "delete form compra where comp_codigo = "+codigo+"";
        return Banco.getCon().manipular(sql);
    }
    public boolean excluirItens()
    {
        String sql = "delete form compra_produto where comp_codigo = "+codigo+"; delete form compra_folha where comp_codigo = "+codigo+"";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean gravarItem(int codigoI, int qtd, double preco, boolean flag)
    {
        String sql;
        if(flag)
            sql = "INSERT INTO compra_produto(comp_codigo, pro_codigo, compp_qtd, compp_preco) VALUES ("+codigo+", "+codigoI+", "+qtd+", "+preco+");";
        else
            sql = "INSERT INTO compra_folha(comp_codigo, fo_codigo, compf_qtd, compf_preco) VALUES ("+codigo+", "+codigoI+", "+qtd+", "+preco+");";
        return Banco.getCon().manipular(sql);
    }
    
    public int maxCodigo()//comp_codigo, forn_codigo, comp_valortotal, comp_data, func_codigo
    {
        String sql = "select max(comp_codigo) "
                + "from compra ";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return rs.getInt(1);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    public static ResultSet ConsultaCompra(String valor, int tipo, Date dataI, Date dataF)
    {
        String query = null;
        if (valor.equals(""))
        {
            query = "select c.comp_codigo, forn.forn_nome, c.comp_valortotal, c.comp_data, func.func_nome "
                + " from compra c, fornecedor forn, funcionario func where func.func_codigo = c.func_codigo or forn_codigo = c.forn_codigo order by comp_codigo";
        }
        else
        {
            switch (tipo)
            {
                case 0:// data
                {
                    query = "select c.comp_codigo, forn.forn_nome, c.comp_valortotal, c.comp_data, func.func_nome "
                             + " from compra c, fornecedor forn, funcionario func "
                            + "where comp_data = '"+dataI+"' and func.func_codigo = c.func_codigo or forn_codigo = c.forn_codigo order by comp_codigo";
                    break;
                }
                case 1:// periodo
                {
                    query = "select c.comp_codigo, forn.forn_nome, c.comp_valortotal, c.comp_data, func.func_nome "
                             + " from compra c, fornecedor forn, funcionario func "
                            + "where comp_data BETWEEN '"+dataI+"' and '"+dataF+"' and func.func_codigo = c.func_codigo or forn_codigo = c.forn_codigo  order by comp_codigo";
                    break;
                }
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public Compra buscaCompra(int codigo)
    {
        String sql;
        sql = "select c.comp_codigo, c.forn_codigo, c.comp_valortotal, c.comp_data, c.func_codigo "
                + " from compra c where c.comp_codigo = "+codigo+";";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {//int codigo, Fornecedor f, Funcionario func, double valort, Date data, ArrayList<Compra_Folha> lcf, ArrayList<Compra_Produto> lcp
            if (rs.next()) 
            {
                return new Compra(rs.getInt(1), new Fornecedor().buscarCodigo(rs.getInt(2)), new Funcionario().buscarCodigo(rs.getInt(5)), rs.getDouble(3), rs.getDate(4));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    

    
    public static ResultSet ConsultaCompraItem(int codigo, int tipo)
    {
        String query = null;
        if(tipo == 1)
        {
            query = "select cp.comp_codigo, p.pro_nome, cp.compp_qtd, cp.compp_preco "
                 + " from compra_produto cp, produto p "
                 + "where cp.comp_codigo = "+codigo+" and cp.pro_codigo = p.pro_codigo order by p.pro_nome";
        }
        else
        {
            query = "select cf.comp_codigo, f.fo_tamanho, f.fo_descricao, cf.compf_qtd, cf.compf_preco"
                 + " from compra_folha cf, folha f "
                 + "where cp.comp_codigo = "+codigo+" and cp.fo_codigo = f.fo_codigo order by f.fo_tamanho";
        }
        return Banco.getCon().retornaResultSet(query);
    }
        
    public static void configuraModel(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Código", "Fornecedor", "Valor Total", "Data", "Funcionario"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(250);
    }
    
    public static void configuraModelItem(JTable jTable, String texto) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {texto, "Quantidade", "Preço Unitario", "Valor Total"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(250);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(200);
    }
}
