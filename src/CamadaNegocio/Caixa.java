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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;

/**
 * Programers
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 巴御前
 * @author 川波　愁子
 * @author 水川　鈴奈
 * @author 嶌田　治奈
 * @author 小枩　夏輝
 * @author ミシェル
 * @author レア
 * @author レレイナ
 * @author 海女
 * @author 御子
 * @author 稲荷
 */
public class Caixa 
{
    private int codigo;
    private Funcionario funcI, funcF;
    private double saldoI, saldoF, valorR;  //ValorR --> Valor Real
    private LocalDateTime data;
    private String nome;

    public Caixa(int codigo, Funcionario funcI, Funcionario funcF, double saldoI, double saldoF, double valorR, LocalDateTime data, String nome) {
        this.codigo = codigo;
        this.funcI = funcI;
        this.funcF = funcF;
        this.saldoI = saldoI;
        this.saldoF = saldoF;
        this.valorR = valorR;
        this.data = data;
        this.nome = nome;
    }

    public Caixa(int codigo, Funcionario funcI, Funcionario funcF, double saldoI, double saldoF, double valorR, LocalDateTime data) {
        this.codigo = codigo;
        this.funcI = funcI;
        this.funcF = funcF;
        this.saldoI = saldoI;
        this.saldoF = saldoF;
        this.valorR = valorR;
        this.data = data;
    }

    public Caixa(int codigo) {
        this.codigo = codigo;
    }
    
