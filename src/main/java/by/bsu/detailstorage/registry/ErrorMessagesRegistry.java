package by.bsu.detailstorage.registry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessagesRegistry {
    ENTITY_NOT_FOUND("%s with id : %s not found"),
    ENTITY_EXISTS("%s with name : %s already exists"),
    ENTITIES_NOT_FOUND("Entities for your request are not found"),
    ENTITY_WITH_DEPENDENCIES("%s with id: %s has dependencies and can not be deleted."),
    SAME_ENTITY_EXISTS("The same entity already exists"),;

    private final String message;
}
