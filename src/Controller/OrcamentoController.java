package Controller;

import CamadaLogica.ReadOnlyTableModel;
import CamadaNegocio.DetalheServico;
import CamadaNegocio.Orcamento;
import CamadaNegocio.Orcamento_Servico;
import CamadaNegocio.Orcamento_Servico_Detalhe;
import CamadaNegocio.Servico;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import util.Validacao;
import util.mensagens;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 阿武隈
 * @author 長門
 * @author 大和
 * @author 阿賀野
 * @author 矢矧
 * @author 長良
 * @author 天野
 * @author 紅葉
 */
public class OrcamentoController {
    private Orcamento o;
    private final Validacao v; 
    private final mensagens m = new mensagens(); 
    private Servico ser;
    private DetalheServico sd; 
    private int sequenceOS;
    private ArrayList<Orcamento_Servico> excluirS;
    private ArrayList<Integer> excluirSD;

    public OrcamentoController() {
        o = new Orcamento();
        v = new Validacao();
        ser = new Servico();
        sd = new DetalheServico();
        excluirS = new ArrayList<>();
        excluirSD = new ArrayList<>();
        sequenceOS = 1;
    }

    public Orcamento getO() {
        return o;
    }

    public void setO(Orcamento o) {
        this.o = o;
    }

    public Servico getSer() {
        return ser;
    }

    public void setSer(Servico ser) {
        this.ser = ser;
    }
    
    public DetalheServico getSd() {
        return sd;
    }

    public void setSd(DetalheServico sd) {
        this.sd = sd;
    }
    
    public int varidarAddServico(String servico, String valor, String qtd, String custoP, String custoI, String custoAca, String custoArt, String custoChap, String custoMdO, String descricao, String desconto, int linha, String total)
    {
        ArrayList<Orcamento_Servico> temp = o.getLista();
        if(servico.equals(""))
            return 1;
        if(v.ConverteNumeroInteiro(qtd) <= 0)
        {
            return 2;
        }
        if(v.ConverteNumeroReal(valor) <= 0)
            return 3;
        if(v.ConverteNumeroReal(total) <= 0)
        if(linha == -1)
            temp.add(new Orcamento_Servico(ser, v.ConverteNumeroReal(valor), v.ConverteNumeroInteiro(qtd), v.ConverteNumeroReal(custoP), v.ConverteNumeroReal(custoI), v.ConverteNumeroReal(custoAca), v.ConverteNumeroReal(custoArt), v.ConverteNumeroReal(custoChap), v.ConverteNumeroReal(custoMdO), v.ConverteNumeroReal(desconto), descricao, sequenceOS++, new ArrayList<>()));
        else
            temp.add(linha, new Orcamento_Servico(ser, v.ConverteNumeroReal(valor), v.ConverteNumeroInteiro(qtd), v.ConverteNumeroReal(custoP), v.ConverteNumeroReal(custoI), v.ConverteNumeroReal(custoAca), v.ConverteNumeroReal(custoArt), v.ConverteNumeroReal(custoChap), v.ConverteNumeroReal(custoMdO), v.ConverteNumeroReal(desconto), descricao, sequenceOS++, new ArrayList<>()));
        o.setLista(temp);
        return 0;
    }
    