    public Caixa() {
       
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Funcionario getFuncI() {
        return funcI;
    }

    public void setFuncI(Funcionario funcI) {
        this.funcI = funcI;
    }

    public Funcionario getFuncF() {
        return funcF;
    }

    public void setFuncF(Funcionario funcF) {
        this.funcF = funcF;
    }

    public double getSaldoI() {
        return saldoI;
    }

    public void setSaldoI(double saldoI) {
        this.saldoI = saldoI;
    }

    public double getSaldoF() {
        return saldoF;
    }

    public void setSaldoF(double saldoF) {
        this.saldoF = saldoF;
    }

    public double getValorR() {
        return valorR;
    }

    public void setValorR(double valorR) {
        this.valorR = valorR;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public boolean gravar()  // using This
    {
        String sql;
        if(this.codigo == 0) // Abrir
        {
            sql = "insert into caixa (func_abrir, caixa_saldoinicio, caixa_datainicio, func_fechar, caixa_saldofinal, caixa_valorreal) values ("+this.funcI.getCodigo()+", "+this.saldoI+", '"+this.data+"', "+null+", 0, 0)";
        }
        else // Fechar
        {
            sql = "update caixa set func_fechar = "+this.funcF.getCodigo()+", caixa_saldofinal = "+this.saldoF+", caixa_valorreal = "+this.valorR+" where caixa_codigo = "+this.codigo+"";
        }
        return Banco.getCon().manipular(sql);
    }
    
    public boolean abriBanco()  // using This
    {
        String sql;
        if(this.codigo == 0) // Abrir Banco
        {
            sql = "insert into caixa (func_abrir, caixa_saldoinicio, caixa_datainicio, func_fechar, caixa_nome, caixa_saldofinal, caixa_valorreal) values ("+this.funcI.getCodigo()+", "+this.saldoI+", '"+this.data+"', "+null+", '"+nome+"', -1, -1)";
        }
        else // Encerrar Conta
        {
            sql = "update caixa set func_fechar = "+this.funcF.getCodigo()+", caixa_saldofinal = 0, caixa_valorreal = 0 where caixa_codigo = "+this.codigo+"";
        }
        return Banco.getCon().manipular(sql);
    }
    
    public boolean gravarAjusteCaixaBanco(double saldo, String obs)
    {
        String sql = "INSERT INTO conta_pagar(comp_codigo, cp_data, cp_local, cp_valorc, cp_dtpago, cp_valorp, cp_nparcela, tc_codigo, func_codigo, caixa_codigo, cp_datavencimento, cp_obs) " +
                     " VALUES (null, "
                    + " '"+Date.from(Instant.now())+"', null, "+saldo+", null, null, 0, "
                    + " null, "
                    + " "+funcI.getCodigo()+", "
                    + " "+codigo+", null, '"+obs+"');";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir()
    {
        String sql;
        sql = "delete from caixa where caixa_codigo = "+this.codigo+"";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean VerificaCaixaAberto()// Caixa Local
    {
        String sql;
        sql = "select caixa_codigo, max(caixa_codigo) "
            + " from caixa "
            + " where func_fechar is null "
            + " and caixa_valorreal != -1 "
            + " group by caixa_codigo";
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
    
    public Caixa buscaCaixa()
    {
        //int codigo, Funcionario funcI, Funcionario funcF, double saldoI, double saldoF, double valorR, LocalDateTime data
        String sql;
        sql = "select caixa_codigo, func_abrir, func_fechar, caixa_saldoinicio, caixa_saldofinal, caixa_valorreal, caixa_datainicio, max(caixa_codigo) "
                + " from caixa "
                + " where func_fechar is null and caixa_saldoinicio != -1 " 
                + " group by caixa_codigo, func_abrir, func_fechar, caixa_saldoinicio, caixa_saldofinal, caixa_valorreal, caixa_datainicio ";
                ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Caixa(rs.getInt(1), new Funcionario().buscarCodigo(rs.getInt(2)), null, 
                rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getTimestamp(7).toLocalDateTime());//rs.getTimestamp(7)
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public Caixa buscaCaixa(int codigo)
    {
        //int codigo, Funcionario funcI, Funcionario funcF, double saldoI, double saldoF, double valorR, LocalDateTime data
        String sql;
        sql = "select caixa_codigo, func_abrir, func_fechar, caixa_saldoinicio, caixa_saldofinal, caixa_valorreal, caixa_datainicio, caixa_nome "
                + " from caixa "
                + " where caixa_codigo = "+codigo+" " 
                + " group by caixa_codigo, func_abrir, func_fechar, caixa_saldoinicio, caixa_saldofinal, caixa_valorreal, caixa_datainicio ";
                ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Caixa(rs.getInt(1), new Funcionario().buscarCodigo(rs.getInt(2)), null, 
                rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getTimestamp(7).toLocalDateTime(), rs.getString(8));//rs.getTimestamp(7)
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public double SaldoRetirado()
    {
        String sql;
        sql = "select sum(cp_valorc) "
                + " from conta_pagar "
                + " where caixa_codigo = "+codigo+" ";
                ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return rs.getDouble(1);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    public double TotalDesconto(String data)
    {
        String sql;
        sql = "select sum(ps.ps_desconto) "
                + " from pedido p, pedido_servico ps "
                + " where p.caixa_codigo = "+codigo+" and p.pe_datapedido = '"+data+"' and p.pe_codigo = ps.pe_codigo";
                ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return rs.getDouble(1);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
            return 0;
        }
        return 0;
    }
    
    public double TotalPedido(String data)
    {
        String sql;
        sql = "select sum(pe_valortotal) "
                + " from pedido "
                + " where caixa_codigo = "+codigo+" and pe_datapedido = '"+data+"' ";
                ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return rs.getDouble(1);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    public ArrayList<Caixa> buscarPPeriodo(LocalDateTime dataI, LocalDateTime dataF)
    {
        String sql;
        sql = "select * "
                + " from caixa "
                + " where caixa_datainicio between '"+dataI+"' and '"+dataF+"'";
        return null;
    }

    public static ResultSet buscarCaixaBanco(String nome, int op)
    {
        String sql;
        if(nome.equals(""))
        {
            sql = "select caixa_codigo, caixa_nome, caixa_saldoinicio "
                + " from caixa "
                + " where caixa_saldofinal = -1 ";
                ResultSet rs=Banco.getCon().consultar(sql);
            return rs;
        }
        sql = "select caixa_codigo, caixa_nome, caixa_saldoinicio "
                + " from caixa "
                + " where caixa_nome ilike '%"+nome+"%' and caixa_saldofinal = -1 ";
                ResultSet rs=Banco.getCon().consultar(sql);
        return rs;
    }
    
    public static void configuraModel(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Código", "Caixa", "Estado"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(50);
    }
    
    public static void configuraModelCaixaBanco(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Código", "Nome", "Saldo"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(50);
    }
    
}
