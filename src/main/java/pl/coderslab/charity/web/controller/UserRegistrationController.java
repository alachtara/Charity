package pl.coderslab.charity.web.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.charity.exception.UserAlreadyExistException;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.VerificationToken;
import pl.coderslab.charity.registration.OnRegistrationCompleteEvent;
import pl.coderslab.charity.repository.UserService;
import pl.coderslab.charity.web.dto.UserRegistrationDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

@Controller
public class UserRegistrationController {

    private final UserService userService;
    private final MessageSource messages;
    private final ApplicationEventPublisher eventPublisher;

    public UserRegistrationController(UserService userService, MessageSource messages, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.messages = messages;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        final UserRegistrationDto userDto = new UserRegistrationDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userRegistrationDto, BindingResult result, HttpServletRequest request, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }
        try {
            User registered = userService.registerNewUserAccount(userRegistrationDto);

            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered,
                    appUrl, request.getLocale()));
        } catch (UserAlreadyExistException uaeEx) {
            final String message = messages.getMessage("message.registrationUserExistEx", null, request.getLocale());
            model.addAttribute("message", message);
            return "registration";
        }
        return "redirect:/registration?success";

    }

    @GetMapping("/registration/confirm")
    public String confirmRegistration(final HttpServletRequest request, Model model, @RequestParam("token") final String token) {
        Locale locale = request.getLocale();

        final String result = userService.validateVerificationToken(token);
        if (result.equals("valid")) {
            final User user = userService.getUser(token);

            model.addAttribute("successMessage", "message.accountVerified");
            return "redirect:/login";
        }

        String errorMessage;
        if("expired".equals(result)) {
            errorMessage = messages.getMessage("message.expiredToken", null, locale);
        } else {
            errorMessage = messages.getMessage("message.invalidToken", null, locale);
        }

        model.addAttribute("errorMessage", errorMessage);
        return "redirect:/badUser";
    }

    @GetMapping("/resetPassword")
    public String initResetPassword(HttpServletRequest request, final Model model) {

        return "resetPassword";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(HttpServletRequest request, final Model model, @RequestParam("email") final String email) {



        return "redirect:/login";
    }


  }
