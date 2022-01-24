package pl.coderslab.charity.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.UserService;
import pl.coderslab.charity.security.CurrentUser;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserServiceImpl;
import pl.coderslab.charity.web.dto.PasswordDto;
import pl.coderslab.charity.web.dto.UserEditDto;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class DonationController {

    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;

    public DonationController(CategoryService categoryService, InstitutionService institutionService,
                              DonationService donationService, UserServiceImpl userService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
    }

    @ModelAttribute("categories")
    public List<Category> getCategoriesList(){
        return categoryService.getAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> getInstitutionsList() {
        return institutionService.getAll();
    }

    @GetMapping("/form")
    public String initForm(Model model) {
        model.addAttribute("donation", new Donation());

        return "user/form";
    }

   @PostMapping("/form")
    public String form(@ModelAttribute("donation") @Valid Donation donation, BindingResult result, @AuthenticationPrincipal CurrentUser customUser, Model model) {
        if(result.hasErrors()){
            return "form";
        }

        User entityUser = customUser.getUser();
        donation.setUser(entityUser);
        donationService.add(donation);
        return "user/form-confirmation";
   }

   @GetMapping("/personalDetails")
   public String getPersonalDetails(@AuthenticationPrincipal CurrentUser customUser, Model model) {
       User entityUser = customUser.getUser();
       model.addAttribute("user", entityUser);
        return "user/personalDatails";
   }

   @GetMapping("/edit")
    public String initEdit(Model model, @AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        UserEditDto user = userService.getUserDto(entityUser.getId());
        model.addAttribute("user", user);
        return "user/edit";
   }

    @PostMapping("/edit")
    public String processEditUser(@ModelAttribute("user") @Valid UserEditDto userEditDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("errormessage", result.getAllErrors().toString());
            return "/user/edit";
        }
        userService.update(userEditDto);
        return "redirect:";
    }

    @GetMapping("/changePassword")
    public String initPasswordChange(Model model) {
        model.addAttribute("newPassword", new PasswordDto());
        return "user/passwordChange";
    }

    @PostMapping("/changePassword")
    public String processPasswordChange(@ModelAttribute("newPassword") @Valid PasswordDto passwordDto,
                                        BindingResult result, Model model, @AuthenticationPrincipal CurrentUser customUser) {
        if(result.hasErrors()) {
            return "user/passwordChange";
        }

        final User user = customUser.getUser();

        if(!passwordDto.getPassword().equals(passwordDto.getConfirmPassword())) {
            model.addAttribute("message", result.getAllErrors().toString());
            return "user/passwordChange?error=true";
        }

        userService.changeUserPassword(user, passwordDto.getPassword());
        return "redirect:personalDetails?success=true";
    }

    @GetMapping("/donations")
    public String getDonationList(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = customUser.getUser();
        model.addAttribute("donations", donationService.getUserDonationList(entityUser));
//        return findPaginated(1, model, "");
        return "user/donationList";

    }

    @GetMapping("/donationDetails")
    public String getDonationDetail(@RequestParam ("id") Long id, @AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = customUser.getUser();

//        Optional<Donation> optDonation = donationService.get(id);
        Donation donation = donationService.get(id).get();
        if (entityUser.getId() != donation.getUser().getId()) {
            return "redirect:accessForbidden";
        }
        model.addAttribute("donation", donationService.get(id).get());
        return "user/donationDetails";
    }

    @PostMapping("/changeDonationStatus")
    public String changeStatus(@RequestParam ("donationId") Long id, Model model) {
       Donation donation = donationService.get(id).get();

       if(donation == null) {
           model.addAttribute("errorMessage", "Błąd zmiany statusu");
           return "user/donationList";
       }
            donationService.changePickUpStatus(donation);
        return "redirect:donations";
    }

    @GetMapping("/page/{pageNumber}")
    public String findPaginated(@PathVariable (value = "pageNumber") int pageNumber,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Donation> page = donationService.findPaginated(pageNumber, pageSize, sortField, sortDir);
        List<Donation> listDonation = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");


        return "user/donationList";
    }
}
