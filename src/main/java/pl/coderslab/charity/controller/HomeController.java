package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.CurrentUser;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;


@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @ModelAttribute("institutions")
    public List<Institution> institutions() {
        return institutionRepository.findAll();
    }

    @ModelAttribute("bagsQuantity")
    public Integer bagsQuantity() {
        return donationRepository.sumOfBags();
    }

    @ModelAttribute("donationsQuantity")
    public Integer donationsQuantity() {
        return donationRepository.findAll().size();
    }

    @RequestMapping("/")
    public String homeAction() {
        return "index";
    }

    @GetMapping("/login")
    public String loginAction(@AuthenticationPrincipal CurrentUser customUser) {
        if (customUser == null) {
            return "login";
        }
        return "index";
    }

}
