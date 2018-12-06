package com.myf.show.service;

import com.myf.show.model.Product;

import java.util.List;

/**
 * @author: Ant
 * @Date: 2018/10/23 14:42
 * @Description:
 */
public interface ProductService {
    void addOrUpdateProduct(Product product);
    void delProduct(int id);
    void updateProduct(Product product);
    Product getProductById(int id);
    List<Product> getProductByCategory(int Category);
    List<Product> getAllProduct();

    List<Product> searchProductByName(String p_name);
}
