package com.cpan228.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @DecimalMin(value = "1000.00", inclusive = false)
    private double price;
    @Min(value = 2022)
    private int yearOfCreation;
    private Brand brandFrom;
    private int quantity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "distribution_id", nullable = false)
    private Distribution distribution;

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