    public void addTabelaServico(JTable tabela, int linha)
    {
        ArrayList<Orcamento_Servico> temp = o.getLista();
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        if(linha == -1)
        {
            model.addRow(new Object[]{
                temp.get(temp.size()-1).getServ().getNome(),
                temp.get(temp.size()-1).getValor(),
                temp.get(temp.size()-1).getQtd(),
                temp.get(temp.size()-1).getCustoPapel(),
                temp.get(temp.size()-1).getCustoImpre(),
                temp.get(temp.size()-1).getCustoAcab(),
                temp.get(temp.size()-1).getCustoArte(),
                temp.get(temp.size()-1).getCustoChapa(),
                temp.get(temp.size()-1).getCustoMdO(),
                temp.get(temp.size()-1).getDesconto(),
                temp.get(temp.size()-1).getValor()*temp.get(temp.size()-1).getQtd()+temp.get(temp.size()-1).getCustoPapel()+temp.get(temp.size()-1).getCustoImpre()+temp.get(temp.size()-1).getCustoAcab()+temp.get(temp.size()-1).getCustoArte()+temp.get(temp.size()-1).getCustoChapa()+temp.get(temp.size()-1).getCustoMdO(),
                temp.get(temp.size()-1).getDescricao()
            });
        }
        else
            model.setValueAt(temp.get(linha).getServ().getNome(), linha, 0);
            model.setValueAt(temp.get(linha).getValor(), linha, 1);
            model.setValueAt(temp.get(linha).getQtd(), linha, 2);
            model.setValueAt(temp.get(linha).getCustoPapel(), linha, 3);
            model.setValueAt(temp.get(linha).getCustoImpre(), linha, 4);
            model.setValueAt(temp.get(linha).getCustoAcab(), linha, 5);
            model.setValueAt(temp.get(linha).getCustoArte(), linha, 6);
            model.setValueAt(temp.get(linha).getCustoChapa(), linha, 7);
            model.setValueAt(temp.get(linha).getCustoMdO(), linha, 8);
            model.setValueAt(temp.get(linha).getDesconto(), linha, 9);
            model.setValueAt(temp.get(linha).getValor()*temp.get(temp.size()-1).getQtd()+temp.get(temp.size()-1).getCustoPapel()+temp.get(temp.size()-1).getCustoImpre()+temp.get(temp.size()-1).getCustoAcab()+temp.get(temp.size()-1).getCustoArte()+temp.get(temp.size()-1).getCustoChapa()+temp.get(temp.size()-1).getCustoMdO(), linha, 10);
            model.setValueAt(temp.get(linha).getDescricao(), linha, 11);
    }
    
    
    public double calcular(String valor, String qtd, String custoP, String custoI, String custoAca, String custoArt, String custoChap, String custoMdO, String desconto)
    {
        return (v.ConverteNumeroReal(valor) * v.ConverteNumeroInteiro(qtd)) + v.ConverteNumeroReal(custoI) + v.ConverteNumeroReal(custoAca) + v.ConverteNumeroReal(custoArt) + v.ConverteNumeroReal(custoChap) + v.ConverteNumeroReal(custoMdO) - v.ConverteNumeroReal(desconto);
    }
    
    public boolean excluirServico(JTable tabela, int linha, boolean flag)
    {
        ArrayList<Orcamento_Servico_Detalhe> temp = new ArrayList<>();
        ArrayList<Orcamento_Servico> tempS  = new ArrayList<>();
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        if(o.getLista().get(linha).getLista().size() > 0) // DetalheServicoがある時。
        {
            if(m.Pergunta("Este serviço contem Detalhes inseridos! Deseja Excluir?", "Atenção") == JOptionPane.YES_OPTION)
            {
                model.removeRow(linha);
                if(flag)//true   ->> reference to Novo
                {
                    tempS.remove(linha);
                    o.setLista(tempS);
                    return true;
                }
                else
                {
                    o.getLista().get(linha).getLista().forEach((lista) -> {
                        excluirSD.add(lista.getSequence());
                    });
                    excluirS.add(o.getLista().get(linha));
                    tempS.remove(linha);
                    o.setLista(tempS);
                    return true;
                }
            }
            else
                return false;
        }
        else// DetalheServicoがない時。
        {
            model.removeRow(linha);
            if(flag)//true   ->> reference to Novo
            {
                tempS.remove(linha);
                o.setLista(tempS);
                return true;
            }
            else
            {
                tempS.remove(linha);
                o.setLista(tempS);
                return true;
            }
        }
    }
    
    public double calculoTotal(JTable tabela)
    {
        double total = 0;
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        for(int i = 0; i < model.getRowCount(); i++)
        {
            total += v.ConverteNumeroReal(model.getValueAt(i, 10));
        }
        return total;
    }
}
