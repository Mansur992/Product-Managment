package com.product.controller;

import com.product.entity.Products;
import com.product.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String home(Model model){
        List<Products> list = productRepository.findAll();
        model.addAttribute("all_products",list);
        return "index";
    }



    @GetMapping("/load_form")
    public String loadForm(){
        return "add";
    }



    @GetMapping("/edit_form/{id}")
    public String editForm(@PathVariable(value = "id") long id,Model model){
        Optional<Products> product = productRepository.findById(id);
        Products pro = product.get();
        model.addAttribute("product", pro);

        return "edit";
    }

    @PostMapping("/save_products")
    public String saveProducts(@ModelAttribute Products products, HttpSession session){
        productRepository.save(products);
        session.setAttribute("msg", "Product Added Sucessfuly..");

        return "redirect:/";
    }

    @PostMapping("/update_products")
    public String updateProducts(@ModelAttribute Products products, HttpSession session){
        productRepository.save(products);
        session.setAttribute("msg", "Product Updated Sucessfuly..");

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteProducts(@PathVariable(value = "id") long id, HttpSession session){
         productRepository.deleteById(id);

        return "redirect:/";
    }
}
