/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CamadaNegocio;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 巴御前
 * @author 高村　結衣
 * @author 里川　麗奈
 * @author 橋立
 * @author 阿賀野
 * @author 矢矧
 */
public class Producao_Folha {
    private Folha f;
    private int qtd;

    public Producao_Folha(Folha f, int qtd) {
        this.f = f;
        this.qtd = qtd;
    }

    public Producao_Folha() {
    }

    public Folha getF() {
        return f;
    }

    public void setF(Folha f) {
        this.f = f;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    
    //--------------------------------------------------------------------------
    
}
