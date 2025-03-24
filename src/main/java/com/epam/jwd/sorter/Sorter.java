package com.epam.jwd.sorter;

import com.epam.jwd.model.Component;

public interface Sorter {

    String sort(SorterVariations variation, OrderVariations compareVariations, Component textComponent);

}
