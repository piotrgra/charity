package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.enmu.UserState;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.service.UserService;

import java.util.Collections;
import java.util.HashSet;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }


    @RequestMapping("/register")
    public String registerAction(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String postRegisterAction(@ModelAttribute User user) {

        user.setState(UserState.ACTIVE);
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/createRoles")
    public String createRoles(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        Role role2 = new Role();
        role2.setName("ROLE_USER");
        roleRepository.save(role);
        roleRepository.save(role2);
        return "index";
    }
}