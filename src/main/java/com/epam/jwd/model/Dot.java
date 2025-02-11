package com.epam.jwd.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Dot {
    private final BigDecimal x;
    private final BigDecimal y;
    private final BigDecimal z;

    public Dot(BigDecimal x, BigDecimal y, BigDecimal z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public BigDecimal getX() {
        return x;
    }


    public BigDecimal getY() {
        return y;
    }


    public BigDecimal getZ() {
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
        return Objects.equals(x, dot.x) && Objects.equals(y, dot.y) && Objects.equals(z, dot.z);
    }


}
