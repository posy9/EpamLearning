package by.bsu.detailstorage.registry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessagesRegistry {
    ENTITY_NOT_FOUND("%s with id : %s not found"),
    ENTITY_EXISTS("%s with name : %s already exists"),;

    private final String message;
}
