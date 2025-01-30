package com.epam.jwd.model;

import java.util.Objects;

public class Dot {
    private double x;
    private double y;
    private double z;

    public Dot(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }


    public double getY() {
        return y;
    }


    public double getZ() {
        return z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }


    @Override
    public String toString() {
        return "Dot{" + "X: " + x + " Y: " + y + " Z: " + z + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Dot dot = (Dot) obj;
        return x == dot.x && y == dot.y && z == dot.z;
    }


}
