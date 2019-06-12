/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaNegocio.AjustarProduto;
import CamadaNegocio.Produto;
import CamadaNegocio.Funcionario;
import CamadaNegocio.Servico;
import java.util.Date;
import util.Validacao;

/**
 *
 * @author 羽根川　翼
 * @author 阿賀野
 * @author 矢矧
 */
public class AjusteProdutoController 
{
    private Produto p;
    private Funcionario func;    
    private Servico serv;
    private final util.Validacao v;
    private AjustarProduto ap;

    public AjusteProdutoController() {
        p = new Produto();
        func  = new Funcionario();
        v = new Validacao();
        ap = new AjustarProduto();
        serv = new Servico();
    }

    public AjustarProduto getAf() {
        return ap;
    }

    public void setAf(AjustarProduto ap) {
        this.ap = ap;
    }
    
    public void buscaServico(int codigo)
    {
        ap.setServ(serv.buscarCodigo(codigo));
    }
    
    public void buscaFolha(int codigo)
    {
        ap.setF(p.buscarCodigo(codigo));
    }
    
    public int validar(String codigo, String qtd, Date data, boolean flag, String obs)
    {
        ap.setCodigo(v.ConverteNumeroInteiro(codigo));
        if(qtd.equals(""))
            return 1;
        if(v.ConverteNumeroInteiro(qtd) == -999)
            return 2;
        ap.setQtd(v.ConverteNumeroInteiro(qtd));
        if(obs.trim().equals(""))
            return 3;
        if(!flag)
        {
            if(ap.getQtd() > ap.getF().getQtd())
                return 4;
        }
        ap.setFlag(flag);
        ap.setObs(obs);
        ap.setData(data);
        return 0;
    }
    
    public boolean gravar()
    {
        return ap.gravar();
    }
    
    public void atualizaEstoque()
    {
        AtualizarEstoqueController aec = new AtualizarEstoqueController();
        aec.setP(p);
        aec.atualizarEstoqueProduto(ap.isFlag(), ap.getQtd());
    }
}
