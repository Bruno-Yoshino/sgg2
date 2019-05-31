
package Controller;

import CamadaNegocio.Folha;
import javax.swing.JOptionPane;

/**
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 */
public class FolhaController 
{
    private Folha fo;

    public FolhaController() 
    {
        this.fo = new Folha();
    }

    public Folha getFo() {
        return fo;
    }
    
    public int validaCampo(String descricao, String altura, String largura, String tamanho, String codigo)
    {
        int quant;
        double lar, alt;
        if(descricao.equals(""))
            return 1;
        fo.setDescricao(descricao);
        try 
        {
            alt = Double.parseDouble(altura);
            if(alt <= 0)
            {
                return 5;
            }
            fo.setAltura(alt);
        } 
        catch (Exception e) 
        {
            return 2;
        }
        
        try 
        {
            lar = Double.parseDouble(largura);
            if(lar <= 0)
            {
                return 6;
            }
            fo.setLargura(lar);
        } 
        catch (Exception e) 
        {
            return 3;
        }
        
        if(tamanho.trim().equals(""))
        {
            return 7;
        }
        fo.setTamanho(tamanho);
        
        if(fo.CheckExist(tamanho, descricao) && codigo.equals("0"))
        {
            return 8;
        }
        
        return 0;
    }
    
    public boolean gravar(int codigo, boolean status)
    {
        fo.setCodigo(codigo);
        fo.setStatus(status);
        return fo.gravar();
    }

    
    public Folha LocalizaFolha(int codigo)
    {
        return fo = fo.buscarCodigo(codigo);   
    }
    
    public boolean Excluir(int codigo)
    {
        return fo.excluir(codigo);
    }
    
}
