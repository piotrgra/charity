package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.enmu.UserState;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping("admin/users")
public class UserAdminController {

    private final UserRepository userRepository;


    public UserAdminController(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable long id, Model model) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(value -> model.addAttribute("admin", value));
        return "admin/user-form";
    }

    @PostMapping("/update")
    public String postUser(@ModelAttribute User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (userOptional.isPresent()) {
            User currentUser = userOptional.get();

            user.setEnabled(currentUser.getEnabled());
            user.setRoles(currentUser.getRoles());
            user.setPassword(currentUser.getPassword());
            user.setState(UserState.ACTIVE);
            userRepository.save(user);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/enable/{id}")
    public String enableUser(@PathVariable long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            int enabled = ((user.getEnabled() == 1) ? 0 : 1);
            user.setEnabled(enabled);

            userRepository.save(user);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresent(userRepository::delete);
        return "redirect:/admin/users";
    }

}