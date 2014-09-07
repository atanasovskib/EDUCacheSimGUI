/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fcse.cachesim.gui.construction;

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import javax.management.openmbean.InvalidOpenTypeException;
import javax.swing.JLabel;

/**
 *
 * @author Atanasovski
 */
public class Dragable extends JLabel {

    public Dragable(String s) {
        this.setText(s);
        this.dragSource = DragSource.getDefaultDragSource();
        this.dgListener = new DGListenerImpl();
        this.dsListener = new DSListenerImpl();
        this.dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY, this.dgListener);
    }
    private DragSource dragSource;
    private DragGestureListener dgListener;
    private DragSourceListener dsListener;

    class DGListenerImpl implements DragGestureListener {

        @Override
        public void dragGestureRecognized(DragGestureEvent dge) {
            try {
                Transferable transferable = new StringSelection(Dragable.this.getText());
                dge.startDrag(DragSource.DefaultCopyNoDrop, transferable, dsListener);
            } catch (InvalidOpenTypeException e) {
                System.err.println("tasak na DGListener drag gesture recognized" + e.getMessage());
            }
        }

    }

    class DSListenerImpl implements DragSourceListener {

        @Override
        public void dragEnter(DragSourceDragEvent dsde) {
            DragSourceContext context = dsde.getDragSourceContext();
            int myAction = dsde.getDropAction();
            if ((myAction & DnDConstants.ACTION_COPY) != 0) {
                context.setCursor(DragSource.DefaultCopyDrop);
            } else {
                context.setCursor(DragSource.DefaultCopyNoDrop);
            }
        }

        @Override
        public void dragOver(DragSourceDragEvent dsde) {
        }

        @Override
        public void dropActionChanged(DragSourceDragEvent dsde) {
        }

        @Override
        public void dragExit(DragSourceEvent dse) {
        }

        @Override
        public void dragDropEnd(DragSourceDropEvent dsde) {
            if (dsde.getDropSuccess() == false) {
            }
        }

    }
}
