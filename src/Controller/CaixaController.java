/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaNegocio.Caixa;
import CamadaNegocio.Funcionario;
import java.time.LocalDateTime;
import util.*;

/**
 *
 * @author 吉野　廉
 * @author 磐手
 * @author イントレピッド
 */
public class CaixaController 
{
    private Caixa c;
    private Funcionario f;
    private Validacao v;

    public CaixaController() {
        this.c = new Caixa();
        f = new Funcionario();
        v = new Validacao();
    }

    public Caixa getC() {
        return c;
    }

    public void setC(Caixa c) {
        this.c = c;
    }

    public Funcionario getF() {
        return f;
    }

    public void setF(Funcionario f) {
        this.f = f;
    }
    
    public boolean OpenExist()
    {
        return c.VerificaCaixaAberto();
    }
    
    ///-------------------------------------------------------------------- OPEN --------------------------------------
    
    public int varidar(String valor, LocalDateTime data, int codigo)
    {
        c.setSaldoI(v.ConverteNumeroReal(valor));
        if(c.getSaldoI() == -999)
            return 1;
        if(c.getSaldoI() >= 0)
            return 2;
        c.setData(data);
        c.setCodigo(0);
        return 0;
    }
    
    public boolean abrir()
    {
        return c.gravar();
    }
    
    ///-------------------------------------------------------------------- OPEN --------------------------------------
}
