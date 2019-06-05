package CamadaNegocio;

import CamadaLogica.Banco;
import CamadaLogica.ReadOnlyTableModel;
import br.com.ikeda.beans.jTableBancoDados;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 */
public class Funcionario 
{
    private int codigo; //1
    private Cidade cid; //2
    private String nome; // 3
    private String login; // 4
    protected String senha; // 5
    private int nivel; // 6
    private String cpf;  // 7
    private String rg;  // 8
    private String orgemi;  // 9
    private String endereco;  //10
    private int numero; // 11
    private String complemento; // 12
    private String telefone; // 13
    private String celular; // 14
    private String caminho; // 15
    private LocalDate dtadmicao; //16 
    private LocalDate dtdemicao; //17
    private String cep; //18

    public Funcionario(int codigo, Cidade cid, String nome, String login, String senha, int nivel, String cpf, String rg, String orgemi, String endereco, int numero, String complemento, String telefone, String celular, String caminho, LocalDate dtadmicao, LocalDate dtdemicao, String cep) {
        this.codigo = codigo;
        this.cid = cid;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.nivel = nivel;
        this.cpf = cpf;
        this.rg = rg;
        this.orgemi = orgemi;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.telefone = telefone;
        this.celular = celular;
        this.caminho = caminho;
        this.dtadmicao = dtadmicao;
        this.dtdemicao = dtdemicao;
        this.cep = cep;
        Banco.conectar();
    }

