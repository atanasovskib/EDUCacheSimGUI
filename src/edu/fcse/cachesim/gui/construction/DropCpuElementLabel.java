/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fcse.cachesim.gui.construction;

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

public class DropCpuElementLabel extends JLabel {

    public DropCpuElementLabel(String s) {
        this.setText(s);
        DropTargetListener dtListener = new DTListenerImpl();
        this.dropTarget = new DropTarget(this, this.acceptableActions, dtListener, true);
    }
    private final DropTarget dropTarget;
    private final int acceptableActions = DnDConstants.ACTION_COPY;

    class DTListenerImpl implements DropTargetListener {

        @Override
        public void dragEnter(DropTargetDragEvent dtde) {
            DropCpuElementLabel.this.setBorder(BorderFactory.createLineBorder(Color.green));
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
            DropCpuElementLabel.this.setBorder(BorderFactory.createEmptyBorder());
        }

        @Override
        public void drop(DropTargetDropEvent dtde) {
            try {
                Transferable ss = dtde.getTransferable();
                DataFlavor[] flavors = ss.getTransferDataFlavors();
                System.out.println(Arrays.toString(flavors));
                String sk = (String) ss.getTransferData(flavors[0]);
                DropCpuElementLabel.this.setBackground(Color.lightGray);
                DropCpuElementLabel.this.setText(sk);
                DropCpuElementLabel.this.setBorder(BorderFactory.createEmptyBorder());
            } catch (UnsupportedFlavorException ex) {
                Logger.getLogger(DropCpuElementLabel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DropCpuElementLabel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
