package by.bsu.detailstorage.registry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EntityNameRegistry {
    DETAIL("Detail"),
    DEVICE("Device"),
    CATEGORY("Category"),
    TYPE("Type"),
    COUNTRY("Country"),
    BRAND("Brand"),
    USER("User"),
    ROLE("Role");

    private final String entityName;
}
