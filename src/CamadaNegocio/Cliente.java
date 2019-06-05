/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CamadaNegocio;

import CamadaLogica.Banco;
import CamadaLogica.ReadOnlyTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;

/**
 *
 * @author 吉野　廉
 * @author 阿武隈
 * @author 長門
 * @author 大和
 */
public class Cliente {
    private int codigo;
    private Cidade cid;
    private String nome;
    private String endereco;
    private int nunero;
    private String cep;
    private String complemento;
    private String telefone;
    private String celular;
    private Boolean status;
    private String email;
    private Date dataC;

    public Cliente(int codigo, Cidade cid, String nome, String endereco, int nunero, String cep, String complemento, String telefone, String celular, Boolean status, String email, Date dataC, String cpf, String rg, Date dataNasc, String org_insc, String razasoci, String cnpj) {
        this.codigo = codigo;
        this.cid = cid;
        this.nome = nome;
        this.endereco = endereco;
        this.nunero = nunero;
        this.cep = cep;
        this.complemento = complemento;
        this.telefone = telefone;
        this.celular = celular;
        this.status = status;
        this.email = email;
        this.dataC = dataC;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNasc = dataNasc;
        this.org_insc = org_insc;
        this.razasoci = razasoci;
        this.cnpj = cnpj;
    }
    
    private String cpf;
    private String rg;
    private Date dataNasc;
    
    private String org_insc;
    
    private String razasoci;
    private String cnpj;

    public Cliente(int codigo, Cidade cid, String nome, String endereco, int nunero, String cep, String complemento, String telefone, String celular, Boolean status, String email, String cpf, String rg, String org_insc, Date dataNasc, String razasoci, String cnpj) {
        this.codigo = codigo;
        this.cid = cid;
        this.nome = nome;
        this.endereco = endereco;
        this.nunero = nunero;
        this.cep = cep;
        this.complemento = complemento;
        this.telefone = telefone;
        this.celular = celular;
        this.status = status;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.org_insc = org_insc;
        this.dataNasc = dataNasc;
        this.razasoci = razasoci;
        this.cnpj = cnpj;
    }

    public Cliente(int codigo, Cidade cid, String nome, String endereco, int nunero, String cep, String complemento, String telefone, String celular, Boolean status, String email) {
        this.codigo = codigo;
        this.cid = cid;
        this.nome = nome;
        this.endereco = endereco;
        this.nunero = nunero;
        this.cep = cep;
        this.complemento = complemento;
        this.telefone = telefone;
        this.celular = celular;
        this.status = status;
        this.email = email;
    }

