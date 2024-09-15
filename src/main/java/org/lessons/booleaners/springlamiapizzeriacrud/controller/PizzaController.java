package org.lessons.booleaners.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.booleaners.springlamiapizzeriacrud.model.Pizza;
import org.lessons.booleaners.springlamiapizzeriacrud.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository repo;

    @GetMapping()
    public String pizzas(Model model, @RequestParam(name = "name", required = false) String name) {
        List<Pizza> pizzas;
        if(name != null && !name.isEmpty()) {
            pizzas = repo.findByNameContainingIgnoreCaseOrderByNameAsc(name);
        } else {
            pizzas = repo.findAll();
        }
        model.addAttribute("pizza", pizzas);
        return "/pizzas/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", repo.findById(id).get());
        return "/pizzas/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "/pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "/pizzas/create";
        }
        repo.save(formPizza);
        attributes.addFlashAttribute("createMessage", "Pizza " + formPizza.getName() + " successfully inserted");

        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", repo.findById(id).get());
        return "/pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return "/pizzas/edit";
        }
        repo.save(formPizza);
        attributes.addFlashAttribute("updateMessage", "Pizza " + formPizza.getName() + " successfully updated");

        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes attributes) {
        repo.deleteById(id);
        attributes.addFlashAttribute("deleteMessage", "Pizza " + id + " successfully deleted");
        return "redirect:/pizzas";
    }
}
