/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaNegocio.Cidade;
import CamadaNegocio.Funcionario;
import util.Validacao;

/**
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * 
 * @author 弐条
 * @author 七草
 */
public class FuncionarioController {
    private Funcionario func;
    private final Cidade cid;
    private final util.Validacao vali;

    private String user, password;
    
    public FuncionarioController() {
        func = new Funcionario();
        cid = new Cidade();
        vali = new Validacao();
    }

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
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
    
    public Funcionario logar()
    {
        Funcionario f;
         f = func.logar(user, password);
         return f == null ? null : (func = f);
    }

    public int validar(String codigo, String cidade, String nome, String login, String senha, int nivel, String cpf, String rg, String orgemi, String endereco, String numero, String complemento, String telefone, String celular, String caminho, String cep, String senha2)
    {
        func.setCodigo(Integer.parseInt(codigo));
        if(cidade.equals(""))//informa cidade
        {
            return 1;
        }
        func.setCid(new Cidade().buscarCodigo(Integer.parseInt(cidade))); 
        if(nome.trim().equals("")) // nome nao informado
        {
            return 2;
        }
        func.setNome(nome);
        if(login.trim().equals("")) // login nao informado
        {
            return 3;
        }
        func.setLogin(login);
        if(senha.trim().equals("")) // senha nao informado
        {
            return 4;
        }
        if(senha.length() > 15) // erro tamanho senha
        {
            return 5;
        }
        func.setSenha(senha);
        if(nivel == 0) // nivel nao selecionado
        {
            return 6;
        }
        
        if(orgemi.trim().equals("")) // orgemi nao informado
        {
            return 7;
        }
        if(orgemi.length() > 2) // orgemi > 2 nao informado
        {
            return 8;
        }
        func.setOrgemi(orgemi);
        if(endereco.trim().equals("")) // endereco nao informado
        {
            return 9;
        }
        func.setEndereco(endereco);
        if(numero.trim().equals("")) // numero nao informado
        {
            return 10;
        }
        try {
            int num = Integer.parseInt(numero);
            func.setNumero(num);
        } catch (NumberFormatException e) {
            return 11;
        }
        if(telefone.trim().length() <= 3 || celular.trim().length() <= 3)
        {
            return 12;
        }
        func.setTelefone(telefone);
        func.setCelular(celular);
        if(cep.trim().equals(""))
        {
            return 13;
        }
        func.setCep(cep);
        if(func.buscarCPF(cpf) != null && func.getCodigo() == 0)
        {
            return 14;
        }
        func.setCpf(cpf);
        if(!senha.equals(senha2))
        {
            return 15;
        }
        func.setComplemento(complemento);
        if(rg.trim().length() == 3)
        {
            return 16;
        }
        if(cpf.trim().length() == 3)
        {
            return 17;
        }
        
        if(nivel < 0)
        {
            return 18;
        }
        func.setNivel(nivel);
        
        func.setRg(rg);
        func.setCaminho(caminho);
        return 0;
    }
    
    public void localizar(int codigo)
    {
        func = func.buscarCodigo(codigo);
    }
    
    public boolean gravar()
    {
        return func.gravar();
    }
    
    public boolean excluir(int codigo)
    {
        return func.excluir(codigo);
    }
    
    public void localizarCidade(int codigo)
    {
       func.setCid(cid.buscarCodigo(codigo));
    }
    
    public boolean checkADMCount()
    {
        return func.checkADMCount() == 1;
    }
    
    public boolean checkADMCountInicio()
    {
        return func.checkADMCount() == 0;
    }
}
