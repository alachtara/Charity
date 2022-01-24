package pl.coderslab.charity.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;


@Controller
public class HomeController {
    private final InstitutionService institutionService;
    private final DonationService donationService;

    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @ModelAttribute("institutions")
    public List<Institution> getInstitutions() {
       return institutionService.getAll();
    }

    @ModelAttribute("numberOfBags")
    public Long getAllBagsNumber() {
        return donationService.countBags();
    }

    @ModelAttribute("numberOfDonations")
    public int getAllDonationsNumber() {
        return donationService.numberOfDonations();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String test() {
        return "home";
    }




}
