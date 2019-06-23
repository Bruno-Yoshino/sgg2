package CamadaNegocio;

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
public class Orcamento_Servico_Detalhe {
    private DetalheServico ds;
    private int numeracaoI, numeracaoF, vias;
    private String outros;
    private int sequence;

    public Orcamento_Servico_Detalhe(DetalheServico ds, int numeracaoI, int numeracaoF, int vias, String outros, int sequence) {
        this.ds = ds;
        this.numeracaoI = numeracaoI;
        this.numeracaoF = numeracaoF;
        this.vias = vias;
        this.outros = outros;
        this.sequence = sequence;
    }

    public Orcamento_Servico_Detalhe() {
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
