package myapp.webshop.service;

import myapp.webshop.dto.ProductDTO;
import myapp.webshop.entities.Product;


import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();

    Product toProduct(ProductDTO dto);

    ProductDTO fromProduct(Product product);
    List<Product> toProductList(List<ProductDTO> productsDTO);

    List<ProductDTO> fromProductList(List<Product> products);

    void addToUserBucket(Long productId, String username);
}
