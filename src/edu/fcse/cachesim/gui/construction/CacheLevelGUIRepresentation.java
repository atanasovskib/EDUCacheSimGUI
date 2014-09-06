package edu.fcse.cachesim.gui.construction;

import edu.fcse.cachesim.interfaces.CacheLevel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class CacheLevelGUIRepresentation extends JButton implements ActionListener{
    private final CacheLevel level;
    private final ConstructionJFrame2 frame;
    public CacheLevelGUIRepresentation(ConstructionJFrame2 frame,CacheLevel level){
        this.level = level;
        this.frame = frame;
        this.setText(level.getTag());
        this.setPreferredSize(new Dimension(100, 50));
        this.setVisible(true);
        this.addActionListener(this);
    }
    public CacheLevel getCacheLevel(){
        return level;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.showInfo(level.getTag());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CacheLevelGUIRepresentation){
            CacheLevelGUIRepresentation other =  (CacheLevelGUIRepresentation)obj;
            return this.level.getTag().equals(other.getCacheLevel().getTag());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return level.getTag().hashCode(); 
    }
    
}
