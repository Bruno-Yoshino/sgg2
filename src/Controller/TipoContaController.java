/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import CamadaNegocio.TipoConta;
import util.Validacao;
import util.mensagens;
/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 */
public class TipoContaController 
{
    private util.Validacao v;
    private mensagens m;
    private TipoConta tp;

    public TipoContaController() {
        tp = new TipoConta();
        m = new mensagens();
        v = new Validacao();
    }

    public TipoConta getTp() {
        return tp;
    }

    public void setTp(TipoConta tp) {
        this.tp = tp;
    }
    
    public int validar(String cod, String tipo)
    {
        int id = v.ConverteNumeroInteiro(cod);
        if( id == -999)
            return 1;
        if(v.ValidaTexto(tipo) == 1)
            return 2;
        if(tp.buscarTipo(tipo) != null)
            return 3;
        tp.setCodigo(id);
        tp.setTipo(tipo);
        return 0;
    }
    
    public boolean gravar()
    {
        return tp.gravar();
    }
    
    public boolean excluir(String codigo)
    {
        return tp.excluir(v.ConverteNumeroInteiro(codigo));
    }
    
    public void localizar(int codigo)
    {
       tp = tp.buscarCodigo(codigo);
    } 
}
