/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaNegocio.Cidade;
import CamadaNegocio.Estado;
import CamadaNegocio.Cliente;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import util.*;
/**
 * 
 * @author 吉野　廉
 * @author 阿武隈
 * @author 長門
 * @author 大和
 * 
 * @author 弐条
 * @author 七草
 */
public class ClienteController {
    private Cliente c;
    private final Cidade cid;
    private final Estado uf;
    private final Validacao v;
    public ClienteController() {
        v = new Validacao();
        c = new Cliente();
        cid = new Cidade();
        uf = new Estado();
    }

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
    }
    
    public int validar(String codigo, String cidade, String nome, String endereco, String numero, String complemento, String cep, String telefone, String celular, String email, String cpf, String rg, String orgemi_insc, Date dataNasc, String razasoci, String cnpj, boolean flag)
    {
        c.setComplemento(complemento);//11
        c.setCodigo(Integer.parseInt(codigo));//1
        if(cidade.equals(""))//informa cidade
        {
            return 1;
        }
        c.setCid(new Cidade().buscarCodigo(Integer.parseInt(cidade)));//2
        
        if(nome.trim().equals("")) // nome nao informado
        {
            return 2;
        }
        c.setNome(nome);//3
        
        if(orgemi_insc.trim().equals("")) // orgemi nao informado
        {
            return 7;
        }
        if(orgemi_insc.length() > 2) // orgemi > 2 nao informado
        {
            return 8;
        }
        c.setOrg_insc(orgemi_insc);//4
        if(endereco.trim().equals("")) // endereco nao informado
        {
            return 9;
        }
        
        c.setEndereco(endereco);//5
        if(numero.trim().equals("")) // numero nao informado
        {
            return 10;
        }
        try {
            int num = Integer.parseInt(numero);
            c.setNumero(num);//6
        } catch (NumberFormatException e) {
            return 11;
        }
        String telT = telefone.replace(" ", "");
        String telC = celular.replace(" ", "");
        if(telT.equals("()-") && telC.equals("()-"))
        {
            return 12;
        }
        c.setTelefone(telefone);//7
        c.setCelular(celular);//8
        
        if(cep.trim().equals(""))
        {
            return 13;
        }
        c.setCep(cep);//9
        
        if(!email.trim().equals(""))
        {
            if(!v.ValidarEmail(email))
                return 14;
        }
        c.setEmail(email);//10
        if(flag)
        {
            if(cpf.trim().length() != 14)
            {
                return 15;
            }
            String cpfA = cpf;
            cpfA = cpfA.replaceAll("\\.", "");
            cpfA = cpfA.replaceAll("/", "");
            cpfA = cpfA.replaceAll("-", "");
            int dig1, dig2;
            dig1 = v.PrimeiroDigito(cpfA);
            dig2 = v.SegundoDigito(cpfA);  
            String d1, d2;
            d1 = Integer.toString(dig1);
            d2 = Integer.toString(dig2);
            if(cpfA.charAt(9) != d1.charAt(0) || cpfA.charAt(10) != d2.charAt(0))
            {
                return 21;
            }
            c.setCpf(cpf);//1
            if(rg.trim().equals(""))
            {
                return 16;
            }
            c.setRg(rg);//2
            if(!v.ValidarDataMenorAtual(dataNasc))
            {
                return 17;
            }
            c.setDataNasc(dataNasc);//3
            c.setCnpj(null);
        }
        else
        {
            if(cnpj.trim().length() != 18)
            {
                return 18;
            }
            c.setCnpj(cnpj);//1
            if(razasoci.trim().equals(""))
            {
                return 19;
            }
            c.setRazasoci(razasoci);//2
            c.setCpf(null);
        }
        if(c.VerificaCliente(cpf,cnpj) && c.getCodigo() == 0)
        {
            return 20;
        }
        return 0;
    }
    
    public Cliente buscaVerifica()
    {
        return c.buscarCodigo(c.RetornaCodigo(c.getCpf(), c.getCnpj()));
    }
    
    public void localizar(int codigo)
    {
        c = c.buscarCodigo(codigo);
    }
    
    public boolean gravar()
    {
        return c.gravar();
    }
    
    public boolean gravarSegundaParte(boolean flag)
    {
        c.setCodigo(c.RetornaMaxCodigo());
        if(c.getCpf() != null)
            return c.gravarFisica(flag);
        return c.gravarJuridica(flag);
    }
    
    public boolean recuperar()
    {
        return c.recuperar(c.getCodigo()); 
    }
    
    public boolean excluir(int codigo)
    {
        return c.excluir(codigo);
    }
    
    public void localizarCidade(int codigo)
    {
       c.setCid(cid.buscarCodigo(codigo));
    }
    
    public void CarregaEstado(JComboBox c)
    {
        ArrayList<Estado> lista;
        lista = uf.buscarALL();
        for(int i = 0; i < lista.size(); i++)
        {
            c.addItem(lista.get(i).getSigla());
            c.updateUI();
        }
        c.setSelectedIndex(1);
    }
    
    public void CarregaCidade(JComboBox c, String uf)
    {
        ArrayList<Cidade> lista;
        lista = cid.buscarPEstado(uf);
        for(int i = 0; i < lista.size(); i++)
        {
            c.addItem(lista.get(i).getNome());
            c.updateUI();
        }
    }
    
    public int buscaCodigoCidade(String cid, String uf)
    {
        //return 0;
        return this.cid.buscarPCidadeEstado(cid, uf).getCodigo();
    }
    
    public Cliente buscaClienteCodigo(int codigo)
    {
        Cliente temp = c.buscarCodigo(codigo);
        if(temp != null)
            c = temp;
        return temp;
    }
    
    public void carregaFisica(int codigo)
    {
       c.buscarCodigoFisica(codigo);
    }
    
    public void carregaJuridica(int codigo)
    {
       c.buscarCodigoJuridica(codigo);
    }
    
    public void Reativar(int codigo)
    {
        c.recuperar(codigo);
    }
}
