
package edu.fcse.cachesim.gui.utils;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ATFileFilter extends FileFilter{

    @Override
    public boolean accept(File f) {
        if(f.isDirectory()){
            return true;
        }
        return f.getName().endsWith(".atf");
    }

    @Override
    public String getDescription() {
        return "Address Trace files (*.atf)";
    }
    
}
