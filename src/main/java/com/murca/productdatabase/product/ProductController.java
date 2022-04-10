package com.murca.productdatabase.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    @Autowired private ProductService service;
    @GetMapping("/products")
    public String showProductDb(Model model)
    {
        List<Products> listProducts = service.productList();
        model.addAttribute("listProducts",listProducts);
        return "products";
    }

    @GetMapping("/products/new")
    public String showForm(Model model)
    {
        model.addAttribute("product",new Products());
        model.addAttribute("pageTitle","Add New Product");
        return "user_form";
    }
    @PostMapping("/products/save")
    public String saveUser(Products products, RedirectAttributes ra)
    {
        service.save(products);
        ra.addFlashAttribute("massage","The Product Has been Saved Successfully!!");
        return "redirect:/products";
    }
    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable("id")Integer id,Model model,RedirectAttributes ra)
    {
        try {
           Products product= service.get(id);
           model.addAttribute("product",product);
           model.addAttribute("pageTitle","Edit Product ( ID " + id +")");
           return  "user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("massage",e.getMessage());
            return "redirect:/products";
        }
    }
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id")Integer id,RedirectAttributes ra)
    {
        try {
           service.delete(id);
            return  "user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("massage",e.getMessage());

        }
        return "redirect:/products";
    }

}
