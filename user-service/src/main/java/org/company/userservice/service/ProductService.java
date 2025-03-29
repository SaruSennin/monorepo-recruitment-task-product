package org.company.userservice.service;

import lombok.RequiredArgsConstructor;
import org.company.userservice.dto.ProductDTO;
import org.company.userservice.enums.Category;
import org.company.userservice.mapper.ProductMapper;
import org.company.userservice.model.Product;
import org.company.userservice.repository.ProductRepository;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

//    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().
                map(productMapper::toDTO).
                collect(Collectors.toList());
    }

//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Optional<ProductDTO> findById(String id) {
        return productRepository.findById(id).map(productMapper::toDTO);
    }

//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ProductDTO save(ProductDTO productDTO) {
        Errors errors = new BeanPropertyBindingResult(productDTO, "productDTO");
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    private void takeAllErrors(Errors errors) {
        if (errors.hasErrors()) {
            String errorMessages = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            throw new IllegalArgumentException("Validation failed: " + errorMessages);
        }
    }

    //    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public List<ProductDTO> findByCategory(String category) {
        return productRepository.findByCategory(Category.valueOf(category))
                .stream()
                .map(productMapper::toDTO)
                .toList();
    }

//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }
}
