/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaNegocio.DetalheServico;
import util.Validacao;

/**
 *
 * @author 吉野　廉
 * @author モニカ
 * @author 磐手
 * @author イントレピッド
 * 
 * @author 弐条
 * @author 七草
 */
public class DetalheServicoController 
{
    private final util.Validacao v;
    private DetalheServico ds;

    public DetalheServicoController() {
        ds = new DetalheServico();
        v = new Validacao();
    }

    public DetalheServico getP() {
        return ds;
    }

    public void setP(DetalheServico ds) {
        this.ds = ds;
    }
    
     public int validaCampo(String codigo, String nome, boolean status)
    {
       ds.setCodigo(v.ConverteNumeroInteiro(codigo));
        
       if(nome.trim().equals(""))
       {
           return 1;
       }
       
       if(ds.VerificaDescricaoServico(nome) && ds.getCodigo() == 0)
           return 2;
       
       ds.setDescricao(nome);
       ds.setStatus(status);
       return 0;
    }
    
    public boolean gravar()
    {
        return ds.gravar();
    }

    
    public DetalheServico LocalizaDS(int codigo)
    {
        return ds = ds.buscarCodigo(codigo);   
    }
    
    public boolean Excluir(int codigo)
    {
        return ds.excluir(codigo);
    }
    
}
