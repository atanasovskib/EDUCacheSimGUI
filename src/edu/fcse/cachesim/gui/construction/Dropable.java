/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fcse.cachesim.gui.construction;

import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.openmbean.InvalidOpenTypeException;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author Atanasovski
 */
public class Dropable extends JLabel {

    public Dropable(String s) {
        this.setText(s);
        this.dtListener = new DTListenerImpl();
        this.dropTarget = new DropTarget(this, this.acceptableActions, this.dtListener, true);
    }
    private DropTarget dropTarget;
    private DropTargetListener dtListener;
    private int acceptableActions = DnDConstants.ACTION_COPY;

    class DTListenerImpl implements DropTargetListener {

        @Override
        public void dragEnter(DropTargetDragEvent dtde) {
            if (isDragOk(dtde) == false) {
                dtde.rejectDrag();
            }
            Dropable.this.setBorder(BorderFactory.createLineBorder(Color.green));
            dtde.acceptDrag(Dropable.this.acceptableActions);
        }

        @Override
        public void dragOver(DropTargetDragEvent dtde) {
            if (isDragOk(dtde) == false) {
                dtde.rejectDrag();
                return;
            }
            dtde.acceptDrag(Dropable.this.acceptableActions);
        }

        @Override
        public void dropActionChanged(DropTargetDragEvent dtde) {
            if (isDragOk(dtde) == false) {
                dtde.rejectDrag();
                return;
            }
            dtde.acceptDrag(Dropable.this.acceptableActions);
        }

        @Override
        public void dragExit(DropTargetEvent dte) {
            Dropable.this.setBorder(BorderFactory.createEmptyBorder());
        }

        @Override
        public void drop(DropTargetDropEvent dtde) {
            try {
                Transferable ss =  dtde.getTransferable();
                DataFlavor[] flavors=ss.getTransferDataFlavors();
                System.out.println(Arrays.toString(flavors));
                String sk=(String)ss.getTransferData(flavors[0]);
                Dropable.this.setBackground(Color.lightGray);
                Dropable.this.setText(sk);
                Dropable.this.setBorder(BorderFactory.createEmptyBorder());
            } catch (UnsupportedFlavorException ex) {
                Logger.getLogger(Dropable.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Dropable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private boolean isDragOk(DropTargetDragEvent e) {
            return true;
        }
    }
}
