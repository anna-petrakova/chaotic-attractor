/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaotic.attractor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Class that draws final attractor
 * @author Anna Petráková
 */
public abstract class Draw extends JFrame {
    private int width;
    private int height;
    private int iterations;
    private double x = 0;
    private double y = 0;
    private double z = 0;
    private Color fColor;
    private Color bColor = Color.BLACK;
    private Color gradient;
    private Color gradient1;
    private Color gradient2;
    ArrayList<Pair> points = new ArrayList<Pair>();//array list of all generated points
    private JMenuBar menuBar;
    private JMenuItem save;
    private int[] pixels;
        
    public Draw(int w, int h, double x, double y, double z, int iter, Color[] colors) {
        this.width = w;
        this.height = h;
        this.x = x;
        this.y = y;
        this.z = z;
        this.iterations = iter;
        bColor = colors[0];
        fColor = colors[1];
        gradient = colors[2];
        gradient1 = colors[3];
        gradient2 = colors[4];
        
        menuBar = new JMenuBar();
        save = new JMenuItem("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveItemActionPerformed(evt);
            }
        });
        
        menuBar.add(save);
        this.setJMenuBar(menuBar);
        this.setSize(width,height);
        this.setTitle("Preview");
        this.getContentPane().setBackground(bColor);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(new Draw.DrawStuff(), BorderLayout.CENTER);
        this.setVisible(true);
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int[] getPixels() {
        return pixels;
    }
        
    private class DrawStuff extends JComponent {
        public void paint(Graphics g) {
            Graphics2D graph2 = (Graphics2D) g;
            graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                                   
            Point2D point = new Point.Double();
            Shape drawPoint;
            double x1;
            double y1;
            double z1;
            double minX = x;
            double maxX = x;
            double minY = y;
            double maxY = y;
            Pair pair;
            int[][] zasah = new int[width][height];//number of times when one pixel was hit
            pixels = new int[width*height];
                        
            for (int i = 0; i < iterations; i+=1) {
                pair = function(x,y,z);
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
                        
            double maximumHeight = (height-20)/(Math.abs(minY)+Math.abs(maxY));
            double maximumWidth = (width-20)/(Math.abs(minX)+Math.abs(maxX));
            double finalMaximum = Math.min(maximumHeight, maximumWidth);
            int maxZasah = 0;//maximum number of times some pixel was hit
                       
            for (int i = 0; i < points.size(); i++) {
                pair = points.get(i);
                pair.setX((pair.getX() + Math.abs(minX))*finalMaximum);
                pair.setY((pair.getY() + Math.abs(minY))*finalMaximum);
                long x_1 = Math.round((float) pair.getX());//x and y coordinates of pixel the point hit
                long y_1 = Math.round((float) pair.getY());
                if (x_1 < width && y_1 < height) {
                    zasah[(int) x_1][(int) y_1] += 1;
                    if (zasah[(int) x_1][(int) y_1] > maxZasah) {
                        maxZasah = zasah[(int) x_1][(int) y_1];
                    }
                }
            }
            
            double percent = ((double) maxZasah/100)*50;
                        
            for (int i = 0; i < zasah.length; i++) {
                for (int j = 0; j < zasah[i].length; j++) {
                    Color c;
                    if (zasah[i][j] == 0) {
                        c = bColor;
                    } else {
                        if (fColor != null) {
                            c = fColor;
                        } else if (gradient != null) {
                            if (zasah[i][j] > (maxZasah-percent)) {
                                c = gradient;
                            } else {
                                c = blend(bColor, gradient, (float) zasah[i][j]/maxZasah);
                            }
                        } else {
                            if (zasah[i][j] > (maxZasah-percent)) {
                                c = gradient1;
                            } else {
                                c = blend(gradient2, gradient1, (float) zasah[i][j]/maxZasah);
                            }
                        }
                    }
                    
                    pixels[j*width+i] = c.getRGB();
                    
                    graph2.setPaint(c);
                    point.setLocation(i,j);
                    drawPoint = new Line2D.Double(point,point);
                    graph2.draw(drawPoint);
                }
            }         
        }
    }
       
    public Color blend(Color c1, Color c2, float ratio) {
        int red = (int)(c2.getRed() * (ratio+0.15) + c1.getRed() * (0.85 - ratio));
        int green = (int)(c2.getGreen() * (ratio+0.15) + c1.getGreen() * (0.85 - ratio));
        int blue = (int)(c2.getBlue() * (ratio+0.15) + c1.getBlue() * (0.85 - ratio));
        Color c = new Color(red, green, blue);
        return c;
    }
    
    private void saveItemActionPerformed(java.awt.event.ActionEvent evt) {
        new SaveImage(this);
    }
    
    public abstract Pair function(double x, double y, double z);
}
