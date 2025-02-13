package com.epam.jwd.observer;

import com.epam.jwd.model.Plane;

public interface Publisher {
    void notifyAboutPlaneDelete(Integer planeId);

    void notifyAboutNewPlane(Plane plane);

    void notifyAboutPlaneUpdate(Plane plane);

    void notifyAboutClear();
}
