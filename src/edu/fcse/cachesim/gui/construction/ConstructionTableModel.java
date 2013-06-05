
package edu.fcse.cachesim.gui.construction;

import edu.fcse.cachesim.interfaces.Referable;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

public class ConstructionTableModel extends AbstractTableModel{

    private String[] columnNames;
    private String[][] data;
    public ConstructionTableModel(Map<String,Referable> info){
        columnNames=new String[]{"UID","Description"};
        data=new String[info.size()][2];
        int i=0;
        for(String s:info.keySet()){
            data[i][0]=s;
            Referable cl=info.get(s);
            
            data[i][1]=cl.getDescription();
            i++;
        }
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

   
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

}
