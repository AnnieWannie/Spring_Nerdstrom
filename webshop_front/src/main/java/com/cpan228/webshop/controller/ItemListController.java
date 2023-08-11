package com.cpan228.webshop.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.cpan228.webshop.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cpan228.webshop.model.dto.ItemSearchByYearOfCreationDto;
import com.cpan228.webshop.model.dto.ItemSearchByBrandDto;
import com.cpan228.webshop.repository.ItemRepository;
import com.cpan228.webshop.repository.ItemRepositoryPaginated;

@Controller
@Slf4j
@RequestMapping("/itemlist")
public class ItemListController {
    private static final int PAGE_SIZE = 5;
    private ItemRepository itemRepository;

    private ItemRepositoryPaginated itemRepositoryPaginated;

    public ItemListController(ItemRepository itemRepository,
                              ItemRepositoryPaginated itemRepositoryPaginated) {
        this.itemRepository = itemRepository;
        this.itemRepositoryPaginated = itemRepositoryPaginated;
    }

    @GetMapping
    public String showItems(Model model) {
        return "itemlist";
    }

    /**
     * This method will allow us to populate the model with initial item details
     * 1. We will use the itemRepositoryPaginated to retrieve the first page of
     * items (we set the page size to 5)
     * 
     * @param model
     */
    @ModelAttribute
    public void items(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        var itemPage = itemRepositoryPaginated.findAll(PageRequest.of(0, PAGE_SIZE));
        model.addAttribute("items", itemPage);
        model.addAttribute("currentPage", itemPage.getNumber());
        model.addAttribute("totalPages", itemPage.getTotalPages());
    }

    @ModelAttribute
    public void itemsByYearOfCreationDto(Model model) {
        model.addAttribute("itemsByYearOfCreationDto", new ItemSearchByYearOfCreationDto());
    }

    @ModelAttribute
    public void itemsByBrandDto(Model model) { model.addAttribute( "itemsByBrandDto", new ItemSearchByBrandDto()); }

    @PostMapping("/searchbyyearofcreation")
    public String searchItemsByYearOfCreation(@ModelAttribute ItemSearchByYearOfCreationDto itemsByYearOfCreationDto,
            Model model) {
        model.addAttribute("items", itemRepository.findByYearOfCreation(itemsByYearOfCreationDto.getYearOfCreation()));
        return "itemlist";
    }
    @PostMapping("/searchbybrand")
    public String searchItemsByBrand(@ModelAttribute ItemSearchByBrandDto itemsByBrandDto, Model model) {
        model.addAttribute("items", itemRepository.findByBrandFrom(itemsByBrandDto.getBrandFrom()));
        return "itemlist";
    }

    @GetMapping("/switchPage")
    public String switchPage(Model model,
            @RequestParam("pageToSwitch") Optional<Integer> pageToSwitch) {
        var page = pageToSwitch.orElse(0);
        var totalPages = (int) model.getAttribute("totalPages");
        if (page < 0 || page >= totalPages) {
            return "itemlist";
        }
        var itemPage = itemRepositoryPaginated.findAll(PageRequest.of(pageToSwitch.orElse(0),
                PAGE_SIZE));
        model.addAttribute("items", itemPage.getContent());
        model.addAttribute("currentPage", itemPage.getNumber());
        return "itemlist";
    }
    @PostMapping("/deleteallitems")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String processItemsDeletion(@AuthenticationPrincipal User user) {
        log.info("Deleting all items for user: {}", user.getAuthorities());
        itemRepository.deleteAll();
        return "redirect:/itemlist";
    }
}
