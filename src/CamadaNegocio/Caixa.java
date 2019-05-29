/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CamadaNegocio;

import CamadaLogica.ReadOnlyTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 * Programers
 * @author 吉野　廉
 * @author 羽根川　翼
 * 
 * 
 */
public class Caixa 
{
    private int codigo;
    private Funcionario funcI, funcF;
    private double saldoI, saldoF, valorR;
    private LocalDate data;

    public Caixa(int codigo, Funcionario funcI, Funcionario funcF, double saldoI, double saldoF, double valorR, LocalDate data) {
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
        public boolean gravar()  // using This
    {
        String sql;
        if(this.codigo == 0)
        {
            sql = "insert into caixa (func_abrir, caixa_saldoinicial, caixa_data) values ("+this.funcI+", "+this.saldoI+", '"+this.data+"')";
        }
        else
        {
            sql = "update caixa set func_fechar = "+this.funcF+", caixa_saldofinal = "+this.saldoF+", caixa_valorreal = "+this.valorR+" where caixa_codigo = "+this.codigo+"";
        }
        return false;
    }
    
    public boolean excluir()
    {
        String sql;
        sql = "delete from caixa where caixa_codigo = "+this.codigo+"";
        return false;
    }
    
    public ArrayList<Caixa> buscarALL()
    {
        String sql;
        sql = "select * "
                + " from caixa ";
        return null;
    }
    
    public ArrayList<Caixa> buscarPPeriodo(LocalDate dataI, LocalDate dataF)
    {
        String sql;
        sql = "select * "
                + " from caixa "
                + " where caixa_data between '"+dataI+"' and '"+dataF+"'";
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
