package org.ideacreation.seminarium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {

    @GetMapping
    public RedirectView swagger() {
        return new RedirectView("/swagger-ui.html");
    }
}
