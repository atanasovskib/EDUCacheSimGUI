
package edu.fcse.cachesim.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel(String fileName) {
       try {                
           File f=new File(fileName);
          image = ImageIO.read(f);
       } catch (IOException ex) {
            
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }

}