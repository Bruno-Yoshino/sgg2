package Controller;

import CamadaNegocio.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import util.Validacao;
import util.mensagens;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 夕張
 * @author もも
 * @author 林道
 * @author 香取 
 * @author 鹿島
 */
public class LancarDespesaController 
{
    private ContaPagar cp;
    private final util.Validacao v = new Validacao(); 
    private final util.mensagens m = new mensagens(); 

    public LancarDespesaController() {
        this.cp = new ContaPagar();
    }

    public ContaPagar getCp() {
        return cp;
    }

    public void setCp(ContaPagar cp) {
        this.cp = cp;
    }
    
    public void CarregaTipoConta(JComboBox c) throws SQLException
    {
        ResultSet rs = TipoConta.buscarDados("",0);
        
        while(rs.next())
        {
            c.addItem(rs.getString("tc_tipo"));
            c.updateUI();
        }
    }
        
    public int validar(String codigo, String tipo, String conta, String barCode, String valorDoc, Date vencimento, String valorPag, Date pagamento, boolean flag, String banco, String local)
    {
        String var;
        var = valorDoc.replaceAll("\\.", "");
        var = var.replace(',', '.');
        String texto = "";
        cp.setCodigo(v.ConverteNumeroInteiro(codigo));
        cp.setTc(new TipoConta().buscarTipo(tipo));
        if(v.ConverteNumeroReal(var) <= 0)
            return 1;
        cp.setValorC(v.ConverteNumeroReal(var));
        cp.setDataV(vencimento);
        if(!conta.equals(""))
            texto = texto + "Nome da Conta: "+conta+"\n";
        if(!banco.equals(""))
        {
            texto = texto + "Boleto referente a Banco: "+banco+"\n";
            texto = texto + "Codigo de barras: "+banco+"\n";
        }
        cp.setLocal(local);
        cp.setValorP(0);
        cp.setDataP(pagamento);
        cp.setObs(texto);
        cp.setDataL(Date.from(Instant.now()));
        cp.setParcela(0);
        if(flag)
        {
            Integer i;
            String var2 = valorPag.replaceAll("\\.", "");
            var2 = var2.replace(',', '.');
            cp.setValorP(v.ConverteNumeroReal(var2));
            if(v.ConverteNumeroReal(var2) <= 0)
                return 2;
            
            if(v.ConverteNumeroReal(var) < v.ConverteNumeroReal(var2))
            {
                i = m.Pergunta("O valor pago é maior! Deseja Continuar?", "Atenção");
                if(null == i)
                    return 3;
                else
                    switch (i) {
                    case JOptionPane.NO_OPTION:
                        return 3;
                    case JOptionPane.YES_OPTION:
                        return -1;
                    default:
                        return 3;
                }
            }
            
            if(v.ConverteNumeroReal(var2) < v.ConverteNumeroReal(var))
            {
                if(m.Pergunta("O valor pago é menor! Deseja Continuar?", "Atenção") == JOptionPane.NO_OPTION)
                    return 3;
                else
                {
                    i = m.Pergunta("deseja gerar uma nova parcela?", "Atenção");
                    if(null == i)
                        return 3;
                    else
                        switch (i) {
                        case JOptionPane.YES_OPTION:
                            return 5;
                        case JOptionPane.NO_OPTION:
                            return -1;
                        default:
                            return 3;
                    }
                }
            }
        }
        cp.setC(null);
        cp.setComp(null);
        return 0;
    }
    
    public boolean gravar()
    {
        return cp.gravar();
    }
    
    public boolean SeocndInserting()
    {
        int add = 0;
        String resp;
        do{
            resp = JOptionPane.showInputDialog(null, "Informe apos quantos dias: ", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            add = v.ConverteNumeroInteiro(resp);
        }while (add < 0);
        cp.setValorC(cp.getValorC()-cp.getValorC());
        cp.setValorP(0);
        cp.setDataP(null);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cp.getDataV());
        calendar.add(Calendar.DAY_OF_MONTH, add);
        cp.setDataV(calendar.getTime());
        cp.setLocal("");
        cp.setObs("Conta nova pelo fato de ter faltado.");
        return cp.gravar();
    }
    
}
