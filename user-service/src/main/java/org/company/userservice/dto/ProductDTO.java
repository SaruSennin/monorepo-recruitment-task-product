package org.company.userservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private String id;

    @NotBlank(message = "Nazwa nie może być pusta ani zawierać tylko białych znaków")
    private String name;

    @NotBlank(message = "Opis nie może być pusty")
    private String description;

    @NotNull(message = "Cena nie może być pusta")
    @DecimalMin(value = "0.01", message = "Cena musi być wyższa niż 0")
    private BigDecimal price;

    @NotEmpty(message = "Kategoria nie może być pusta")
    private String category;
}
