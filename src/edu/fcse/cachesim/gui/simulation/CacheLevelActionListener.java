package edu.fcse.cachesim.gui.simulation;

import edu.fcse.cachesim.interfaces.CacheLevel;
import edu.fcse.cachesim.interfaces.CacheSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CacheLevelActionListener implements ActionListener {

    SimulationJFrame frame;
    CoreHolderJPanel panelka;
    int levelNum;
    CacheLevel level;

    public CacheLevelActionListener(SimulationJFrame frame, CoreHolderJPanel panel, int levelNum, CacheLevel level) {
        panelka = panel;
        this.frame = frame;
        this.level = level;
        this.levelNum = levelNum;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panelka.onCacheLevelClicked(levelNum, level);
        List<CacheSet> sets = level.getCacheSets();
        frame.loadCacheSetsInPanel(levelNum,sets);
        frame.invalidate();
    }
}
