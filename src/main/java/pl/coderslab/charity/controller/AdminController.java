package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.entity.CurrentUser;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal CurrentUser customUser) {
        return "admin/index";
    }

}