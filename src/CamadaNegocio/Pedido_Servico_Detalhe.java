package CamadaNegocio;

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
public class Pedido_Servico_Detalhe {
    private DetalheServico ds;
    private int numeracaoI, numeracaoF, vias;
    private String outros;
    private int sequence;

    public Pedido_Servico_Detalhe(DetalheServico ds, int numeracaoI, int numeracaoF, int vias, String outros, int sequence) {
        this.ds = ds;
        this.numeracaoI = numeracaoI;
        this.numeracaoF = numeracaoF;
        this.vias = vias;
        this.outros = outros;
        this.sequence = sequence;
    }

    public Pedido_Servico_Detalhe() {
    }

    public DetalheServico getDs() {
        return ds;
    }

    public void setDs(DetalheServico ds) {
        this.ds = ds;
    }

    public int getNumeracaoI() {
        return numeracaoI;
    }

    public void setNumeracaoI(int numeracaoI) {
        this.numeracaoI = numeracaoI;
    }

    public int getNumeracaoF() {
        return numeracaoF;
    }

    public void setNumeracaoF(int numeracaoF) {
        this.numeracaoF = numeracaoF;
    }

    public int getVias() {
        return vias;
    }

    public void setVias(int vias) {
        this.vias = vias;
    }

    public String getOutros() {
        return outros;
    }

    public void setOutros(String outros) {
        this.outros = outros;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
    
    
}
