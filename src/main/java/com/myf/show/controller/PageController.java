package com.myf.show.controller;

import com.myf.show.model.LoginModel;
import com.myf.show.model.Product;
import com.myf.show.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: Ant
 * @Date: 2018/10/23 13:42
 * @Description:
 */
@Controller
public class PageController {

    @Autowired
    private ProductService productService;

    // 展示主页面
    @RequestMapping("/")
    public String getShowPage(HttpSession session) {
        List<Product> productByCategory = productService.getProductByCategory(1);
        session.setAttribute("productList", productByCategory);
        session.setAttribute("category", 1);
        return "index";
    }

    // 通过分类展示页面
    @RequestMapping("/showCategory")
    public String getPageByCategory(HttpSession session, String category) {
        int category_int = 1;
        try {
            category_int = Integer.parseInt(category);
        } catch (Exception e) {
            category_int = 1;
        }
        List<Product> productByCategory = productService.getProductByCategory(category_int);
        session.setAttribute("productList", productByCategory);
        session.setAttribute("category", category_int);
        return "index";
    }

    @RequestMapping("/login")
    public String getLoginPage(HttpSession session) {
        if (session.getAttribute("isLogin") == "true") {
            return "admin";
        }
        return "login";
    }

    // 后台系统 - 展示登录
    @RequestMapping("/asklogin")
    public @ResponseBody
    LoginModel askLogin(@Param("pwd") String pwd, HttpSession session) {
        LoginModel loginModel = new LoginModel();
        if ("z78l10y125".equals(pwd)) {
            loginModel.setStatus(true);
            session.setAttribute("isLogin", "true");
        } else {
            loginModel.setStatus(false);
            loginModel.setInfo("密码错误！");
        }
        return loginModel;
    }

    @RequestMapping("/admin")
    public String getAdminPage(HttpSession session) {
        if (session.getAttribute("isLogin") == "true"){
            List<Product> allProduct = productService.getAllProduct();
            for (Product product : allProduct) {
                if (product.getP_desc().length() > 22)
                    product.setP_desc(product.getP_desc().substring(0, 22) + "...");
            }
            session.setAttribute("productList", allProduct);
            return "admin";
        }else {
            return "login";
        }
    }

    //注销
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("isLogin");
        return "login";
    }
}
