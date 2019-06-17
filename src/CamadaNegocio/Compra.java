/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CamadaNegocio;

import CamadaLogica.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
              " VALUES ("+f == null ? null : f.getCodigo()+", "+valort+", '"+data+"', "+func.getCodigo()+");";
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
        
}
