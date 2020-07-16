/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaotic.attractor;

/**
 * Actually not a pair, but a triple
 * @author Anna Petráková
 */
public class Pair {
    private double x = 0.0;
    private double y = 0.0;
    private double z = 0.0;
    
    public Pair(double x, double y,double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Pair()
    {

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
    
    public String toString() {
        return "[" + x + " , " + y + " , " + z +"]";
    }
    
    
}
