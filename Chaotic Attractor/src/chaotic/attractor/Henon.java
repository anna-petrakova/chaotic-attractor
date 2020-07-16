/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaotic.attractor;

import java.awt.Color;

/**
 *
 * @author Anna Petráková
 */
public class Henon extends Draw {
    private double aParameter;
    private double bParameter;
    
    public Henon(int w, int h, double x, double y, double z, int i, Color[] colors,double a, double b) {
        super(w,h,x,y,z,i,colors);
        this.aParameter = a;
        this.bParameter = b;
    }
    
    @Override
    public Pair function(double x, double y, double z) {
        double x1 = 1+aParameter*(x*x)+bParameter*y;
        double y1 = x;
        return (new Pair(x1,y1,z));
    }
    
    
}
