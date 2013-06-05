
package edu.fcse.cachesim.gui.utils;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class CCFileFilter extends FileFilter{

    @Override
    public boolean accept(File f) {
        if(f.isDirectory()){
            return true;
        }
        return f.getName().endsWith(".ccf");
    }

    @Override
    public String getDescription() {
        return "Cache Configuration files (*.ccf)";
    }
    
}
