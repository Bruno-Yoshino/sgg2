/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CamadaNegocio;

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
    
    
}
