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
public class PolynomialA extends Draw {
    private double aParameter;
    private double bParameter;
    private double cParameter;

    public PolynomialA(int w, int h, double x, double y, double z, int i, Color[] colors,double a, double b, double c) {
        super(w,h,x,y,z,i,colors);
        this.aParameter = a;
        this.bParameter = b;
        this.cParameter = c;
    }
    
    @Override
    public Pair function(double x, double y, double z) {
        double x1 = aParameter + y - z*y;
        double y1 = bParameter+z-x*z;
        double z1 = cParameter+x-y*x;
        return (new Pair(x1,y1,z1));
    }
    
}
