/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaLogica.ReadOnlyTableModel;
import CamadaNegocio.Compra;
import CamadaNegocio.ContaPagar;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTable;
import util.Validacao;
import util.mensagens;

/**
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 夕張
 * @author 香取 
 * @author 鹿島
 */
public class GerenciarParcelaController {
    
    private Compra c;
    private ContaPagar cp;
    private final util.Validacao v = new Validacao(); 
    private final util.mensagens m = new mensagens(); 
    
    public GerenciarParcelaController() {

    }

    public Compra getC() {
        return c;
    }

    public void setC(Compra c) {
        this.c = c;
    }

    public ContaPagar getCp() {
        return cp;
    }

    public void setCp(ContaPagar cp) {
        this.cp = cp;
    }
    
    public static void configuraModelItem(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Numero Parcela", "Valor", "Data Vencimento"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(150);
    }
    
    public int gerarParcelas(String qtd, String intervalo, JTable jTable, double valor, Date data)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        int par, inter;
        ReadOnlyTableModel model = (ReadOnlyTableModel) jTable.getModel();
        par = v.ConverteNumeroInteiro(qtd);
        
        if(par <= 0)
        {
            return 1;
        }
        
        inter = v.ConverteNumeroInteiro(intervalo);
        if(inter < -3)
        {
            return 2;
        }
        
        model.addRow(new Object[]{
            1,
            PrimeiraParcela(par, valor),
            data
        });
        
        for(int i = 2; i <= par; i++)
        {
            if(inter == 0)
            {
                calendar.add(Calendar.MONTH, 1);
            }
            else
            {
                if(inter >0)
                {
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }
                else
                {
                    calendar.add(Calendar.WEEK_OF_MONTH, inter * -1);
                }
            }
            model.addRow(new Object[]{
                i,
                valor / par,
                calendar.getTime()
            });
        
        }
        
        return 0;
    }
    
    private double PrimeiraParcela(int qtd, double valor)
    {
        double resto = valor % qtd;
        if(resto > 0)
        {
            return valor / qtd + resto;
        }
        return valor / qtd;
    }
}
