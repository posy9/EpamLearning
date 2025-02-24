package com.epam.jwd.factory;

import com.epam.jwd.exception.IllegalNameOfComponentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentFactoryTest {

    @Test
    public void componentFactory_shouldThrowException_whenContextIsIncorrect() {
        String input = "WrongContext";
        ComponentFactory componentFactory = ComponentFactory.getInstance();
        assertThrows(IllegalNameOfComponentException.class, () -> componentFactory.newInstanceOf(input));
    }
}
