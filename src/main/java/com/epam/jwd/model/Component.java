package com.epam.jwd.model;

import java.util.List;

public interface Component  {

    void add(Component component);

    List<Component> getChild();

    boolean hasChild();

    String getName();

}
