/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CamadaNegocio;

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
public class Compra_Produto 
{
    private Compra c;
    private Produto p;
    private int qtd;
    private double preco;

    public Compra_Produto(Compra c, Produto p, int qtd, double preco) {
        this.c = c;
        this.p = p;
        this.qtd = qtd;
        this.preco = preco;
    }

    public Compra_Produto() {
    }

    public Compra getC() {
        return c;
    }

    public void setC(Compra c) {
        this.c = c;
    }

    public Produto getP() {
        return p;
    }

    public void setP(Produto p) {
        this.p = p;
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

    
}
