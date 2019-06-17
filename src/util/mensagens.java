/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JOptionPane;

/**
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 */
public class mensagens 
{

    public mensagens() {
    }
    
    public final void ErroMessage(String text, String title)
    {
        JOptionPane.showMessageDialog(null, text, title, JOptionPane.ERROR_MESSAGE);
    }
    public final void WarmingMessage(String text, String title)
    {
        JOptionPane.showMessageDialog(null, text, title, JOptionPane.WARNING_MESSAGE);
    }
    public final void InformationMessage(String text, String title)
    {
        JOptionPane.showMessageDialog(null, text, title, JOptionPane.INFORMATION_MESSAGE);
    }
    public final void QuestionMessage(String text, String title)
    {
        JOptionPane.showMessageDialog(null, text, title, JOptionPane.QUESTION_MESSAGE);
    }
    
    public final Integer Pergunta(String text, String title)
    {
        return JOptionPane.showConfirmDialog(null, text, title, JOptionPane.QUESTION_MESSAGE);
    }
}
