package Controller;

import CamadaNegocio.Cheque;
import CamadaNegocio.Cliente;
import CamadaNegocio.ContaReceber;
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
    private ContaReceber cr;

    public ChequeController() {
        this.v = new Validacao();
        this.c = new Cheque();
        this.cr = new ContaReceber();
    }

    public Cheque getC() {
        return c;
    }

    public void setC(Cheque c) {
        this.c = c;
    }

    public ContaReceber getCr() {
        return cr;
    }

    public void setCr(ContaReceber cr) {
        this.cr = cr;
    }
    
    //int codigo, ContaReceber cr, String dono, String cpf, double valor, Date data, Date predata, int nAgencia, String nConta, String nBanco, String nCheque, String obs, Date dataComp, String motivo
    public int varidar(int codigo, String dono, String cpf, String valor, Date data, Date predata, String nAgencia, String nConta, String nBanco, String nCheque, String obs, Date dataComp, String motivo, boolean flag, boolean op, String cliente, String codigoCR)
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
        
        if(!v.ValidarDataDuasData(data, predata) && !v.ValidarDataDuasDataIgual(predata, data))
        {
            return 4;
        }
        
        c.setData(data);
        c.setDataComp(dataComp);
        c.setPredata(predata);
        
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
        
        if(codigoCR.equals(""))
        {
            c.setCr(null);
        }
        else
        {
            c.setCr(cr);
        }
        
        if(codigo != 0 && !op)
        {
            if(!v.ValidarDataDuasData(data, dataComp))
            {
                return 9;
            }
            c.setDataComp(dataComp);
            if(flag)
                c.setMotivo(0);
            else
                c.setMotivo(v.ConverteNumeroInteiro(motivo));
        }
        c.setObs(obs);
        c.setCliente(cliente);
        return 0;
    }
    
    public boolean gravar()
    {
        return c.gravar();
    }
    
    public boolean compensar()
    {
        return c.compensar();
    }
    
    public void buscaConta(int codigo)
    {
        c = new Cheque().buscar(codigo);
    }
    
    public boolean excluir()
    {
        return c.excluir();
    }

    public void buscaClietne(int codigo)
    {
        Cliente c = new Cliente().buscarCodigo(codigo);
        this.c.setCliente(c.getNome());
    }
    
    public void buscaContaReceber(int codigo)
    {
        cr = new ContaReceber().buscar(codigo);
        c.setCr(cr);
    }
    
    public String retornaCPF()
    {
        String cpf = new Cliente().retornaCPF(cr.getP().getCli().getCodigo());
        cpf = cpf.replaceAll("/", "");
        cpf = cpf.replaceAll("-", "");
        cpf = cpf.replaceAll("\\.", "");
        return cpf;
    }
    
}
