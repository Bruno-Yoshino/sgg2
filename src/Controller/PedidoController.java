package Controller;

import CamadaLogica.ReadOnlyTableModel;
import CamadaNegocio.Cliente;
import CamadaNegocio.DetalheServico;
import CamadaNegocio.FormaPagamento;
import CamadaNegocio.Pedido;
import CamadaNegocio.Pedido_Servico;
import CamadaNegocio.Pedido_Servico_Detalhe;
import CamadaNegocio.Pedido;
import CamadaNegocio.Pedido_Servico;
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
 * @author 鳳翔
 * @author 川村
 * @author 磐手
 * @author イントレピッド
 * @author 七海
 * @author 海女
 * @author 御子
 * @author 稲荷
 */
public class PedidoController {
    
    private Pedido p;
    private final Validacao v; 
    private final mensagens m = new mensagens(); 
    private Servico ser;
    private DetalheServico sd; 
    private int sequenceOS;
    private ArrayList<Pedido_Servico> excluirS;
    private ArrayList<Integer> excluirSD;
    private ArrayList<String> excluirDetalhes;

    public PedidoController() {
        p = new Pedido();
        v = new Validacao();
        ser = new Servico();
        sd = new DetalheServico();
        excluirS = new ArrayList<>();
        excluirSD = new ArrayList<>();
        excluirDetalhes = new ArrayList<>();
        sequenceOS = 1;
    }

    public Pedido getO() {
        return p;
    }

    public void setO(Pedido p) {
        this.p = p;
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
        p.setCli(new Cliente().buscarCodigo(codigo));
    }
    
    public String exibirServico(int codigo)
    {
        return new Servico().buscarCodigo(codigo).getNome();
    }
    
    public int varidarPedido(String codigo, String cliente, String formaPag, String valorT, Date dataP, Date varidade, boolean flag) throws SQLException
    {
        p.setCodigo(v.ConverteNumeroInteiro(codigo));
        if(cliente.equals(""))
            return 1;
        if(v.ConverteNumeroReal(valorT) <= 0)
            return 2;
        p.setValorTotal(v.ConverteNumeroReal(valorT));
        if(!v.ValidarDataDuasData(dataP, varidade) && !flag)
            if(!v.ValidarDataDuasDataIgual(dataP, varidade))
                return 3;
        p.setPedido(dataP);
        p.setEntrega(varidade);
        p.setFp(new FormaPagamento().buscaForma(formaPag));
        return 0;
    }
    
    public int varidarAddServico(String servico, String valor, String qtd, String descricao, String desconto, int linha, String total)
    {
        ArrayList<Pedido_Servico> temp = p.getLista();
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
            temp.add(new Pedido_Servico(ser, v.ConverteNumeroReal(valor), v.ConverteNumeroInteiro(qtd),  v.ConverteNumeroReal(desconto), descricao, sequenceOS++, new ArrayList<>()));
        else
            temp.add(linha, new Pedido_Servico(ser, v.ConverteNumeroReal(valor), v.ConverteNumeroInteiro(qtd), v.ConverteNumeroReal(desconto), descricao, temp.get(linha).getSequence(), new ArrayList<>()));
        p.setLista(temp);
        return 0;
    }
    
    public int varidarAddServicoDetalhe(String detalhe, String numeracaoI, String numeracaoF, String via, String outros, JTable tabela, int linhaS, int linhaD)
    {
        ArrayList<Pedido_Servico> tempS = p.getLista();
        ArrayList<Pedido_Servico_Detalhe> tempSD = p.getLista().get(linhaS).getLista();
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
            tempSD.add(new Pedido_Servico_Detalhe(ds, v.ConverteNumeroInteiro(numeracaoI), v.ConverteNumeroInteiro(numeracaoF), v.ConverteNumeroInteiro(via), outros, tempS.get(linhaS).getSequence()));
        //else
        //    tempSD.add(linhaD, new Pedido_Servico_Detalhe(ds, v.ConverteNumeroInteiro(numeracaoI), v.ConverteNumeroInteiro(numeracaoF), v.ConverteNumeroInteiro(via), outros, tempS.get(linhaS).getSequence()));
        p.getLista().get(linhaS).setLista(tempSD);
        return 0;
    }
    
    public void addTabelaServico(JTable tabela, int linha)
    {
        ArrayList<Pedido_Servico> temp = p.getLista();
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        if(linha == -1)
        {
            model.addRow(new Object[]{
                temp.get(temp.size()-1).getServ().getNome(),
                temp.get(temp.size()-1).getValor(),
                temp.get(temp.size()-1).getQtd(),
                temp.get(temp.size()-1).getDesconto(),
                temp.get(temp.size()-1).getValor()*temp.get(temp.size()-1).getQtd()-temp.get(temp.size()-1).getDesconto(),
                temp.get(temp.size()-1).getDescricao()
            });
        }
        else
        {
            model.setValueAt(temp.get(linha).getServ().getNome(), linha, 0);
            model.setValueAt(temp.get(linha).getValor(), linha, 1);
            model.setValueAt(temp.get(linha).getQtd(), linha, 2);
            model.setValueAt(temp.get(linha).getDesconto(), linha, 9);
            model.setValueAt(temp.get(linha).getValor()*temp.get(linha).getQtd()-temp.get(linha).getDesconto(), linha, 10);
            model.setValueAt(temp.get(linha).getDescricao(), linha, 11);
        }
    }
    
