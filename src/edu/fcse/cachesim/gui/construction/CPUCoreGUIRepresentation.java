package edu.fcse.cachesim.gui.construction;

import edu.fcse.cachesim.interfaces.CPUCore;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class CPUCoreGUIRepresentation extends JButton implements ActionListener {

    private final ConstructionJFrame2 frame;
    private final CPUCore core;

    public CPUCoreGUIRepresentation(ConstructionJFrame2 frame, CPUCore core) {
        this.frame = frame;
        this.core = core;
        this.setPreferredSize(new Dimension(50, 50));
        this.setText(core.getTag());
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (String tag : frame.getCreatedCPUs().keySet()) {
            if (tag.equals(core.getTag())) {
                this.setBorder(BorderFactory.createLineBorder(Color.blue));
            } else {
                frame.getCreatedCPUs().get(tag).setBorder(BorderFactory.createEmptyBorder());
            }
        }
        frame.getCreatedCPUsPanel().updateUI();
        frame.loadCoreInfo(core);
    }

    public CPUCore getCore() {
        return core;
    }
}
