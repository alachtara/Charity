package pl.coderslab.charity.web.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.service.InstitutionService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/institutions")
public class InstitutionController {

    private final InstitutionService institutionService;

    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping("")
    public String institutionList(Model model) {
        model.addAttribute("institutions", institutionService.getAll());
        return "admin/institution/institutions";
    }

    @GetMapping("/add")
    public String initAddInstitution(Model model) {
        model.addAttribute("institution", new Institution());
        return "admin/institution/addEditInstitution";
    }

    @PostMapping("/add")
    public String processAddInstitution(@ModelAttribute("institution") @Valid Institution institution, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "admin/addEditInstitution";
        }

        try {
            institutionService.add(institution);
        } catch (DataIntegrityViolationException uaeEx) {
            model.addAttribute("message", "Fundacja o nazwie " + institution.getName() + " już istnieje.");
            return "admin/institution/addEditInstitution";
        }
        return "redirect:";
    }

    @GetMapping("/edit")
    public String prepareEditInstitution(@RequestParam Long editId, Model model) {
        model.addAttribute("institution", institutionService.get(editId).get());
        return "admin/institution/addEditInstitution";
    }

    @PostMapping("/edit")
    public String processEditInstitution(@ModelAttribute("institution") @Valid Institution institution, BindingResult result) {
        if(result.hasErrors()) {
            return "admin/institution/addEditInstitution";
        }
        institutionService.update(institution);
        return "redirect:";
    }

    @GetMapping(path = "/delete")
    public String initDelete(@RequestParam Long deleteId, Model model) {
        model.addAttribute("institution", institutionService.get(deleteId).get());
        return "admin/institution/delete";
    }

    @PostMapping("/delete")
    public String remove(@ModelAttribute("institution") Institution institution, @RequestParam String confirmed) {
        if ("yes".equals(confirmed)) {
            institutionService.delete(institution.getId());
        }
        return "redirect:";
    }

}
