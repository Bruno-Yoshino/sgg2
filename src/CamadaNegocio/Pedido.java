package CamadaNegocio;

import CamadaLogica.Banco;
import CamadaLogica.ReadOnlyTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
                  " cli_codigo, func_codigo, pe_valortotal, pe_datapedido, pe_entrega, fpg_codigo, orc_numero) " +
                  " VALUES ("+cli.getCodigo()+", "+f.getCodigo()+", "+valorTotal+", '"+pedido+"', '"+entrega+"', "+fp.getCodigo()+", "+(orc.getCodigo() == 0 ? null : orc.getCodigo())+");";
        }
        else
        {
            sql = "UPDATE pedido " +
                  " SET  pe_entrega='"+entrega+"' " +
                  " WHERE pe_codigo="+codigo+";";
        }
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo)
    {
        String sql = "UPDATE pedido " +
                  " SET  pe_entrega='"+null+"' " +
                  " WHERE pe_codigo="+codigo+";";//"DELETE FROM pedido " +
                     //" WHERE pe_codigo="+codigo+";";
        return Banco.getCon().manipular(sql); 
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
    
    public Pedido buscar(int codigo) throws SQLException
    {
        Pedido temp = new Pedido();
        String sql = "SELECT pe_codigo, cli_codigo, func_codigo, pe_valortotal, pe_datapedido, pe_entrega, fpg_codigo, orc_numero " +
                     " FROM pedido "
                    +" WHERE pe_codigo = "+codigo+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        if(rs.next())
        {
            temp.setCodigo(rs.getInt(1));
            temp.setCli(new Cliente().buscarCodigo(rs.getInt(2)));
            temp.setValorTotal(rs.getDouble(4));
            temp.setPedido(rs.getDate(5));
            temp.setEntrega(rs.getDate(6));
            temp.setFp(new FormaPagamento().buscaForma(rs.getInt(7)));
            temp.setOrc(new Orcamento().buscar(rs.getInt(8)));
        }
        temp.setLista(new Pedido_Servico().buscar(temp.getCodigo()));
        return temp;
    }
    
    public static ResultSet ConsultaPedido(String valor, int tipo, Date dataI, Date dataF)
    {
        String query = null;
        if (valor.equals(""))
        {
            query = "SELECT p.pe_codigo, c.cli_nome, p.pe_valortotal, p.pe_datapedido, p.pe_entrega "
                + " FROM pedido p, cliente c "
                + " WHERE p.cli_codigo = c.cli_codigo ";
        }
        else
        {
            switch (tipo)
            {
                case 0:// Tudo
                {
                    query = "SELECT p.pe_codigo, c.cli_nome, p.pe_valortotal, p.pe_datapedido, p.pe_entrega "
                          + " FROM pedido p, cliente c "
                          + " WHERE p.cli_codigo = c.cli_codigo ";
                    break;
                }
                case 1:// Data
                {
                    query = "SELECT p.pe_codigo, c.cli_nome, p.pe_valortotal, p.pe_datapedido, p.pe_entrega "
                          + " FROM pedido p, cliente c "
                          + " WHERE p.pe_datapedido = '"+valor+"' and p.cli_codigo = c.cli_codigo ";
                    break;
                }
                case 2:// Periodo
                {
                    query = "SELECT p.pe_codigo, c.cli_nome, p.pe_valortotal, p.pe_datapedido, p.pe_entrega "
                             + " FROM pedido p, cliente c "
                            + " WHERE p.pe_datapedido BETWEEN '"+dataI+"' and '"+dataF+"' and p.cli_codigo = c.cli_codigo";
                    break;
                }
                case 3:// Numero
                {
                    query = "select p.pe_codigo, c.cli_nome, p.pe_valortotal, p.pe_datapedido, p.pe_entrega "
                             + " FROM pedido p, cliente c "
                            + "where p.pe_codigo = '"+valor+"' and p.cli_codigo = c.cli_codigo";
                    break;
                }
            }
        }
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
        query = "SELECT ds.ds_descricao, psd.psd_numeracaoini, psd.psd_numeracaofim, psd.psd_vias, psd.psd_outros, psd.os_sequence " +
                " FROM pedido_servico_detalhe psd, detalhe_serv ds "
              + " WHERE psd.pe_codigo = "+codigo+" and os_sequence = "+sequence+" and psd.ds_codigo = ds.ds_codigo;";
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static void configuraModel(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Número", "Cliente", "Data Pedido", "Data Vencimento", "Valor Total"};
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
