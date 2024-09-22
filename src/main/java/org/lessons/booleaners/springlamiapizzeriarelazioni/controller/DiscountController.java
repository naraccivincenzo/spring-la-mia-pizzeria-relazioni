package org.lessons.booleaners.springlamiapizzeriarelazioni.controller;

import jakarta.validation.Valid;
import org.lessons.booleaners.springlamiapizzeriarelazioni.model.Discount;
import org.lessons.booleaners.springlamiapizzeriarelazioni.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/discounts")
public class DiscountController {

    @Autowired
    private DiscountService service;

    @GetMapping()
    public String discounts(Model model, @RequestParam(name = "name", required = false) String name) {
        List<Discount> discounts;
        if (name != null && ! name.isEmpty()) {
            discounts = service.findByName(name);
        } else {
            discounts = service.findAll();
        }
        model.addAttribute("discount", discounts);
        return "/discounts/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("discount", service.getById(id));
        return "/discounts/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("discount", new Discount());
        return "/discounts/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("discount") Discount formDiscount, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "/discounts/create";
        }
        service.create(formDiscount);
        attributes.addFlashAttribute("createMessage", "Special Offer " + formDiscount.getDiscountName() + " was successfully inserted");

        return "redirect:/pizzas/" + formDiscount.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("discount", service.getById(id));
        return "/discounts/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("discount") Discount formDiscount, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "/discounts/edit";
        }
        service.update(formDiscount);
        attributes.addFlashAttribute("updateMessage", "Special Offer " + formDiscount.getDiscountName() + " was successfully updated");

        return "redirect:/discounts";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes attributes) {
        service.delete(id);
        attributes.addFlashAttribute("deleteMessage", "Special Offer " + id + " was successfully deleted");
        return "redirect:/discounts";
    }
}
