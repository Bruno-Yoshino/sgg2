package Controller;

import CamadaLogica.ReadOnlyTableModel;
import CamadaNegocio.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import util.SystemControl;
import util.Validacao;
import util.mensagens;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 夕張
 * @author もも
 * @author 林道
 * @author 香取 
 * @author 鹿島
 */
public class LancarDespesaController 
{
    private ContaPagar cp;
    private final SystemControl sc = new SystemControl();
    private final util.Validacao v = new Validacao(); 
    private final util.mensagens m = new mensagens(); 

    public LancarDespesaController() {
        this.cp = new ContaPagar();
    }

    public ContaPagar getCp() {
        return cp;
    }

    public void setCp(ContaPagar cp) {
        this.cp = cp;
    }
    
    public void CarregaTipoConta(JComboBox c) throws SQLException
    {
        ResultSet rs = TipoConta.buscarDados("",0);
        
        while(rs.next())
        {
            c.addItem(rs.getString("tc_tipo"));
            c.updateUI();
        }
    }
        
    public int validar(String codigo, String tipo, String conta, String barCode, String valorDoc, Date vencimento, String valorPag, Date pagamento, boolean flag, String banco, String local, int op)
    {
        String texto = "";
        cp.setCodigo(v.ConverteNumeroInteiro(codigo));
        cp.setTc(new TipoConta().buscarTipo(tipo));
        if(v.ConverteNumeroReal(valorDoc) <= 0)
            return 1;
        cp.setValorC(v.ConverteNumeroReal(valorDoc));
        cp.setDataV(vencimento);
        if(!conta.equals(""))
            texto = texto + "Nome da Conta: "+conta+";\n";
        if(!banco.equals(""))
        {
            texto = texto + "Boleto referente a Banco: "+banco+";\n";
            texto = texto + "Codigo de barras: "+barCode+";\n";
        }
        cp.setLocal(local);
        cp.setValorP(0);
        cp.setDataP(null);
        cp.setObs(texto);
        cp.setDataL(Date.from(Instant.now()));
        cp.setParcela(0);
        if(vencimento == null)
        {
            return 7;
        }
        
        if(op == 3 && v.ValidarDataMenorAtual(vencimento))
        {
            return 6;
        }
        
        if(flag)
        {
            Integer i;
            cp.setValorP(v.ConverteNumeroReal(valorPag));
            cp.setDataP(pagamento);
            
            if(pagamento == null)
                return 8;
                
            if(v.ConverteNumeroReal(valorPag) <= 0)
                return 2;
            
            if(v.ConverteNumeroReal(valorDoc) < v.ConverteNumeroReal(valorPag))
            {
                i = m.Pergunta("O valor pago é maior! Deseja Continuar?", "Atenção");
                if(null == i)
                    return 3;
                else
                    switch (i) {
                    case JOptionPane.NO_OPTION:
                        return 3;
                    case JOptionPane.YES_OPTION:
                        return -1;
                    default:
                        return 3;
                }
            }
            
            if(v.ConverteNumeroReal(valorPag) < v.ConverteNumeroReal(valorDoc))
            {
                if(m.Pergunta("O valor pago é menor! Deseja Continuar?", "Atenção") == JOptionPane.NO_OPTION)
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
        }
        cp.setC(null);
        cp.setComp(null);
        return 0;
    }
    
    public boolean gravar()
    {
        return cp.gravar();
    }
    
    public boolean alterarDespesa()
    {
        return cp.alterarDespesa();
    }
    
    public boolean SeocndInserting(String valor, String valorP)
    {
        int add = 0;
        String resp;
        do{
            resp = JOptionPane.showInputDialog(null, "Informe apos quantos dias apos esta data "+Date.from(Instant.now())+": ", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            add = v.ConverteNumeroInteiro(resp);
        }while (add < 0);
        cp.setValorC(v.ConverteNumeroReal(valor)-v.ConverteNumeroReal(valorP));
        cp.setValorP(0);
        cp.setDataP(null);
        cp.setComp(null);
        cp.setC(null);
        cp.setTc(null);
        cp.setFunc(cp.getFunc());
        cp.setDataL(Date.from(Instant.now()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(Instant.now()));
        calendar.add(Calendar.DAY_OF_MONTH, add);
        cp.setDataV(calendar.getTime());
        cp.setLocal("");
        cp.setObs("Nova Parcela.");
        
        return cp.gravar();
    }
    
    public void carregarTabela(JTable tabela) throws SQLException
    {
        ResultSet rs = ContaPagar.buscarDados();
        ReadOnlyTableModel model = (ReadOnlyTableModel) tabela.getModel();
        TipoConta temp = new TipoConta(), temp2;
        String texto;
        while(rs.next())
        {
            temp2 = temp.buscarCodigo(rs.getInt(9));
            if(temp2 != null)
            {
                texto = temp2.getTipo();
            }
            else
            {
                texto = "Compra";
            }
            model.addRow(new Object[]{
                texto,
                rs.getString(13),
                sc.truncar(rs.getDouble(5)),
                rs.getDate(12),
                rs.getInt(1)
            });
        }
    }
    
    public void buscar(int codigo) throws SQLException
    {
        ResultSet rs = ContaPagar.buscarDados();
        cp.setCodigo(rs.getInt(1));
        cp.setComp(new Compra().buscaCompra(rs.getInt(2)));
        cp.setTc(new TipoConta().buscarCodigo(rs.getInt(9)));
        cp.setC(new Caixa().buscaCaixa());
        cp.setDataL(rs.getDate(3));
        cp.setLocal(rs.getString(4));
        cp.setValorC(rs.getDouble(5));
        cp.setDataP(rs.getDate(6));
        cp.setValorP(rs.getDouble(7));
        cp.setParcela(rs.getInt(8));
        cp.setDataV(rs.getDate(12));
        cp.setObs(rs.getString(13));
        //cp_codigo, comp_codigo, cp_data, cp_local, cp_valorc, cp_dtpago, cp_valorp, cp_nparcela, 
        //tc_codigo, func_codigo, caixa_codigo, cp_datavencimento, cp_obs
    }
    
    public int validarContasPagar(String codigo, String valorPag, Date pagamento, String local, String total)
    {
        cp.setCodigo(v.ConverteNumeroInteiro(codigo));
        if(valorPag.equals(""))
        {
            return 1;
        }

        if(v.ConverteNumeroReal(valorPag) < 0)
        {
            return 2;
        }
        
        Integer i;
        cp.setValorP(v.ConverteNumeroReal(valorPag));
        cp.setDataP(pagamento);
        cp.setLocal(local);

        if(v.ConverteNumeroReal(total) < v.ConverteNumeroReal(valorPag))
        {
            i = m.Pergunta("O valor pago é maior! Deseja Continuar?", "Atenção");
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

        if(v.ConverteNumeroReal(valorPag) < v.ConverteNumeroReal(total))
        {
            if(m.Pergunta("O valor pago é menor! Deseja Continuar?", "Atenção") == JOptionPane.NO_OPTION)
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
    
    public boolean alterar()
    {
        return cp.alterar();
    }
    
    public static void configuraModelItem(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Tipo", "Observação", "Valor", "Data de Vencimento", "Codigo C."};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(150);
    }
    
    public void separarString(JTextField txtCodigoBorra, JTextField txtNomeConta)
    {
        String[] text = cp.getObs().split(";", 0);
        String[] temp;
        if(text.length == 1)
        {
            temp = text[0].split(":", 0);
            txtNomeConta.setText(temp[1].trim());
        }
        else
        {
            temp = text[0].split(":", 0);
            txtNomeConta.setText(temp[1].trim());
            temp = text[2].split(":", 0);
            txtCodigoBorra.setText(temp[1].trim());
        }
    }
    
    public boolean checarContaPaga()
    {
        return cp.verificaPago();
    }
    
    public boolean excluir()
    {
        return cp.excluir(cp.getCodigo());
    }
    
    public ContaPagar buscar(String codigo)
    {
        ContaPagar TempCp = new ContaPagar().buscarDados(codigo);
        if(TempCp == null)
            return null;
        else
        {
            cp = TempCp;
            return cp;
        }
    }
}
