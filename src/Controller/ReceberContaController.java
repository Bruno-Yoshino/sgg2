package Controller;

import CamadaLogica.ReadOnlyTableModel;
import CamadaNegocio.ContaPagar;
import CamadaNegocio.ContaReceber;
import CamadaNegocio.Pedido;
import CamadaNegocio.TipoConta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import util.Validacao;
import util.mensagens;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 巴御前
 * @author 高村　結衣
 * @author 里川　麗奈
 * @author 川波　愁子
 * @author 水川　鈴奈
 * @author 嶌田　治奈
 * @author 小枩　夏輝
 * @author ウィリアム
 * @author レレイナ
 */
public class ReceberContaController {
    private Validacao v = new Validacao();
    private ContaReceber cr;
    private Pedido p;
    private final mensagens m;

    public ReceberContaController() {
        cr = new ContaReceber();
        m = new mensagens();
        p = new Pedido();
    }

    public ContaReceber getCr() {
        return cr;
    }

    public void setCr(ContaReceber cr) {
        this.cr = cr;
    }

    public Pedido getP() {
        return p;
    }

    public void setP(Pedido p) {
        this.p = p;
    }
    
    public boolean SeocndInserting()
    {
        int add = 0;
        String resp;
        do{
            resp = JOptionPane.showInputDialog(null, "Informe apos quantos dias: ", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            add = v.ConverteNumeroInteiro(resp);
        }while (add < 0);
        cr.setValor(cr.getValor()-cr.getValorP());
        cr.setValorP(0);
        cr.setDataP(null);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cr.getDataV());
        calendar.add(Calendar.DAY_OF_MONTH, add);
        cr.setDataV(calendar.getTime());
        cr.setObs("Nova parcela.");
        return cr.gravar();
    }
    
    public boolean alterar()
    {
        return cr.alterar();
    }
    
    public int validarContasReceber(String codigoC, String valorPag, Date pagamento, String obs) throws SQLException
    {
        cr.buscaContaReceber(v.ConverteNumeroInteiro(codigoC));
        if(valorPag.equals(""))
        {
            return 1;
        }
        if(v.ConverteNumeroReal(valorPag) < 0)
        {
            return 2;
        }
        if(!v.ValidarDataDuasData(pagamento, cr.getP().getPedido()) && !v.ValidarDataDuasDataIgual(pagamento, cr.getP().getPedido()))
        {
            return 4;
        }
        Integer i;
        cr.setValorP(v.ConverteNumeroReal(valorPag));
        cr.setDataP(pagamento);
        cr.setObs(obs);
        if(v.ConverteNumeroReal(cr.getValor()) < v.ConverteNumeroReal(valorPag))
        {
            i = m.Pergunta("O valor Recebido é maior! Deseja Continuar?", "Atenção");
            if(null == i)
                return 3;
            else
                switch (i) {
                case JOptionPane.NO_OPTION:
                    return 3;
                case JOptionPane.YES_OPTION:
                    return 0;
                default:
                    return 3;
            }
        }

        if(v.ConverteNumeroReal(valorPag) < v.ConverteNumeroReal(cr.getValor()))
        {
            if(m.Pergunta("O valor Recebido é menor! Deseja Continuar?", "Atenção") == JOptionPane.NO_OPTION)
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
        return 0;
    }
    
    public void carregarTabela(JTable tabela, int op) throws SQLException
    {
        ResultSet rs = ContaReceber.buscarDados(op);
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        while(rs.next())
        {
            model.addRow(new Object[]{
                rs.getString(1),//Nome Clietne
                rs.getInt(2),// Numero Pedido
                rs.getDouble(3),//Valor Conta
                rs.getDate(4),// Data Vencimento
                rs.getDate(5),// Data Pedido
                rs.getInt(6)// Numero Conta
            });
        }
    }
    
    public boolean atualizarValor()
    {
        int qtd = cr.QtdParcela();
        boolean x = true;
        ArrayList<Integer> lista = cr.CodigoContaReceber(p.getCodigo());
        if(qtd == 1)
        {
             x = cr.alterarvalorConta(lista.get(0));
        }
        else
        {
            double valor = PrimeiraParcela(qtd, p.getValorTotal());
            cr.setValor(valor);
            cr.alterarvalorConta(lista.get(0));
            for(int i = 1; lista.size() < i; i++)
            {
                cr.setValor(valor/qtd);
                x = cr.alterarvalorConta(lista.get(i));
            }
        }
        return x;
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
