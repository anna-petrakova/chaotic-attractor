/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaotic.attractor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Class for UI.
 * @author Anna Petráková
 */
public class chaoticForm extends javax.swing.JFrame {
    private double valueA = 0;
    private double valueB = 0;
    private double valueC = 0;
    private double valueD = 0;
    private int divider = 100;//decimals
    private SpinnerNumberModel modelA;
    private SpinnerNumberModel modelB;
    private SpinnerNumberModel modelC;
    private SpinnerNumberModel modelD;
    private JPanel drawArea;
    private int iter = 500000;
    private int width = 1080;
    private int height = 900;  
    
    private ArrayList<Pair> points = new ArrayList<Pair>();
    private double x1;
    private double y1;
    private double z1;
    private double x = 0.0;
    private double y = 0.0;
    private double z = 0.0;
    private Pair pair;
        
    /**
     * Creates new form chaoticForm
     */
    public chaoticForm() {
        this.setTitle("ChaoGen");
        initComponents();
        this.setResizable(false);
        sliderA.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                valueA = sliderA.getValue();
                valueA = valueA/divider;
                aParameter.setText("a: " + valueA);
                spinnerA.setValue(valueA);
                repaint();
            }
        });
        sliderB.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                valueB = sliderB.getValue();
                valueB = valueB/divider;
                bParameter.setText("b: " + valueB);
                spinnerB.setValue(valueB);
                repaint();
            }
        });
        sliderC.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                valueC = sliderC.getValue();
                valueC = valueC/divider;
                cParameter.setText("c: " + valueC);
                spinnerC.setValue(valueC);
                repaint();
            }
        });
        sliderD.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                valueD = sliderD.getValue();
                valueD = valueD/divider;
                dParameter.setText("d: " + valueD);
                spinnerD.setValue(valueD);
                repaint();
            }
        });
        
        setSliders();
        setSpinners(); 
        
        spinnerA.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Double v = (((double) spinnerA.getValue())*divider);
                sliderA.setValue(v.intValue());
               
            }
        });
        spinnerB.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Double v = (((double) spinnerB.getValue())*divider);
                sliderB.setValue(v.intValue());
            }
        });
        spinnerC.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Double v = (((double) spinnerC.getValue())*divider);
                sliderC.setValue(v.intValue());
            }
        });
        spinnerD.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Double v = (((double) spinnerD.getValue())*divider);
                sliderD.setValue(v.intValue());
            }
        });
        
        gradient1.setVisible(false);
        gradient21.setVisible(false);
        gradient22.setVisible(false);
        // Area for simple attractor preview
        drawArea = new DrawSimply();
        drawArea.setBounds(10, 440, 665, 390);
        drawArea.setBackground(Color.WHITE);
        drawArea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        this.add(drawArea);
        
    }
    //Getters and Setters
    public double getValueA() {
        return valueA;
    }
    public void setValueA(double a) {
        this.valueA = a;
        Double v = ((double) a*divider);
        sliderA.setValue(v.intValue());
    }
    public double getValueB() {
        return valueB;
    }
    public void setValueB(double b) {
        this.valueB = b;
        Double v = ((double) b*divider);
        sliderB.setValue(v.intValue());
    }
    public double getValueC() {
        return valueC;
    }
    public void setValueC(double c) {
        this.valueC = c;
        Double v = ((double) c*divider);
        sliderC.setValue(v.intValue());
    }
    public double getValueD() {
        return valueD;
    }
    public void setValueD(double d) {
        this.valueD = d;
        Double v = ((double) d*divider);
        sliderD.setValue(v.intValue());
    }
    public boolean selectedLorenz() {
        return optionLorenz.isSelected();
    }
    public void setSelectedLorenz(boolean b) {
        optionLorenz.setSelected(b);
        enableSliders();
        setSliders(); 
        enableSpinners();
        setSpinners();
    }
    public boolean selectedHenon() {
        return optionHenon.isSelected();
    }
    public void setSelectedHenon(boolean b) {
        optionHenon.setSelected(b);
        setSliders();
        setSpinners();
        
        sliderC.setEnabled(false);
        sliderD.setEnabled(false);
        
        spinnerC.setEnabled(false);
        spinnerD.setEnabled(false);
    }
    public boolean selectedPickover() {
        return optionPickover.isSelected();
    }
    public void setSelectedPickover(boolean b) {
        optionPickover.setSelected(b);
        enableSliders();
        setSliders();
        enableSpinners();
        setSpinners();
    }
    public boolean selectedJong() {
        return optionJong.isSelected();
    }
    public void setSelectedJong(boolean b) {
        optionJong.setSelected(b);
        enableSliders();
        setSliders();
        enableSpinners();
        setSpinners();
    }
    public boolean selectedPolynom() {
        return optionPolynom.isSelected();
    }
    public void setSelectedPolynom(boolean b) {
        optionPolynom.setSelected(b);
        setSliders();
        setSpinners();
        
        sliderC.setEnabled(true);
        sliderD.setEnabled(false);
        
        spinnerC.setEnabled(true);
        spinnerD.setEnabled(false);
    }
    public String getIterations() {
        return iterationsField.getText();
    }
    public void setIterations(String s) {
        try {
            iter = Integer.parseInt(s);
        } 
        catch (Exception e){
            JOptionPane.showMessageDialog(this, "bad iterations", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (!(iter > 10000000)) {
            iterationsField.setText(s);
        } else {
            iter = 500000;
            iterationsField.setText("500000");
        }
    }
    public String getWidthField() {
        return widthField.getText();
    }
    public void setWidthField(String s) {
        try {
            width = Integer.parseInt(s);
        } 
        catch (Exception e){
            JOptionPane.showMessageDialog(this, "bad width", "Error", JOptionPane.ERROR_MESSAGE);
        }
        widthField.setText(s);
    }
    public String getHeightField() {
        return heightField.getText();
    }
    public void setHeightField(String s) {
        try {
            height = Integer.parseInt(s);
        } 
        catch (Exception e){
            JOptionPane.showMessageDialog(this, "bad height", "Error", JOptionPane.ERROR_MESSAGE);
        }
        heightField.setText(s);
    }
    public int getDecimals() {
        return (int) decimals.getValue();
    }
    public void setDecimals(String d) {
        try {
            decimals.setValue(Integer.parseInt(d));
        } 
        catch (Exception e){
            JOptionPane.showMessageDialog(this, "bad height", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        typeGroup = new javax.swing.ButtonGroup();
        colorGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        optionPickover = new javax.swing.JRadioButton();
        optionJong = new javax.swing.JRadioButton();
        optionLorenz = new javax.swing.JRadioButton();
        optionHenon = new javax.swing.JRadioButton();
        optionPolynom = new javax.swing.JRadioButton();
        sliderA = new javax.swing.JSlider();
        aParameter = new javax.swing.JLabel();
        spinnerA = new javax.swing.JSpinner();
        sliderB = new javax.swing.JSlider();
        bParameter = new javax.swing.JLabel();
        spinnerB = new javax.swing.JSpinner();
        sliderC = new javax.swing.JSlider();
        cParameter = new javax.swing.JLabel();
        spinnerC = new javax.swing.JSpinner();
        sliderD = new javax.swing.JSlider();
        dParameter = new javax.swing.JLabel();
        spinnerD = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        decimals = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        iterationsField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        widthField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        heightField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        simpleColor = new javax.swing.JRadioButton();
        foregroundColorButton = new javax.swing.JButton();
        gradientColor = new javax.swing.JRadioButton();
        gradient1 = new javax.swing.JButton();
        gradient2Color = new javax.swing.JRadioButton();
        gradient21 = new javax.swing.JButton();
        gradient22 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        backgroundColorButton = new javax.swing.JButton();
        generateButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        resetMenuItem = new javax.swing.JMenuItem();
        aboutMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Type:");

        typeGroup.add(optionPickover);
        optionPickover.setSelected(true);
        optionPickover.setText("Pickover");
        optionPickover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionPickoverActionPerformed(evt);
            }
        });

        typeGroup.add(optionJong);
        optionJong.setText("Peter de Jong");
        optionJong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionJongActionPerformed(evt);
            }
        });

        typeGroup.add(optionLorenz);
        optionLorenz.setText("Lorenz");
        optionLorenz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionLorenzActionPerformed(evt);
            }
        });

        typeGroup.add(optionHenon);
        optionHenon.setText("Henon");
        optionHenon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionHenonActionPerformed(evt);
            }
        });

        typeGroup.add(optionPolynom);
        optionPolynom.setText("Polynomial A");
        optionPolynom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionPolynomActionPerformed(evt);
            }
        });

        sliderA.setMaximum(4);
        sliderA.setMinimum(-4);
        sliderA.setValue(0);

        aParameter.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        aParameter.setText("a: 0");

        sliderB.setMaximum(4);
        sliderB.setMinimum(-4);
        sliderB.setValue(0);

        bParameter.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bParameter.setText("b: 0");

        sliderC.setMaximum(4);
        sliderC.setMinimum(-4);
        sliderC.setValue(0);

        cParameter.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cParameter.setText("c: 0");

        sliderD.setMaximum(4);
        sliderD.setMinimum(-4);
        sliderD.setValue(0);

        dParameter.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dParameter.setText("d: 0");

        jLabel9.setText("Number of decimals:");

        decimals.setModel(new javax.swing.SpinnerNumberModel(2, 2, 4, 1));
        decimals.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                decimalsStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(optionHenon)
                                    .addComponent(optionPolynom))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(decimals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(158, 158, 158))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(sliderD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(optionJong)
                                    .addComponent(optionPickover)
                                    .addComponent(optionLorenz))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sliderA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sliderB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sliderC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cParameter, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerC))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(aParameter, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerA, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bParameter, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerB))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(dParameter, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerD, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(optionPickover)
                    .addComponent(sliderA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(aParameter)
                        .addComponent(spinnerA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(optionJong)
                    .addComponent(sliderB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bParameter)
                        .addComponent(spinnerB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(optionLorenz)
                    .addComponent(sliderC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cParameter)
                        .addComponent(spinnerC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(optionHenon)
                    .addComponent(sliderD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dParameter)
                        .addComponent(spinnerD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(optionPolynom)
                    .addComponent(jLabel9)
                    .addComponent(decimals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("View:");

        jLabel3.setText("Iterations:");

        iterationsField.setText("500000");

        jLabel4.setText("Resolution:");

        widthField.setText("1080");

        jLabel5.setText("x");

        heightField.setText("900");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(iterationsField, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(widthField, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(heightField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(iterationsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(widthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(heightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Color:");

        colorGroup.add(simpleColor);
        simpleColor.setSelected(true);
        simpleColor.setText("Simple Color");
        simpleColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpleColorActionPerformed(evt);
            }
        });

        foregroundColorButton.setBackground(new java.awt.Color(0, 0, 0));
        foregroundColorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foregroundColorButtonActionPerformed(evt);
            }
        });

        colorGroup.add(gradientColor);
        gradientColor.setText("Gradient 1");
        gradientColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradientColorActionPerformed(evt);
            }
        });

        gradient1.setBackground(new java.awt.Color(255, 255, 255));
        gradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradient1ActionPerformed(evt);
            }
        });

        colorGroup.add(gradient2Color);
        gradient2Color.setText("Gradient 2");
        gradient2Color.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradient2ColorActionPerformed(evt);
            }
        });

        gradient21.setBackground(new java.awt.Color(255, 255, 255));
        gradient21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradient21ActionPerformed(evt);
            }
        });

        gradient22.setBackground(new java.awt.Color(0, 0, 0));
        gradient22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradient22ActionPerformed(evt);
            }
        });

        jLabel8.setText("Background:");

        backgroundColorButton.setBackground(new java.awt.Color(255, 255, 255));
        backgroundColorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backgroundColorButtonActionPerformed(evt);
            }
        });

        generateButton.setText("Generate");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(gradient2Color)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(gradient21))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(gradientColor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(gradient1))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(simpleColor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(foregroundColorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gradient22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(generateButton))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(backgroundColorButton)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(simpleColor)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel8)
                        .addComponent(foregroundColorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(backgroundColorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gradientColor)
                            .addComponent(gradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(gradient2Color, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(13, 13, 13))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(gradient22, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(gradient21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        fileMenu.setText("File");

        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        resetMenuItem.setText("Reset");
        resetMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(resetMenuItem);

        jMenuBar1.add(fileMenu);

        aboutMenu.setText("About");

        aboutMenuItem.setText("About program");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        aboutMenu.add(aboutMenuItem);

        jMenuBar1.add(aboutMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(395, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void foregroundColorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foregroundColorButtonActionPerformed
        colorSetter(foregroundColorButton, "Simple color");
    }//GEN-LAST:event_foregroundColorButtonActionPerformed

    private void backgroundColorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backgroundColorButtonActionPerformed
        colorSetter(backgroundColorButton, "Background color");
    }//GEN-LAST:event_backgroundColorButtonActionPerformed

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
               
        setIterations(this.iterationsField.getText());
        setWidthField(this.widthField.getText());
        setHeightField(this.heightField.getText());
        
        Color bColor = backgroundColorButton.getBackground();
        
        Color[] colors;
              
        if (simpleColor.isSelected()) {
            colors = new Color[] {bColor, foregroundColorButton.getBackground(), null, null, null};
        } else if (gradientColor.isSelected()) {
            colors = new Color[] {bColor, null, gradient1.getBackground(), null, null};
        } else {
            colors = new Color[] {bColor, null, null, gradient21.getBackground(), gradient22.getBackground()};
        }
        
        if (optionPickover.isSelected()) {
            new Pickover(width, height, 0,0,0, iter, colors, valueA, valueB, valueC, valueD);
        } else if (optionLorenz.isSelected()) {
            new Lorenz(width, height, 1,0,0,iter, colors, valueA, valueB, valueC, valueD);
        } else if (optionJong.isSelected()) {
            new PeterDeJong(width, height, 0,0,0, iter, colors, valueA, valueB, valueC, valueD);
        } else if (optionHenon.isSelected()) {
            new Henon(width, height, 0,0,0, iter, colors, valueA, valueB);
        } else if (optionPolynom.isSelected()) {
            new PolynomialA(width, height, 0,0,0, iter, colors, valueA, valueB, valueC);
        }
    }//GEN-LAST:event_generateButtonActionPerformed

    private void optionPickoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionPickoverActionPerformed
        enableSliders();
        setSliders();
        enableSpinners();
        setSpinners();
    }//GEN-LAST:event_optionPickoverActionPerformed

    private void optionJongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionJongActionPerformed
        enableSliders();
        setSliders();
        enableSpinners();
        setSpinners();
    }//GEN-LAST:event_optionJongActionPerformed

    private void optionLorenzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionLorenzActionPerformed
        enableSliders();
        setSliders(); 
        enableSpinners();
        setSpinners();
    }//GEN-LAST:event_optionLorenzActionPerformed

    private void optionHenonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionHenonActionPerformed
        setSliders();
        setSpinners();
        
        sliderC.setEnabled(false);
        sliderD.setEnabled(false);
        
        spinnerC.setEnabled(false);
        spinnerD.setEnabled(false);
    }//GEN-LAST:event_optionHenonActionPerformed

    private void optionPolynomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionPolynomActionPerformed
        setSliders();
        setSpinners();
        
        sliderC.setEnabled(true);
        sliderD.setEnabled(false);
        
        spinnerC.setEnabled(true);
        spinnerD.setEnabled(false);
    }//GEN-LAST:event_optionPolynomActionPerformed

    private void decimalsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_decimalsStateChanged
        int value = (Integer) decimals.getValue();
        if (value == 2) {
            divider = 100;
        } else if (value == 3) {
            divider = 1000;
        } else if (value == 4) {
            divider = 10000;
        }
        setSliders();
        setSpinners();
    }//GEN-LAST:event_decimalsStateChanged

    private void gradientColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradientColorActionPerformed
        foregroundColorButton.setVisible(false);
        gradient1.setVisible(true);
        gradient21.setVisible(false);
        gradient22.setVisible(false);
    }//GEN-LAST:event_gradientColorActionPerformed

    private void gradient2ColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradient2ColorActionPerformed
        foregroundColorButton.setVisible(false);
        gradient1.setVisible(false);
        gradient21.setVisible(true);
        gradient22.setVisible(true);
    }//GEN-LAST:event_gradient2ColorActionPerformed

    private void simpleColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpleColorActionPerformed
        foregroundColorButton.setVisible(true);
        gradient1.setVisible(false);
        gradient21.setVisible(false);
        gradient22.setVisible(false);
    }//GEN-LAST:event_simpleColorActionPerformed

    private void gradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradient1ActionPerformed
        colorSetter(gradient1, "Simple gradient color");
    }//GEN-LAST:event_gradient1ActionPerformed

    private void gradient21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradient21ActionPerformed
        colorSetter(gradient21, "First gradient color");
    }//GEN-LAST:event_gradient21ActionPerformed

    private void gradient22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradient22ActionPerformed
        colorSetter(gradient22, "Second gradient color");
    }//GEN-LAST:event_gradient22ActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        About a = new About();
        a.setVisible(true);    
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void resetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetMenuItemActionPerformed
        optionPickover.setSelected(true);
        this.sliderA.setValue(0);
        this.sliderB.setValue(0);
        this.sliderC.setValue(0);
        this.sliderD.setValue(0);
        this.iterationsField.setText("500000");
        this.iter = 500000;
        this.widthField.setText("1080");
        this.width = 1080;
        this.heightField.setText("900");
        this.height = 900;
        this.simpleColor.setSelected(true);
        this.foregroundColorButton.setBackground(Color.BLACK);
        this.backgroundColorButton.setBackground(Color.WHITE);
        this.gradient1.setBackground(Color.BLACK);
        this.gradient21.setBackground(Color.WHITE);
        this.gradient22.setBackground(Color.BLACK);
        this.decimals.setValue(2);
        this.divider = 100;
        this.foregroundColorButton.setVisible(true);
        this.gradient1.setVisible(false);
        this.gradient21.setVisible(false);
        this.gradient22.setVisible(false);        
    }//GEN-LAST:event_resetMenuItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        new Save(this);    
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        new Load(this);    
    }//GEN-LAST:event_openMenuItemActionPerformed
    // Draw very simple chaotic attractor with 30 000 iterations and black color
    private class DrawSimply extends JPanel {
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            Graphics2D graph2 = (Graphics2D) g;
            graph2.setPaint(Color.BLACK);
                       
            Point2D point = new Point.Double();
            Shape drawPoint;
                        
            if (optionLorenz.isSelected()) {
                x = 1.0;
                y = 0.0;
                z = 0.0;
            } else {
                x = 0.0;
                y = 0.0;
                z = 0.0;
            }
            double minX = x;
            double maxX = x;
            double minY = y;
            double maxY = y;
            
                        
            for (int i = 0; i < 30000; i+=1) {
                if (optionPickover.isSelected()) {
                    pair = functionPickover(x,y,z);
                } else if (optionLorenz.isSelected()) {
                    pair = functionLorenz(x,y,z);
                } else if (optionJong.isSelected()) {
                    pair = functionJong(x,y,z);
                } else if (optionHenon.isSelected()) {
                    pair = functionHenon(x,y,z);
                } else {
                    pair = functionPolynomial(x,y,z);
                }
                
                points.add(pair);
                x1 = pair.getX();
                y1 = pair.getY();
                z1 = pair.getZ();
                
                minX = Math.min(minX, x1);
                maxX = Math.max(maxX, x1);
                minY = Math.min(minY, y1);
                maxY = Math.max(maxY, y1);
                
                x = x1;
                y = y1;
                z = z1;
            }
            
            double maximumHeight = (390)/(Math.abs(minY)+Math.abs(maxY));//coefficient in linear stretching for height
            double maximumWidth = (665)/(Math.abs(minX)+Math.abs(maxX)); //coefficient in linear stretching for width
            double finalMaximum = Math.min(maximumHeight, maximumWidth);
            
            for (int i = 0; i < points.size(); i++) {
                pair = points.get(i);
                pair.setX((pair.getX() + Math.abs(minX))*finalMaximum);
                pair.setY((pair.getY() + Math.abs(minY))*finalMaximum);
                point.setLocation(pair.getX(), pair.getY());
                drawPoint = new Line2D.Double(point,point);
                graph2.draw(drawPoint);
            }
                        
            points.clear();            
        }
    }
    
    protected void colorSetter(javax.swing.JButton button, String s) {
        Color initialBackground = button.getBackground();
        Color background = JColorChooser.showDialog(null, s,initialBackground);
        button.setBackground(background);
    }
    
    protected void enableSpinners() {
        spinnerC.setEnabled(true);
        spinnerD.setEnabled(true);
    }
    protected void setSpinners() {
        double number = 4.0;//range in spinner
        BigDecimal number2 = new BigDecimal((double) 1/divider);//step in spinner
        if (optionLorenz.isSelected()) {
            number = 20.0;
        }
               
        number2 = number2.setScale(4, RoundingMode.DOWN);
        
        modelA = new SpinnerNumberModel(0.0,-number ,number,number2);
        modelB = new SpinnerNumberModel(0.0,-number ,number,number2);
        modelC = new SpinnerNumberModel(0.0,-number ,number,number2);
        modelD = new SpinnerNumberModel(0.0,-number ,number,number2);
        
        spinnerA.setModel(modelA);
        spinnerB.setModel(modelB);
        spinnerC.setModel(modelC);
        spinnerD.setModel(modelD);
        
    }
    
    protected void enableSliders() {
        sliderC.setEnabled(true);
        sliderD.setEnabled(true);
    }
    
    protected void setSliders() {
        int number = 4;//range in slider
        if (sliderA.getMaximum() > 5*divider) {//if the last chosen type was Lorenz
            sliderA.setValue(0);
            sliderB.setValue(0);
            sliderC.setValue(0);
            sliderD.setValue(0);
        }
        
        if (optionLorenz.isSelected()) {
            number = 20;
        }
        
        sliderA.setMaximum(number*divider);
        sliderA.setMinimum(-number*divider);
        sliderB.setMaximum(number*divider);
        sliderB.setMinimum(-number*divider);
        sliderC.setMaximum(number*divider);
        sliderC.setMinimum(-number*divider);
        sliderD.setMaximum(number*divider);
        sliderD.setMinimum(-number*divider);                
    }
    
    public Pair functionPickover(double x, double y, double z) {
        x1 = Math.sin(valueA*y)-z*Math.cos(valueB*x);
        y1 = z*Math.sin(valueC*x) - Math.cos(valueD*y);
        z1 = Math.sin(x);
        return (new Pair(x1,y1,z1));
    }
    
    public Pair functionLorenz(double x, double y, double z) {
        x1 = x+valueA*valueD*(y-x);
        y1 = y+valueD*(valueB*x-y-z*x);
        z1 = z+valueD*(x*y-valueC*z);
        return (new Pair(x1,y1,z1));
    }
    
    public Pair functionJong(double x, double y,double z) {
        x1 = Math.sin(valueA*y) - Math.cos(valueB*x);
        y1 = Math.sin(valueC*x) - Math.cos(valueD*y);
        z1 = 0;
        return (new Pair(x1,y1,z1));
    }
    
    public Pair functionHenon(double x, double y, double z) {
        x1 = 1+valueA*(x*x)+valueB*y;
        y1 = x;
        return (new Pair(x1,y1,z));
    }
    
    public Pair functionPolynomial(double x, double y, double z) {
        x1 = valueA + y - z*y;
        y1 = valueB+z-x*z;
        z1 = valueC+x-y*x;
        return (new Pair(x1,y1,z1));
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(chaoticForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chaoticForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chaoticForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chaoticForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chaoticForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aParameter;
    private javax.swing.JMenu aboutMenu;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JLabel bParameter;
    private javax.swing.JButton backgroundColorButton;
    private javax.swing.JLabel cParameter;
    private javax.swing.ButtonGroup colorGroup;
    private javax.swing.JLabel dParameter;
    private javax.swing.JSpinner decimals;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton foregroundColorButton;
    private javax.swing.JButton generateButton;
    private javax.swing.JButton gradient1;
    private javax.swing.JButton gradient21;
    private javax.swing.JButton gradient22;
    private javax.swing.JRadioButton gradient2Color;
    private javax.swing.JRadioButton gradientColor;
    private javax.swing.JTextField heightField;
    private javax.swing.JTextField iterationsField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JRadioButton optionHenon;
    private javax.swing.JRadioButton optionJong;
    private javax.swing.JRadioButton optionLorenz;
    private javax.swing.JRadioButton optionPickover;
    private javax.swing.JRadioButton optionPolynom;
    private javax.swing.JMenuItem resetMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JRadioButton simpleColor;
    private javax.swing.JSlider sliderA;
    private javax.swing.JSlider sliderB;
    private javax.swing.JSlider sliderC;
    private javax.swing.JSlider sliderD;
    private javax.swing.JSpinner spinnerA;
    private javax.swing.JSpinner spinnerB;
    private javax.swing.JSpinner spinnerC;
    private javax.swing.JSpinner spinnerD;
    private javax.swing.ButtonGroup typeGroup;
    private javax.swing.JTextField widthField;
    // End of variables declaration//GEN-END:variables
}
