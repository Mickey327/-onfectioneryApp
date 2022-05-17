package com.example.confectioneryApp.product;

import com.example.confectioneryApp.category.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = { CategoryService.class })
public interface ProductMapper {
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "weight", source = "weight"),
            @Mapping(target = "price", source = "price"),
            @Mapping(target = "imageName", source = "imageName"),
            @Mapping(target = "description", source = "description")
    })
    ProductDto fromEntity(Product product);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "weight", source = "weight"),
            @Mapping(target = "price", source = "price"),
            @Mapping(target = "imageName", source = "imageName"),
            @Mapping(target = "description", source = "description")
    })
    Product toEntity(ProductDto productDto);


}
