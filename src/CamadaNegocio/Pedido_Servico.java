package CamadaNegocio;

import java.util.ArrayList;

/**
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
public class Pedido_Servico {
    private Servico serv;
    private double valor;
    private int qtd;
    private double desconto;
    private String descricao;
    private int sequence;
    private ArrayList<Pedido_Servico_Detalhe> lista;

    public Pedido_Servico(Servico serv, double valor, int qtd, double desconto, String descricao, int sequence, ArrayList<Pedido_Servico_Detalhe> lista) {
        this.serv = serv;
        this.valor = valor;
        this.qtd = qtd;
        this.desconto = desconto;
        this.descricao = descricao;
        this.sequence = sequence;
        this.lista = lista;
    }

    public Pedido_Servico() {
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

    public ArrayList<Pedido_Servico_Detalhe> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Pedido_Servico_Detalhe> lista) {
        this.lista = lista;
    }
    
    
}
