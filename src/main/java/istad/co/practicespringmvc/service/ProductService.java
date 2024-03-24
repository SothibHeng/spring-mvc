package istad.co.practicespringmvc.service;

import istad.co.practicespringmvc.dto.ProductRequest;
import istad.co.practicespringmvc.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProduct(String productName);
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse findProductByID(int id );
    void deleteProduct(int productId);

    ProductResponse updateProduct(int id ,  ProductRequest productRequest);
}
