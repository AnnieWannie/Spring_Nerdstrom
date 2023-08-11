package com.cpan228.webshop.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.cpan228.webshop.model.Item.Brand;

@Data
@NoArgsConstructor
public class ItemSearchByBrandDto {
    private Brand brandFrom;
}
