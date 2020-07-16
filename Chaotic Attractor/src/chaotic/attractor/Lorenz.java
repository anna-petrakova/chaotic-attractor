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
public class Lorenz extends Draw {
    private double aParameter;
    private double bParameter;
    private double cParameter;
    private double dParameter;

    public Lorenz(int w, int h, double x, double y, double z, int i, Color[] colors,double a, double b, double c, double d) {
        super(w,h,x,y,z,i,colors);
        this.aParameter = a;
        this.bParameter = b;
        this.cParameter = c;
        this.dParameter = d;
    }   
    
    @Override
    public Pair function(double x, double y, double z) {
        double x1 = x+aParameter*dParameter*(y-x);
        double y1 = y+dParameter*(bParameter*x-y-z*x);
        double z1 = z+dParameter*(x*y-cParameter*z);
        return (new Pair(x1,y1,z1));
    }
    
}
