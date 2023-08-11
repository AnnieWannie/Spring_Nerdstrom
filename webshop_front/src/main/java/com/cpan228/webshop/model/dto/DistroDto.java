package com.cpan228.webshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistroDto {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private List<ItemDto> items;
}
