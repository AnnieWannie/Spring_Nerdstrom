package com.cpan228.webshop.repository;

import com.cpan228.webshop.model.Item;
import com.cpan228.webshop.model.Item.Brand;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.cpan228.webshop.model.Distribution;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DistributionRepository extends CrudRepository<Distribution, Long> {

        List<Distribution> findByItems_NameAndItems_BrandFrom(String name, Brand brandFrom);

}
