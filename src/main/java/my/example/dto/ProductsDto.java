package my.example.dto;

import my.example.model.Product;

public class ProductsDto {

    private ProductsDto() {
        throw new IllegalStateException("Data Transfer Object class");
    }

    public static Product createProductMockService(Product dataMock) {
        return Product.builder()
                      .id(dataMock.getId())
                      .code(dataMock.getCode())
                      .name(dataMock.getName())
                      .description(dataMock.getDescription())
                      .image(dataMock.getImage())
                      .price(dataMock.getPrice())
                      .category(dataMock.getCategory())
                      .quantity(dataMock.getQuantity())
                      .inventoryStatus(dataMock.getInventoryStatus())
                      .rating(dataMock.getRating())
                      .build();
    }
}