/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * プログラマー
 * @author 吉野　廉
 * @author モニカ
 */
public class SystemControl 
{
    private javax.swing.JFileChooser jfilechooser = null;
    private BufferedImage img;
    
    public SystemControl() {
    }
    
    
    public void HabilityComponents(Component c[], boolean flag)
    {
        for (Component c1 : c) 
        {
                c1.setEnabled(flag);
        }
    }
    
    public void Edity(Component c[])
    {
        for (Component c1 : c) 
        {
            switch(c1.getName())
            {
                case "btnNovo":
                    c1.setEnabled(false);
                    break;
                    
                case "btnSair":
                    c1.setEnabled(true);
                    break;
                    
                case "btnCancelar":
                    c1.setEnabled(true);
                    break;
                
                case "btnAlterar":
                    c1.setEnabled(false);
                    break;
                
                case "btnExcluir":
                    c1.setEnabled(false);
                    break;
                
                case "btnGravar":
                    c1.setEnabled(true);
                    break;
                    
                case "btnLocalizar":
                    c1.setEnabled(false);
                    break;
                    
                default: c1.setEnabled(true);
            }
        }
    }
    
    public void Alter(Component c[])
    {
        for (Component c1 : c) 
        {
            switch(c1.getName())
            {
                case "btnNovo":
                    c1.setEnabled(false);
                    break;
                    
                case "btnCancelar":
                    c1.setEnabled(true);
                    break;
                                    
                case "btnSair":
                    c1.setEnabled(true);
                    break;
                    
                case "btnAlterar":
                    c1.setEnabled(true);
                    break;
                
                case "btnExcluir":
                    c1.setEnabled(true);
                    break;
                
                case "btnGravar":
                    c1.setEnabled(false);
                    break;
                    
                case "btnLocalizar":
                    c1.setEnabled(true);
                    break;
                    
                default: c1.setEnabled(true);
            }
        }
    }
    
    public void Initialize(Component c[])
    {
        for (Component c1 : c) 
        {
            switch(c1.getName())
            {
                case "btnNovo":
                    c1.setEnabled(true);
                    break;
                    
                case "btnCancelar":
                    c1.setEnabled(false);
                    break;
                
                case "btnAlterar":
                    c1.setEnabled(false);
                    break;
                
                case "btnExcluir":
                    c1.setEnabled(false);
                    break;
                
                case "btnGravar":
                    c1.setEnabled(false);
                    break;
                    
                case "btnLocalizar":
                    c1.setEnabled(true);
                    break;
                                        
                case "btnSair":
                    c1.setEnabled(true);
                    break;
                    
                default: c1.setEnabled(false);
            }
        }
    }
    
    public String CarregaLogo(JLabel imagem)
    {
        String local = null;
        int chamar = getJFileChooser().showOpenDialog(null);
        {
            if(chamar == jfilechooser.APPROVE_OPTION)
            {
                File f = getJFileChooser().getSelectedFile();
                try
                {
                    img = ImageIO.read(f);
                    imagem.setIcon(new ImageIcon(img.getScaledInstance(imagem.getWidth(),imagem.getHeight(),img.SCALE_DEFAULT)));
                }
                catch (IOException ex)
                {
                    local = "";
                }
              local = jfilechooser.getSelectedFile().getPath();
            }
        }
        return local;
    }
    
    public javax.swing.JFileChooser getJFileChooser()
    {
        if(jfilechooser == null)
        {
            jfilechooser = new javax.swing.JFileChooser();
            jfilechooser.setMultiSelectionEnabled(false);
        }
        return jfilechooser;
    }
}
