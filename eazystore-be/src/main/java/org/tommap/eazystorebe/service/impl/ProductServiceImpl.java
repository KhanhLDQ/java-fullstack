package org.tommap.eazystorebe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tommap.eazystorebe.mapper.ProductMapper;
import org.tommap.eazystorebe.model.dto.ProductDto;
import org.tommap.eazystorebe.model.entity.Product;
import org.tommap.eazystorebe.repository.ProductRepository;
import org.tommap.eazystorebe.service.IProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }
}
