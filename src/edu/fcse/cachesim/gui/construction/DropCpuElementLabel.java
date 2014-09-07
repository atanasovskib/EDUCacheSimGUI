/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fcse.cachesim.gui.construction;

import edu.fcse.cachesim.implementation.CacheLevelImpl;
import edu.fcse.cachesim.interfaces.CacheLevel;
import edu.fcse.cachesim.interfaces.ReplacementPolicy;
import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class DropCpuElementLabel extends JLabel {
    private CacheLevel associatedLevel;
    private int level;
    public DropCpuElementLabel(String s,int level) {
        this.setText(s);
        this.level = level;
        DropTargetListener dtListener = new DTListenerImpl();
        this.dropTarget = new DropTarget(this, this.acceptableActions, dtListener, true);
        this.setOpaque(true);
    }
    private final DropTarget dropTarget;
    private final int acceptableActions = DnDConstants.ACTION_COPY;

    class DTListenerImpl implements DropTargetListener {
        private Border oldBorder;
        @Override
        public void dragEnter(DropTargetDragEvent dtde) {
            oldBorder=DropCpuElementLabel.this.getBorder();
            DropCpuElementLabel.this.setBorder(BorderFactory.createLineBorder(Color.green,2));
            dtde.acceptDrag(DropCpuElementLabel.this.acceptableActions);
        }

        @Override
        public void dragOver(DropTargetDragEvent dtde) {
            dtde.acceptDrag(DropCpuElementLabel.this.acceptableActions);
        }

        @Override
        public void dropActionChanged(DropTargetDragEvent dtde) {
            dtde.acceptDrag(DropCpuElementLabel.this.acceptableActions);
        }

        @Override
        public void dragExit(DropTargetEvent dte) {
            DropCpuElementLabel.this.setBorder(oldBorder);
        }

        @Override
        public void drop(DropTargetDropEvent dtde) {
            try {
                Transferable ss = dtde.getTransferable();
                DataFlavor[] flavors = ss.getTransferDataFlavors();
                String sk = (String) ss.getTransferData(flavors[0]);
                CacheLevel level = destringifyLevel(sk);
                DropCpuElementLabel.this.associatedLevel=level;
                float[] vals=new float[3];
                Color.RGBtoHSB(161, 172, 246, vals);
                DropCpuElementLabel.this.setBackground(Color.getHSBColor(vals[0], vals[1], vals[2]));
                DropCpuElementLabel.this.setText("<html>"+"<b>UID: "+level.getTag()+"</b><br/>"+level.getSize()+"B "+level.getRP()+"</html>");
                DropCpuElementLabel.this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            } catch (UnsupportedFlavorException ex) {
                Logger.getLogger(DropCpuElementLabel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DropCpuElementLabel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        private CacheLevel destringifyLevel(String str){
            String []parts=str.split("#");
            ReplacementPolicy rp;
            switch(parts[0]){
                case "BitPLRU":
                    rp=ReplacementPolicy.BitPLRU;
                    break;
                case "LRU":
                    rp=ReplacementPolicy.LRU;
                    break;
                default:
                    rp=ReplacementPolicy.FIFO;
            }
            int size=Integer.parseInt(parts[1]);
            int assoc=Integer.parseInt(parts[2]);
            int lw=Integer.parseInt(parts[3]);
            String tag = parts[4].replaceAll("_tarabaImaseTuka_", "#");
            return new CacheLevelImpl(tag, rp, size, assoc, lw);
        }
    }
}
