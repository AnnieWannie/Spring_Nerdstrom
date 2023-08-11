package com.cpan228.webshop.controller;

import com.cpan228.webshop.model.Item;
import com.cpan228.webshop.model.Item.Brand;
import com.cpan228.webshop.model.dto.DistroDto;
import com.cpan228.webshop.model.dto.ItemDto;
import com.cpan228.webshop.repository.ItemRepository;
import com.cpan228.webshop.repository.ItemRepositoryPaginated;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admindash")
@CrossOrigin(origins = "http://localhost:8082")
public class AdminDashController {
    private final RestTemplate restTemplate;
    private ItemRepository itemRepository;
    private ItemRepositoryPaginated itemRepositoryPaginated;

    public AdminDashController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.itemRepository = itemRepository;
        this.itemRepositoryPaginated = itemRepositoryPaginated;
    }

    @GetMapping
    public String adminDash(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        int pageSize = 4;
        List<DistroDto> distros = getDistros();

        // Calculate total pages
        int totalPages = (int) Math.ceil((double) distros.size() / pageSize);

        // Get the sublist of distros for the current page
        int fromIndex = page * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, distros.size());
        List<DistroDto> distrosForPage = distros.subList(fromIndex, toIndex);

        model.addAttribute("distros", distrosForPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "admindash";
    }

    @GetMapping("/items/search")
    public String stockDash(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name="name") String name, @RequestParam(name="brandFrom") Brand brand) {
        int pageSize = 4;
        List<DistroDto> items = getItems(name, brand);

        int totalPages = (int) Math.ceil((double) items.size() / pageSize);

        int fromIndex = page * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, items.size());
        List<DistroDto> itemsForPage = items.subList(fromIndex, toIndex);

        model.addAttribute("distros", itemsForPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
//        var items = restTemplate.getForObject("http://localhost:8082/api/admindash/items/search?name=" + name + "&brandFrom=" + brand, DistroDto[].class);
//        model.addAttribute("distros", items);
        return "adminDash";
    }

private List<DistroDto> getDistros() {
    String apiUrl = "http://localhost:8082/api/admindash";

    // Create headers and set basic authentication
    HttpHeaders headers = new HttpHeaders();
    headers.setBasicAuth("admin", "password");

    // Set the desired content type if needed
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Create an HttpEntity with the headers
    HttpEntity<String> entity = new HttpEntity<>(headers);

    // Make the API request with the specified headers
    ResponseEntity<DistroDto[]> responseEntity = restTemplate.exchange(
        apiUrl,
        HttpMethod.GET,
        entity,
        DistroDto[].class
    );

    DistroDto[] distros = responseEntity.getBody();
    if (distros != null) {
        return Arrays.asList(distros);
    } else {
        return Collections.emptyList();
    }
}
private List<DistroDto> getItems(String name, Brand brand) {
    String apiUrl = "http://localhost:8082/api/admindash/items/search?name=" + name + "&brandFrom=" + brand;

    // Create headers and set basic authentication
     HttpHeaders headers = new HttpHeaders();
    headers.setBasicAuth("admin", "password");

    // Set the desired content type if needed
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Create an HttpEntity with the headers
    HttpEntity<String> entity = new HttpEntity<>(headers);

    // Make the API request with the specified headers
    ResponseEntity<DistroDto[]> responseEntity = restTemplate.exchange(
        apiUrl,
        HttpMethod.GET,
        entity,
        DistroDto[].class
    );

    DistroDto[] distros = responseEntity.getBody();
    if (distros != null) {
        return Arrays.asList(distros);
    } else {
        return Collections.emptyList();
    }
}

}

