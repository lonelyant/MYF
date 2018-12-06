package com.myf.show.mapper;

import com.myf.show.model.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Ant
 * @Date: 2018/10/23 14:02
 * @Description:
 */
@Mapper
public interface ProductMapper {
    void addProduct(Product product);
    void delProduct(int id);
    void updateProduct(Product product);
    Product getProductById(int id);
    List<Product> getProductByCategory(int Category);
    List<Product> getAllProduct();

    List<Product> searchProductByName(String p_name);

}
