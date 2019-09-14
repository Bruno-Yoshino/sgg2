package Controller;

import CamadaLogica.ReadOnlyTableModel;
import CamadaNegocio.Caixa;
import CamadaNegocio.Cliente;
import CamadaNegocio.ContaReceber;
import CamadaNegocio.DetalheServico;
import CamadaNegocio.FormaPagamento;
import CamadaNegocio.Orcamento;
import CamadaNegocio.Pedido_Servico_Detalhe;
import CamadaNegocio.Pedido;
import CamadaNegocio.Pedido_Servico;
import CamadaNegocio.Producao;
import CamadaNegocio.Servico;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author 巴御前
 * @author 高村　結衣
 * @author 里川　麗奈
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
    private final ArrayList<Pedido_Servico> excluirS;
    private final ArrayList<Integer> excluirSD;
    private final ArrayList<Integer> excluirSDCodigo;
    private final ArrayList<String> excluirDetalhes;

    public PedidoController() {
        p = new Pedido();
        v = new Validacao();
        ser = new Servico();
        sd = new DetalheServico();
        excluirS = new ArrayList<>();
        excluirSD = new ArrayList<>();
        excluirSDCodigo = new ArrayList<>();
        excluirDetalhes = new ArrayList<>();
    }

    public Pedido getP() {
        return p;
    }

    public void setP(Pedido p) {
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
        ser = new Servico().buscarCodigo(codigo);
        return ser.getNome();
    }
    
    public int varidarPedido(String codigo, String cliente, String formaPag, String valorT, Date dataP, Date varidade, boolean flag) throws SQLException
    {
        p.setCodigo(v.ConverteNumeroInteiro(codigo));
        if(cliente.equals(""))
            return 1;
        if(v.ConverteNumeroReal(valorT) <= 0)
            return 2;
        p.setValorTotal(v.ConverteNumeroReal(valorT));
        if(!v.ValidarDataDuasData(dataP, varidade) && flag)// dcEbtrega is Visible? flag is check this. True check. False ignoreted
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
            return 4;
        if(linha == -1)
            temp.add(new Pedido_Servico(ser, v.ConverteNumeroReal(valor), v.ConverteNumeroInteiro(qtd),  v.ConverteNumeroReal(desconto), descricao, 0, new ArrayList<>()));
        else //addではなく、temp.get(Linha).setXXXXX();で処理を行ってください。
        {   
            temp.get(linha).setQtd(v.ConverteNumeroInteiro(qtd));
            temp.get(linha).setDesconto(v.ConverteNumeroReal(desconto));
            temp.get(linha).setDescricao(descricao);
            temp.get(linha).setServ(ser);
            temp.get(linha).setValor(v.ConverteNumeroReal(valor));
        }
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
                if(v.ConverteNumeroInteiro(numeracaoF) <= 0)
                    return 4;
                if(v.ConverteNumeroInteiro(numeracaoI) > v.ConverteNumeroInteiro(numeracaoF))
                    return 5;
                break;
            case "VIAS":
                if(numeracaoI.equals(""))
                    return 6;
                if(v.ConverteNumeroInteiro(via) <= 0)
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
            model.setValueAt(temp.get(linha).getDesconto(), linha, 3);
            model.setValueAt(temp.get(linha).getValor()*temp.get(linha).getQtd()-temp.get(linha).getDesconto(), linha, 4);
            model.setValueAt(temp.get(linha).getDescricao(), linha, 5);
        }
    }
    
    public void addTabelaServicoDetalhe(JTable tabela, int linha)// まだ  =>> OK 2019/06/29
    {
        ArrayList<Pedido_Servico> temp = p.getLista();
        ArrayList<Pedido_Servico_Detalhe> psdTemp = temp.get(linha).getLista();
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
//        if(linha == -1)
//        {
//            model.addRow(new Object[]{
//                temp.get(linha).getLista().get(temp.get(temp.size()-1).getLista().size()-1).getDs().getDescricao(),
//                temp.get(linha).getLista().get(temp.get(temp.size()-1).getLista().size()-1).getVias(),
//                temp.get(linha).getLista().get(temp.get(temp.size()-1).getLista().size()-1).getNumeracaoI(),
//                temp.get(linha).getLista().get(temp.get(temp.size()-1).getLista().size()-1).getNumeracaoF(),
//                temp.get(linha).getLista().get(temp.get(temp.size()-1).getLista().size()-1).getOutros(),
//                temp.get(linha).getLista().get(temp.get(temp.size()-1).getLista().size()-1).getDs().getCodigo()
//            });
            model.addRow(new Object[]{
                psdTemp.get(psdTemp.size()-1).getDs().getDescricao(),
                psdTemp.get(psdTemp.size()-1).getVias(),
                psdTemp.get(psdTemp.size()-1).getNumeracaoI(),
                psdTemp.get(psdTemp.size()-1).getNumeracaoF(),
                psdTemp.get(psdTemp.size()-1).getOutros(),
                psdTemp.get(psdTemp.size()-1).getDs().getCodigo()
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
    
    
    public double calcular(String valor, String qtd, String desconto)
    {
        return (v.ConverteNumeroReal(valor) * v.ConverteNumeroInteiro(qtd) - v.ConverteNumeroReal(desconto));
    }
    
    public void excluirDetalheServico(JTable tabela, int linhaS, int linhaDS, boolean flag, String codigoO)
    {
        ArrayList<Pedido_Servico> tempS = p.getLista();
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        model.removeRow(linhaDS);
        if(!flag) // False -> Alterar
        {
            excluirDetalhes.add(new Pedido_Servico_Detalhe().CreatingDeleteSQLComand(v.ConverteNumeroInteiro(codigoO), tempS.get(linhaS).getServ().getCodigo(), p.getLista().get(linhaS).getLista().get(linhaDS).getDs().getCodigo(), p.getLista().get(linhaS).getLista().get(linhaDS).getSequence()));
        }
        p.getLista().get(linhaS).getLista().remove(linhaDS);
    }
    
    public boolean excluirServico(JTable tabela, int linha, boolean flag)
    {
        ArrayList<Pedido_Servico> tempS  = p.getLista();
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        if(p.getLista().get(linha).getLista().size() > 0) // DetalheServicoがある時。
        {
            if(m.Pergunta("Este serviço contem Detalhes inseridos! Deseja Excluir?", "Atenção") == JOptionPane.YES_OPTION)
            {
                if(flag)//true   ->> reference to Novo
                {
                    model.removeRow(linha);
                    tempS.remove(linha);
                    p.setLista(tempS);
                    return true;
                }
                else
                {
                    if(p.verificaStatusProducao(p.getCodigo(), p.getLista().get(linha).getSequence()))
                    {
                        model.removeRow(linha);
                        //Producaoでの消去
                        p.getLista().get(linha).getLista().forEach((lista) -> {
                            excluirSD.add(lista.getSequence());
                        });
                        excluirS.add(p.getLista().get(linha));
                        tempS.remove(linha);
                        p.setLista(tempS);
                        return true;
                    }
                    else
                    {
                        return false;
                    }
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
                if(p.verificaStatusProducao(p.getCodigo(), p.getLista().get(linha).getSequence()))
                {
                    //Producaoでの消去
                    excluirS.add(p.getLista().get(linha));
                    tempS.remove(linha);
                    p.setLista(tempS);
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
    }
    
    public int ultimoCodigoInserido()
    {
        return new Pedido_Servico().buscarUltimoCodigo();
    }
    
    public boolean verificaStatus(int codigo, int linha)//Pedido
    {
        return p.verificaStatusProducao(codigo, p.getLista().get(linha).getSequence());
    }
    
    public double calculoTotal(JTable tabela)
    {
        double total = 0;
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        for(int i = 0; i < model.getRowCount(); i++)
        {
            total += v.ConverteNumeroReal(model.getValueAt(i, 4));
        }
        return total;
    }
    
    public void carregarTabelaDetalheServico(JTable tabela, int linha)
    {
        ArrayList<Pedido_Servico> temp = p.getLista();
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        for (int i = 0; i < temp.get(linha).getLista().size(); i++) 
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
    
    public boolean existPedioServicoDetalhe()
    {
        ArrayList<Pedido_Servico> temp = p.getLista();
        return temp.size() > 0 && temp.get(0).getLista().size() > 0;
    }
    
    public void UpdateNumberPedido() throws SQLException
    {
        p.setCodigo(new Pedido().UltimoCodigo());
    }
           
    public void carregarFormaPagamento(JComboBox cb)
    {
        try 
        {
            ResultSet rs = new FormaPagamento().carregar();
            while (rs.next())
            {
                cb.addItem(rs.getString(1));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void carregarDetalhes(JComboBox cb) throws SQLException
    {
        ResultSet rs = new DetalheServico().buscarDetalhes();
        while (rs.next()) 
        {
                 cb.addItem(rs.getString(2));
        }
    }
    
    public boolean gravarPedido()
    {
        return p.gravar();
    }
    
    public boolean gravarPedidoServico()
    {
        boolean control = true;
        int f;
        for(int i = 0; i < p.getLista().size() && control; i++)
        {
            control = p.getLista().get(i).gravar(p.getCodigo());
            f = ultimoCodigoInserido();
            p.getLista().get(i).setSequence(f);
            for (int j = 0; j < p.getLista().get(i).getLista().size(); j++) 
            {
                 p.getLista().get(i).getLista().get(j).setSequence(f);
            }
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
                control = p.getLista().get(i).getLista().get(y).gravar2(p.getCodigo(), p.getLista().get(i).getServ().getCodigo());
            }
        }
        return control;
    }
    
    public boolean alterarPedidoServico()
    {
        boolean control = true;
        for(int i = 0; i < p.getLista().size() && control; i++)
        {
            if(p.getLista().get(i).getSequence() != 0 && p.getLista().get(i).ChecarExiste(p.getCodigo(), p.getLista().get(i).getSequence()))
                control = p.getLista().get(i).alterar(p.getCodigo());
            else
            {
                control = p.getLista().get(i).gravar(p.getCodigo());
                p.getLista().get(i).setSequence(new Pedido_Servico().buscarUltimoCodigo());
                new ProducaoController().gerarProducao(p.getLista().get(i), p);
            }
                
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
        //verificar se uma parcela foi paga, se tiver retorna false  OK
        //verificar se existe algum pedido em adamento, entregue ou pausado, se tiver retorna false OK
        if(p.qtdParcelaPaga() != 0)
        {
            return false;
        }
        
        if(p.qtdPedidoAndamento()!= 0)
        {
            return false;
        }
        new Producao().excluir(codigo);
        new Pedido_Servico_Detalhe().excluir(codigo);
        new Pedido_Servico().excluir(codigo);
        new ContaReceber().excluirContaReceberPedido(codigo);
        return new Pedido().excluir(codigo);
    }
    
    public boolean excluirContaReceber()
    {
        return new ContaReceber().excluirContaReceberPedido(p.getCodigo());
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
    
    public void carregarTabelaServicoOrcamento(JTable tabela)
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
                temp.get(i).getValor()-temp.get(i).getDesconto(),
                temp.get(i).getDescricao()
            });
        }
    }
    
//    public boolean checarStatusProducao(int codigoP, int linha)
//    {
//        if(linha == -1)
//        {
//            return false;
//        }
//        else
//        {
//            return p.verificaStatusProducao(codigoP, p.getLista().get(linha).getSequence());
//        }
//    }
    
    public void buscaCaixa()
    {
        p.setC(new Caixa().buscaCaixa());
    }
    
    public boolean gerarProducao()
    {
        boolean x = true;
        for(int i = 0; i < p.getLista().size() && x; i++)
        {
            x = new ProducaoController().gerarProducao(p.getLista().get(i), p);
        }
        return x;
    }
    
    public boolean exculir()
    {
        boolean x = true;
        for(int i = 0; i < excluirDetalhes.size() && x; i++)
        {
            x = new Pedido_Servico_Detalhe().executeDelete(excluirDetalhes.get(i));
        }
        for(int i = 0; i < excluirSD.size() && x; i++)
        {
            x = new Pedido_Servico_Detalhe().excluir(p.getCodigo(), excluirSD.get(i));
        }
        for(int i = 0; i < excluirS.size() && x; i++)
        {
            
            x = new Producao().excluir(p.getCodigo(), excluirS.get(i).getSequence());
            x = new Pedido_Servico().excluir(p.getCodigo(), excluirS.get(i).getSequence());
        }
        return x;
    }
    
    public boolean alterar()
    {
        return p.alterar();
    }
    
    public int carregarOrcamento(int codigo)
    {
        ArrayList<Pedido_Servico> listaPS = new ArrayList<>();
        ArrayList<Pedido_Servico_Detalhe> listaPSD = new ArrayList<>();
        try 
        {
            Orcamento temp = new Orcamento().buscar(codigo);
            if(!v.ValidarDataDuasData(Date.from(Instant.now()), temp.getValidade()) && !v.ValidarDataDuasDataIgual(Date.from(Instant.now()), temp.getValidade()))
            {
                return 1;
            }
            p.setOrc(temp);
            p.setCli(temp.getCli());
            p.setValorTotal(temp.getValorTotal());
            for(int i = 0; i < temp.getLista().size(); i++)
            {
                for(int x = 0; x < temp.getLista().get(i).getLista().size(); x++)
                {//                                         DetalheServico ds, int numeracaoI, int numeracaoF, int vias, String outros, int sequence
                    listaPSD.add(new Pedido_Servico_Detalhe(temp.getLista().get(i).getLista().get(x).getDs(), temp.getLista().get(i).getLista().get(x).getNumeracaoI(), temp.getLista().get(i).getLista().get(x).getNumeracaoF(), temp.getLista().get(i).getLista().get(x).getVias(), temp.getLista().get(i).getLista().get(x).getOutros(), 0));
                }
                //                                          Servico serv, double valor, int qtd, double desconto, String descricao, int sequence, ArrayList<Pedido_Servico_Detalhe> lista
                listaPS.add(new Pedido_Servico(temp.getLista().get(i).getServ(), 
                        (temp.getLista().get(i).getValor()*temp.getLista().get(i).getQtd()+temp.getLista().get(i).getCustoAcab()
                                +temp.getLista().get(i).getCustoArte()+temp.getLista().get(i).getCustoChapa()
                                +temp.getLista().get(i).getCustoImpre()+temp.getLista().get(i).getCustoMdO()
                                +temp.getLista().get(i).getCustoPapel()), temp.getLista().get(i).getQtd(), temp.getLista().get(i).getDesconto(), temp.getLista().get(i).getDescricao(), 0, listaPSD));
                listaPSD = new ArrayList<>();
            }
        } catch (SQLException ex) 
        {
            Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setLista(listaPS);
       
        return 0;
    }
    
    public void gerarContaReceber()
    {
        ContaReceber cr = new ContaReceber();
        cr.setP(p);
        cr.setDataV(Date.from(Instant.now()));
        cr.setValor(p.getValorTotal());
        cr.gravar();
    }
    
    public void gerarContaReceber(Date dataV)
    {
        ContaReceber cr = new ContaReceber();
        cr.setP(p);
        cr.setDataV(dataV);
        cr.setValor(p.getValorTotal());
        cr.gravar();
    }
    
    public static void configuraModelServico(JTable jTable) // Configurar Tabela Servico
    {
        String colunas[] = new String [] {"Serviço", "Valor", "Quantidade", "Desconto", "Valor Total", "Descrição"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(150);
    }
    
    public static void configuraModelDetalhe(JTable jTable) // Configurar Tabela Detalhe
    {
        String colunas[] = new String [] {"Descrição", "Vias", "Numeração Inicio", "Numeração Fim", "Outros", "Codigo D"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(250);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(100);
    }
}
