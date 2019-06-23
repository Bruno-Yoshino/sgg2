package Controller;

import CamadaNegocio.DetalheServico;
import CamadaNegocio.Orcamento;
import CamadaNegocio.Orcamento_Servico;
import CamadaNegocio.Servico;
import java.util.ArrayList;
import javax.swing.JTable;
import util.Validacao;

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
    private Servico ser;
    private DetalheServico sd; 
    private int sequenceOS, sequenceOSD;

    public OrcamentoController() {
        o = new Orcamento();
        v = new Validacao();
        ser = new Servico();
        sd = new DetalheServico();
        sequenceOS = 1;
        sequenceOSD = 1;
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
    
    public int varidar(String servico, String valor, String qtd, String custoP, String custoI, String custoAca, String custoArt, String custoChap, String custoMdO, String descricao)
    {
        ArrayList<Orcamento_Servico> temp = o.getLista();
        if(servico.equals(""))
            return 1;
        temp.add(new Orcamento_Servico(ser, v.ConverteNumeroReal(valor), v.ConverteNumeroInteiro(qtd), v.ConverteNumeroReal(custoP), v.ConverteNumeroReal(custoI), v.ConverteNumeroReal(custoAca), v.ConverteNumeroReal(custoChap), v.ConverteNumeroReal(custoMdO), v.ConverteNumeroReal(valor), v.ConverteNumeroReal(valor), descricao, sequenceOS++, new ArrayList<>()));
        o.setLista(temp);
        return 0;
    }
    
    /*
            this.serv = serv;
        this.valor = valor;
        this.qtd = qtd;
        this.custoPapel = custoPapel;
        this.custoImpre = custoImpre;
        this.custoAcab = custoAcab;        this.custoArte = custoArte;        this.custoChapa = custoChapa;
        this.custoMdO = custoMdO;
        this.desconto = desconto;
        this.descricao = descricao;
        this.sequence = sequence;
        this.lista = lista;
    */
    
    public double calcular(String valor, String qtd, String custoP, String custoI, String custoAca, String custoArt, String custoChap, String custoMdO)
    {
        return (v.ConverteNumeroReal(valor) * v.ConverteNumeroInteiro(qtd)) + v.ConverteNumeroReal(custoI) + v.ConverteNumeroReal(custoAca) + v.ConverteNumeroReal(custoArt) + v.ConverteNumeroReal(custoChap) + v.ConverteNumeroReal(custoMdO);
    }
    
}
