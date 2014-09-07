package edu.fcse.cachesim.gui.construction;

import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawHere extends JPanel{

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.drawOval(10, 10, 10, 10);
    }
    
}
