package kov.develop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Welcome page
 */

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "meetings";
    }

}
