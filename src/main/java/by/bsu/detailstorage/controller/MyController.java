package by.bsu.detailstorage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class MyController {

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<String> getExample() {
        return ResponseEntity.ok("{\"message\": \"Hello\"}");
    }
}
