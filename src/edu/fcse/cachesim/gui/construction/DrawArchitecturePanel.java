package edu.fcse.cachesim.gui.construction;

import edu.fcse.cachesim.interfaces.CPUCore;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

public class DrawArchitecturePanel extends JPanel {

    private final Map<String, Shape> drawnComponents;
    private Map<String, CPUCore> cores;

    public DrawArchitecturePanel(Map<String, CPUCore> cores) {
        this.drawnComponents = new HashMap<>();
        this.cores = cores;
    }
    public void setCores(Map<String, CPUCore> cores) {
        this.cores = cores;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension panelSize = this.getSize();
        int coreWidth = (int) (0.8 * (panelSize.getWidth() / cores.size()));
        int coreHeight = (int) (0.9 * panelSize.getHeight());
        if (coreWidth > 1.2 * coreHeight) {
            coreWidth = (int) 1.2 * coreHeight;
        }
        if (coreWidth < 50 || coreHeight < 40) {
            g.drawString("Expand window", ((int) panelSize.getWidth() / 2) - 25, 10);
            return;
        }
        drawnComponents.clear();
        // height total = 25 parts
        // core = l1 = 4parts = 16%
        //l2 = 5 = 20%, l3 = 6 = 24%
        //lines = 2parts = 8%
        double coreCircleRadius = 0.16 * coreHeight;
        double lineHeight = 0.08 * coreHeight;
        drawCores(g, coreWidth, coreCircleRadius);
        drawLinesFromCores(g, lineHeight);
    }
    
    private void drawCores(Graphics g, int coreWidth, double coreCircleRadius){
        double centerOfCore = coreWidth/2;
        int i=10;
        for(String coreTag:cores.keySet()){
            int tagWidth = (int)g.getFontMetrics().getStringBounds(coreTag, g).getWidth();
            g.drawString(coreTag, (int)centerOfCore, i);
            i+=10;
            drawnComponents.put(coreTag,new Shape(centerOfCore, 15, coreCircleRadius, coreCircleRadius));
            g.drawOval((int)centerOfCore, 15, (int)coreCircleRadius, (int)coreCircleRadius);
            centerOfCore+=coreWidth;
        }
    }
    private class Shape{
        public Shape(double x, double y, double width, double height){
            this.x=x;
            this.y=y;
            this.width=width;
            this.height=height;
        }
        public double x,y,width,height;
    }
    
    private void drawLinesFromCores(Graphics g, double lineHeight) {
        
    }
}
