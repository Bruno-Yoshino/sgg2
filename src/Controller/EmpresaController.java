/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaNegocio.Cidade;
import CamadaNegocio.Empresa;
import CamadaNegocio.Funcionario;
import util.Validacao;

/**
 *
 * @author Bruno Yoshino
 */
public class EmpresaController {
    
    private Empresa emp;
    private Cidade cid;
    private util.Validacao vali;

    private String user, password;
    
    public EmpresaController() {
        emp = new Empresa();
        cid = new Cidade();
        vali = new Validacao();
    }

    public Empresa getEmp() {
        return emp;
    }

    public void setEmp(Empresa emp) {
        this.emp = emp;
    }
    
    
    public int validar(String cidade, String nome, String cnpj, String endereco, String numero, String telefone, String email, String caminho, String cep)
    {
        emp.setCaminho(caminho);
        if(cidade.equals(""))//informa cidade
        {
            return 1;
        }
        emp.setCid(new Cidade().buscarCodigo(Integer.parseInt(cidade))); 
        if(nome.trim().equals("")) // nome nao informado
        {
            return 2;
        }
        emp.setNome(nome);
        if(endereco.trim().equals("")) // endereco nao informado
        {
            return 9;
        }
        emp.setEndereco(endereco);
        if(numero.trim().equals("")) // numero nao informado
        {
            return 10;
        }
        try {
            int num = Integer.parseInt(numero);
            emp.setNumero(num);
        } catch (NumberFormatException e) {
            return 11;
        }
        if(telefone.trim().length() <= 3)
        {
            return 12;
        }
        emp.setTelefone(telefone);
        if(cep.trim().equals(""))
        {
            return 13;
        }
        emp.setCep(cep);
        if(cnpj.trim().length() != 18)
        {
            return 14;
        }
        emp.setCnpj(cnpj);
        emp.setEmail(cnpj);
        return 0;
    }
    
    public Empresa localizar()
    {
        Empresa temp = emp.buscarEmpresa(); 
        if(temp == null)
            return null;
        emp = temp;
        return temp;
    }
    
    public boolean gravar()
    {
        return emp.gravar();
    }
    
    
    public void localizarCidade(int codigo)
    {
       emp.setCid(cid.buscarCodigo(codigo));
    }
    
}
