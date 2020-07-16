/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaotic.attractor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class that saves txt file with parameters
 * @author Anna Petráková
 */
public class Save {
    private JTextField filename = new JTextField();
    private JTextField dir = new JTextField();
    private chaoticForm ch;
         
    public Save(chaoticForm chaotic) {
        this.ch = chaotic;
        
        JFileChooser save = new JFileChooser();
        save.setAcceptAllFileFilterUsed(false);
        save.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
        
        int value = save.showSaveDialog(null);
        if (value == JFileChooser.APPROVE_OPTION) {
            filename.setText(save.getSelectedFile().getName());
            dir.setText(save.getCurrentDirectory().toString());
            save();
        }
        if (value == JFileChooser.CANCEL_OPTION) {
            filename.setText("");
            dir.setText("");
        }        
    }
    
    private void save() {
        BufferedWriter output = null;
        try {
            File file = new File(dir.getText()+"\\"+filename.getText()+".txt");
            output = new BufferedWriter(new FileWriter(file));
            output.write("Decimals " + ch.getDecimals());
            output.newLine();
            output.write("Type ");
            
            if (ch.selectedLorenz()) {
                output.write("Lorenz");
            } else if (ch.selectedHenon()) {
                output.write("Henon");
            } else if (ch.selectedJong()) {
                output.write("Jong");
            } else if (ch.selectedPickover()) {
                output.write("Pickover");
            } else  {
                output.write("Polynom");
            }
            output.newLine();
            output.write("ParameterA " + ch.getValueA());
            output.newLine();
            output.write("ParameterB " + ch.getValueB());
            output.newLine();
            output.write("ParameterC " + ch.getValueC());
            output.newLine();
            output.write("ParameterD " + ch.getValueD());
            output.newLine();
            output.write("Iterations " + ch.getIterations());
            output.newLine();
            output.write("Width " + ch.getWidthField());
            output.newLine();
            output.write("Height " + ch.getHeightField());
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
          if ( output != null ) {
              try {
                  output.close();
              } catch (IOException ex) {
                  Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
        }
    }
    
}
