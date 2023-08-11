package com.cpan228.webshop.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cpan228.webshop.model.Item;
import com.cpan228.webshop.model.Item.Brand;

//It will be an interface that defines operations with the fighter
//table in the database
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByBrandFrom(Brand brandFrom);

    List<Item> findByNameAndBrandFrom(String name, Brand brandFrom);
    List<Item> findByYearOfCreation(int yearOfCreation);
}

