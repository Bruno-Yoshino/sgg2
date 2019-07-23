/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaNegocio.Caixa;
import CamadaNegocio.ContaPagar;

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
public class AtualizarCaixaController 
{
    private Caixa c;

    public AtualizarCaixaController() {
    }

    public Caixa getC() {
        return c;
    }

    public void setC(Caixa c) {
        this.c = c;
    }
    
    public final double saldoAtualizado(int codigo)
    {
        c = new Caixa().buscaCaixa();
        return c.getSaldoI() - new ContaPagar().saldoRetirado(codigo);
    }
}
