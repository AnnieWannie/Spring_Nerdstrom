package com.cpan228.webshop.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @DecimalMin(value="1000.00", inclusive = false)
    private double price;
    @Min(value=2022)
    private int yearOfCreation;
    private Brand brandFrom;
    private int quantity;


    @Builder.Default
    private LocalDate createdAt = LocalDate.now();

    public enum Brand {
        DIOR("Dior"), YSL("YSL"), CK("Calvin Klein"), GUCCI("Gucci"), CDG("Comme Des Garcons"), Awake_NY("Awake NY"), Polo("Polo"), Louis_Vuitton("Louis Vuitton");

        private String title;

        private Brand(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}
