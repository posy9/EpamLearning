package com.epam.jwd.context;

import com.epam.jwd.model.Dot;

import java.util.Objects;

public class FormContext implements FigureContext {

    private final String formName;
    private final Dot a;
    private final Dot b;
    private final Dot c;

    FormContext(String formName, Dot a, Dot b, Dot c) {
        this.formName = formName;
        this.a = a;
        this.b = b;
        this.c = c;

    }

    @Override
    public String getFigureName() {
        return formName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormContext that = (FormContext) o;
        return Objects.equals(formName, that.formName)
                && Objects.equals(a, that.a)
                && Objects.equals(b, that.b)
                && Objects.equals(c, that.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formName, a, b, c);
    }

    @Override
    public String toString() {
        return "FormContext{" +
                "formName='" + formName + '\'' +
                ", a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
