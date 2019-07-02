package Controller;

import CamadaLogica.ReadOnlyTableModel;
import CamadaNegocio.Cliente;
import CamadaNegocio.DetalheServico;
import CamadaNegocio.FormaPagamento;
import CamadaNegocio.Orcamento;
import CamadaNegocio.Orcamento_Servico;
import CamadaNegocio.Orcamento_Servico_Detalhe;
import CamadaNegocio.Servico;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
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
    private ArrayList<String> excluirDetalhes;

    public OrcamentoController() {
        o = new Orcamento();
        v = new Validacao();
        ser = new Servico();
        sd = new DetalheServico();
        excluirS = new ArrayList<>();
        excluirSD = new ArrayList<>();
        excluirDetalhes = new ArrayList<>();
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
    //-----------------------------------------------------------------------------------
    public void buscaClietne(int codigo)
    {
        o.setCli(new Cliente().buscarCodigo(codigo));
    }
    
    public String exibirServico(int codigo)
    {
        return new Servico().buscarCodigo(codigo).getNome();
    }
    
    public int varidarOrcamento(String codigo, String cliente, String formaPag, String valorT, Date dataorc, Date varidade) throws SQLException
    {
        o.setCodigo(v.ConverteNumeroInteiro(codigo));
        if(cliente.equals(""))
            return 1;
        if(v.ConverteNumeroReal(valorT) <= 0)
            return 2;
        o.setValorTotal(v.ConverteNumeroReal(valorT));
        if(!v.ValidarDataDuasData(dataorc, varidade))
            if(!v.ValidarDataDuasDataIgual(dataorc, varidade))
                return 3;
        o.setOrcado(dataorc);
        o.setValidade(varidade);
        o.setFp(new FormaPagamento().buscaForma(formaPag));
        return 0;
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
            temp.add(linha, new Orcamento_Servico(ser, v.ConverteNumeroReal(valor), v.ConverteNumeroInteiro(qtd), v.ConverteNumeroReal(custoP), v.ConverteNumeroReal(custoI), v.ConverteNumeroReal(custoAca), v.ConverteNumeroReal(custoArt), v.ConverteNumeroReal(custoChap), v.ConverteNumeroReal(custoMdO), v.ConverteNumeroReal(desconto), descricao, temp.get(linha).getSequence(), new ArrayList<>()));
        o.setLista(temp);
        return 0;
    }
    
    public int varidarAddServicoDetalhe(String detalhe, String numeracaoI, String numeracaoF, String via, String outros, JTable tabela, int linhaS, int linhaD)
    {
        ArrayList<Orcamento_Servico> tempS = o.getLista();
        ArrayList<Orcamento_Servico_Detalhe> tempSD = o.getLista().get(linhaS).getLista();
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        DetalheServico ds = new DetalheServico().buscarDescricao(detalhe);
        switch(detalhe.toUpperCase())
        {
            case "NUMERAÇÃO":
                if(numeracaoI.equals(""))
                    return 1;
                if(numeracaoF.equals(""))
                    return 2;
                if(v.ConverteNumeroInteiro(numeracaoI) < 0)
                    return 3;
                if(v.ConverteNumeroInteiro(numeracaoF) < 0)
                    return 4;
                if(v.ConverteNumeroInteiro(numeracaoI) > v.ConverteNumeroInteiro(numeracaoF))
                    return 5;
                break;
            case "VIAS":
                if(numeracaoI.equals(""))
                    return 6;
                if(v.ConverteNumeroInteiro(numeracaoI) <= 0)
                    return 7;
                break;
            case "OUTROS":
                if(numeracaoI.equals(""))
                    return 8;
                break;
            default:
        }
        
        for(int i = 0; i < model.getRowCount(); i++)
        {
            if(v.ConverteNumeroInteiro(model.getValueAt(i, 5)) == ds.getCodigo())
                return 9;
        }
       // if(linhaD < 0)
            tempSD.add(new Orcamento_Servico_Detalhe(ds, v.ConverteNumeroInteiro(numeracaoI), v.ConverteNumeroInteiro(numeracaoF), v.ConverteNumeroInteiro(via), outros, tempS.get(linhaS).getSequence()));
        //else
        //    tempSD.add(linhaD, new Orcamento_Servico_Detalhe(ds, v.ConverteNumeroInteiro(numeracaoI), v.ConverteNumeroInteiro(numeracaoF), v.ConverteNumeroInteiro(via), outros, tempS.get(linhaS).getSequence()));
        o.getLista().get(linhaS).setLista(tempSD);
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
                temp.get(temp.size()-1).getValor()*temp.get(temp.size()-1).getQtd()+temp.get(temp.size()-1).getCustoPapel()+temp.get(temp.size()-1).getCustoImpre()+temp.get(temp.size()-1).getCustoAcab()+temp.get(temp.size()-1).getCustoArte()+temp.get(temp.size()-1).getCustoChapa()+temp.get(temp.size()-1).getCustoMdO()-temp.get(temp.size()-1).getDesconto(),
                temp.get(temp.size()-1).getDescricao()
            });
        }
        else
        {
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
            model.setValueAt(temp.get(linha).getValor()*temp.get(linha).getQtd()+temp.get(linha).getCustoPapel()+temp.get(linha).getCustoImpre()+temp.get(linha).getCustoAcab()+temp.get(linha).getCustoArte()+temp.get(linha).getCustoChapa()+temp.get(linha).getCustoMdO()- temp.get(linha).getDesconto(), linha, 10);
            model.setValueAt(temp.get(linha).getDescricao(), linha, 11);
        }
    }
    
    public void addTabelaServicoDetalhe(JTable tabela, int linha)// まだ  =>> OK 2019/06/29
    {
        ArrayList<Orcamento_Servico> temp = o.getLista();
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
//        if(linha == -1)
//        {
            model.addRow(new Object[]{
                temp.get(linha).getLista().get(temp.get(temp.size()-1).getLista().size()-1).getDs().getDescricao(),
                temp.get(linha).getLista().get(temp.get(temp.size()-1).getLista().size()-1).getVias(),
                temp.get(linha).getLista().get(temp.get(temp.size()-1).getLista().size()-1).getNumeracaoI(),
                temp.get(linha).getLista().get(temp.get(temp.size()-1).getLista().size()-1).getNumeracaoF(),
                temp.get(linha).getLista().get(temp.get(temp.size()-1).getLista().size()-1).getOutros(),
                temp.get(linha).getLista().get(temp.get(temp.size()-1).getLista().size()-1).getDs().getCodigo()
            });
//        }
//        else
//        {
//            model.setValueAt(temp.get(linha).getServ().getNome(), linha, 0);
//            model.setValueAt(temp.get(linha).getValor(), linha, 1);
//            model.setValueAt(temp.get(linha).getQtd(), linha, 2);
//            model.setValueAt(temp.get(linha).getCustoPapel(), linha, 3);
//            model.setValueAt(temp.get(linha).getCustoImpre(), linha, 4);
//            model.setValueAt(temp.get(linha).getCustoAcab(), linha, 5);
//        }
    }
    
    
    public double calcular(String valor, String qtd, String custoP, String custoI, String custoAca, String custoArt, String custoChap, String custoMdO, String desconto)
    {
        return (v.ConverteNumeroReal(valor) * v.ConverteNumeroInteiro(qtd)) + v.ConverteNumeroReal(custoI) + v.ConverteNumeroReal(custoAca) + v.ConverteNumeroReal(custoArt) + v.ConverteNumeroReal(custoChap) + v.ConverteNumeroReal(custoMdO) - v.ConverteNumeroReal(desconto);
    }
    
    public void excluirDetalheServico(JTable tabela, int linhaS, int linhaDS, boolean flag, String codigoO)
    {
        ArrayList<Orcamento_Servico> tempS = o.getLista();
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        model.removeRow(linhaDS);
        if(!flag) // False -> Alterar
        {
            excluirDetalhes.add(new Orcamento_Servico_Detalhe().CreatingDeleteSQLComand(v.ConverteNumeroInteiro(codigoO), tempS.get(linhaS).getServ().getCodigo(), o.getLista().get(linhaS).getLista().get(linhaDS).getDs().getCodigo(), o.getLista().get(linhaS).getLista().get(linhaDS).getSequence()));
        }
        o.getLista().get(linhaS).getLista().remove(linhaDS);
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
    
    public void carregarTabelaDetalheServico(JTable tabela, int linha)
    {
        ArrayList<Orcamento_Servico> temp = o.getLista();
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        for (int i = 0; i < temp.size(); i++) 
        {
                model.addRow(new Object[]{
                temp.get(linha).getLista().get(i).getDs().getDescricao(),
                temp.get(linha).getLista().get(i).getVias(),
                temp.get(linha).getLista().get(i).getNumeracaoI(),
                temp.get(linha).getLista().get(i).getNumeracaoF(),
                temp.get(linha).getLista().get(i).getOutros(),
                temp.get(linha).getLista().get(i).getDs().getCodigo()
            });
        }
    }
    
    public void UpdateNumberOrcamento() throws SQLException
    {
        o.setCodigo(new Orcamento().UltimoCodigo());
    }
           
    public void carregarFormaPagamento()
    {
        
    }
    
    public void carregarDetalhes(JComboBox cb) throws SQLException
    {
        ResultSet rs = new DetalheServico().buscarDetalhes();
        while (rs.next()) 
        {
                 cb.addItem(rs.getString(2));
        }
    }
    
    public void clearSequenceNumber()
    {
        sequenceOS = 1;
    }
    
    public boolean gravarOrcamento()
    {
        return o.gravar();
    }
    
    public boolean gravarOrcemntoServico()
    {
        boolean control = true;
        for(int i = 0; i < o.getLista().size() && control; i++)
        {
            control = o.getLista().get(i).gravar(o.getCodigo());
        }
        return control;
    }
    
    public boolean gravarOrcamentoServicoDetalhe()
    {
        boolean control = true;
        for(int i = 0; i < o.getLista().size() && control; i++)
        {
            for(int y = 0; y < o.getLista().get(i).getLista().size() && control; y++)
            {
                control = o.getLista().get(i).getLista().get(y).gravar(o.getCodigo(), o.getLista().get(i).getServ().getCodigo());
            }
        }
        return control;
    }
    
    public boolean alterarOrcamentoServico()
    {
        boolean control = true;
        for(int i = 0; i < o.getLista().size() && control; i++)
        {
            if(o.getLista().get(i).ChecarExiste(o.getCodigo(), o.getLista().get(i).getSequence()))
                control = o.getLista().get(i).alterar(o.getCodigo());
            else
                control = o.getLista().get(i).gravar(o.getCodigo());
        }
        return control;
    }
    
    public boolean alterarOrcamentoServicoDetalhe()
    {
        boolean control = true;
        for(int i = 0; i < o.getLista().size() && control; i++)
        {
            for(int y = 0; y < o.getLista().get(i).getLista().size() && control; y++)
            {
                if(o.getLista().get(i).getLista().get(y).ChecarExiste(o.getCodigo(), o.getLista().get(i).getSequence(), o.getLista().get(i).getLista().get(y).getDs().getCodigo()))
                    control = o.getLista().get(i).getLista().get(y).alterar(o.getCodigo(), o.getLista().get(i).getServ().getCodigo());
                else
                    control = o.getLista().get(i).getLista().get(y).gravar(o.getCodigo(), o.getLista().get(i).getServ().getCodigo());
            }
        }
        return control;
    }
    
    public boolean excluirOrcamento(int codigo)
    {
        return new Orcamento_Servico_Detalhe().excluir(codigo) && new Orcamento_Servico().excluir(codigo) && new Orcamento().excluir(codigo);
    }
    
    public void buscarDados(int codigo) throws SQLException
    {
        Orcamento temp = o.buscar(codigo);
        o = temp == null ? new Orcamento() : temp;
    }
    
    public void carregarTabelaServico(JTable tabela)
    {
        ArrayList<Orcamento_Servico> temp = o.getLista();
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        for(int i = 0; i < temp.size(); i++)
        {
            model.addRow(new Object[]{
                temp.get(i).getServ().getNome(),
                temp.get(i).getValor(),
                temp.get(i).getQtd(),
                temp.get(i).getCustoPapel(),
                temp.get(i).getCustoImpre(),
                temp.get(i).getCustoAcab(),
                temp.get(i).getCustoArte(),
                temp.get(i).getCustoChapa(),
                temp.get(i).getCustoMdO(),
                temp.get(i).getDesconto(),
                temp.get(i).getValor()*temp.get(i).getQtd()+temp.get(i).getCustoPapel()+temp.get(i).getCustoImpre()+temp.get(i).getCustoAcab()+temp.get(i).getCustoArte()+temp.get(i).getCustoChapa()+temp.get(i).getCustoMdO(),
                temp.get(i).getDescricao()
            });
        }
    }
}