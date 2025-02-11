package com.epam.jwd.model;

import java.util.Objects;
import java.util.concurrent.Flow;

public class Plane implements Figure {

    private Integer id;
    private Dot a;
    private Dot b;
    private Dot c;


    public Plane(Dot a, Dot b, Dot c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Plane(Integer id, Dot a, Dot b, Dot c) {
        this.id = id;
        this.a = a;
        this.b = b;
        this.c = c;
    }


    @Override
    public Dot getA() {
        return a;
    }

    @Override
    public Dot getB() {
        return b;
    }

    @Override
    public Dot getC() {
        return c;
    }

    public Integer getId() {
        return id;
    }


    public void setA(Dot a) {
        this.a = a;

    }

    public void setB(Dot b) {
        this.b = b;

    }

    public void setC(Dot c) {
        this.c = c;

    }


    public Plane withId(Integer id) {
        return new Plane(id, a, b, c);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return Objects.equals(a, plane.a) && Objects.equals(b, plane.b) && Objects.equals(c, plane.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    @Override
    public String toString() {
        return "Plane{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }


}
