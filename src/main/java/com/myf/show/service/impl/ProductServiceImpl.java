package com.myf.show.service.impl;

import com.myf.show.mapper.ProductMapper;
import com.myf.show.model.Product;
import com.myf.show.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Ant
 * @Date: 2018/10/23 14:43
 * @Description:
 */
@Service("ProductService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addOrUpdateProduct(Product product) {
        int id = product.getId();
        if (id != 0){
            Product productById = productMapper.getProductById(id);
            if (productById!=null && productById.getId()!=0){
                productMapper.updateProduct(product);
            }else {
                productMapper.addProduct(product);
            }
        }else {
            productMapper.addProduct(product);
        }

    }

    @Override
    public void delProduct(int id) {
        productMapper.delProduct(id);
    }

    @Override
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }

    @Override
    public Product getProductById(int id) {
        return productMapper.getProductById(id);
    }

    @Override
    public List<Product> getProductByCategory(int Category) {
        return productMapper.getProductByCategory(Category);
    }

    @Override
    public List<Product> getAllProduct() {
        return productMapper.getAllProduct();
    }

    @Override
    public List<Product> searchProductByName(String p_name) {
        return productMapper.searchProductByName(p_name);
    }
}
