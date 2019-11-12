package CamadaLogica;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.JFileChooser;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 川波
 * @author 水川
 * @author 巴御前
 * @author アナスタシア
 * @author 早川　茜
 * @author 高村　結衣
 * @author 里川　麗奈
 * @author 川波　愁子
 * @author 水川　鈴奈
 * @author 嶌田　治奈
 * @author 小枩　夏輝
 * @author 海女
 * @author 御子
 * @author 稲荷
 * 
 * 
 * 
 */
public class Relatorio {
    
    public void ImprimirRelatorio(ResultSet rs, String ArqNome, String TituloRelatorio) throws JRException
    {
      //implementação da interface JRDataSource para DataSource ResultSet
      JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
      //chamando o relatório
      String jasperPrint =          
          JasperFillManager.fillReportToFile(ArqNome, null, jrRS);
      JasperViewer viewer = new JasperViewer(jasperPrint, false, false);
      viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);//maximizado
      viewer.setTitle(TituloRelatorio);//titulo do relatório
      viewer.setVisible(true);
    }
    
    /*public void ImprimirRelatorioComParametro(String Sql, String ArqNome, String TituloRelatorio, String NomeCodigo, int codigo) throws JRException
    {
        BancoDados banco = new BancoDados();
//        banco.abrirConexao();
        Map parameters = new HashMap();
        parameters.put(NomeCodigo, codigo);
        ResultSet rs = banco.retornaResultSet(Sql);
        JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
        String jasperPrint = JasperFillManager.fillReportToFile(ArqNome, parameters, jrRS);
        JasperViewer viewer = new JasperViewer(jasperPrint, false, false);
        viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);//maximizado
        viewer.setTitle("Relatório de(a) " + TituloRelatorio);//titulo do relatório
        viewer.setVisible(true);
        banco.fecharConexao();
    }*/
    
    /*public void ImprimirRelatorioPDF_SemParametro(String Sql, String ArqNome, String TituloRelatorio) throws JRException
    {
        
        BancoDados banco = new BancoDados();
        JFileChooser chooser = new JFileChooser();
        String caminho = "";
        File file = null;
        int retorno = chooser.showSaveDialog(null);
        ResultSet rs = banco.retornaResultSet(Sql);
        JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
        String jasperPrint = JasperFillManager.fillReportToFile(ArqNome, null, jrRS);
        
        if (retorno == JFileChooser.APPROVE_OPTION)
        {
            caminho = chooser.getSelectedFile().getAbsolutePath();
        }
        if(!caminho.equals(""))
        {
            JasperExportManager.exportReportToPdfFile(jasperPrint, caminho+".pdf");
            file = new File(caminho);
            try
            {
                //perguntar se deseja ver o pdf
                java.awt.Desktop.getDesktop().open( new File(caminho+".pdf"));
            }
            catch (IOException ex)
            {
            }
        }
    }*/
    
    public void ImprimirRelatorioPDF(ResultSet rs, String ArqNome) throws JRException
    {
        JFileChooser chooser = new JFileChooser();
        String caminho = "";
        File file = null;
        int retorno = chooser.showSaveDialog(null);
        JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
        String jasperPrint = JasperFillManager.fillReportToFile(ArqNome, null, jrRS);
        if (retorno == JFileChooser.APPROVE_OPTION)
        {
            caminho = chooser.getSelectedFile().getAbsolutePath();
        }
        if(!caminho.equals(""))
        {
            JasperExportManager.exportReportToPdfFile(jasperPrint, caminho+".pdf");
            //JasperExportManager.exportReportToXmlFile(jasperPrint, caminho+".xml", true);
            file = new File(caminho);
            try
            {
                //perguntar se deseja ver o pdf
                java.awt.Desktop.getDesktop().open( new File(caminho+".pdf"));
                //java.awt.Desktop.getDesktop().open( new File(caminho+".xml"));
            }
            catch (IOException ex)
            {
            }
        }
    }
    
    public void ImprimirRelatorioPDF(int codigoCli, Date dataIni, Date dataFim, String ArqNome) throws JRException
    {
        JFileChooser chooser = new JFileChooser();
        String caminho = "";
        File file = null;
        int retorno = chooser.showSaveDialog(null);
        HashMap parametros = new HashMap();
        parametros.put("clienteCodigo", codigoCli);
        parametros.put("dataIni", dataIni);
        parametros.put("dataFim", dataFim);
        
        String jasperPrint = JasperFillManager.fillReportToFile(ArqNome, parametros, Banco.getCon().getConnection());
        if (retorno == JFileChooser.APPROVE_OPTION)
        {
            caminho = chooser.getSelectedFile().getAbsolutePath();
        }
        if(!caminho.equals(""))
        {
            JasperExportManager.exportReportToPdfFile(jasperPrint, caminho+".pdf");
            //JasperExportManager.exportReportToXmlFile(jasperPrint, caminho+".xml", true);
            file = new File(caminho);
            try
            {
                //perguntar se deseja ver o pdf
                java.awt.Desktop.getDesktop().open( new File(caminho+".pdf"));
                //java.awt.Desktop.getDesktop().open( new File(caminho+".xml"));
            }
            catch (IOException ex)
            {
            }
        }
    }
    
}
