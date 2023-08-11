package com.cpan228.webshop.model.dto;

import com.cpan228.webshop.model.Item.Brand;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemSearchByNameAndBrandDto {
    private String name;
    private String brandFrom;
}
