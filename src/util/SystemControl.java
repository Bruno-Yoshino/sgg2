/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import br.com.marciorl.beans.DateChooser;
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.PieChart.Data;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
    
    public void limpar(Component c[])
    {
        for (Component c1 : c) 
        {    
            if(c1 instanceof JTextField)
                ((JTextField) c1).setText("");
            else
            {
                if(c1 instanceof JTextArea)
                    ((JTextArea) c1).setText("");
                else
                {
                    if(c1 instanceof JFormattedTextField)
                        ((JFormattedTextField) c1).setText("");
                    else
                    {
                        if(c1 instanceof JCheckBox)
                            ((JCheckBox) c1).setSelected(false);
                        else
                        {
                            if(c1 instanceof JRadioButton)
                                ((JRadioButton) c1).setSelected(false);
                            else
                            {
                                if(c1 instanceof JComboBox)
                                    ((JComboBox) c1).setSelectedIndex(1);
                                else
                                {
                                    if(c1 instanceof JTable)
                                    {
                                        for(int i = 0; i < ((JTabbedPane) c1).getTabCount(); i++)
                                        {
                                            ((JTabbedPane) c1).setSelectedIndex(i);
                                            limpar(((JTabbedPane) c1).getComponents());
                                        }
                                    }
                                    else
                                    {
                                        if(c1 instanceof DateChooser)
                                            ((DateChooser) c1).setData(Date.from(Instant.now()));
                                        else
                                        {
                                            if(c1 instanceof JTable)
                                                 ((JTable) c1).removeAll();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
