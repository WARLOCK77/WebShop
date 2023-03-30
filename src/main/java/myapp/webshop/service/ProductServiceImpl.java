package myapp.webshop.service;

import myapp.webshop.dao.ProductRepository;
import myapp.webshop.dto.ProductDTO;
import myapp.webshop.entities.Bucket;
import myapp.webshop.entities.Product;
import myapp.webshop.entities.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final UserService userService;
    private final BucketService bucketService;


    public ProductServiceImpl(ProductRepository productRepository, UserService userService, BucketService bucketService) {
        this.productRepository=productRepository;
        this.userService = userService;
        this.bucketService = bucketService;
    }

    @Override
    public List<ProductDTO> getAll() {
        return fromProductList(productRepository.findAll());
    }

    @Override
    @Transactional
    public void addToUserBucket(Long productId, String username) {
        User user = userService.findByName(username);
        if (user == null) {
            throw new RuntimeException("User no found - " + username);
        }
        Bucket bucket = user.getBucket();
        if (bucket == null) {
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(newBucket);
            userService.save(user);
        } else {
            bucketService.addProducts(bucket, Collections.singletonList(productId));
        }
    }


    @Override
    public Product toProduct(ProductDTO dto) {
        if ( dto == null ) {
            throw new RuntimeException("ProductDTO not found");
        }

        return new Product();
    }

    @Override
    public ProductDTO fromProduct(Product product) {
        if ( product == null ) {
            throw new RuntimeException("Product not found");
        }
        return new ProductDTO(product.getId(), product.getTitle(), product.getPrice());
    }

    @Override
    public List<Product> toProductList(List<ProductDTO> productsDTO) {
        if ( productsDTO == null ) {
            throw new RuntimeException("ProductsListDTO not found");
        }

        List<Product> list = new ArrayList<>( productsDTO.size() );
        for ( ProductDTO productDTO : productsDTO ) {
            list.add( toProduct( productDTO ) );
        }

        return list;
    }

    @Override
    public List<ProductDTO> fromProductList(List<Product> products) {
        if ( products == null ) {
            throw new RuntimeException("ProductsList not found");
        }

        List<ProductDTO> list = new ArrayList<>( products.size() );
        for ( Product product : products ) {
            list.add( fromProduct( product ) );
        }

        return list;
    }
}
