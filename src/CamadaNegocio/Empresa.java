/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CamadaNegocio;

import CamadaLogica.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Bruno Yoshino
 */
public class Empresa {
    private Cidade cid;
    private String nome, cnpj, endereco, telefone, caminho, email, cep;
    private int numero;
    
    public Empresa(Cidade cid, String nome, String cnpj, String endereco, String telefone, String caminho, String email, String cep, int numero) {
        this.cid = cid;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.caminho = caminho;
        this.email = email;
        this.cep = cep;
        this.numero = numero;
    }
    
    public Empresa() {
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public Cidade getCid() {
        return cid;
    }

    public void setCid(Cidade cid) {
        this.cid = cid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    //-------------------------------------------------DAO-------------------------------------------------------
    
     public boolean gravar()  // using This
    {
         String sql;
         if(verificaEmpresa() == 0)
         {
             sql = "INSERT INTO empresa(\n" +
"	cid_codigo, emp_nome, emp_cnpj, emp_endereco, emp_numero, emp_telefone, emp_caminho, emp_email, emp_cep)\n" +
"	VALUES ("+this.cid.getCodigo()+", '"+this.nome+"', '"+this.cnpj+"', '"+this.endereco+"', "+this.numero+", '"+this.telefone+"', '"+this.caminho+"', '"+email+"', '"+cep+"')";
         }
         else
             sql = "update empresa set cid_codigo = "+this.cid.getCodigo()+", emp_nome = '"+this.nome+"', emp_cnpj = '"+this.cnpj+"', emp_endereco = '"+this.endereco+"', emp_telefone = '"+this.telefone+"', emp_caminho = '"+this.caminho+"', emp_email = '"+email+"', emp_cep = '"+cep+"', emp_numero = "+this.numero+"";
        return Banco.getCon().manipular(sql);
    }
    public Empresa buscarEmpresa()
    {
        String sql;
        sql = "select cid_codigo, emp_nome, emp_cnpj, emp_endereco, emp_telefone, emp_caminho, emp_email, emp_cep, emp_numero from empresa";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {//                       Cidade cid, String nome,             String cnpj,      String endereco, S   tring telefone,  String caminho, int numero
                return new Empresa(new Cidade().buscarCodigo(rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public int verificaEmpresa()
    {
        String sql;
        sql = "select count(*) from empresa";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return rs.getInt(1);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
}
