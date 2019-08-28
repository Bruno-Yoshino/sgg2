package Controller;

import CamadaLogica.ReadOnlyTableModel;
import CamadaNegocio.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
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
    
    public boolean SeocndInserting(String valor, String valorP, Date dataV)
    {
        int add = 0;
        String resp;
//        do{
//            resp = JOptionPane.showInputDialog(null, "Informe apos quantos dias apos esta data "+Date.from(Instant.now())+": ", "Atenção", JOptionPane.INFORMATION_MESSAGE);
//            add = v.ConverteNumeroInteiro(resp);
//        }while (add < 0);
        cp.setValorC(v.ConverteNumeroReal(valor)-v.ConverteNumeroReal(valorP));
        cp.setValorP(0);
        cp.setDataP(null);
        cp.setC(null);
        cp.setFunc(cp.getFunc());
        cp.setDataL(Date.from(Instant.now()));
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(Date.from(Instant.now()));
//        calendar.add(Calendar.DAY_OF_MONTH, add);
//        cp.setDataV(calendar.getTime());
        cp.setDataV(dataV);
        cp.setLocal("");
        if(cp.getObs().equals("Nova Parcela."))
            cp.setParcela(cp.getParcela());
        else
            cp.setParcela(cp.getCodigo());
        cp.setObs("Nova Parcela.");
        
        return cp.gravar();
    }
    
    public boolean SeocndInserting(String valor, String valorP, Date dataV, String obs, String codigo)
    {
        int add = 0;
        String resp;
//        do{
//            resp = JOptionPane.showInputDialog(null, "Informe apos quantos dias apos esta data "+Date.from(Instant.now())+": ", "Atenção", JOptionPane.INFORMATION_MESSAGE);
//            add = v.ConverteNumeroInteiro(resp);
//        }while (add < 0);
        cp.setValorC(v.ConverteNumeroReal(valor)-v.ConverteNumeroReal(valorP));
        cp.setValorP(0);
        cp.setDataP(null);
        cp.setC(null);
        cp.setFunc(cp.getFunc());
        cp.setDataL(Date.from(Instant.now()));
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(Date.from(Instant.now()));
//        calendar.add(Calendar.DAY_OF_MONTH, add);
//        cp.setDataV(calendar.getTime());
        cp.setDataV(dataV);
        cp.setLocal("");
//        if(obs.equals("Nova Parcela."))
        int i =  cp.buscarNParcela(codigo);
        if(i == 0)
            cp.setParcela(v.ConverteNumeroInteiro(codigo));
        else
            cp.setParcela(v.ConverteNumeroInteiro(i));
//        else
//            cp.setParcela(v.ConverteNumeroInteiro(codigo));
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
                sc.DataOnly(rs.getDate(12)),
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
    
    public int validarContasPagar(String codigo, String valorPag, Date pagamento, String local, String total, String saldo, String caixaS)
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
        
        if(v.ConverteNumeroReal(saldo) - v.ConverteNumeroReal(valorPag) < 0)
            return 4;
        
        if(caixaS.equals(""))
            return 6;
        
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
        jTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(350);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(120);
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
        ContaPagar TempCp = new ContaPagar().buscarDado(codigo);
        if(TempCp == null)
            return null;
        else
        {
            cp = TempCp;
            return cp;
        }
    }
    
    public void buscarCaixa(int codigo)
    {
        CaixaController c = new CaixaController();
        c.buscarCaixa(codigo);
        if(c.getC().getNome() == null)
        {
            c.getC().setNome("Caixa Local");
        }
        cp.setC(c.getC());
    }
    
    public double SaldoAtualizado()
    {
        return new AtualizarCaixaController().saldoAtualizadoGeral(cp.getC().getCodigo());
    }
    
    public boolean checarParcela(String codigo)
    {
        ContaPagar temp = new ContaPagar().buscarDado(codigo);
        if(temp.getParcela() == 0)
        {
            return true;
        }
        else
        {
            ArrayList<ContaPagar> lista = new ContaPagar().retornaLista(String.valueOf(temp.getParcela()));
            if(lista.isEmpty())
            {
                return false;
            }
            else
            {
                boolean flag;
                int i;
                for(i = 0; lista.get(i).getCodigo() != Integer.parseInt(codigo); i++)
                {

                }
                if(i == 0)
                    return lista.get(i).getDataP() == null;
                else
                    return false;
            }
        }
    }
    
    public boolean extornarValor(int codigo)
    {
        ContaPagar temp = new ContaPagar().buscarDado(String.valueOf(codigo));
        if(temp.getParcela() == 0 && temp.buscarQTDParcela(String.valueOf(codigo)) == 0)
        {
            cp.alterarPEstorno(codigo);
            return true;
        }
        else
        {
            ArrayList<ContaPagar> lista = new ContaPagar().retornaListaSPago(String.valueOf(codigo));
            int i;
            for(i = 0; lista.get(i).getCodigo() != codigo; i++) 
            {
                
            }
            if(i == lista.size() - 1)
            {
                int tempCodigo = cp.buscarCodigoMaxParcelaCDtV(lista.get(i).getParcela(), lista.get(i).getDataV(), v.ConverteNumeroInteiro(codigo));
                if(temp.buscaCaixa(lista.get(i).getC().getCodigo()))
                {
                    if(tempCodigo == lista.get(i).getCodigo())
                    {
                        cp.alterarPEstorno(codigo);
                        return true;
                    }
                    else
                    {
                        // Altera somente os seguinte dados: caixa_codigo = null, cp_dtpago = null, cp_valop = 0 where cp_codigo = lista.get(i).getCodigo(); && Excluir a proxima parcela
                        if(cp.buscarCodigoMaxParcelaPaga(lista.get(i).getParcela(), lista.get(i).getDataV(), v.ConverteNumeroInteiro(codigo)) == codigo)
                        {
                            cp.alterarPEstorno(codigo);
                            cp.excluir(tempCodigo);
                            return true;
                        }
                        return false;
                    }
                }
                else
                {
                     return false;
                }
            }
            else
            {
                return false;
            }
        }
    }
}
