package edu.fcse.cachesim.gui.simulation;

import edu.fcse.cachesim.interfaces.CacheSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CacheSetActionListener implements ActionListener {

    SimulationJFrame frame;
    CacheSet set;

    public CacheSetActionListener(SimulationJFrame frame, CacheSet set) {
        this.frame = frame;
        this.set = set;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.loadCacheLinesInPanel(set.getCacheLines());
        frame.invalidate();
    }
}
