package CamadaNegocio;

import java.util.ArrayList;

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
public class Orcamento_Servico {
    private Servico serv;
    private double valor;
    private int qtd;
    private double custoPapel, custoImpre, custoAcab, custoArte, custoChapa, custoMdO, desconto;
    private String descricao;
    private int sequence;
    private ArrayList<Orcamento_Servico_Detalhe> lista;

    public Orcamento_Servico(Servico serv, double valor, int qtd, double custoPapel, double custoImpre, double custoAcab, double custoArte, double custoChapa, double custoMdO, double desconto, String descricao, int sequence, ArrayList<Orcamento_Servico_Detalhe> lista) {
        this.serv = serv;
        this.valor = valor;
        this.qtd = qtd;
        this.custoPapel = custoPapel;
        this.custoImpre = custoImpre;
        this.custoAcab = custoAcab;
        this.custoArte = custoArte;
        this.custoChapa = custoChapa;
        this.custoMdO = custoMdO;
        this.desconto = desconto;
        this.descricao = descricao;
        this.sequence = sequence;
        this.lista = lista;
    }

    public Orcamento_Servico() {
        this.lista = new ArrayList<>();
    }

    public Servico getServ() {
        return serv;
    }

    public void setServ(Servico serv) {
        this.serv = serv;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getCustoPapel() {
        return custoPapel;
    }

    public void setCustoPapel(double custoPapel) {
        this.custoPapel = custoPapel;
    }

    public double getCustoImpre() {
        return custoImpre;
    }

    public void setCustoImpre(double custoImpre) {
        this.custoImpre = custoImpre;
    }

    public double getCustoAcab() {
        return custoAcab;
    }

    public void setCustoAcab(double custoAcab) {
        this.custoAcab = custoAcab;
    }

    public double getCustoArte() {
        return custoArte;
    }

    public void setCustoArte(double custoArte) {
        this.custoArte = custoArte;
    }

    public double getCustoChapa() {
        return custoChapa;
    }

    public void setCustoChapa(double custoChapa) {
        this.custoChapa = custoChapa;
    }

    public double getCustoMdO() {
        return custoMdO;
    }

    public void setCustoMdO(double custoMdO) {
        this.custoMdO = custoMdO;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public ArrayList<Orcamento_Servico_Detalhe> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Orcamento_Servico_Detalhe> lista) {
        this.lista = lista;
    }
    
    
}
