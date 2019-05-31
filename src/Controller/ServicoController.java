/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaNegocio.Servico;
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
public class ServicoController 
{
     private final util.Validacao v;
    private Servico s;

    public ServicoController() {
        s = new Servico();
        v = new Validacao();
    }

    public Servico getP() {
        return s;
    }

    public void setP(Servico s) {
        this.s = s;
    }
    
     public int validaCampo(String codigo, String nome, boolean status)
    {
       s.setCodigo(v.ConverteNumeroInteiro(codigo));
        
       if(nome.trim().equals(""))
       {
           return 1;
       }
       
       if(s.VerificaServico(nome) && s.getCodigo() == 0)
           return 2;
       
       s.setNome(nome);
       s.setStatus(status);
       return 0;
    }
    
    public boolean gravar()
    {
        return s.gravar();
    }

    
    public Servico LocalizaServico(int codigo)
    {
        return s = s.buscarCodigo(codigo);   
    }
    
    public boolean Excluir(int codigo)
    {
        return s.excluir(codigo);
    }
}