    public Cliente() {
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNunero() {
        return nunero;
    }

    public void setNumero(int nunero) {
        this.nunero = nunero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrg_insc() {
        return org_insc;
    }

    public void setOrg_insc(String org_insc) {
        this.org_insc = org_insc;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getRazasoci() {
        return razasoci;
    }

    public void setRazasoci(String razasoci) {
        this.razasoci = razasoci;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Date getDataC() {
        return dataC;
    }

    public void setDataC(Date dataC) {
        this.dataC = dataC;
    }
    
    //-----------------------------------------------------DAO---------------------------------------------------------
    public boolean gravar()  // using This
    {
        String sql;
        if(this.codigo == 0)
        {
            sql = "insert into cliente (cid_codigo, cli_nome, cli_endereco, cli_numero, cli_cep, cli_complemento, cli_telefone, cli_celular, cli_email, cli_cadastro, cli_status) "
                    + "values ("+this.cid.getCodigo()+", '"+this.nome+"', '"+this.endereco+"', "+this.nunero+", '"+this.cep+"', '"+this.complemento+"', "
                    + "'"+this.telefone+"', "
                    + "'"+this.celular+"', '"+this.email+"', '"+Date.from(Instant.now())+"', true)";
        }
        else
        {
            sql = "update cliente set cid_codigo = "+this.cid.getCodigo()+", cli_nome = '"+this.nome+"', cli_endereco = '"+this.endereco+"', "
                    + " cli_numero = "+this.nunero+", cli_cep = '"+this.cep+"', cli_complemento = '"+this.complemento+"', "
                    + " cli_telefone = '"+this.telefone+"', cli_celular = '"+this.celular+"',  cli_email = '"+this.email+"' where cli_codigo = "+this.codigo+"";
        }
        return Banco.getCon().manipular(sql);
    }
    public boolean gravarJuridica(boolean flag)  // using This
    {
        String sql;
        if(flag)
        {
            sql = "insert into juridica (cli_codigo, cli_razasoci, cli_cnpj, cli_inscest) "
                    + "values ("+this.codigo+", '"+this.razasoci+"', '"+this.cnpj+"', '"+this.org_insc+"')";
        }
        else
        {
            sql = "update juridica set cli_razasoci = '"+this.razasoci+"', cli_cnpj = '"+this.cnpj+"', cli_inscest = '"+this.org_insc+"' where cli_codigo = "+this.codigo+"";
        }
        return Banco.getCon().manipular(sql);
    }
    public boolean gravarFisica(boolean flag)  // using This
    {
        String sql;
        if(flag)
        {
            sql = "insert into fisica (cli_codigo, cli_cpf, cli_rg, cli_orgemi, cli_datanasc) "
                    + "values ("+this.codigo+", '"+this.cpf+"', '"+this.rg+"', '"+this.org_insc+"', '"+this.dataNasc+"')";
        }
        else
        {
            sql = "update fisica set cli_cpf = '"+this.cpf+"', cli_rg = '"+this.rg+"', cli_orgemi = '"+this.org_insc+"', cli_datanasc = '"+this.dataNasc+"' where cli_codigo = "+this.codigo+"";
        }
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo)
    {
        String sql;
        sql = "update cliente set cli_status = false where cli_codigo = "+codigo+"";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean recuperar(int codigo)
    {
        String sql;
        sql = "update cliente set cli_status = true where cli_codigo = "+codigo+"";
        return Banco.getCon().manipular(sql);
    }
        
    public Cliente buscarCodigo(int c)
    {
        String sql;
        sql = "select cli_codigo, cid_codigo, cli_nome, cli_endereco, cli_numero, cli_cep, "
                + "cli_complemento, cli_telefone, cli_celular, cli_status, cli_email  from cliente where cli_codigo = "+c+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {  //int codigo, Cidade cid, String nome, String endereco, int nunero, String cep, 
                //String complemento, String telefone, String celular, Boolean status, String email
                return new Cliente(rs.getInt("cli_codigo"), new Cidade().buscarCodigo(rs.getInt(2)), 
                        rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), 
                        rs.getString(7), rs.getString(8), rs.getString(9), rs.getBoolean(10), rs.getString(11));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public void buscarCodigoFisica(int c)
    {
        String sql;
        sql = "select cli_cpf, cli_rg, cli_orgemi, cli_datanasc  from fisica where cli_codigo = "+c+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            { 
                this.cpf = rs.getString(1);
                this.rg = rs.getString(2);
                this.org_insc = rs.getString(3);
                this.dataNasc = rs.getDate(4);
            }
            else
            {
                this.cpf = null;
                this.rg = null;
                this.org_insc = null;
                this.dataNasc = null;
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void buscarCodigoJuridica(int c)
    {
        String sql;
        sql = "select cli_razasoci, cli_cnpj, cli_inscest from juridica where cli_codigo = "+c+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {  
                this.razasoci = rs.getString(1);
                this.cnpj = rs.getString(2);
                this.org_insc = rs.getString(3);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean VerificaCliente(String cpf, String cnpj)
    {
        String sql;
        if(cpf != null)
            sql = "select * from fisica where cli_cpf = '"+cpf+"'";
        else
            sql = "select * from juridica where cli_cnpj = '"+cnpj+"'";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return true;
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public int RetornaCodigo(String cpf, String cnpj)
    {
        String sql;
        if(cpf == null)
            sql = "select * from fisica where cli_cpf = '"+cpf+"'";
        else
            sql = "select * from juridica where cli_cnpj = '"+cnpj+"'";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return rs.getInt("cli_codigo");
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    public int RetornaMaxCodigo()
    {
        String sql = "select max(cli_codigo) from cliente";
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
    
    public static ResultSet buscarDados(String valor, int tipo, boolean flag)//Para consulta
    {
      //"Código", "Nome", "CPF/CNPJ", "Telefone", "Celular", "Enderecço", "Numero", "Complemento"
        String query = null;
        if(flag)
        {   //Fisica
            if (valor.equals(""))
            {
                query = "select c.cli_codigo, c.cli_nome, f.cli_cpf,  c.cli_telefone, c.cli_celular, c.cli_endereco, c.cli_numero,c.cli_complemento, c.cli_status from cliente c, fisica f where c.cli_codigo = f.cli_codigo order by c.cli_nome";
            }
            else
            {
                switch (tipo)
                {
//                    case 0:
//                    {
//                        query = "select c.cli_codigo, c.cli_nome, f.cli_cpf, c.cli_telefone, c.cli_celular, c.cli_endereco, c.cli_numero,c.cli_complemento, c.cli_status from cliente c, fisica f where c.cid_codigo = " + Integer.parseInt(valor) + " order by c.cli_nome";
//                        break;
//                    }
                    case 0:
                    {
                        query = "select c.cli_codigo, c.cli_nome, f.cli_cpf, c.cli_telefone, c.cli_celular, c.cli_endereco, c.cli_numero,c.cli_complemento, c.cli_status from cliente c, fisica f where c.cli_nome ilike '%" + valor + "%' and c.cli_codigo = f.cli_codigo order by c.cli_nome";
                        break;
                    }
                    case 1:
                    {
                        query = "select c.cli_codigo, c.cli_nome, f.cli_cpf, c.cli_telefone, c.cli_celular, c.cli_endereco, c.cli_numero,c.cli_complemento, c.cli_status from cliente c, fisica f where f.cli_cpf = " + valor + " and c.cli_codigo = f.cli_codigo order by c.cli_nome";
                        break;
                    }
                }
            }
        }
        else // juridica
        {
            if (valor.equals(""))
            {
                query = "select c.cli_codigo, c.cli_nome, j.cli_cnpj, c.cli_telefone, c.cli_celular, c.cli_endereco, c.cli_numero,c.cli_complemento, c.cli_status from cliente c, juridica j where c.cli_codigo = j.cli_codigo order by c.cli_nome";
            }
            else
            {
                switch (tipo)
                {
//                    case 0:
//                    {
//                        query = "select c.cli_codigo, c.cli_nome, j.cli_cnpj, c.cli_telefone, c.cli_celular, c.cli_endereco, c.cli_numero,c.cli_complemento, c.cli_status from cliente c, juridica j where c.cid_codigo = " + Integer.parseInt(valor) + " order by c.cli_nome";
//                        break;
//                    }
                    case 0:
                    {
                        query = "select c.cli_codigo, c.cli_nome, j.cli_cnpj, c.cli_telefone, c.cli_celular, c.cli_endereco, c.cli_numero,c.cli_complemento, c.cli_status from cliente c, juridica j where c.cli_nome ilike '%" + valor + "%' and c.cli_codigo = j.cli_codigo order by c.cli_nome";
                        break;
                    }
                    case 2:
                    {
                        query = "select c.cli_codigo, c.cli_nome, j.cli_cnpj, c.cli_telefone, c.cli_celular, c.cli_endereco, c.cli_numero,c.cli_complemento, c.cli_status from cliente c, juridica j where j.cid_cnpj like '%" + valor + "%' order by c.cli_nome";
                        break;
                    }
                }
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static void configuraModel(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Código", "Nome", "CPF/CNPJ", "Telefone", "Celular", "Enderecço", "Numero", "Complemento", "Status"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(250);
        jTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(7).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(8).setPreferredWidth(100);
    }
    
}
