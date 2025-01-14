package com.epam.jwd.model;
public class Dot{
     private double x;
     private double y;
     private double z;

     public Dot(double x, double y, double z){
         this.x = x;
         this.y = y;
         this.z = z;
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

     @Override
    public String toString(){
         return "Dot{" + "X: " + x + " Y: " + y + " Z: " + z + "}";
     }

     @Override
    public boolean equals(Object obj){
         if (this == obj) {return true;}
         if (obj == null || getClass() !=obj.getClass()) {return false;}
         Dot dot = (Dot) obj;
         return x == dot.x && y == dot.y && z == dot.z;
     }

    
}