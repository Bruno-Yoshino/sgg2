package Controller;

import CamadaLogica.ReadOnlyTableModel;
import CamadaNegocio.ContaReceber;
import CamadaNegocio.Pedido;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import util.SystemControl;
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

/*
    ２０１９年０８月２９日: 鈴奈メモ：Conta_receberにboolean cr_flagを新たに追加してください。＜－データベース。
*/
public class ReceberContaController {
    private Validacao v = new Validacao();
    private ContaReceber cr;
    private Pedido p;
    private final mensagens m;
    private final util.SystemControl sc = new SystemControl();

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
        cr = new ContaReceber().buscaContaReceber(v.ConverteNumeroInteiro(codigoC));
        if(valorPag.equals(""))
        {
            return 1;
        }
        if(v.ConverteNumeroReal(valorPag) < 0)
        {
            return 2;
        }
        if(v.ValidarDataDuasData(cr.getP().getPedido(), pagamento) == false)
        {
            if(v.ValidarDataDuasDataIgual(pagamento, cr.getP().getPedido()) == false)
                return 4;
        }
        Integer i;
        cr.setValorP(v.ConverteNumeroReal(valorPag));
        cr.setDataP(pagamento);
        cr.setObs(obs);
        if(cr.getValor() < v.ConverteNumeroReal(valorPag))
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

        if(v.ConverteNumeroReal(valorPag) < cr.getValor())
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
                sc.truncar(rs.getDouble(3)),//Valor Conta 
                rs.getDate(4),// Data Vencimento
                rs.getDate(5),// Data Pedido
                rs.getInt(6)// Numero Conta
            });
            //cr.cr_codigo, cr.pe_codigo, cr.cr_datavenc, cr.cr_obs, cr.cr_valor, cr.cr_datapago, cr.cr_vlorp
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
    
    public boolean estornarValor(int codigoCR)
    {
        //確認方法、　CR_codigoを使用して情報の修得を行い、Pe_codigoで分割回数を習得する。
        //分割数が0の場合はそのまま払い戻しを行う。そうでない場合は、ArrayListを使用してCP_codigoの位置を調べる. <-selectではOrder by cr_codigo
        //消去する場合は一つ手前支払いを確認しそれが同じ日付とフラグがFalseなら消去。
        //ListaContaReceberを使用してください。
        return false;
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
    
    public static void configuraModel(JTable jTable) // Configurar Tabela Detalhe
    {
        String colunas[] = new String [] {"Cliente", "Número Pedido", "Valor a ser Cobrado", "Data de Vencimento", "Data do Peido", "Numero da conta"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(250);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(125);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(125);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(125);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(50);
    }
}
