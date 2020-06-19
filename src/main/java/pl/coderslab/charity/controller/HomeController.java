package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;


@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;

    public HomeController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @ModelAttribute("institutions")
    public List<Institution> institutions() {
        return institutionRepository.findAll();
    }

    @RequestMapping("/")
    public String homeAction(Model model) {
        return "index";
    }

    @RequestMapping("/form")
    public String formAction(Model model) {
        return "form";
    }

    @RequestMapping("/login")
    public String loginAction(Model model) {
        return "login";
    }

    @RequestMapping("/register")
    public String registerAction(Model model) {
        return "register";
    }
}
