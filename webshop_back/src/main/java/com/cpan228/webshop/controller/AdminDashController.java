package com.cpan228.webshop.controller;

import com.cpan228.webshop.model.Item;
import com.cpan228.webshop.model.Distribution;

import java.util.List;

import com.cpan228.webshop.model.Item.Brand;
import com.cpan228.webshop.repository.DistributionRepository;
import com.cpan228.webshop.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admindash")
@CrossOrigin("http://localhost:8081")
public class AdminDashController {

    private final DistributionRepository distroRepository;
    private final ItemRepository itemRepository;

    public AdminDashController(DistributionRepository distroRepository, ItemRepository itemRepository) {
        this.distroRepository = distroRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public List<Distribution> getAllDistributions() {
        return (List<Distribution>) distroRepository.findAll();
    }

    @GetMapping("/{id}/items")
    public List<Item> getItemsForDistribution(@PathVariable long id) {
        var currentDistributionCentre = distroRepository.findById(id);
        var items = currentDistributionCentre.get().getItems();
        return items;
    }


    @GetMapping("/items/search")
    public List<Distribution> searchItemsByName(@RequestParam(name="name") String name, @RequestParam(name="brandFrom") Brand brand) {
        System.out.println(name +" "+ brand);
        List<Distribution> items = distroRepository.findByItems_NameAndItems_BrandFrom(name, brand);
        return items;
    }

    @PostMapping("/{id}/items")
    public Item addItemToCentre(@PathVariable long id, @RequestBody Item item) {
        var currentDistributionCentre = distroRepository.findById(id);
        item.setDistribution(currentDistributionCentre.get());
        var savedItem = itemRepository.save(item);
        return savedItem;
    }

    @DeleteMapping("/{id}/items/{itemId}")
    public ResponseEntity<Void> deleteItemFromCentre(@PathVariable long id, @PathVariable long itemId) {
        if (!distroRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        var currentDistributionCentre = distroRepository.findById(id);
        var item = itemRepository.findById(itemId);
        if (item.isEmpty() || !item.get().getDistribution().equals(currentDistributionCentre.get())) {
            return ResponseEntity.notFound().build();
        }
        itemRepository.deleteById(itemId);
        return ResponseEntity.noContent().build();
    }


}





