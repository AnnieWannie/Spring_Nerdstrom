package com.cpan228.webshop.controller;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cpan228.webshop.model.Item;
import com.cpan228.webshop.model.Item.Brand;
import com.cpan228.webshop.model.User;
import com.cpan228.webshop.repository.ItemRepository;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/add")
public class AddController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public String add() {
        return "add";
    }

    @ModelAttribute
    public void brands(Model model) {
        var brands = EnumSet.allOf(Brand.class);
        model.addAttribute("brands", brands);
        log.info("animes converted to string:  {}", brands);
    }

    /**
     * 1. We have created a new Fighter object here, to be populated from the form
     * inputs
     * 2. We have to reference the Fighter object properties in the form and bind
     * them to the corresponding inputs
     * 3. We have to submit Form (execute POST request) and make sure fighter
     * details are valid
     *
     * @return Fighter model that we will need only for request (form) submission
     */
    @ModelAttribute
    // This model attribute has a lifetime of a request
    public Item item() {
        return Item
                .builder()
                .build();
    }

    @PostMapping
    public String processItemAddition(@Valid Item item, BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        }
        log.info("Processing item: {}", item);
        itemRepository.save(item);
        return "redirect:/itemlist";
    }

}
