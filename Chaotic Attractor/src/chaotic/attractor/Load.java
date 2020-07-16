/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaotic.attractor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class opens txt file with parameters
 * @author Anna Petráková
 */
public class Load {
    private JTextField filename = new JTextField();
    private JTextField dir = new JTextField();
    private chaoticForm ch;
    
    public Load(chaoticForm chaotic) {
        this.ch = chaotic;
        
        JFileChooser load = new JFileChooser();
        load.setAcceptAllFileFilterUsed(false);
        load.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
        
        int value = load.showOpenDialog(null);
        if (value == JFileChooser.APPROVE_OPTION) {
            filename.setText(load.getSelectedFile().getName());
            dir.setText(load.getCurrentDirectory().toString());
            load();
        }
        if (value == JFileChooser.CANCEL_OPTION) {
            filename.setText("");
            dir.setText("");
        }    
    }
    
    public void load() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(dir.getText()+"\\"+filename.getText())))) {

            ch.setDecimals((parseLine(br.readLine()))[1]);
            String s = parseLine(br.readLine())[1];
            if ("Pickover".equals(s)) {
                ch.setSelectedPickover(true);
            } else if ("Lorenz".equals(s)) {
                ch.setSelectedLorenz(true);
            } else if ("Jong".equals(s)) {
                ch.setSelectedJong(true);
            } else if ("Henon".equals(s)) {
                ch.setSelectedHenon(true);
            } else {
                ch.setSelectedPolynom(true);
            }
            ch.setValueA(Double.parseDouble((parseLine(br.readLine()))[1]));
            ch.setValueB(Double.parseDouble((parseLine(br.readLine()))[1]));
            ch.setValueC(Double.parseDouble((parseLine(br.readLine()))[1]));
            ch.setValueD(Double.parseDouble((parseLine(br.readLine()))[1]));
            ch.setIterations((parseLine(br.readLine()))[1]);
            ch.setWidthField((parseLine(br.readLine()))[1]);
            ch.setHeightField((parseLine(br.readLine()))[1]);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String[] parseLine(String line) {
        String[] fields = line.split(" ");
        
        if (fields.length != 2) {
            return null;
        }
        
        return fields;       
    }
}
