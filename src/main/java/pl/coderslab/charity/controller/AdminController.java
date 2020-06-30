package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.CurrentUser;
import pl.coderslab.charity.repository.InstitutionRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final InstitutionRepository institutionRepository;

    public AdminController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("")
    public String admin() {
        return "admin/index";
    }

    @GetMapping("/institutions")
    public String institutions(Model model) {
        model.addAttribute("allInstitutions", institutionRepository.findAll());
        return "admin/institutions";
    }


}