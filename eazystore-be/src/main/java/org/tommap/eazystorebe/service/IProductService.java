package org.tommap.eazystorebe.service;

import org.tommap.eazystorebe.model.dto.ProductDto;

import java.util.List;

public interface IProductService {
    List<ProductDto> getProducts();
}
