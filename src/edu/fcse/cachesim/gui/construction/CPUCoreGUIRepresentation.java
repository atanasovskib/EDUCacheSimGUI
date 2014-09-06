package edu.fcse.cachesim.gui.construction;

import edu.fcse.cachesim.interfaces.CPUCore;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class CPUCoreGUIRepresentation extends JButton implements ActionListener{
    private final ConstructionJFrame2 frame;
    private final CPUCore core;
    
    public CPUCoreGUIRepresentation(ConstructionJFrame2 frame, CPUCore core){
        this.frame = frame;
        this.core = core;
        this.setPreferredSize(new Dimension(50,50));
        this.setText(core.getTag());
        this.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.loadCoreInfo(core);
    }
    public CPUCore getCore()
    {
        return core;
    }
}
