package org.company.userservice.mapper;

import org.company.userservice.dto.ProductDTO;
import org.company.userservice.enums.Category;
import org.company.userservice.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setCategory(mapCategoryToString(product.getCategory()));
        return dto;
    }

    public Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(mapStringToCategory(dto.getCategory()));
        return product;
    }

    private String mapCategoryToString(Category category) {
        return category != null ? category.name() : null;
    }

    private Category mapStringToCategory(String category) {
        return category != null ? Category.valueOf(category) : null;
    }
}
