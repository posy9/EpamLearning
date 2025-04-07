package by.bsu.detailstorage.registry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EntityNameRegistry {
    DETAIL("Detail"),
    DEVICE("Device");

    private final String entityName;
}
