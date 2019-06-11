/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CamadaNegocio;

import java.util.Date;

/**
 *
 * @author 羽根川　翼
 * @author 阿賀野
 * @author 矢矧
 */
public class AjustarFolha {
    private int codigo;
    private Servico serv;
    private Folha f;
    private int qtd;
    private Date data;
    private boolean flag;
    private String obs;
    private Funcionario func;

    public AjustarFolha(int codigo, Servico serv, Folha f, int qtd, Date data, boolean flag, String obs, Funcionario func) {
        this.codigo = codigo;
        this.serv = serv;
        this.f = f;
        this.qtd = qtd;
        this.data = data;
        this.flag = flag;
        this.obs = obs;
        this.func = func;
    }

    public AjustarFolha() {
        this.serv = new Servico();
        this.f = new Folha();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Servico getServ() {
        return serv;
    }

    public void setServ(Servico serv) {
        this.serv = serv;
    }

    public Folha getF() {
        return f;
    }

    public void setF(Folha f) {
        this.f = f;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
    }
    
    public void gravar()
    {
        
    }
    
    
}
