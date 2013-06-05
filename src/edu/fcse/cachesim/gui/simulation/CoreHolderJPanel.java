package edu.fcse.cachesim.gui.simulation;

import edu.fcse.cachesim.interfaces.CPUCore;
import edu.fcse.cachesim.interfaces.CacheLevel;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CoreHolderJPanel extends JPanel {

    CPUCore core;
    javax.swing.JLabel assocLabel;
    javax.swing.JLabel typeLabel;
    javax.swing.JLabel rpLabel;
    javax.swing.JLabel sizeLabel;
    javax.swing.JButton l1;
    javax.swing.JButton l2;
    javax.swing.JButton l3;

    public CoreHolderJPanel(SimulationJFrame frame, CPUCore core) {
        this.core = core;
        java.awt.GridLayout layout = new java.awt.GridLayout(0, 1);
        layout.setVgap(3);
        setLayout(layout);
        l1 = new javax.swing.JButton();
        l1.setText(core.getLevel(1).getTag());
        l1.addActionListener(new CacheLevelActionListener(frame, this, 1, core.getLevel(1)));
        add(l1);

        l2 = new javax.swing.JButton();
        l2.setText(core.getLevel(2).getTag());
        l2.addActionListener(new CacheLevelActionListener(frame, this, 2, core.getLevel(2)));
        add(l2);

        l3 = new javax.swing.JButton();
        l3.setText(core.getLevel(3).getTag());
        l3.addActionListener(new CacheLevelActionListener(frame, this, 3, core.getLevel(3)));
        add(l3);
        typeLabel = new javax.swing.JLabel("<html>Type: <b>Level 1</b></html>");
        assocLabel = new javax.swing.JLabel("<html>Assoc: <b>" + core.getLevel(1).getAssoc() + "</b></html>");
        rpLabel = new javax.swing.JLabel("<html>RP: <b>" + core.getLevel(1).getRP() + "</b></html>");
        sizeLabel = new javax.swing.JLabel();
        setCacheSize(core.getLevel(1).getSize());
        add(typeLabel);
        add(assocLabel);
        add(rpLabel);
        add(sizeLabel);
    }

    public JButton getLevel(int levelNum) {
        switch (levelNum) {
            case 1:
                return l1;
            case 2:
                return l2;
            case 3:
                return l3;
        }
        return null;
    }

    private void setAssociativity(int assoc) {
        assocLabel.setText("<html>Assoc: <b>" + assoc + "</b></html>");
    }

    private void setType(int type) {
        typeLabel.setText("<html>Type: <b>Level " + type + "</b></html>");
    }

    private void setRP(String rp) {
        rpLabel.setText("<html>RP: <b>" + rp + "</b></html>");
    }

    private void setCacheSize(long sizeInBytes) {

        String m = "B";
        if (sizeInBytes > 1023) {
            sizeInBytes /= 1024;
            m = "KB";
        }
        if (sizeInBytes > 1023) {
            sizeInBytes /= 1024;
            m = "MB";
        }
        if (sizeInBytes > 1023) {
            sizeInBytes /= 1024;
            m = "GB";
        }
        sizeLabel.setText("<html>Size: <b>" + sizeInBytes + " " + m + "</b></html>");
    }

    public void onCacheLevelClicked(int levelNum, CacheLevel level) {
        setType(levelNum);
        setAssociativity(level.getAssoc());
        setCacheSize(level.getSize());
        setRP(level.getRP().toString());
    }
}
