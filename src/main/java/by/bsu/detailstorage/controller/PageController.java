package by.bsu.detailstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/adminPage")
    public String getAdminPage() {
        return "adminPage";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
