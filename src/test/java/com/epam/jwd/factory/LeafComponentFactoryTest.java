package com.epam.jwd.factory;

import com.epam.jwd.exception.IllegalNameOfComponentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LeafComponentFactoryTest {

    @Test
    public void leafComponentFactory_shouldThrowException_whenContextIsIncorrect() {
        String input = "WrongContext";
        LeafComponentFactory leafComponentFactory = LeafComponentFactory.getInstance();
        assertThrows(IllegalNameOfComponentException.class, () -> leafComponentFactory.newInstanceOf(input));
    }
}
