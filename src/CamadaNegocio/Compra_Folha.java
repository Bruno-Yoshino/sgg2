/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CamadaNegocio;

import CamadaLogica.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;

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
public class Compra_Folha 
{
    private Compra c;
    private Folha f;
    private int qtd;
    private double preco;

    public Compra_Folha(Compra c, Folha f, int qtd, double preco) {
        this.c = c;
        this.f = f;
        this.qtd = qtd;
        this.preco = preco;
    }

    public Compra_Folha() {
    }
    
    public Compra getC() {
        return c;
    }

    public void setC(Compra c) {
        this.c = c;
    }

    public Folha getF() {
        return f;
    }

    public void setF(Folha f) {
        this.f = f;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
        
    public ArrayList<Compra_Folha> buscaCompraFolha(int codigo)
    {
        ArrayList<Compra_Folha> lista = new ArrayList<>();
        String sql;
        sql = "select cf.comp_codigo, cf.fo_codigo, cf.compf_qtd, cf.compf_preco"
                 + " from compra_folha cf "
                 + "where cp.comp_codigo = "+codigo+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {//int codigo, Fornecedor f, Funcionario func, double valort, Date data, ArrayList<Compra_Folha> lcf, ArrayList<Compra_Produto> lcp
            if (rs.next()) 
            {
                lista.add(new Compra_Folha(c, new Folha().buscarCodigo(rs.getInt(2)), rs.getInt(3), rs.getDouble(4)));
            }
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
        return lista;
    }
}
