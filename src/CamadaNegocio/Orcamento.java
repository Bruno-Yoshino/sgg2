package CamadaNegocio;

import CamadaLogica.Banco;
import CamadaLogica.ReadOnlyTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

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
public class Orcamento {
    private int codigo;
    private Cliente cli;
    private Funcionario f;
    private double valorTotal;
    private FormaPagamento fp;
    private Date orcado, validade;
    private ArrayList<Orcamento_Servico> lista;

    public Orcamento(int codigo, Cliente cli, Funcionario f, double valorTotal, FormaPagamento fp, Date orcado, Date validade, ArrayList<Orcamento_Servico> lista) {
        this.codigo = codigo;
        this.cli = cli;
        this.f = f;
        this.valorTotal = valorTotal;
        this.fp = fp;
        this.orcado = orcado;
        this.validade = validade;
        this.lista = lista;
    }

    public Orcamento() {
        this.lista = new ArrayList<>();
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

    public Date getOrcado() {
        return orcado;
    }

    public void setOrcado(Date orcado) {
        this.orcado = orcado;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public ArrayList<Orcamento_Servico> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Orcamento_Servico> lista) {
        this.lista = lista;
    }
    
    public boolean gravar()
    {
        String sql = "";
        if(codigo == 0)
        {
            sql = "INSERT INTO orcamento( " +
                  " cli_codigo, func_codigo, orc_valortotal, orc_dataorc, orc_validade, fpg_codigo) " +
                  " VALUES ("+cli.getCodigo()+", "+f.getCodigo()+", "+valorTotal+", '"+orcado+"', '"+validade+"', "+fp.getCodigo()+");";
        }
        else
        {
            sql = "UPDATE orcamento " +
                  " SET cli_codigo="+cli.getCodigo()+", func_codigo="+f.getCodigo()+", orc_valortotal="+valorTotal+", orc_dataorc='"+orcado+"', orc_validade='"+validade+"', fpg_codigo="+fp.getCodigo()+" " +
                  " WHERE orc_numero="+codigo+";";
        }
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo)
    {//後でPedido とリンクがあるかどうかの確認.
        String sql = "DELETE FROM orcamento " +
                     " WHERE orc_numero="+codigo+";";
        return Banco.getCon().manipular(sql); 
    }
    
    public boolean verificaPedido(int codigo)
    {//後でPedido とリンクがあるかどうかの確認.
        String sql = "select * from pedido " +
                     " WHERE orc_numero="+codigo+";";
        ResultSet rs=Banco.getCon().consultar(sql);
        try {
            if(rs.next())
            {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Orcamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public final int UltimoCodigo() throws SQLException
    {
        String sql = "select max(orc_numero) from orcamento";
        ResultSet rs=Banco.getCon().consultar(sql);
        if(rs.next())
        {
            return rs.getInt(1);
        }
        return 0;
    }
    
    public Orcamento buscar(int codigo) throws SQLException
    {
        Orcamento temp = new Orcamento();
        String sql = "SELECT orc_numero, cli_codigo, func_codigo, orc_valortotal, orc_dataorc, orc_validade, fpg_codigo " +
                     " FROM orcamento "
                    + " WHERE orc_numero = "+codigo+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        if(rs.next())
        {
            temp.setCodigo(rs.getInt(1));
            temp.setCli(new Cliente().buscarCodigo(rs.getInt(2)));
            temp.setF(new Funcionario().buscarCodigo(rs.getInt(3)));
            temp.setValorTotal(rs.getDouble(4));
            temp.setOrcado(rs.getDate(5));
            temp.setValidade(rs.getDate(6));
            temp.setFp(new FormaPagamento().buscaForma(rs.getInt(7)));
        }
        temp.setLista(new Orcamento_Servico().buscar(temp.getCodigo()));
        return temp;
    }
    
    public static ResultSet ConsultaOrcamento(String valor, int tipo, Date dataI, Date dataF)
    {
        String query = null;
        if (valor.equals(""))
        {
            query = "SELECT o.orc_numero, c.cli_nome, o.orc_valortotal, o.orc_dataorc, o.orc_validade "
                + " FROM orcamento o, cliente c "
                + " WHERE o.cli_codigo = c.cli_codigo ";
        }
        else
        {
            switch (tipo)
            {
                case 0:// Tudo
                {
                    query = "SELECT o.orc_numero, c.cli_nome, o.orc_valortotal, o.orc_dataorc, o.orc_validade "
                          + " FROM orcamento o, cliente c "
                          + " WHERE o.cli_codigo = c.cli_codigo ";
                    break;
                }
                case 1:// Data
                {
                    query = "SELECT o.orc_numero, c.cli_nome, o.orc_valortotal, o.orc_dataorc, o.orc_validade "
                          + " FROM orcamento o, cliente c "
                          + " WHERE o.orc_dataorc = '"+valor+"' and o.cli_codigo = c.cli_codigo ";
                    break;
                }
                case 2:// Periodo
                {
                    query = "SELECT o.orc_numero, c.cli_nome, o.orc_valortotal, o.orc_dataorc, o.orc_validade "
                             + " FROM orcamento o, cliente c "
                            + " WHERE o.orc_dataorc BETWEEN '"+dataI+"' and '"+dataF+"' and o.cli_codigo = c.cli_codigo";
                    break;
                }
                case 3:// Numero
                {
                    query = "select o.orc_numero, c.cli_nome, o.orc_valortotal, o.orc_dataorc, o.orc_validade "
                             + " FROM orcamento o, cliente c "
                            + "where o.orc_numero = '"+valor+"' and o.cli_codigo = c.cli_codigo";
                    break;
                }
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static ResultSet RelatorioOrcamento(String valor, int tipo, Date dataI, Date dataF)
    {
        String query = null;
        if (valor.equals("")) // Tudo
        {
            query = "select * "
                  + " from empresa emp, cliente cli, orcamento o, orcamento_servico os, orcamento_servico_detalhe osd, funcionario f, servico s, detalhe_serv ds "
                  + " WHERE o.cli_codigo = c.cli_codigo and "
                         + " o.orc_numero = os.orc_numero and "
                         + " os.serv_codigo = s.serv_codigo and "
                         + " osd.os_sequence = os_sequence and "
                         + " osd.ds_codigo = ds.ds_codigo";
        }
        else
        {
            switch (tipo)
            {
                case 0:// Tudo
                {
                    query = "select * "
                        + " from empresa emp, cliente c, orcamento o, orcamento_servico os, orcamento_servico_detalhe osd, funcionario f, servico s, detalhe_serv ds "
                        + " WHERE o.cli_codigo = c.cli_codigo and "
                         + " o.orc_numero = os.orc_numero and "
                         + " os.serv_codigo = s.serv_codigo and "
                         + " osd.os_sequence = os.os_sequence and "
                         + " osd.ds_codigo = ds.ds_codigo and " +
                           " f.func_codigo = o.func_codigo";
                    break;
                }
                case 1:// Data
                {
                    query = "SELECT * "
                          + " FROM empresa emp, cliente c, orcamento o, orcamento_servico os, orcamento_servico_detalhe osd, funcionario f, servico s, detalhe_serv ds "
                          + " WHERE o.orc_dataorc = '"+valor+"' and "
                         + " o.cli_codigo = c.cli_codigo and "
                         + " o.orc_numero = os.orc_numero and "
                         + " os.serv_codigo = s.serv_codigo and "
                         + " osd.os_sequence = os.os_sequence and "
                         + " osd.ds_codigo = ds.ds_codigo and " +
                           " f.func_codigo = o.func_codigo";
                    break;
                }
                case 2:// Periodo
                {
                    query = "select * "
                            + " from empresa emp, cliente c, orcamento o, orcamento_servico os, orcamento_servico_detalhe osd, funcionario f, servico s, detalhe_serv ds "
                            + " WHERE o.orc_dataorc BETWEEN '"+dataI+"' and '"+dataF+"' and "
                         + " o.cli_codigo = c.cli_codigo and "
                         + " o.orc_numero = os.orc_numero and "
                         + " os.serv_codigo = s.serv_codigo and "
                         + " osd.os_sequence = os.os_sequence and "
                         + " osd.ds_codigo = ds.ds_codigo and " +
                           " f.func_codigo = o.func_codigo";
                    break;
                }
                case 3:// Numero
                {
                    query = "select * "
                  + " from empresa emp, cliente c, orcamento o, orcamento_servico os, orcamento_servico_detalhe osd, funcionario f, servico s, detalhe_serv ds "
                  + " WHERE o.orc_numero = "+valor+" and "
                         + " o.cli_codigo = c.cli_codigo and "
                         + " o.orc_numero = os.orc_numero and "
                         + " os.serv_codigo = s.serv_codigo and "
                         + " osd.os_sequence = os.os_sequence and "
                         + " osd.ds_codigo = ds.ds_codigo and " +
                           " f.func_codigo = o.func_codigo";
                    break;
                }
            }
        }
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static ResultSet ConsultaOrcamentoServico(int codigo)
    {
        String query = null;
        query = "SELECT s.serv_nome, os.os_valor, os.os_qtd, os.os_custopapel, os.os_custoarte, os.os_custoimpressao, os.os_custoacabamento, os.os_custochapa, os.os_customdo, os.os_desconto, os.os_descricao, os.os_sequence " +
                " FROM orcamento_servico os, servico s "
              + " WHERE os.orc_numero = "+codigo+" and os.serv_codigo = s.serv_codigo;";
        return Banco.getCon().retornaResultSet(query);
    }
    
    public static ResultSet ConsultaOrcamentoServicoD(int codigo, int sequence)
    {
        String query = null;
        query = "SELECT ds.ds_descricao, osd.osd_numeracaoini, osd.osd_numeracaofim, osd.osd_vias, osd.osd_outros, osd.os_sequence " +
                " FROM orcamento_servico_detalhe osd, detalhe_serv ds "
              + " WHERE osd.orc_numero = "+codigo+" and os_sequence = "+sequence+" and osd.ds_codigo = ds.ds_codigo;";
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
    
    public static void configuraModelOrcamentoS(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Serviço", "Valor", "Quantidade", "C. Papel", "C. Arte", "C. Impressão", "C. Acabamento", "C. Chapa", "C. MdO", "Desconto", "Valor Total", "Descrição"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(250);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(6).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(7).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(8).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(9).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(10).setPreferredWidth(250);
    }
    
    public static void configuraModelOrcamentoSD(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
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
