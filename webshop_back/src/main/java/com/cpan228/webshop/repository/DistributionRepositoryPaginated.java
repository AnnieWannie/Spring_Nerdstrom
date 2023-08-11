package com.cpan228.webshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cpan228.webshop.model.Item;

public interface DistributionRepositoryPaginated extends PagingAndSortingRepository<Item, Long>, CrudRepository<Item, Long> {
}
