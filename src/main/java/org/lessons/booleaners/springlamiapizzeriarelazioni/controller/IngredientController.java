package org.lessons.booleaners.springlamiapizzeriarelazioni.controller;

import jakarta.validation.Valid;
import org.lessons.booleaners.springlamiapizzeriarelazioni.model.Ingredient;
import org.lessons.booleaners.springlamiapizzeriarelazioni.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService service;

    @GetMapping()
    public String ingredients(Model model, @RequestParam(name = "name", required = false) String name) {
        List<Ingredient> ingredients;
        if (name != null && ! name.isEmpty()) {
            ingredients = service.findByName(name);
        } else {
            ingredients = service.findAll();
        }
        model.addAttribute("ingredient", ingredients);
        return "/ingredients/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ingredient", service.getById(id));
        return "/ingredients/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "/ingredients/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredient", formIngredient);
            return "/ingredients/create";
        }
        service.create(formIngredient);
        redirectAttributes.addFlashAttribute("createMessage", formIngredient.getName() + " has been created successfully!");
        return "redirect:/ingredients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ingredient", service.getById(id));
        return "/ingredients/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredient", formIngredient);
            return "/ingredients/edit";
        }
        service.update(formIngredient);
        redirectAttributes.addFlashAttribute("updateMessage", formIngredient.getName() + " has been updated successfully!");
        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes attributes) {
        service.delete(id);
        attributes.addFlashAttribute("deleteMessage", "Ingredient " + id + " has been deleted successfully!");
        return "redirect:/ingredients";
    }
}
