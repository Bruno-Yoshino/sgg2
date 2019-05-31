/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaNegocio.*;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JComboBox;
import util.Validacao;
import util.mensagens;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * 
 * @author 弐条
 * @author 七草
 */
public class CidadeController 
{
    private Cidade cid;
    private util.Validacao v;
    private mensagens m;
    private Estado est = new Estado();

    public CidadeController() {
        cid = new Cidade();
        v = new Validacao();
        m = new mensagens();
    }

    public Cidade getCid() {
        return cid;
    }

    public void setCid(Cidade cid) {
        this.cid = cid;
    }
    
    public int validar(String cod, String nome, String uf)
    {
        est = est.buscarSigla(uf);
        int id = v.ConverteNumeroInteiro(cod);
        if( id == -999)
            return 1;
        if(v.ValidaTexto(uf) == 1)
            return 2;
        if(cid.VerificaCidade(nome, est.getCodigo()) && cid.getCodigo() == 0)
            return 3;
        cid.setCodigo(id);
        cid.setNome(nome);
        cid.setE(est);
        return 0;
    }
    
    public boolean gravar()
    {
        return cid.gravar();
    }
    
    public boolean excluir(String codigo)
    {
        return cid.excluir(v.ConverteNumeroInteiro(codigo));
    }
    
    public void localizar(int codigo)
    {
       cid = cid.buscarCodigo(codigo);
    }  
    
    public void CarregaEstado(JComboBox c)
    {
        ArrayList<Estado> lista;
        lista = est.buscarALL();
        for(int i = 0; i < lista.size(); i++)
        {
            c.addItem(lista.get(i).getSigla());
            c.updateUI();
        }
    }
    
    
}