    public void addTabelaServicoDetalhe(JTable tabela, int linha)// まだ  =>> OK 2019/06/29
    {
        ArrayList<Pedido_Servico> temp = p.getLista();
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
        ArrayList<Pedido_Servico> tempS = p.getLista();
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        model.removeRow(linhaDS);
//        if(!flag) // False -> Alterar
//        {
//            excluirDetalhes.add(new Pedido_Servico_Detalhe().CreatingDeleteSQLComand(v.ConverteNumeroInteiro(codigoO), tempS.get(linhaS).getServ().getCodigo(), p.getLista().get(linhaS).getLista().get(linhaDS).getDs().getCodigo(), p.getLista().get(linhaS).getLista().get(linhaDS).getSequence()));
//        }
        p.getLista().get(linhaS).getLista().remove(linhaDS);
    }
    
    public boolean excluirServico(JTable tabela, int linha, boolean flag)
    {
        ArrayList<Pedido_Servico_Detalhe> temp = new ArrayList<>();
        ArrayList<Pedido_Servico> tempS  = new ArrayList<>();
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        if(p.getLista().get(linha).getLista().size() > 0) // DetalheServicoがある時。
        {
            if(m.Pergunta("Este serviço contem Detalhes inseridos! Deseja Excluir?", "Atenção") == JOptionPane.YES_OPTION)
            {
                model.removeRow(linha);
                if(flag)//true   ->> reference to Novo
                {
                    tempS.remove(linha);
                    p.setLista(tempS);
                    return true;
                }
                else
                {
                    p.getLista().get(linha).getLista().forEach((lista) -> {
                        excluirSD.add(lista.getSequence());
                    });
                    excluirS.add(p.getLista().get(linha));
                    tempS.remove(linha);
                    p.setLista(tempS);
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
                p.setLista(tempS);
                return true;
            }
            else
            {
                tempS.remove(linha);
                p.setLista(tempS);
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
        ArrayList<Pedido_Servico> temp = p.getLista();
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
    
    public void UpdateNumberPedido() throws SQLException
    {
        p.setCodigo(new Pedido().UltimoCodigo());
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
    
    public boolean gravarPedido()
    {
        return p.gravar();
    }
    
    public boolean gravarPedidoServico()
    {
        boolean control = true;
        for(int i = 0; i < p.getLista().size() && control; i++)
        {
            control = p.getLista().get(i).gravar(p.getCodigo());
        }
        return control;
    }
    
    public boolean gravarPedidoServicoDetalhe()
    {
        boolean control = true;
        for(int i = 0; i < p.getLista().size() && control; i++)
        {
            for(int y = 0; y < p.getLista().get(i).getLista().size() && control; y++)
            {
                control = p.getLista().get(i).getLista().get(y).gravar(p.getCodigo(), p.getLista().get(i).getServ().getCodigo());
            }
        }
        return control;
    }
    
    public boolean alterarPedidoServico()
    {
        boolean control = true;
        for(int i = 0; i < p.getLista().size() && control; i++)
        {
            if(p.getLista().get(i).ChecarExiste(p.getCodigo(), p.getLista().get(i).getSequence()))
                control = p.getLista().get(i).alterar(p.getCodigo());
            else
                control = p.getLista().get(i).gravar(p.getCodigo());
        }
        return control;
    }
    
    public boolean alterarPedidoServicoDetalhe()
    {
        boolean control = true;
        for(int i = 0; i < p.getLista().size() && control; i++)
        {
            for(int y = 0; y < p.getLista().get(i).getLista().size() && control; y++)
            {
                if(p.getLista().get(i).getLista().get(y).ChecarExiste(p.getCodigo(), p.getLista().get(i).getSequence(), p.getLista().get(i).getLista().get(y).getDs().getCodigo()))
                    control = p.getLista().get(i).getLista().get(y).alterar(p.getCodigo(), p.getLista().get(i).getServ().getCodigo());
                else
                    control = p.getLista().get(i).getLista().get(y).gravar(p.getCodigo(), p.getLista().get(i).getServ().getCodigo());
            }
        }
        return control;
    }
    
    public boolean excluirPedido(int codigo)
    {
        return new Pedido_Servico_Detalhe().excluir(codigo) && new Pedido_Servico().excluir(codigo) && new Pedido().excluir(codigo);
    }
    
    public void buscarDados(int codigo) throws SQLException
    {
        Pedido temp = p.buscar(codigo);
        p = temp == null ? new Pedido() : temp;
    }
    
    public void carregarTabelaServico(JTable tabela)
    {
        ArrayList<Pedido_Servico> temp = p.getLista();
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        for(int i = 0; i < temp.size(); i++)
        {
            model.addRow(new Object[]{
                temp.get(i).getServ().getNome(),
                temp.get(i).getValor(),
                temp.get(i).getQtd(),
                temp.get(i).getDesconto(),
                temp.get(i).getValor()*temp.get(i).getQtd()-temp.get(i).getDesconto(),
                temp.get(i).getDescricao()
            });
        }
    }
    
}