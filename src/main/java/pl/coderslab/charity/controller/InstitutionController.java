package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.enmu.InstitutionState;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.Optional;

@Controller
@RequestMapping("/admin/institutions")
public class InstitutionController {

    private final InstitutionRepository institutionRepository;

    public InstitutionController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }


    @GetMapping("/add")
    public String addInstitution(Model model) {
        model.addAttribute("institution", new Institution());
        return "admin/institution-form";
    }

    @GetMapping("/update/{id}")
    public String addInstitution(@PathVariable long id, Model model) {
        Optional<Institution> institution = institutionRepository.findById(id);
        institution.ifPresent(value -> model.addAttribute("institution", value));
        return "admin/institution-form";
    }

    @PostMapping("/add")
    public String postAddInstitution(@ModelAttribute Institution institution) {
        institution.setState(InstitutionState.ACTIVE);
        institutionRepository.save(institution);
        return "redirect:/admin/institutions";
    }

    @GetMapping("/delete/{id}")
    public String deleteInstitution(@PathVariable long id) {
        Optional<Institution> institution = institutionRepository.findById(id);
        institution.ifPresent(institutionRepository::delete);
        return "redirect:/admin/institutions";
    }


}