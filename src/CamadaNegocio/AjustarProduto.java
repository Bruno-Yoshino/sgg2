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
import java.util.Date;
import javax.swing.JTable;

/**
 *
 * @author 羽根川　翼
 * @author 阿賀野
 * @author 矢矧
 */
public class AjustarProduto {
    private int codigo;
    private Servico serv;
    private Produto p;
    private int qtd;
    private Date data;
    private boolean flag;
    private String obs;
    private Funcionario func;

    public AjustarProduto(int codigo, Servico serv, Produto p, int qtd, Date data, boolean flag, String obs, Funcionario func) {
        this.codigo = codigo;
        this.serv = serv;
        this.p = p;
        this.qtd = qtd;
        this.data = data;
        this.flag = flag;
        this.obs = obs;
        this.func = func;
    }

    public AjustarProduto() {
        this.serv = new Servico();
        this.p = new Produto();
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

    public Produto getP() {
        return p;
    }

    public void setP(Produto p) {
        this.p = p;
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
            sql = "INSERT INTO ajuste_produto( " +
                  " serv_codigo, pro_codigo, func_codigo, ap_qtd, ap_data, ap_flag, ap_obs) "
                + " VALUES ("+(serv == null ? null : serv.getCodigo())+", "+p.getCodigo()+", "+func.getCodigo()+", "+qtd+", '"+data+"', "+flag+", '"+obs+"')";
        }
        else
        {
            sql = "UPDATE ajuste_produto " +
                  " SET serv_codigo="+serv == null ? null : serv.getCodigo()+", pro_codigo="+p.getCodigo()+", func_codigo="+func.getCodigo()+", ap_qtd="+qtd+", ap_data='"+data+"', ap_flag="+flag+", ap_obs='"+obs+"' " +
                  " WHERE ap_codigo="+codigo+"";
        }
        return Banco.getCon().manipular(sql);
    }
    
    public AjustarProduto buscarCodigo(int i)
    {
        String sql;
        sql = "SELECT ap.ap_codigo, ap.serv_codigo, ap.pro_codigo, ap.func_codigo, ap.ap_qtd, ap.ap_data, ap.ap_flag, ap.ap_obs " +
              "FROM ajuste_produto ap, servico s, produto p, funcionario func "
            + "Where ap.serv_codigo = s.serv_codigo and ap.pro_codigo = p.pro_codigo and ap.func_codigo = func.func_codigo "
            + "and ap.ap_codigo = "+i+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {//int codigo, Servico serv, Produto p, int qtd, Date data, boolean flag, String obs, Funcionario func
                return new AjustarProduto(rs.getInt(1), new Servico().buscarCodigo(rs.getInt(2)), new Produto().buscarCodigo(rs.getInt(3)), rs.getInt(5), rs.getDate(6), rs.getBoolean(7), rs.getString(8), new Funcionario().buscarCodigo(4));
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
            query = "SELECT ap.ap_codigo, ap.serv_codigo, ap.pro_codigo, ap.func_codigo, ap.ap_qtd, ap.ap_data, ap.ap_flag, ap.ap_obs " +
                    "FROM ajuste_produto ap, servico s, produto p, funcionario func "
                    + "Where ap.serv_codigo = s.serv_codigo and ap.pro_codigo = p.pro_codigo and ap.func_codigo = func.func_codigo "
                    + "Order by ap.ap_data";
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
                    query = "SELECT ap.ap_codigo, ap.serv_codigo, ap.pro_codigo, ap.func_codigo, ap.ap_qtd, ap.ap_data, ap.ap_flag, ap.ap_obs " +
                            "FROM ajuste_produto ap, servico s, produto p, funcionario func "
                            + "Where ap.serv_codigo = s.serv_codigo and ap.pro_codigo = p.pro_codigo and ap.func_codigo = func.func_codigo and func.func_nome ilike '%"+valor+"%' "
                            + "Order by ap.ap_data";
                    break;
                }
                case 1:
                    query = "SELECT ap.ap_codigo, ap.serv_codigo, ap.pro_codigo, ap.func_codigo, ap.ap_qtd, ap.ap_data, ap.ap_flag, ap.ap_obs " +
                            "FROM ajuste_produto ap, servico s, produto p, funcionario func "
                            + "Where ap.ap_data = '"+data1+"' and ap.serv_codigo = s.serv_codigo and ap.pro_codigo = p.pro_codigo and ap.func_codigo = func.func_codigo "
                            + "Order by ap.ap_data";
                    break;
                case 2:
                    query = "SELECT ap.ap_codigo, ap.serv_codigo, ap.pro_codigo, ap.func_codigo, ap.ap_qtd, ap.ap_data, ap.ap_flag, ap.ap_obs " +
                            "FROM ajuste_produto ap, servico s, produto p, funcionario func "
                            + "Where ap.ap_data BETWEEN '"+data1+"' and '"+data2+"' and  ap.serv_codigo = s.serv_codigo and ap.pro_codigo = p.pro_codigo and ap.func_codigo = func.func_codigo "
                            + "Order by ap.ap_data";
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
                            "FROM ajuste_produto ap, servico s, produto p, funcionario func, empresa emp "
                            + "Where ap.serv_codigo = s.serv_codigo and ap.pro_codigo = p.pro_codigo and ap.func_codigo = func.func_codigo and ap.ap_data between  '"+dataI+"'  and  '"+dataF+"'  "
                            + "Order by ap.ap_data";
                break;
            }
            case 1:// Data
            {
                query = "SELECT * " +
                            "FROM ajuste_produto ap, servico s, produto p, funcionario func, empresa emp "
                            + "Where ap.serv_codigo = s.serv_codigo and ap.pro_codigo = p.pro_codigo and ap.func_codigo = func.func_codigo and ap.ap_data = '"+dataI+"'  "
                            + "Order by ap.ap_data";
                break;
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static void configuraModel(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {//int codigo, Servico serv, Produto p, int qtd, Date data, boolean flag, String obs, Funcionario func
        String colunas[] = new String [] {"Código", "Servico", "Produto", "Qtd", "Data", "Tipo", "Obs", "Funcionario"};
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
