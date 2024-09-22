package org.lessons.booleaners.springlamiapizzeriarelazioni.controller;

import jakarta.validation.Valid;
import org.lessons.booleaners.springlamiapizzeriarelazioni.model.Discount;
import org.lessons.booleaners.springlamiapizzeriarelazioni.model.Pizza;
import org.lessons.booleaners.springlamiapizzeriarelazioni.service.IngredientService;
import org.lessons.booleaners.springlamiapizzeriarelazioni.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService service;

    @Autowired
    private IngredientService ingredientService;

    @GetMapping()
    public String pizzas(Model model, @RequestParam(name = "name", required = false) String name) {
        List<Pizza> pizzas;
        if (name != null && ! name.isEmpty()) {
            pizzas = service.findByName(name);
        } else {
            pizzas = service.findAll();
        }
        model.addAttribute("pizza", pizzas);
        return "/pizzas/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", service.getById(id));
        return "/pizzas/show";
    }

    @GetMapping("/{id}/discount")
    public String showDiscount(@PathVariable("id") Integer id, Model model) {
        Discount discount = new Discount();
        discount.setStartDate(LocalDate.now());
        discount.setPizza(service.getById(id));
        model.addAttribute("discount", discount);
        return "/discounts/create";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientService.findAll());
        return "/pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientService.findAll());
            return "/pizzas/create";
        }
        service.create(formPizza);
        attributes.addFlashAttribute("createMessage", "Pizza " + formPizza.getName() + " successfully inserted");

        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", service.getById(id));
        model.addAttribute("ingredients", ingredientService.findAll());
        return "/pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientService.findAll());
            return "/pizzas/edit";
        }
        service.update(formPizza);
        attributes.addFlashAttribute("updateMessage", "Pizza " + formPizza.getName() + " successfully updated");

        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes attributes) {
        service.delete(id);
        attributes.addFlashAttribute("deleteMessage", "Pizza " + id + " successfully deleted");
        return "redirect:/pizzas";
    }
}
