package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final InstitutionRepository institutionRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public AdminController(InstitutionRepository institutionRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.institutionRepository = institutionRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
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

    @GetMapping("/administrators")
    public String administrators(Model model) {
        Role role = roleRepository.findByName("ROLE_ADMIN");
        List<User> allAdmins = userRepository.findAllByRoles(role);

        model.addAttribute("allAdmins", allAdmins);
        return "admin/administrators";
    }

    @GetMapping("/users")
    public String users(Model model) {
        Role role = roleRepository.findByName("ROLE_USER");
        List<User> allUsers = userRepository.findAllByRoles(role);

        model.addAttribute("allUsers", allUsers);
        return "admin/users";
    }
}

