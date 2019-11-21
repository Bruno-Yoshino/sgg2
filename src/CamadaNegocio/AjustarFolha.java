package CamadaNegocio;

import CamadaLogica.Banco;
import CamadaLogica.ReadOnlyTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JTable;

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
    
    public boolean gravar()
    {
        String sql = "";
        if(codigo == 0)
        {
            sql = "INSERT INTO ajuste_folha( " +
                  " serv_codigo, fo_codigo, func_codigo, af_qtd, af_data, af_flag, af_obs) " +
                  " VALUES ("+(serv == null ? null : serv.getCodigo())+", "+f.getCodigo()+", "+func.getCodigo()+", "+qtd+", '"+data+"', "+flag+", '"+obs+"')";
        }
        else
        {
            sql = "UPDATE ajuste_folha " +
                  " SET serv_codigo="+serv == null ? null : serv.getCodigo()+", fo_codigo="+f.getCodigo()+", func_codigo="+func.getCodigo()+", af_qtd="+qtd+", af_data='"+data+"', af_flag="+flag+", af_obs='"+obs+"' " +
                  " WHERE af_codigo="+codigo+"";
        }
        return Banco.getCon().manipular(sql);
    }
    
    public AjustarFolha buscarCodigo(int i)
    {
        String sql;
        sql = "SELECT af.af_codigo, af.serv_codigo, af.fo_codigo, af.func_codigo, af.af_qtd, af.af_data, af.af_flag, af.af_obs " +
              "FROM ajuste_folha af, servico s, folha f, funcionario func "
            + "Where af.serv_codigo = s.serv_codigo and af.fo_codigo = f.fo_codigo and af.func_codigo = func.func_codigo "
            + "and af.af_codigo = "+i+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {//int codigo, Servico serv, Folha f, int qtd, Date data, boolean flag, String obs, Funcionario func
                return new AjustarFolha(rs.getInt(1), new Servico().buscarCodigo(rs.getInt(2)), new Folha().buscarCodigo(rs.getInt(3)), rs.getInt(5), rs.getDate(6), rs.getBoolean(7), rs.getString(8), new Funcionario().buscarCodigo(4));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public static ResultSet buscarDados(String valor, int tipo, Date data1, Date data2)//Para consulta
    {
      
        String query = null;
        if (valor.equals(""))
        {
            query = "SELECT af.af_codigo, s.serv_nome, f.fo_tamanho, f.fo_descricao, af.af_qtd, af.af_data, af.af_flag, af.af_obs, func.func_nome " +
                    "FROM ajuste_folha af, servico s, folha f, funcionario func "
                    + "Where af.serv_codigo = s.serv_codigo and af.fo_codigo = f.fo_codigo and af.func_codigo = func.func_codigo "
                    + "Order by af.af_data";
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
                    query = "SELECT af.af_codigo, s.serv_nome, f.fo_tamanho, f.fo_descricao, af.af_qtd, af.af_data, af.af_flag, af.af_obs, func.func_nome " +
                            "FROM ajuste_folha af, servico s, folha f, funcionario func "
                            + "Where af.serv_codigo = s.serv_codigo and af.fo_codigo = f.fo_codigo and af.func_codigo = func.func_codigo and func.func_nome ilike '%"+valor+"%' "
                            + "Order by af.af_data";
                    break;
                }
                case 1:
                    query = "SELECT af.af_codigo, s.serv_nome, f.fo_tamanho, f.fo_descricao, af.af_qtd, af.af_data, af.af_flag, af.af_obs, func.func_nome " +
                            "FROM ajuste_folha af, servico s, folha f, funcionario func "
                            + "Where af.af_data = '"+data1+"' and af.serv_codigo = s.serv_codigo and af.fo_codigo = f.fo_codigo and af.func_codigo = func.func_codigo "
                            + "Order by af.af_data";
                    break;
                case 2:
                    query = "SELECT af.af_codigo, s.serv_nome, f.fo_tamanho, f.fo_descricao, af.af_qtd,  af.af_data, af.af_flag, af.af_obs, func.func_nome " +
                            "FROM ajuste_folha af, servico s, folha f, funcionario func "
                            + "Where af.af_data BETWEEN '"+data1+"' and '"+data2+"' and  af.serv_codigo = s.serv_codigo and af.fo_codigo = f.fo_codigo and af.func_codigo = func.func_codigo "
                            + "Order by af.af_data";
                    break;
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
     public static ResultSet Relatorio(String valor, int tipo, Date dataI, Date dataF)
    {
        String query = null;
        switch (tipo)
        {
            case 0:// Periodo
            {
                query = "SELECT * " +
                            "FROM ajuste_folha af, servico s, folha f, funcionario func, empresa emp "
                            + "Where af.serv_codigo = s.serv_codigo and af.fo_codigo = f.fo_codigo and af.func_codigo = func.func_codigo and af.af_data between  '"+dataI+"'  and  '"+dataF+"'  "
                            + "Order by af.af_data";
                break;
            }
            case 1:// Data
            {
                query = "SELECT * " +
                            "FROM ajuste_folha af, servico s, folha f, funcionario func, empresa emp "
                            + "Where af.serv_codigo = s.serv_codigo and af.fo_codigo = f.fo_codigo and af.func_codigo = func.func_codigo and af.af_data = '"+dataI+"'  "
                            + "Order by af.af_data";
                break;
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static void configuraModel(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {//int codigo, Servico serv, Folha f, int qtd, Date data, boolean flag, String obs, Funcionario func
        String colunas[] = new String [] {"Código", "Servico", "Folha", "Qtd", "Data", "Tipo", "Obs", "Funcionario"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(15);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(6).setPreferredWidth(250);
        jTable.getColumnModel().getColumn(7).setPreferredWidth(200);
    }
}
