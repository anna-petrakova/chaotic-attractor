/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaotic.attractor;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class that saves the final output
 * @author Anna Petráková
 */
public class SaveImage {
    private JTextField filename = new JTextField();
    private JTextField dir = new JTextField();
    private String type;
    
    public SaveImage(Draw draw) {
        JFileChooser save = new JFileChooser();
        
        save.setAcceptAllFileFilterUsed(false);
        save.addChoosableFileFilter(new FileNameExtensionFilter("*.png", "png"));
        save.addChoosableFileFilter(new FileNameExtensionFilter("*.jpg", "jpg"));
        save.addChoosableFileFilter(new FileNameExtensionFilter("*.bmp", "bmp"));
                
        int value = save.showSaveDialog(null);
        if (value == JFileChooser.APPROVE_OPTION) {
            filename.setText(save.getSelectedFile().getName());
            dir.setText(save.getCurrentDirectory().toString());
            type = save.getFileFilter().getDescription();
            
            save(draw);
        }
        if (value == JFileChooser.CANCEL_OPTION) {
            filename.setText("");
            dir.setText("");
        }        
    }
    
    private void save(Draw draw) {
        try
        {
            BufferedImage image = new BufferedImage(draw.getWidth(), draw.getHeight(), BufferedImage.TYPE_INT_RGB);    
            image.setRGB(0, 0, draw.getWidth(), draw.getHeight(), draw.getPixels(), 0, draw.getWidth());
            
            if ("*.jpg".equals(type)) {
                ImageIO.write(image,"jpg", new File(dir.getText()+"\\"+filename.getText()+".jpg"));
            } else if ("*.png".equals(type)) {
                ImageIO.write(image,"png", new File(dir.getText()+"\\"+filename.getText()+".png"));
            } else {
                ImageIO.write(image,"bmp", new File(dir.getText()+"\\"+filename.getText()+".bmp"));
            }
        }
        catch(Exception exception)
        {
            Logger.getLogger(Draw.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
}