    public Funcionario() {
        Banco.conectar();
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
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

    public String getOrgemi() {
        return orgemi;
    }

    public void setOrgemi(String orgemi) {
        this.orgemi = orgemi;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public LocalDate getDtadmicao() {
        return dtadmicao;
    }

    public void setDtadmicao(LocalDate dtadmicao) {
        this.dtadmicao = dtadmicao;
    }

    public LocalDate getDtdemicao() {
        return dtdemicao;
    }

    public void setDtdemicao(LocalDate dtdemicao) {
        this.dtdemicao = dtdemicao;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    //------------------------------------------DAO---------------------------------------------------------------
    /*
    func_codigo, cid_codigo, func_nome, func_login, func_senha, func_nivel, func_cpf, func_rg, func_orgemi, func_endereco, func_numero, func_complemento, func_telefone, func_celular, func_caminho, func_dtadimicao, func_dtdemissao, func_cep
    */
    public boolean gravar()  // using This
    {
        String sql;
        if(this.codigo == 0)
        {
            sql = "insert into funcionario (cid_codigo, func_nome, func_login, func_senha, func_nivel, func_cpf, func_rg, func_orgemi, func_endereco, func_numero, func_complemento, func_telefone, func_celular, func_caminho, func_dtadimicao, func_dtdemissao, func_cep\n" +
") values ("+this.cid.getCodigo()+", '"+this.getNome()+"', '"+this.getLogin()+"', '"+this.getSenha()+"', "+this.getNivel()+", '"+this.getCpf()+"', '"+this.getRg()+"', '"+this.getOrgemi()+"', '"+this.getEndereco()+"', "+this.getNumero()+", '"+this.getComplemento()+"', '"+this.getTelefone()+"', '"+this.getCelular()+"', '"+this.getCaminho()+"', '"+LocalDate.now()+"', "+null+", '"+this.getCep()+"')";
        }
        else
        {
            sql = "update funcionario set cid_codigo = "+this.cid.getCodigo()+", func_nome = '"+this.getNome()+"', func_login = '"+this.getLogin()+"', func_senha = '"+this.getSenha()+"',"
                    + " func_nivel = "+this.getNivel()+", func_cpf = '"+this.getCpf()+"', func_rg = '"+this.getRg()+"', func_orgemi = '"+this.getOrgemi()+"', func_endereco = '"+this.getEndereco()+"', func_numero = "+this.getNumero()+", func_complemento = '"+this.getComplemento()+"', func_telefone = '"+this.getTelefone()+"', func_celular = '"+this.getCelular()+"', func_caminho = '"+this.getCaminho()+"', func_cep = '"+this.getCep()+"' where func_codigo = "+this.codigo+"";
        }
        return Banco.getCon().manipular(sql);
    }
   
    public boolean excluir(int codigo)
    {
        String sql;
        sql = "update funcionario set func_dtdemissao = '"+LocalDate.now()+"'  where func_codigo = "+codigo+"";
        return Banco.getCon().manipular(sql);
    }
    
    public Funcionario buscarNome(String s)
    {
        String sql;
        sql = "select func_codigo, cid_codigo, func_nome, func_login, func_senha, func_nivel, func_cpf, "
                + "func_rg, func_orgemi, func_endereco, func_numero, func_complemento, func_telefone, "
                + "func_celular, func_caminho, func_dtadimicao, func_dtdemissao, func_cep "
                + "from funcionario "
                + "where func_nome like '%"+s+"%' and func_dtdemissao is not null order by func_nome";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Funcionario(rs.getInt(1), new Cidade().buscarCodigo(rs.getInt(2)), rs.getString(3), 
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), 
                        rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), LocalDate.parse(rs.getString(16))
                , rs.getString(17) == null ? null : LocalDate.parse(rs.getString(17)), rs.getString(18));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public Funcionario buscarCodigo(int i)
    {
        String sql;
        sql = "select func_codigo, cid_codigo, func_nome, func_login, func_senha, func_nivel, func_cpf, "
                + "func_rg, func_orgemi, func_endereco, func_numero, func_complemento, func_telefone, "
                + "func_celular, func_caminho, func_dtadimicao, func_dtdemissao, func_cep "
                + "from funcionario "
                + "where func_codigo = "+i+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Funcionario(rs.getInt(1), new Cidade().buscarCodigo(rs.getInt(2)), rs.getString(3), 
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), 
                        rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), LocalDate.parse(rs.getString(16))
                , rs.getString(17) == null ? null : LocalDate.parse(rs.getString(17)), rs.getString(18));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public Funcionario logar(String login, String senha)
    {
        String sql;
        sql = "select func_codigo, cid_codigo, func_nome, func_login, func_senha, func_nivel, func_cpf, "
                + "func_rg, func_orgemi, func_endereco, func_numero, func_complemento, func_telefone, "
                + "func_celular, func_caminho, func_dtadimicao, func_dtdemissao, func_cep "
                + "from funcionario "
                + "where func_login = '"+login+"' and func_senha = '"+senha+"' and func_dtdemissao is null";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Funcionario(rs.getInt(1), new Cidade().buscarCodigo(rs.getInt(2)), rs.getString(3), 
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), 
                        rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), LocalDate.parse(rs.getString(16))
                , null, rs.getString(18));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public Funcionario buscarCPF(String s)
    {
        String sql;
        sql = "select func_codigo, cid_codigo, func_nome, func_login, func_senha, func_nivel, func_cpf, "
                + "func_rg, func_orgemi, func_endereco, func_numero, func_complemento, func_telefone, "
                + "func_celular, func_caminho, func_dtadimicao, func_dtdemissao, func_cep "
                + "from funcionario "
                + "where func_cpf = '"+s+"' and func_dtadimicao is not null order by func_nome";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Funcionario(rs.getInt(1), new Cidade().buscarCodigo(rs.getInt(2)), rs.getString(3), 
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), 
                        rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), LocalDate.parse(rs.getString(16))
                , rs.getString(17) == null ? null : LocalDate.parse(rs.getString(17)), rs.getString(18));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public static ResultSet buscarDados(String valor, int tipo)//Para consulta
    {
      
        String query = null;
        if (valor.equals(""))
        {
            query = "select func_codigo, cid_codigo, func_nome, func_login, func_senha, func_nivel, func_cpf, "
                + "func_rg, func_orgemi, func_endereco, func_numero, func_complemento, func_telefone, "
                + "func_celular, func_caminho, func_dtadimicao, func_dtdemissao, func_cep "
                + "from funcionario order by func_nome";
        }
        else
        {
            switch (tipo)
            {
//                case 0:
//                {
//                    query = "select func_codigo, cid_codigo, func_nome, func_login, func_senha, func_nivel, func_cpf, "
//                    + "func_rg, func_orgemi, func_endereco, func_numero, func_complemento, func_telefone, "
//                    + "func_celular, func_caminho, func_dtadimicao, func_dtdemissao, func_cep "
//                    + "from funcionario "
//                    + "where func_codigo = "+valor+"  order by func_nome";
//                    break;
//                }
                case 0:
                {
                    query = "select func_codigo, cid_codigo, func_nome, func_login, func_senha, func_nivel, func_cpf, "
                    + "func_rg, func_orgemi, func_endereco, func_numero, func_complemento, func_telefone, "
                    + "func_celular, func_caminho, func_dtadimicao, func_dtdemissao, func_cep "
                    + "from funcionario "
                    + "where func_nome ilike '%"+valor+"%'  order by func_nome";
                    break;
                }
                case 1:
                    query = "select func_codigo, cid_codigo, func_nome, func_login, func_senha, func_nivel, func_cpf, "
                    + "func_rg, func_orgemi, func_endereco, func_numero, func_complemento, func_telefone, "
                    + "func_celular, func_caminho, func_dtadimicao, func_dtdemissao, func_cep "
                    + "from funcionario "
                    + "where func_cpf = '"+valor+"' order by func_nome";
                    break;
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }

    public static void configuraModel(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Código", "Nome", "CPF", "Telefone", "Celular", "Data Adimissão", "Data Demissão"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(90);
        jTable.getColumnModel().getColumn(6).setPreferredWidth(90);
    }
    
    public int checkADMCount()
    {
        String sql;
        sql = "select count(*)"
                + "from funcionario "
                + "where func_nivel = 64 and func_dtadimicao is not null";
        ResultSet rs = Banco.getCon().consultar(sql);
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
