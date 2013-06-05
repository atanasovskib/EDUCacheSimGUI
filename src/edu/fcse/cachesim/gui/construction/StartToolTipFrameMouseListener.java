/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fcse.cachesim.gui.construction;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class StartToolTipFrameMouseListener implements MouseListener {

    String textToLoad;
    String linkToLoad;
    InfoJFrame frameToLoad;
    String title;

    public StartToolTipFrameMouseListener(InfoJFrame frameToLoad, String titleOfFrame, String textToLoad, String linkToLoad) {
        this.textToLoad = textToLoad;
        this.linkToLoad = linkToLoad;
        this.frameToLoad = frameToLoad;
        title = titleOfFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (frameToLoad == null) {
            frameToLoad = new InfoJFrame(title,textToLoad, linkToLoad);
        } else {
            frameToLoad.initFields(title,textToLoad, linkToLoad);
        }
        frameToLoad.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
