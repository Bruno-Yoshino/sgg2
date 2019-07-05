/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import br.com.marciorl.beans.DateChooser;
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
import javax.swing.JPanel;
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
    
    
    public final void HabilityComponents(Component c[], boolean flag)
    {
        for (Component c1 : c) 
        {
                c1.setEnabled(flag);
        }
    }
    
    public final void Edity(Component c[])
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
                    
                case "btnEntregar":
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
    
    public final void Alter(Component c[])
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
                    
                case "btnEntregar":
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
    
    public final void Initialize(Component c[])
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
                    
                case "btnEntregar":
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
    
    public final String CarregaLogo(JLabel imagem)
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
    
    public final javax.swing.JFileChooser getJFileChooser()
    {
        if(jfilechooser == null)
        {
            jfilechooser = new javax.swing.JFileChooser();
            jfilechooser.setMultiSelectionEnabled(false);
        }
        return jfilechooser;
    }
    
    public final void limpar(Component c[])
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
                                            else
                                            {
                                                if(c1 instanceof JPanel)
                                                    limpar(((JPanel) c1).getComponents());
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
    
    public final String removeCharacter(String texto, int posicao)//posicao = texto.lenght() - 1
    {
        String temp = "";
        for(int i = 0; i < posicao; i++)
            temp += texto.charAt(i);
        return temp;
    }
    
    public final String BankCheck(String number)
    {
        switch(number)
        {
            case "654": return "Banco A.J.Renner S.A.";
            case "246": return "Banco ABC Brasil S.A.";
            case "025": return "Banco Alfa S.A.";
            case "641": return "Banco Alvorada S.A.";
            case "213": return "Banco Arbi S.A.";
            case "019": return "Banco Azteca do Brasil S.A.";
            case "029": return "Banco Banerj S.A.";
            case "000": return "Banco Bankpar S.A.";
            case "740": return "Banco Barclays S.A.";
            case "107": return "Banco BBM S.A.";
            case "031": return "Banco Beg S.A.";
            case "739": return "Banco BGN S.A.";
            case "096": return "Banco BM&F de Serviços de Liquidação e Custódia S.A";
            case "318": return "Banco BMG S.A.";
            case "752": return "Banco BNP Paribas Brasil S.A.";
            case "248": return "Banco Boavista Interatlântico S.A.";
            case "218": return "Banco Bonsucesso S.A.";
            case "065": return "Banco Bracce S.A.";
            case "036": return "Banco Bradesco BBI S.A.";
            case "204": return "Banco Bradesco Cartões S.A.";
            case "394": return "Banco Bradesco Financiamentos S.A.";
            case "237": return "Banco Bradesco S.A.";
            case "225": return "Banco Brascan S.A.";
            case "M15": return "Banco BRJ S.A.";
            case "m15": return "Banco BRJ S.A.";
            case  "15": return "Banco BRJ S.A.";
            case "208": return "Banco BTG Pactual S.A.";
            case "044": return "Banco BVA S.A.";
            case "263": return "Banco Cacique S.A.";
            case "473": return "Banco Caixa Geral - Brasil S.A.";
            case "412": return "Banco Capital S.A.";
            case "040": return "Banco Cargill S.A.";
            case "745": return "Banco Citibank S.A.";
            case "M08": return "Banco Citicard S.A.";
            case "m08": return "Banco Citicard S.A.";
            case  "08": return "Banco Citicard S.A.";
            case "241": return "Banco Clássico S.A.";
            case "M19": return "Banco CNH Capital S.A.";
            case "m19": return "Banco CNH Capital S.A.";
            case  "19": return "Banco CNH Capital S.A.";
            case "215": return "Banco Comercial e de Investimento Sudameris S.A.";
            case "756": return "Banco Cooperativo do Brasil S.A. - BANCOOB";
            case "748": return "Banco Cooperativo Sicredi S.A.";
            case "075": return "Banco CR2 S.A.";
            case "721": return "Banco Credibel S.A.";
            case "222": return "Banco Credit Agricole Brasil S.A.";
            case "505": return "Banco Credit Suisse (Brasil) S.A.";
            case "229": return "Banco Cruzeiro do Sul S.A.";
            case "266": return "Banco Cédula S.A.";
            case "003": return "Banco da Amazônia S.A.";
            case "083": return "Banco da China Brasil S.A.";//083-3
            case "M21": return "Banco Daimlerchrysler S.A.";
            case "m21": return "Banco Daimlerchrysler S.A.";
            case "21":  return "Banco Daimlerchrysler S.A.";
            case "707": return "Banco Daycoval S.A.";
            case "300": return "Banco de La Nacion Argentina";
            case "495": return "Banco de La Provincia de Buenos Aires";
            case "494": return "Banco de La Republica Oriental del Uruguay";
            case "M06": return "Banco de Lage Landen Brasil S.A.";
            case "m06": return "Banco de Lage Landen Brasil S.A.";
            case "06":  return "Banco de Lage Landen Brasil S.A.";
            case "024": return "Banco de Pernambuco S.A. - BANDEPE";
            case "456": return "Banco de Tokyo-Mitsubishi UFJ Brasil S.A.";
            case "214": return "Banco Dibens S.A.";
            case "001": return "Banco do Brasil S.A.";
            case "047": return "Banco do Estado de Sergipe S.A.";
            case "037": return "Banco do Estado do Pará S.A.";
            case "039": return "Banco do Estado do Piauí S.A. - BEP";
            case "041": return "Banco do Estado do Rio Grande do Sul S.A.";
            case "004": return "Banco do Nordeste do Brasil S.A.";
            case "256": return "Banco Fator S.A.";
            case "M03": return "Banco Fiat S.A.";
            case "m03": return "Banco Fiat S.A.";
            case "03":  return "Banco Fiat S.A.";
            case "224": return "Banco Fibra S.A.";
            case "626": return "Banco Ficsa S.A.";
            case "M18": return "Banco Ford S.A.";
            case "m18": return "Banco Ford S.A.";
            case "18":  return "Banco Ford S.A.";
            case "233": return "Banco GE Capital S.A.";
            case "734": return "Banco Gerdau S.A.";
            case "M07": return "Banco GMAC S.A.";
            case "m07": return "Banco GMAC S.A.";
            case "07":  return "Banco GMAC S.A.";
            case "612": return "Banco Guanabara S.A.";
            case "M22": return "Banco Honda S.A.";
            case "m22": return "Banco Honda S.A.";
            case "22":  return "Banco Honda S.A.";
            case "063": return "Banco Ibi S.A. Banco Múltiplo";
            case "M11": return "Banco IBM S.A.";
            case "m11": return "Banco IBM S.A.";
            case "11":  return "Banco IBM S.A.";
            case "604": return "Banco Industrial do Brasil S.A.";
            case "320": return "Banco Industrial e Comercial S.A.";
            case "653": return "Banco Indusval S.A.";
            case "630": return "Banco Intercap S.A.";
            case "077": return "Banco Intermedium S.A.";//077-9
            case "249": return "Banco Investcred Unibanco S.A.";
            case "M09": return "Banco Itaucred Financiamentos S.A.";
            case "m09": return "Banco Itaucred Financiamentos S.A.";
            case "09":  return "Banco Itaucred Financiamentos S.A.";
            case "184": return "Banco Itaucred Financiamentos S.A.";
            case "479": return "Banco ItaúBank S.A";
            case "376": return "Banco J. P. Morgan S.A.";
            case "074": return "Banco J. Safra S.A.";
            case "217": return "Banco John Deere S.A.";
            case "076": return "Banco KDB S.A.";
            case "757": return "Banco KEB do Brasil S.A.";
            case "600": return "Banco Luso Brasileiro S.A.";
            case "212": return "Banco Matone S.A.";
            case "M12": return "Banco Maxinvest S.A.";
            case "m12": return "Banco Maxinvest S.A.";
            case "12":  return "Banco Maxinvest S.A.";
            case "389": return "Banco Mercantil do Brasil S.A.";
            case "746": return "Banco Mercantil do Brasil S.A.";
            case "M10": return "Banco Moneo S.A.";
            case "m10": return "Banco Moneo S.A.";
            case "10":  return "Banco Moneo S.A.";
            case "738": return "Banco Morada S.A.";
            case "066": return "Banco Morgan Stanley S.A.";
            case "243": return "Banco Máxima S.A.";
            case "045": return "Banco Opportunity S.A.";
            case "M17": return "Banco Ourinvest S.A.";
            case "m17": return "Banco Ourinvest S.A.";
            case "17":  return "Banco Ourinvest S.A.";
            case "623": return "Banco Panamericano S.A.";
            case "611": return "Banco Paulista S.A.";
            case "613": return "Banco Pecúnia S.A.";
            case "094": return "Banco Petra S.A.";//094-2
            case "643": return "Banco Pine S.A.";
            case "742": return "Banco Porto Seguro S.A.";
            case "735": return "Banco Pottencial S.A.";
            case "638": return "Banco Prosper S.A.";
            case "M24": return "Banco PSA Finance Brasil S.A.";
            case "m24": return "Banco PSA Finance Brasil S.A.";
            case "24":  return "Banco PSA Finance Brasil S.A.";
            case "747": return "Banco Rabobank International Brasil S.A.";
            case "088": return "Banco Randon S.A.";//088-4
            case "356": return "Banco Real S.A.";
            case "633": return "Banco Rendimento S.A.";
            case "741": return "Banco Ribeirão Preto S.A.";
            case "M16": return "Banco Rodobens S.A.";
            case "m16": return "Banco Rodobens S.A.";
            case "16":  return "Banco Rodobens S.A.";
            case "072": return "Banco Rural Mais S.A.";
            case "453": return "Banco Rural S.A.";
            case "422": return "Banco Safra S.A.";
            case "033": return "Banco Santander (Brasil) S.A.";
            case "250": return "Banco Schahin S.A.";
            case "743": return "Banco Semear S.A.";
            case "749": return "Banco Simples S.A.";
            case "366": return "Banco Société Générale Brasil S.A.";
            case "637": return "Banco Sofisa S.A.";
            case "012": return "Banco Standard de Investimentos S.A.";
            case "464": return "Banco Sumitomo Mitsui Brasileiro S.A.";
            case "082": return "Banco Topázio S.A.";//082-5
            case "M20": return "Banco Toyota do Brasil S.A.";
            case "m20": return "Banco Toyota do Brasil S.A.";
            case "20":  return "Banco Toyota do Brasil S.A.";
            case "M13": return "Banco Tricury S.A.";
            case "m13": return "Banco Tricury S.A.";
            case "13":  return "Banco Tricury S.A.";
            case "634": return "Banco Triângulo S.A.";
            case "M14": return "Banco Volkswagen S.A.";
            case "m14": return "Banco Volkswagen S.A.";
            case "14":  return "Banco Volkswagen S.A.";
            case "M23": return "Banco Volvo (Brasil) S.A.";
            case "m23": return "Banco Volvo (Brasil) S.A.";
            case "23":  return "Banco Volvo (Brasil) S.A.";
            case "655": return "Banco Votorantim S.A.";
            case "610": return "Banco VR S.A.";
            case "370": return "Banco WestLB do Brasil S.A.";
            case "021": return "BANESTES S.A. Banco do Estado do Espírito Santo";
            case "719": return "Banif-Banco Internacional do Funchal (Brasil)S.A.";
            case "755": return "Bank of America Merrill Lynch Banco Múltiplo S.A.";
            case "744": return "BankBoston N.A.";
            case "073": return "BB Banco Popular do Brasil S.A.";
            case "078": return "BES Investimento do Brasil S.A.-Banco de Investimento";
            case "069": return "BPN Brasil Banco Múltiplo S.A.";
            case "070": return "BPN Brasil Banco Múltiplo S.A.";
            case "092": return "Brickell S.A. Crédito, financiamento e Investimento";//092-2
            case "104": return "Caixa Econômica Federal";
            case "477": return "Citibank N.A.";
            case "081": return "Concórdia Banco S.A.";//081-7
            case "097": return "Cooperativa Central de Crédito Noroeste Brasileiro Ltda.";//097-3
            case "085": return "Cooperativa Central de Crédito Urbano-CECRED";//085-x
            case "099": return "Cooperativa Central de Economia e Credito Mutuo das Unicreds";//099-x
            case "090": return "Cooperativa Central de Economia e Crédito Mutuo das Unicreds";//090-2
            case "089": return "Cooperativa de Crédito Rural da Região de Mogiana";//089-2
            case "087": return "Cooperativa Unicred Central Santa Catarina";//087-6
            case "098": return "Credicorol Cooperativa de Crédito Rural";//098-1
            case "487": return "Deutsche Bank S.A. - Banco Alemão";
            case "751": return "Dresdner Bank Brasil S.A. - Banco Múltiplo";
            case "064": return "Goldman Sachs do Brasil Banco Múltiplo S.A.";
            case "062": return "Hipercard Banco Múltiplo S.A.";
            case "399": return "HSBC Bank Brasil S.A. - Banco Múltiplo";
            case "168": return "HSBC Finance (Brasil) S.A. - Banco Múltiplo";
            case "492": return "ING Bank N.V.";
            case "652": return "Itaú Unibanco Holding S.A.";
            case "341": return "Itaú Unibanco S.A.";
            case "079": return "JBS Banco S.A.";
            case "488": return "JPMorgan Chase Bank";
            case "014": return "Natixis Brasil S.A. Banco Múltiplo";
            case "753": return "NBC Bank Brasil S.A. - Banco Múltiplo";
            case "086": return "OBOE Crédito Financiamento e Investimento S.A.";//086-8
            case "254": return "Paraná Banco S.A.";
            case "409": return "UNIBANCO - União de Bancos Brasileiros S.A.";
            case "230": return "Unicard Banco Múltiplo S.A.";
            case "091": return "Unicred Central do Rio Grande do Sul";//091-4
            case "084": return "Unicred Norte do Paraná";
            default: return "Desconhecido";
        }
    }
}
