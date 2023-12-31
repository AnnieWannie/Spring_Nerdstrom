package com.cpan228.webshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cpan228.webshop.model.Item;


//It will be an interface that defines operations with the fighter
//table in the database
//This interface will extend PagingAndSortingRepository, which will allow us to retrieve fighters in pages
public interface ItemRepositoryPaginated extends PagingAndSortingRepository<Item, Long>, CrudRepository<Item, Long> {
}
