package com.epam.jwd.parser;

import com.epam.jwd.model.Component;

public interface Parser {

    Component handle(String text);

}
