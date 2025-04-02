package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.model.Detail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/details")
public class DetailController {

    @PostMapping
    public ResponseEntity<String> createDetail(@RequestBody Detail detail) {
        return ResponseEntity.ok("{\"message\": \"Hello\"}");
    }
}
