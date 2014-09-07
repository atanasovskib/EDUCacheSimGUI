package edu.fcse.cachesim.gui.construction;

import com.sun.glass.ui.Cursor;
import com.sun.glass.ui.Pixels;
import edu.fcse.cachesim.interfaces.CacheLevel;
import java.awt.Dimension;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class CacheLevelGUIRepresentation extends JButton implements ActionListener{
    private final CacheLevel level;
    private final ConstructionJFrame2 frame;
    private DragSource dragSource;
    private DragGestureListener dragGestureListener;
    private DragSourceListener dragSourceListener;
    
    public CacheLevelGUIRepresentation(ConstructionJFrame2 frame,CacheLevel level){
        this.level = level;
        this.frame = frame;
        this.setText(level.getTag());
        this.setPreferredSize(new Dimension(100, 50));
        this.setVisible(true);
        this.addActionListener(this);
        this.dragSource = DragSource.getDefaultDragSource();
        this.dragGestureListener = new CacheLevelDragGestureListener();
        this.dragSourceListener = new CacheLevelDragSourceListener();
        //this.setCursor(java.awt.Cursor.getPredefinedCursor(Cursor.CURSOR_MOVE));
    }
    public CacheLevel getCacheLevel(){
        return level;
    }
    public String stringifyCacheLevel(){
        CacheLevel lvl = CacheLevelGUIRepresentation.this.getCacheLevel();
        StringBuilder sb = new StringBuilder();
        sb.append("rp:");
        sb.append(lvl.getRP());
        sb.append("#size:");
        sb.append(lvl.getSize());
        sb.append(("#assoc:"));
        sb.append(lvl.getAssoc());
        sb.append("#lw:");
        sb.append(lvl.getLineWidth());
        sb.append("#tag:");
        sb.append(lvl.getTag().replaceAll("#", "_tarabaImaseTuka_").replaceAll(":","_dveTockiImaseTuka_"));
        
        return sb.toString();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.showInfo(level.getTag());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CacheLevelGUIRepresentation){
            CacheLevelGUIRepresentation other =  (CacheLevelGUIRepresentation)obj;
            return this.level.getTag().equals(other.getCacheLevel().getTag());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return level.getTag().hashCode(); 
    }
    class CacheLevelDragGestureListener implements DragGestureListener{

        @Override
        public void dragGestureRecognized(DragGestureEvent dge) {
            String data = CacheLevelGUIRepresentation.this.stringifyCacheLevel();
            Transferable transferable = new StringSelection(data);
            dge.startDrag(DragSource.DefaultCopyNoDrop, transferable, dragSourceListener);
        }
        
    }
    class CacheLevelDragSourceListener implements DragSourceListener{

        @Override
        public void dragEnter(DragSourceDragEvent dsde) {
            DragSourceContext context = dsde.getDragSourceContext();
            int myAction = dsde.getDropAction();
            if((myAction & DnDConstants.ACTION_COPY) != 0){
                context.setCursor(DragSource.DefaultCopyDrop);
            }else{
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
        }
        
    }
}
