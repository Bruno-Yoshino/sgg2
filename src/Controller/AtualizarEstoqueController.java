/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaNegocio.Folha;
import CamadaNegocio.Produto;

/**
 *
 * @author 羽根川　翼
 * @author 阿賀野
 * @author 矢矧
 */
public class AtualizarEstoqueController 
{
    private Produto p;
    private Folha f;

    public AtualizarEstoqueController() {
        p = new Produto();
        f = new Folha();
    }

    public Produto getP() {
        return p;
    }

    public void setP(Produto p) {
        this.p = p;
    }

    public Folha getF() {
        return f;
    }

    public void setF(Folha f) {
        this.f = f;
    }
    
    public boolean atualizarEstoqueProduto(boolean flag, int qtd)
    {
        if(flag) // true Increment
        {
            p.setQtd(p.getQtd()+qtd);
        }
        else // flase = decrement
        {
            p.setQtd(p.getQtd()-qtd);
        }
        return p.atualizarEstoque();
    }
    
    public boolean atualizarEstoqueFolha(boolean flag, int qtd)
    {
        if(flag) // true Increment
        {
            f.setQtd(f.getQtd()+qtd);
        }
        else // flase = decrement
        {
            f.setQtd(f.getQtd()-qtd);
        }
        return f.atualizarEstoque();
    }
}
