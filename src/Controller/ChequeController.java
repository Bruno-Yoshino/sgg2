/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaNegocio.Cheque;
import java.util.Date;
import util.Validacao;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 巴御前
 * @author 高村　結衣
 * @author 里川　麗奈
 * @author 川波　愁子
 * @author 水川　鈴奈
 * @author 嶌田　治奈
 * @author 小枩　夏輝
 * @author 阿賀野
 * @author 矢矧
 * @author ウィリアム
 */
public class ChequeController {
    
    private final util.Validacao v;
    private Cheque c;

    public ChequeController() {
        this.v = new Validacao();
    }

    public Cheque getC() {
        return c;
    }

    public void setC(Cheque c) {
        this.c = c;
    }
    //int codigo, ContaReceber cr, String dono, String cpf, double valor, Date data, Date predata, int nAgencia, String nConta, String nBanco, String nCheque, String obs, Date dataComp, String motivo
    public int varidar(int codigo, String dono, String cpf, String valor, Date data, Date predata, String nAgencia, String nConta, String nBanco, String nCheque, String obs, Date dataComp, String motivo, boolean flag)
    {
        c.setCodigo(codigo);
        if(dono.trim().equals(""))
        {
            return 1;
        }
        c.setDono(dono);
        
        if(cpf.trim().equals(""))
        {
            return 2;
        }
        c.setCpf(cpf);
        
        if(v.ConverteNumeroReal(valor) <= 0)
        {
            return 3;
        }
        c.setValor(v.ConverteNumeroReal(valor));
        
        if(!v.ValidarDataDuasData(predata, data) || !v.ValidarDataDuasDataIgual(predata, data))
        {
            return 4;
        }
        c.setData(data);
        c.setDataComp(dataComp);
        
        if(nAgencia.trim().equals(""))
        {
            return 5;
        }
        c.setnAgencia(nAgencia);
        
        if(nConta.trim().equals(""))
        {
            return 6;
        }
        c.setnConta(nConta);
        
        if(nBanco.trim().equals(""))
        {
            return 7;
        }
        c.setnBanco(nBanco);
        
        if(nCheque.trim().equals(""))
        {
            return 8;
        }
        c.setnCheque(nCheque);
        
        if(codigo != 0)
        {
            if(!v.ValidarDataDuasData(dataComp, data))
            {
                return 9;
            }
            c.setDataComp(dataComp);
            
            if(flag && motivo.trim().equals(""))
            {
                return 10;
            }
            c.setMotivo(motivo);
        }
        c.setObs(obs);
        return 0;
    }
    
    public boolean gravar()
    {
        return c.gravar();
    }
    
    public void buscaConta(int codigo)
    {
        c = new Cheque().buscar(codigo);
    }
    
    public boolean excluir()
    {
        return c.excluir();
    }
}
