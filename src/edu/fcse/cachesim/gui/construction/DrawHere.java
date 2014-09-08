package edu.fcse.cachesim.gui.construction;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DrawHere extends JPanel{
    private String ovoaGoCrtaj;
    public DrawHere(){
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    public Dimension  getPreferredSize() {
        return new Dimension(250,200);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.drawString(ovoaGoCrtaj, 10, 10);
    }
    public void setSoDaCrtam(String ovoa){
        this.ovoaGoCrtaj=ovoa;
    }
}
