package com.epam.jwd.parser;

import com.epam.jwd.model.Component;

public interface Parser {

    void handle(Component component, String text);
}
