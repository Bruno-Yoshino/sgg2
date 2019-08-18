package util;

import CamadaLogica.ReadOnlyTableModel;
import javax.swing.JTable;

public final class ClassHome 
{   
    public static void configuraModel(JTable jTable) // Configurar Tabela Para consulta ou para Alterar
    {
        String colunas[] = new String [] {"Nome", "Valor", "Data Vencimento"};
        jTable.setModel(new ReadOnlyTableModel(colunas, 0));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(250);
    }
}
