/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaNegocio.Produto;
import util.*;
/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author 鳳翔
 * @author 利根
 * 
 * @author 弐条
 * @author 七草
 * 
 */
public class ProdutoController {
    private Produto p;
    private Validacao v;

    public ProdutoController() {
        p = new Produto();
        v =  new Validacao();
    }

    public Produto getP() {
        return p;
    }

    public void setP(Produto p) {
        this.p = p;
    }
    
     public int validaCampo(String codigo, String nome, char tipo, boolean status, String caminho)
    {
       p.setCodigo(v.ConverteNumeroInteiro(codigo));
        
       if(nome.trim().equals(""))
       {
           return 1;
       }
       
       if(p.VerificaProduto(nome) && p.getCodigo() == 0)
       {
           return 2;
       }
       p.setNome(nome);
       p.setTipo(tipo);
       p.setStatus(status);
       p.setCaminho(caminho);
       return 0;
    }
    
    public boolean gravar()
    {
        return p.gravar();
    }

    
    public Produto LocalizaProduto(int codigo)
    {
        return p = p.buscarCodigo(codigo);   
    }
    
    public boolean Excluir(int codigo)
    {
        return p.excluir(codigo);
    }
}
