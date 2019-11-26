package Controller;

import CamadaLogica.ReadOnlyTableModel;
import CamadaNegocio.Cheque;
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
    ２０１９年09月01日: 鈴奈メモ：cr_flagは支払いが完全かどうかを調べる、True = 本体, False = 新しく本体から作られたクローン
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
        cr.setValor(cr.getValor()-cr.getValorP());
        cr.setValorP(0);
        cr.setDataP(null);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cr.getDataV());
        calendar.add(Calendar.DAY_OF_MONTH, add);
        cr.setDataV(calendar.getTime());
        cr.setObs("Nova parcela.");
        cr.setFlag(false);
        return cr.gravar();
    }
    
    public boolean alterar()
    {
        return cr.alterar();
    }
    
    public int validarContasReceber(String codigoC, String valorPag, Date pagamento, String obs) throws SQLException
    {
        cr = new ContaReceber().buscaContaReceber(v.ConverteNumeroInteiro(codigoC));
        valorPag = v.verificaNunero(valorPag);
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
            if(v.ValidarDataDuasDataIgual(cr.getP().getPedido(), pagamento) == false)
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
            model.addRow(new Object[]
            {//"Nome", "Valor", "Data Vencimento"
                rs.getString(1),//Nome Clietne
                rs.getInt(2),// Numero Pedido
                sc.truncar(rs.getDouble(3)),//Valor Conta 
                sc.DataOnly(rs.getDate(4)),// Data Vencimento
                sc.DataOnly(rs.getDate(5)),// Data Pedido
                rs.getInt(6),// Numero Conta
                rs.getString(7)
            });
        }
    }
    
//    public boolean atualizarValor()
//    {
//        int qtd = cr.QtdParcela();
//        boolean x = true;
//        ArrayList<Integer> lista = cr.CodigoContaReceber(p.getCodigo());
//        if(qtd == 1)
//        {
//             x = cr.alterarvalorConta(lista.get(0));
//        }
//        else
//        {
//            double valor = PrimeiraParcela(qtd, p.getValorTotal()), total = 0;
//            total += valor;
//            cr.setValor(valor);
//            cr.alterarvalorConta(lista.get(0));
//            for(int i = 1; lista.size()-1 < i; i++)
//            {
//                cr.setValor(valor/qtd);
//                x = cr.alterarvalorConta(lista.get(i));
//            }
//        }
//        return x;
//    }
    
    public boolean estornarValor(int codigoCR)
    {
        //確認方法、　CR_codigoを使用して情報の修得を行い、Pe_codigoで分割回数を習得する。 OK
        //分割数が0の場合はそのまま払い戻しを行う。OK
        //そうでない場合は、ArrayListを使用してCP_codigoの位置を調べる. <-selectではOrder by cr_codigo OK
        //消去する場合は一つ手前支払いを確認しそれが同じ日付とフラグがFalseなら消去。 OK 日付の確認は必要ない、なぜならOder by で確認済み
        //ListaContaReceberを使用してください。 OK
        ContaReceber crTemp = new ContaReceber().buscar(codigoCR);
        if(crTemp.QtdParcela(codigoCR) == 1)
        {
            crTemp.estornarValor(codigoCR);
            return true;
        }
        else
        {
            ArrayList<ContaReceber> lista = crTemp.ListaContaReceber(crTemp.getP().getCodigo());
            int i;
            for(i = 0; lista.get(i).getCodigo() != codigoCR; i++)
            {
                
            }
            if(lista.size() - 1 == i)
            {
                cr.excluirCheques(codigoCR);
                crTemp.estornarValor(codigoCR);
                return true;
            }
            else
            {
                if(lista.get(i+1).getDataP() == null)
                {
                    if(!lista.get(i+1).isFlag())
                    {
                        lista.get(i+1).excluir();
                    }
                    cr.excluirCheques(codigoCR);
                    crTemp.estornarValor(codigoCR);
                    return true;
                }
            }
        }
        return false;
    }
    
    private double PrimeiraParcela(int qtd, double valor)
    {
        return v.ConverteNumeroReal(sc.arredondar(valor / qtd));
    }
    
    public boolean verificaConta(int npedido, int nconta)
    {
       return nconta == cr.minCodioParcela(npedido);
    }
    
//    public void buscar(int codigo) throws SQLException
//    {
//        cr = new ContaReceber().buscaContaReceber(codigo);
//    }
    
    public int buscar(int codigo) throws SQLException
    {
        return new ContaReceber().buscarForma(codigo);
    }
    
    public void addTabelaCheque(JTable tabela)
    {
        Cheque c = new Cheque().buscarMax();
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        model.addRow(new Object[]
        {//"Código", "Dono", "CPF", "Valor", "Data Lançado", "Pré Data"
            c.getCodigo(),
            c.getDono(),
            c.getCpf(),
            c.getValor(),
            c.getData(),
            c.getPredata(),
        });
    }
    
    public double SumTotalCheque(JTable tabela)
    {
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        double tot = 0;
        for (int i = 0; i < tabela.getRowCount(); i++) {
            tot += v.ConverteNumeroReal(model.getValueAt(i, 3)); 
        }
        return tot;
    }
    
    public boolean excluirCheque(JTable tabela)
    {
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        Cheque c = new Cheque();
        c.setCodigo(v.ConverteNumeroInteiro(model.getValueAt(tabela.getSelectedRow(), 0)));
        return c.excluir();
    }
    
    public boolean excluirUltimoCheque(JTable tabela)
    {
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        Cheque c = new Cheque();
        c.setCodigo(v.ConverteNumeroInteiro(model.getValueAt(tabela.getRowCount()-1, 0)));
        return c.excluir();
    }
    
    public void CleanUpCheque(int codigoCR)
    {
        cr.excluirCheques(codigoCR);
    }
    
    public void carregarCheques(int codigo, JTable tabela)
    {
        ArrayList<Cheque> c = new Cheque().buscarCheques(codigo);
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        c.forEach((c1) -> {
            model.addRow(new Object[]
            {//"Código", "Dono", "CPF", "Valor", "Data Lançado", "Pré Data"
                c1.getCodigo(),
                c1.getDono(),
                c1.getCpf(),
                c1.getValor(),
                c1.getData(),
                c1.getPredata(),
            });
        });

    }
    
    public ResultSet retornaResultSet(int codigo)
    {
        return ContaReceber.buscarDadosRel(codigo);
    }
        
    
    public static void configuraModel(JTable jTable)
    {
        String colunas[] = new String [] {"Cliente", "Número Pedido", "Valor a ser Cobrado", "Data de Vencimento", "Data do Pedio", "Numero da conta", "Tipo"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(100);
    }
}
