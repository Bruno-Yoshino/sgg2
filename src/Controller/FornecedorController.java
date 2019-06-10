/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaNegocio.Cidade;
import CamadaNegocio.Fornecedor;
import CamadaNegocio.Fornecedor;
import util.Validacao;

/**
 * @author 吉野　廉
 * @author 海女
 * 
 * @author 弐条
 * @author 七草
 */
public class FornecedorController 
{
    private Fornecedor forn;
    private Cidade cid;
    private util.Validacao vali;

    private String user, password;
    
    public FornecedorController() {
        forn = new Fornecedor();
        cid = new Cidade();
        vali = new Validacao();
    }

    public Fornecedor getForn() {
        return forn;
    }

    public void setForn(Fornecedor forn) {
        this.forn = forn;
    }
    
    public int validar(String user, String password) // para login
    {
        if(vali.ValidaTexto(user) == 1)
        {
            return 1;
        }
        if(vali.ValidaTexto(password) == 1)
        {
            return 2;
        }
        this.user = user;
        this.password = password;
        return 0;
    }

    public int validar(String codigo, String cidade, String nome, String telefone, String celular, String email)
    {
        
        if(cidade.equals(""))//informa cidade
        {
            return 1;
        }
        forn.setCodigo(Integer.parseInt(codigo));
        forn.setCid(new Cidade().buscarCodigo(Integer.parseInt(cidade))); 
        if(nome.trim().equals("")) // nome nao informado
        {
            return 2;
        }
        forn.setNome(nome);
        String telT = telefone.replace(" ", "");
        String telC = celular.replace(" ", "");
        if(telT.equals("()-") && telC.equals("()-"))
        {
            return 12;
        }
        forn.setTel(telefone);
        forn.setCel(celular);
        forn.setEmail(email);
        return 0;
    }
    
    public void localizar(int codigo)
    {
        forn = forn.buscarCodigo(codigo);
    }
    
    public boolean gravar()
    {
        return forn.gravar();
    }
    
    public boolean excluir(int codigo)
    {
        return forn.excluir(codigo);
    }
    
    public void localizarCidade(int codigo)
    {
       forn.setCid(cid.buscarCodigo(codigo));
    }
}
