/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaNegocio.Caixa;
import CamadaNegocio.Funcionario;
import java.awt.Color;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
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
    private final mensagens m;

    public CaixaController() {
        this.c = new Caixa();
        f = new Funcionario();
        v = new Validacao();
        m = new mensagens();
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
//        valor = valor.replaceAll("\\.", "");
//        valor = valor.replace(',', '.');
        c.setSaldoI(v.ConverteNumeroReal(valor));
        if(c.getSaldoI() == -999)
            return 1;
        if(c.getSaldoI() <= 0)
            return 2;
        c.setData(data);
        c.setCodigo(0);
        c.setFuncI(f);
        return 0;
    }
    
    public boolean abrir()
    {
        return c.gravar();
    }
    
    ///-------------------------------------------------------------------- CLOSE --------------------------------------
    
    public void buscarCaixa()
    {
        c = new Caixa().buscaCaixa();
    }
    
    public double buscaDesconto(String data)
    {
        return c.TotalDesconto(data);
    }
    
    public double buscaValorReal(String data, String valorC, String valorR)
    {
        return c.TotalPedido(data) + v.ConverteNumeroReal(valorC) - v.ConverteNumeroReal(valorR);
    }
    
    public double SaldoRetirado()
    {
        return c.SaldoRetirado();
    }
    
    public Color verificaDiferenca(String valor, String valorR)
    {
        if(v.ConverteNumeroReal(valor) == v.ConverteNumeroReal(valorR))
        {
            return Color.blue;
        }
        else
        {
            if(v.ConverteNumeroReal(valor) > v.ConverteNumeroReal(valorR))
            {
                return Color.yellow;
            }
            else
            {    
                return Color.red;
            }
        }
    }
    
    public double diferenca(String valor, String valorR)
    {
        return v.ConverteNumeroReal(valor) - v.ConverteNumeroReal(valorR);
    }
    
    public int validar(String valor, String diferenca, String valorR)
    {
        if(v.ConverteNumeroReal(valor) < 0)
        {
            return 1;
        }
        if(v.ConverteNumeroReal(diferenca) > 0)
        {
            if(m.Pergunta("Existe Diferença! Deseja Continuar?", "Informação") != JOptionPane.YES_OPTION)
            {
                return 2;
            }
        }
        c.setFuncF(f);
        c.setValorR(v.ConverteNumeroReal(valorR));
        c.setSaldoF(v.ConverteNumeroReal(valor));
        return 0;
    }
    
    public boolean fechar()
    {
        return c.gravar();
    }
}
