package com.epam.jwd.observer;

import com.epam.jwd.model.Plane;

public interface Subscriber {
    void planeToUpdate(Plane plane);

    void planeToDelete(Integer id);

    void planeToAdd(Plane plane);

    void callToClear();

}
