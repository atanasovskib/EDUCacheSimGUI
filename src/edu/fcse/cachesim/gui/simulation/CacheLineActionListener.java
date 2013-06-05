/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fcse.cachesim.gui.simulation;

import edu.fcse.cachesim.interfaces.CacheLine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Delix
 */
public class CacheLineActionListener implements ActionListener{
    int lineNum;
    CacheLine line;
    SimulationJFrame frame;
    public CacheLineActionListener(SimulationJFrame frame,int lineNum,CacheLine line){
        this.lineNum=lineNum;
        this.line=line;
        this.frame=frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.loadCacheLineInfo(lineNum, line);
    }
    
}
