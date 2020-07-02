package pl.coderslab.charity.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.enmu.UserState;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Controller
@RequestMapping("admin/administrators")
public class AdministratorController {

    private final InstitutionRepository institutionRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdministratorController(InstitutionRepository institutionRepository, RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.institutionRepository = institutionRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/add")
    public String addAdministrator(Model model) {
        model.addAttribute("admin", new User());
        return "admin/administrator-form";
    }

    @GetMapping("/update/{id}")
    public String updateAdministrator(@PathVariable long id, Model model) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(value -> model.addAttribute("admin", value));
        return "admin/administrator-form";
    }

    @PostMapping("/add")
    public String postAddAdministrator(@ModelAttribute User user) {
        if (user.getId() == null) {
            user.setPassword(passwordEncoder.encode("random"));
            user.setEnabled(1);
            user.setState(UserState.ACTIVE);
        } else {
            Optional<User> userOptional = userRepository.findById(user.getId());

            if (userOptional.isPresent()) {
                User u = userOptional.get();
                user.setState(u.getState());
                user.setEnabled(u.getEnabled());
                user.setPassword(u.getPassword());
            }
        }
        Role userRole = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(user);
        return "redirect:/admin/administrators";
    }

    @GetMapping("/deleteRole/{id}")
    public String deleteAdministrator(@PathVariable long id, Model model) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Role userRole = roleRepository.findByName("ROLE_USER");
            user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
            userRepository.save(user);
        }
        return "redirect:/admin/administrators";
    }

}