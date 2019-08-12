/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import CamadaLogica.ReadOnlyTableModel;
import CamadaNegocio.Compra;
import CamadaNegocio.ContaPagar;
import CamadaNegocio.ContaReceber;
import CamadaNegocio.Pedido;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.JTextField;
import util.SystemControl;
import util.Validacao;
import util.mensagens;

/**
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 夕張
 * @author 香取 
 * @author 鹿島
 * @author 海女
 * @author 御子
 * @author 稲荷
 */
public class GerenciarParcelaController {
    
    private Compra c;
    private ContaPagar cp;
    private Pedido p;
    private ContaReceber cr;
    private SystemControl sc;
    private final util.Validacao v = new Validacao(); 
    private final util.mensagens m = new mensagens(); 
    
    public GerenciarParcelaController() {
        cp = new ContaPagar();
        cr = new ContaReceber();
        sc = new SystemControl();
    }

    public Compra getC() {
        return c;
    }

    public void setC(Compra c) {
        this.c = c;
        cp.setComp(c);
    }

    public ContaPagar getCp() {
        return cp;
    }

    public void setCp(ContaPagar cp) {
        this.cp = cp;
    }

    public Pedido getP() {
        return p;
    }

    public void setP(Pedido p) {
        this.p = p;
        cr.setP(p);
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
        
        if(par <= 1)
        {
            return 1;
        }
        
        inter = v.ConverteNumeroInteiro(intervalo);
        if(inter < -3)
        {
            return 2;
        }
        double temp;
        if(par != 1)
        {
            temp = PrimeiraParcela(par, valor);
        }
        else
        {
            temp = valor;
        }
        Date dataT;
        String strDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dataT = calendar.getTime();
        strDate = dateFormat.format(data);
        model.addRow(new Object[]{
            1,
            temp,
            strDate
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
                    calendar.add(Calendar.DAY_OF_MONTH, inter);
                }
                else
                {
                    calendar.add(Calendar.WEEK_OF_MONTH, inter * -1);
                }
            }
            dataT = calendar.getTime();
            strDate = dateFormat.format(dataT);
            model.addRow(new Object[]{
                i,
                valor / par,
                strDate
            });
        
        }
        
        return 0;
    }
    
    private double PrimeiraParcela(int qtd, double valor)
    {
        if(valor / qtd != 0)
        {
//            resto = valor / qtd;
//            resto = valor - resto * qtd;
            double x = valor - ((valor / qtd)*qtd);
            return valor / qtd + x;
        }
        return valor / qtd;
    }
    
    public int varidar(String valor)
    {
        
         if(v.ConverteNumeroReal(valor) < -3 && valor.length() > 0)
         {
             return 1;
         }
         
         return 0;
    }
    
    public int varidarValor(String valor, JTextField fd)
    {
        String var;
        var = valor.replaceAll("\\.", "");
        var = var.replace(',', '.');
         if(v.ConverteNumeroReal(var) <= 0 && var.length() > 0)
         {
             return 1;
         }
         fd.setText(var);
         return 0;
    }
    
    public void atualizarValor(JTextField fd, JTable jTable, int linha)
    {
        ReadOnlyTableModel model = (ReadOnlyTableModel) jTable.getModel();
        model.setValueAt(fd.getText(), linha, 1);
    }
    
    public int check(JTable jTable, JTextField total)
    {
        ReadOnlyTableModel model = (ReadOnlyTableModel) jTable.getModel();
        double tot = 0;
        for (int i = 0; i < model.getRowCount(); i++) 
        {
            tot += v.ConverteNumeroReal(model.getValueAt(i, 1));
        }
        if(tot != v.ConverteNumeroReal(total.getText()))
            return 1;
        
        return 0;
    }
    
    public boolean gravar(JTable jTable)
    {
        ReadOnlyTableModel model = (ReadOnlyTableModel) jTable.getModel();
        boolean x = true;
        if(p != null)
        {
            cr.setValor((Double) model.getValueAt(0, 1));
            cp.setDataV(sc.StringDate(String.valueOf(model.getValueAt(0, 2))));
            x = cr.gravar();
            for (int i = 1; i < model.getRowCount() && x; i++) 
            {
                cr.setValor((Double) model.getValueAt(i, 1));
                cr.setDataV(sc.StringDate(String.valueOf(model.getValueAt(i, 2))));
                x = cr.gravar();
            }
            return x;
        }
        if(cp == null)// == null reference Compra
        {
            cp = new ContaPagar();
            cp.setComp(c);
            cp.setC(null);
            cp.setTc(null);
            cp.setFunc(c.getFunc());
            cp.setDataL(Date.from(Instant.now()));
        }
        cp.setParcela(0);
        cp.setValorC(v.ConverteNumeroReal(model.getValueAt(0, 1)));
        //cp.setDataV((Date) model.getValueAt(0, 2));
        cp.setDataV(sc.StringDate(String.valueOf(model.getValueAt(0, 2))));
        cp.setDataP(null);
        x = cp.gravar();
        cp.setParcela(cp.maxCodigo());
        for (int i = 1; i < model.getRowCount() && x; i++) 
        {
            cp.setValorC((Double) model.getValueAt(i, 1));
            cp.setDataV(sc.StringDate(String.valueOf(model.getValueAt(i, 2))));
            x = cp.gravar();
        }
        return x;
    }
    
    public void RemoveAll(JTable jTable)
    {
        ReadOnlyTableModel model = (ReadOnlyTableModel) jTable.getModel();
        for(int i = 0; i < model.getRowCount(); i++)
        {
            model.removeRow(i);
        }
    }
}
