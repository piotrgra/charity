package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.CurrentUser;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;


@Controller
public class DonationController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;

    public DonationController(InstitutionRepository institutionRepository, DonationRepository donationRepository, CategoryRepository categoryRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
    }

    @ModelAttribute("allCategories")
    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }

    @ModelAttribute("allInstitutions")
    public List<Institution> allInstitutions() {
        return institutionRepository.findAll();
    }

    @RequestMapping("/form")
    public String form(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            model.addAttribute("currentUser", currentUser.getUser());

        }
        model.addAttribute("donation", new Donation());
        return "form";
    }

    @PostMapping("/form")
    public String postForm(@ModelAttribute Donation donation) {
        donationRepository.save(donation);
        return "form-confirm";
    }

}
