package com.myf.show.controller;

import com.myf.show.model.LoginModel;
import com.myf.show.model.Product;
import com.myf.show.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author: Ant
 * @Date: 2018/10/23 13:46
 * @Description:
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    // 添加产品
    @RequestMapping("/addOrUpdateProduct")
    public @ResponseBody LoginModel addOrUpdateProduct(@RequestParam(value = "image", required = false) MultipartFile image, Product product , HttpSession session,HttpServletRequest request) {
        LoginModel model = new LoginModel();
        if (session.getAttribute("isLogin") != "true"){
            model.setStatus(false);
            model.setInfo("登录过期，请刷新后重新登录。");
            return model;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss") ;
        if (image != null && !image.isEmpty()) {
            String originalFilename = image.getOriginalFilename();
            String filePath = dateFormat.format(new Date()) + new Random().nextInt(99) + originalFilename.substring(originalFilename.lastIndexOf("."));
            String savePath = ProductController.class.getClassLoader().getResource("").getPath() + "/static/img/product/";
//            String savePath = "D:\\MYF\\target\\classes\\static\\img\\product\\";
            try {
                image.transferTo(new File(savePath+filePath));
            } catch (Exception e) {
                model.setStatus(false);
                model.setInfo("图片上传失败！");
            }
            product.setP_imgurl(filePath);
            model.setStatus(true);
        }else {
            if (product.getId() != 0){
                product.setP_imgurl(productService.getProductById(product.getId()).getP_imgurl());
            }else {
                product.setP_imgurl("noimg.jpg");
            }
        }
        productService.addOrUpdateProduct(product);
        model.setStatus(true);
        return model;
    }
    // 删除产品
    @RequestMapping("delProduct")
    public @ResponseBody LoginModel delProduct(@Param("p_id")int p_id,HttpSession session,HttpServletRequest request){
        System.out.println(request.getSession().getServletContext().getRealPath("/"));
        LoginModel model = new LoginModel();
        if (session.getAttribute("isLogin") != "true"){
            model.setStatus(false);
            model.setInfo("登录过期，请刷新后重新登录。");
            return model;
        }
        try {
            productService.delProduct(p_id);
            model.setStatus(true);
        } catch (Exception e) {
            model.setStatus(false);
            model.setInfo(e.toString());
        }
        return model;
    }

    // 更新产品

    // 查询产品
    @RequestMapping("/update")
    public String getupdatePage(HttpSession session,@Param("id")int id){
        if (session.getAttribute("isLogin") != "true"){
            return "login";
        }
        Product productById = productService.getProductById(id);
        session.setAttribute("product",productById);
        return "update";
    }


}
