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
    
    public void ErroMessage(String text, String title)
    {
        JOptionPane.showMessageDialog(null, text, title, JOptionPane.ERROR_MESSAGE);
    }
    public void WarmingMessage(String text, String title)
    {
        JOptionPane.showMessageDialog(null, text, title, JOptionPane.WARNING_MESSAGE);
    }
    public void InformationMessage(String text, String title)
    {
        JOptionPane.showMessageDialog(null, text, title, JOptionPane.INFORMATION_MESSAGE);
    }
    public void QuestionMessage(String text, String title)
    {
        JOptionPane.showMessageDialog(null, text, title, JOptionPane.QUESTION_MESSAGE);
    }
    
    public Integer Pergunta(String text, String title)
    {
        return JOptionPane.showConfirmDialog(null, text, title, JOptionPane.QUESTION_MESSAGE);
    }
}
