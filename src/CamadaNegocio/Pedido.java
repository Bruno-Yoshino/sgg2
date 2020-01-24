package CamadaNegocio;

import CamadaLogica.Banco;
import CamadaLogica.ReadOnlyTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
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

public class Pedido {
    private int codigo;
    private Cliente cli;
    private Orcamento orc;
    private Funcionario f;
    private double valorTotal;
    private FormaPagamento fp;
    private Date pedido, entrega;
    private ArrayList<Pedido_Servico> lista;
    private Caixa c;

    public Pedido(int codigo, Cliente cli, Orcamento orc, Funcionario f, double valorTotal, FormaPagamento fp, Date pedido, Date entrega, ArrayList<Pedido_Servico> lista) {
        this.codigo = codigo;
        this.cli = cli;
        this.orc = orc;
        this.f = f;
        this.valorTotal = valorTotal;
        this.fp = fp;
        this.pedido = pedido;
        this.entrega = entrega;
        this.lista = lista;
    }

    public Pedido() {
        lista = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public Orcamento getOrc() {
        return orc;
    }

    public void setOrc(Orcamento orc) {
        this.orc = orc;
    }

    public Funcionario getF() {
        return f;
    }

    public void setF(Funcionario f) {
        this.f = f;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public FormaPagamento getFp() {
        return fp;
    }

    public void setFp(FormaPagamento fp) {
        this.fp = fp;
    }

    public Date getPedido() {
        return pedido;
    }

    public void setPedido(Date pedido) {
        this.pedido = pedido;
    }

    public Date getEntrega() {
        return entrega;
    }

    public void setEntrega(Date entrega) {
        this.entrega = entrega;
    }

    public ArrayList<Pedido_Servico> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Pedido_Servico> lista) {
        this.lista = lista;
    }

    public Caixa getC() {
        return c;
    }

    public void setC(Caixa c) {
        this.c = c;
    }
    
    public boolean gravar()
    {
        String sql = "";
        if(codigo == 0)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(entrega);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            entrega = calendar.getTime();
            sql = "INSERT INTO pedido( " +
                  " cli_codigo, func_codigo, pe_valortotal, pe_datapedido, pe_dataentrega, fpg_codigo, orc_numero, caixa_codigo) " +
                  " VALUES ("+cli.getCodigo()+", "+f.getCodigo()+", "+valorTotal+", '"+pedido+"', '"+entrega+"', "+fp.getCodigo()+", "+(orc == null ? null : orc.getCodigo())+", "+c.getCodigo()+");";
        }
        else
        {
            sql = "UPDATE pedido " +
                  " SET  pe_dataentrega='"+entrega+"', fpg_codigo = "+fp.getCodigo()+" " +
                  " WHERE pe_codigo="+codigo+";";
        }
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar()
    {
        String sql = "UPDATE pedido " +
                     " SET  cli_codigo = "+cli.getCodigo()+", func_codigo = "+f.getCodigo()+", pe_valortotal = "+valorTotal+", pe_datapedido = '"+pedido+"', fpg_codigo = "+fp.getCodigo()+" " +
                     " WHERE pe_codigo="+codigo+";";
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo)
    {
//        String sql = "UPDATE pedido " +
//                  " SET  pe_dataentrega="+null+" " +
//                  " WHERE pe_codigo="+codigo+";";
        String sql = "DELETE FROM pedido " +
                     " WHERE pe_codigo="+codigo+";";
        return Banco.getCon().manipular(sql); 
    }
        
    public int qtdParcelaPaga()
    {
        String sql = "select count(*) from conta_receber where pe_codigo = "+codigo+" and cr_datapago is not null";
        ResultSet rs=Banco.getCon().consultar(sql);
        try {
            if(rs.next())
            {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
    
    public int qtdPedidoAndamento()
    {
        String sql = "select count(*) from producao where pe_codigo = "+codigo+" and prod_status != 4";
        ResultSet rs=Banco.getCon().consultar(sql);
        try {
            if(rs.next())
            {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
    
    public final int UltimoCodigo() throws SQLException
    {
        String sql = "select max(pe_codigo) from pedido";
        ResultSet rs=Banco.getCon().consultar(sql);
        if(rs.next())
        {
            return rs.getInt(1);
        }
        return 0;
    }
    
//    public boolean statusProducao(int codigoP, int codigoS)
//    {
//        String sql = "select prod_codigo from producao "
//                   + " where pe_codigo = "+codigoP+" and ps_sequence = "+codigoS+"; ";
//        ResultSet rs=Banco.getCon().consultar(sql);
//        try {
//            if(rs.next())
//            {
//                return true;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
    
    public Pedido buscar(int codigo) throws SQLException
    {
        Pedido temp = new Pedido();
        String sql = "SELECT pe_codigo, cli_codigo, func_codigo, pe_valortotal, pe_datapedido, pe_dataentrega, fpg_codigo, orc_numero " +
                     " FROM pedido "
                    +" WHERE pe_codigo = "+codigo+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        if(rs.next())
        {
            temp.setCodigo(rs.getInt(1));
            temp.setCli(new Cliente().buscarCodigo(rs.getInt(2)));
            temp.setF(new Funcionario().buscarCodigo(rs.getInt(3)));
            temp.setValorTotal(rs.getDouble(4));
            temp.setPedido(rs.getDate(5));
            temp.setEntrega(rs.getDate(6));
            temp.setFp(new FormaPagamento().buscaForma(rs.getInt(7)));
            temp.setOrc(new Orcamento().buscar(rs.getInt(8)));
        }
        temp.setLista(new Pedido_Servico().buscar(temp.getCodigo()));
        return temp;
    }
    
    public static ResultSet ConsultaPedido(String valor, int tipo, Date dataI, Date dataF, boolean flag)
    {
        String query = null;
//        if (valor.equals(""))
//        {
//            query = "SELECT p.pe_codigo, c.cli_nome, p.pe_valortotal, p.pe_datapedido, p.pe_dataentrega "
//                + " FROM pedido p, cliente c "
//                + " WHERE p.cli_codigo = c.cli_codigo ";
//        }
//        else
//        {
            if(flag)
            {
                switch (tipo)
                {
                    case 0:// Tudo
                    {
                        query = "SELECT p.pe_codigo, c.cli_nome, p.pe_valortotal, p.pe_datapedido, p.pe_dataentrega "
                              + " FROM pedido p, cliente c "
                              + " WHERE p.cli_codigo = c.cli_codigo and p.pe_dataentrega < p.pe_datapedido";
                        break;
                    }
                    case 1:// Data
                    {
                        query = "SELECT p.pe_codigo, c.cli_nome, p.pe_valortotal, p.pe_datapedido, p.pe_dataentrega "
                              + " FROM pedido p, cliente c "
                              + " WHERE p.pe_datapedido = '"+valor+"' and p.cli_codigo = c.cli_codigo and p.pe_dataentrega < p.pe_datapedido";
                        break;
                    }
                    case 2:// Periodo
                    {
                        query = "SELECT p.pe_codigo, c.cli_nome, p.pe_valortotal, p.pe_datapedido, p.pe_dataentrega "
                                 + " FROM pedido p, cliente c "
                                + " WHERE p.pe_datapedido BETWEEN '"+dataI+"' and '"+dataF+"' and p.cli_codigo = c.cli_codigo and p.pe_dataentrega < p.pe_datapedido";
                        break;
                    }
                    case 3:// Numero
                    {
                        query = "select p.pe_codigo, c.cli_nome, p.pe_valortotal, p.pe_datapedido, p.pe_dataentrega "
                                 + " FROM pedido p, cliente c "
                                + "where p.pe_codigo = '"+valor+"' and p.cli_codigo = c.cli_codigo and p.pe_dataentrega < p.pe_datapedido";
                        break;
                    }
                    case 4:// Nome Cliente
                    {
                        query = "select p.pe_codigo, c.cli_nome, p.pe_valortotal, p.pe_datapedido, p.pe_dataentrega "
                                 + " FROM pedido p, cliente c "
                                + "where c.cli_nome ilike '%"+valor+"%' and p.cli_codigo = c.cli_codigo and p.pe_dataentrega < p.pe_datapedido";
                        break;
                    }
                }
            }
            else
            {
                switch (tipo)
                {
                    case 0:// Tudo
                    {
                        query = "SELECT p.pe_codigo, c.cli_nome, p.pe_valortotal, p.pe_datapedido, p.pe_dataentrega "
                              + " FROM pedido p, cliente c "
                              + " WHERE p.cli_codigo = c.cli_codigo ";
                        break;
                    }
                    case 1:// Data
                    {
                        query = "SELECT p.pe_codigo, c.cli_nome, p.pe_valortotal, p.pe_datapedido, p.pe_dataentrega "
                              + " FROM pedido p, cliente c "
                              + " WHERE p.pe_datapedido = '"+valor+"' and p.cli_codigo = c.cli_codigo ";
                        break;
                    }
                    case 2:// Periodo
                    {
                        query = "SELECT p.pe_codigo, c.cli_nome, p.pe_valortotal, p.pe_datapedido, p.pe_dataentrega "
                                 + " FROM pedido p, cliente c "
                                + " WHERE p.pe_datapedido BETWEEN '"+dataI+"' and '"+dataF+"' and p.cli_codigo = c.cli_codigo";
                        break;
                    }
                    case 3:// Numero
                    {
                        query = "select p.pe_codigo, c.cli_nome, p.pe_valortotal, p.pe_datapedido, p.pe_dataentrega "
                                 + " FROM pedido p, cliente c "
                                + "where p.pe_codigo = '"+valor+"' and p.cli_codigo = c.cli_codigo";
                        break;
                    }
                    case 4:// Nome Cliente
                    {
                        query = "select p.pe_codigo, c.cli_nome, p.pe_valortotal, p.pe_datapedido, p.pe_dataentrega "
                                 + " FROM pedido p, cliente c "
                                + "where c.cli_nome ilike '%"+valor+"%' and p.cli_codigo = c.cli_codigo";
                        break;
                    }
                }
            }
//        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static ResultSet ConsultaPedidoServico(int codigo)
    {
        String query = null;
        query = "SELECT s.serv_nome, ps.ps_valor, ps.ps_qtd, ps.ps_desconto, ps.ps_descricao, ps.ps_sequence " +
                " FROM pedido_servico ps, servico s "
              + " WHERE ps.pe_codigo = "+codigo+" and ps.serv_codigo = s.serv_codigo;";
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static ResultSet ConsultaPedidoServicoD(int codigo, int sequence)
    {
        String query = null;
        query = "SELECT ds.ds_descricao, psd.psd_numeracaoini, psd.psd_numeracaofim, psd.psd_vias, psd.psd_outros, psd.ps_sequence " +
                " FROM pedido_servico_detalhe psd, detalhe_serv ds "
              + " WHERE psd.pe_codigo = "+codigo+" and ps_sequence = "+sequence+" and psd.ds_codigo = ds.ds_codigo;";
        return Banco.getCon().retornaResultSet(query);
    }
    
    public boolean verificaStatusProducao(int codigo, int sequencia)
    {
        String sql = "SELECT pe_codigo " +
                     " FROM producao "
                    +" WHERE pe_codigo = "+codigo+" and ps_sequence = "+sequencia+" and prod_status = 4";
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            if(rs.next())
            {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static ResultSet RelatorioPedido(String valor, int tipo, Date dataI, Date dataF)
    {
        String query = null;
        switch (tipo)
        {
            case 0:// Periodo e nome(Codigo do Cliente)
            {
                query = "select * "
                    + " from empresa emp, cliente c, pedido p, pedido_servico ps, funcionario f, servico s " +
                    " WHERE p.cli_codigo = "+valor+" "
                  + "p.pe_dataorc BETWEEN '"+dataI+"' and '"+dataF+"' and " +
                    " p.cli_codigo = c.cli_codigo and " +
                    " ps.pe_numero = p.pe_numero and " +
                    " ps.serv_codigo = s.serv_codigo and " +
                    " f.func_codigo = p.func_codigo";
                break;
            }
            case 1:// Numero
            {
                query = "select * "
              + " from empresa emp, cliente c, pedido p, pedido_servico ps, funcionario f, servico s " +
                " WHERE p.pe_numero = "+valor+" and " +
                " p.cli_codigo = c.cli_codigo and " +
                " ps.pe_numero = p.pe_numero and " +
                " ps.serv_codigo = s.serv_codigo and " +
                " f.func_codigo = p.func_codigo";
                break;
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static void configuraModel(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Número", "Cliente", "Data Pedido", "Data Entrega", "Valor Total"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(150);
    }
    
    public static void configuraModelPedidoS(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Serviço", "Valor", "Quantidade", "Desconto", "Valor Total", "Descrição"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(250);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(250);
    }
    
    public static void configuraModelPedidoSD(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Descrição", "Vias", "Numeração I.", "Numeração F", "Outros"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(250);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(200);
    }
    
}
