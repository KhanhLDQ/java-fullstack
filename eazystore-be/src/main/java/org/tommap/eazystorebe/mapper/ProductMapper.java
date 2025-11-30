package org.tommap.eazystorebe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tommap.eazystorebe.model.dto.ProductDto;
import org.tommap.eazystorebe.model.entity.Product;

import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ERROR)
public interface ProductMapper {
    @Mapping(target = "productId", source = "id")
    ProductDto toDto(Product product);
}
