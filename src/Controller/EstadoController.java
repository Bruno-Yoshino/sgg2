/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaApresentacao.ConsultaPadrao;
import CamadaNegocio.Estado;
import util.*;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 */
public class EstadoController 
{
    private CamadaNegocio.Estado est;
    private util.Validacao v;
    private mensagens m;

    public EstadoController() {
        est = new Estado();
        v = new Validacao();
        m = new mensagens();
    }

    public Estado getEst() {
        return est;
    }

    public void setEst(Estado est) {
        this.est = est;
    }
    
    public int validar(String cod, String uf)
    {
        int id = v.ConverteNumeroInteiro(cod);
        if( id == -999)
            return 1;
        if(v.ValidaTexto(uf) == 1)
            return 2;
        if(est.CheckEstado(uf))
            return 3;
        est.setCodigo(id);
        est.setSigla(uf);
        return 0;
    }
    
    public boolean gravar()
    {
        return est.gravar();
    }
    
    public boolean excluir(String codigo)
    {
        return est.excluir(v.ConverteNumeroInteiro(codigo));
    }
    
    public void Localizar(int codigo)
    {
       this.setEst(est.buscarCodigo(codigo));
    }
}
