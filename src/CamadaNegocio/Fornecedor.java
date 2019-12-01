package CamadaNegocio;

import CamadaLogica.Banco;
import CamadaLogica.ReadOnlyTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JTable;

/**
 *
 * @author 吉野　廉
 * @author モニカ
 * @author 海女
 * @author 御子
 * @author 稲荷
 * 
 * @author 弐条
 * @author 七草
 */
public class Fornecedor 
{
    private int codigo;
    private Cidade cid;
    private String nome;
    private String tel;
    private String cel;
    private String email;
    private String endereco;

    public Fornecedor(int codigo, Cidade cid, String nome, String tel, String cel, String email, String endereco) {
        this.codigo = codigo;
        this.cid = cid;
        this.nome = nome;
        this.tel = tel;
        this.cel = cel;
        this.email = email;
        this.endereco = endereco;
    }

    public Fornecedor() {
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    ///---------------------------------------------------------------- Data Base---------------------------------------------
    public boolean gravar()  // using This
    {
        String sql;
        if(this.codigo == 0)
        {
            sql = "insert into fornecedor (cid_codigo, forn_nome, forn_telefone, forn_celular, forn_email, forn_endereco\n" +
                    ") values ("+this.cid.getCodigo()+", '"+this.getNome()+"', '"+this.getTel()+"', '"+this.getCel()+"', '"+this.email+"', '"+this.endereco+"')";
        }
        else
        {
            sql = "update fornecedor set cid_codigo = "+this.cid.getCodigo()+", forn_nome = '"+this.getNome()+"', "
                    + " forn_telefone = '"+this.getTel()+"', forn_celular = '"+this.getCel()+"', forn_email = '"+this.email+"', forn_endereco = '"+this.endereco+"' where forn_codigo = "+this.codigo+"";
        }
        return Banco.getCon().manipular(sql);
    }
   
    public boolean excluir(int codigo)
    {
        String sql;
        sql = "delete from fornecedor where forn_codigo = "+codigo+"";
        return Banco.getCon().manipular(sql);
    }
    
    public Fornecedor buscarNome(String s)
    {
        String sql;
        sql = "select forn_codigo, cid_codigo, forn_nome, "
                + "forn_telefone, "
                + "forn_celular, forn_email, forn_endereco "
                + "from fornecedor "
                + "where forn_nome like '%"+s+"%' order by forn_nome";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Fornecedor(rs.getInt(1), new Cidade().buscarCodigo(rs.getInt(2)), rs.getString(3), 
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public boolean checarNome(String s)
    {
        String sql;
        sql = "select forn_codigo, cid_codigo, forn_nome, "
                + "forn_telefone, "
                + "forn_celular, forn_email"
                + "from fornecedor "
                + "where forn_nome = '"+s+"' order by forn_nome";
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
    
    public Fornecedor buscarCodigo(int i)
    {
        String sql;
        sql = "select forn_codigo, cid_codigo, forn_nome, "
                + "forn_telefone, "
                + "forn_celular, forn_email, forn_endereco "
                + "from fornecedor "
                + "where forn_codigo = "+i+" ";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Fornecedor(rs.getInt(1), new Cidade().buscarCodigo(rs.getInt(2)), rs.getString(3), 
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
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
            query = "select forn_codigo, cid_codigo, forn_nome, "
                + "forn_telefone, "
                + "forn_celular, forn_email "
                + "from fornecedor "
                + "order by forn_nome";
        }
        else
        {
            switch (tipo)
            {
//                case 0:
//                {
//                    query = "select forn_codigo, cid_codigo, forn_nome, "
//                        + "forn_telefone, "
//                        + "forn_celular, forn_email "
//                        + "from fornecedor "
//                        + "where forn_codigo = "+valor+"  order by forn_nome";
//                    break;
//                }
                case 0:
                {
                    query = "select forn_codigo, cid_codigo, forn_nome, "
                        + "forn_telefone, "
                        + "forn_celular, forn_email "
                        + "from fornecedor "
                        + "where forn_nome ilike '%"+valor+"%'  order by forn_nome";
                    break;
                }
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }

    public static void configuraModel(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Código", "Nome", "Telefone", "Celular", "E-mail"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(300);
    }

}
