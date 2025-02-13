package com.epam.jwd.model;

import java.util.Objects;

public class Plane implements Figure {

    private Integer id;
    private final Dot a;
    private final Dot b;
    private final Dot c;

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
