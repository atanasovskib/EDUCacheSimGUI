
package edu.fcse.cachesim.gui.construction;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TableSelectionListener implements ListSelectionListener{
    JTable table;
    ConstructionJFrame frame;
    public TableSelectionListener(ConstructionJFrame frame,JTable table){
        this.table=table;
        this.frame=frame;
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        frame.enableDeleteButton();
        
    }
    
}
