package pl.coderslab.charity.web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.exception.UserAlreadyExistException;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.security.CurrentUser;
import pl.coderslab.charity.service.UserServiceImpl;
import pl.coderslab.charity.web.dto.UserEditDto;
import pl.coderslab.charity.web.dto.UserRegistrationDto;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserServiceImpl userService;

    public AdminUserController(UserRepository userRepository, RoleRepository roleRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @ModelAttribute("roleList")
    public List<Role> roles() {
        return roleRepository.findAll();
    }

    @GetMapping("")
    public String allUsersList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/user/users";
    }

    @GetMapping("/admins")
    public String adminList(Model model) {
        Role role = roleRepository.findByName("ROLE_ADMIN");
        model.addAttribute("users", userRepository.findAllByRoles(role));
        return "admin/user/users";
    }

    @GetMapping("/users")
    public String usersRoleUserList(Model model) {
        Role role = roleRepository.findByName("ROLE_USER");
        model.addAttribute("users", userRepository.findAllByRoles(role));
        return "admin/user/users";
    }

    @GetMapping("/add")
    public String initRegisterUserAdmin(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "admin/user/add";
    }

    @PostMapping("/add")
    public String registerUserAdminAccount(@ModelAttribute("user") @Valid UserRegistrationDto userRegistrationDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/user/add";
        }

        if(userRegistrationDto.getPassword().equals(userRegistrationDto.getMatchingPassword())) {
            try {
                userService.save(userRegistrationDto);
            } catch (UserAlreadyExistException uaeEx) {
                model.addAttribute("message", "Użytkownik o podanym adresie e-mail już istnieje.");
                return "admin/user/add";
            }
            return "redirect:";
        } else {
            return "redirect:/add?error=true";
        }
    }

    @GetMapping("/edit")
    public String prepareEditUserAdmin(@RequestParam Long editId, Model model) {
        UserEditDto user = userService.getUserDto(editId);
        model.addAttribute("user", user);
        return "admin/user/edit";
    }

    @PostMapping("/edit")
    public String processEditUserAdmin(@ModelAttribute("user") @Valid UserEditDto userEditDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("message", result.getAllErrors().toString());
            return "admin/user/edit";
        }
        userService.update(userEditDto);
        return "redirect:";
    }

    @GetMapping(path = "/delete")
    public String initDelete(@RequestParam Long deleteId, Model model) {
        model.addAttribute("user", userRepository.findById(deleteId).get());
        return "admin/user/delete";
    }

    @PostMapping("/delete")
    public String remove(@ModelAttribute("user") User user, @RequestParam String confirmed, @AuthenticationPrincipal CurrentUser customUser, Model model) {
        if(customUser.getUser().getId().equals(user.getId())) {
            model.addAttribute("errormessage", "Nie możesz usunąć użytkownika, na którego jesteś zalogowany.");
            return "return ";
        }

        if ("yes".equals(confirmed)) {
            userRepository.delete(user);
        }
        return "redirect:";
    }

    @GetMapping(path = "/block")
    public String initBlock(@RequestParam Long blockId, Model model) {
        UserEditDto user = userService.getUserDto(blockId);
        model.addAttribute("user", user);
        return "admin/user/block";
    }

    @PostMapping("/block")
    public String block(@ModelAttribute("user") UserEditDto userDto, @RequestParam String confirmed) {
        if ("yes".equals(confirmed)) {
            userService.changeUnable(userDto);
        }
        return "redirect:";
    }

    @GetMapping(path = "/unblock")
    public String initUnblock(@RequestParam Long unblockId, Model model) {
        UserEditDto user = userService.getUserDto(unblockId);
        model.addAttribute("user", user);
        return "admin/user/block";
    }

    @PostMapping("/unblock")
    public String unblock(@ModelAttribute("user") UserEditDto userDto, @RequestParam String confirmed) {
        if ("yes".equals(confirmed)) {
            userService.changeUnable(userDto);
        }
        return "redirect:";
    }


}
