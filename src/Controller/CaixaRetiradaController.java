/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaNegocio.Caixa;
import CamadaNegocio.ContaPagar;
import CamadaNegocio.Funcionario;
import java.util.Date;
import util.Validacao;
import util.mensagens;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 巴御前
 * @author 水川　鈴奈
 * @author 嶌田　治奈
 * @author 小枩　夏輝
 * @author ミシェル
 * @author レア
 * @author レレイナ
 */
public class CaixaRetiradaController {
    private Caixa c;
    private ContaPagar cp;
    private final util.Validacao v = new Validacao(); 
    private final util.mensagens m = new mensagens(); 
    
    public CaixaRetiradaController() {
        c = new Caixa();
        cp = new ContaPagar();
    }

    public Caixa getC() {
        return c;
    }

    public void setC(Caixa c) {
        this.c = c;
    }

    public ContaPagar getCp() {
        return cp;
    }

    public void setCp(ContaPagar cp) {
        this.cp = cp;
    }
    
    public boolean verificaCaixa()
    {
        return c.VerificaCaixaAberto();
    }
    
    public void buscaCaixa()
    {
        cp.setC(new Caixa().buscaCaixa());
    }
    
    public double saldoAtual()
    {
        return new AtualizarCaixaController().saldoAtualizado(cp.getC().getCodigo());
    }
    
    public int validar(String valor, Date data, String motivo, String valorAtual)
    {
        if(v.ConverteNumeroReal(valor) <= 0)
        {
            return 1;
        }
        if(v.ConverteNumeroReal(valor) > v.ConverteNumeroReal(valorAtual))
        {
            return 2;
        }
        if(motivo.trim().equals(""))
        {
            return 3;
        }

        cp.setDataL(data);
        cp.setValorC(v.ConverteNumeroReal(valor));
        cp.setObs(motivo);
        cp.setLocal("");
        cp.setDataP(cp.getDataL());
        cp.setDataV(cp.getDataL());
        cp.setParcela(0);
        cp.setLocal("");
        cp.setValorP(0);
        
        return 0;
    }
    
    public boolean gravar()
    {
        return cp.gravar();
    }
    
    public void buscaFuncionario(int codigo)
    {
        cp.setFunc(new Funcionario().buscarCodigo(codigo));
    }
}
