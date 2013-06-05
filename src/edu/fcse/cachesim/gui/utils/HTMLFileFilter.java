
package edu.fcse.cachesim.gui.utils;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class HTMLFileFilter extends FileFilter{

    @Override
    public boolean accept(File f) {
        if(f.isDirectory()){
            return true;
        }
        return f.getName().endsWith(".html") || f.getName().endsWith(".htm");
    }

    @Override
    public String getDescription() {
        return "Hyper Text Markup Language (*.html)";
    }
    
}
