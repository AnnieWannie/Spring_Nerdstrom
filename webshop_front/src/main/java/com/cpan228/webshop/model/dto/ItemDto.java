package com.cpan228.webshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private int id;
    private String name;
    private double price;
    private int yearOfCreation;
    private String brandFrom;
    private int quantity;
    private String createdAt;
}

