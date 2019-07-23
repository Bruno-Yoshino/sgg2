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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    
    public boolean gravar()  // using This
    {
        String sql;
        if(this.codigo == 0) // Abrir
        {
            sql = "insert into caixa (func_abrir, caixa_saldoinicio, caixa_datainicio, func_fechar) values ("+this.funcI.getCodigo()+", "+this.saldoI+", '"+this.data+"', "+null+")";
        }
        else // Fechar
        {
            sql = "update caixa set func_fechar = "+this.funcF.getCodigo()+", caixa_saldofinal = "+this.saldoF+", caixa_valorreal = "+this.valorR+" where caixa_codigo = "+this.codigo+"";
        }
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir()
    {
        String sql;
        sql = "delete from caixa where caixa_codigo = "+this.codigo+"";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean VerificaCaixaAberto()
    {
        String sql;
        sql = "select caixa_codigo, max(caixa_codigo) "
            + " from caixa where func_fechar is null"
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
                + " where func_fechar is null" //成功しなかった場合　Where文でfunc_fechar＝nullの確認。
                + " group by caixa_codigo, func_abrir, func_fechar, caixa_saldoinicio, caixa_saldofinal, caixa_valorreal, caixa_datainicio ";
                ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if (rs.next()) 
            {
                return new Caixa(rs.getInt(1), new Funcionario().buscarCodigo(rs.getInt(2)), null, 
                rs.getDouble(4), 0, 0, rs.getTimestamp(7).toLocalDateTime());//rs.getTimestamp(7)
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

    
    public static void configuraModel(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Código", "Caixa", "Estado"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(50);
    }
    
}
