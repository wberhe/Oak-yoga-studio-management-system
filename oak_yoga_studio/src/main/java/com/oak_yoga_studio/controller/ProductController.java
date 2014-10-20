/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.controller;

import com.oak_yoga_studio.domain.Product;
import com.oak_yoga_studio.service.IProductService;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Weldino
 */
@Controller
public class ProductController {

    @Autowired
    private IProductService productService;

   

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getAll(Model model) {

        model.addAttribute("products",productService.getAllProducts() );
        return "productList";
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public String get(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        return "editProduct";
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.POST)
    public String editProduct(@Valid Product product, BindingResult result,
            @PathVariable int id) {
        String view = "redirect:/products";
        if (!result.hasErrors()) {
            productService.updateProduct(product);
        } else {
            view = "editProduct";
        }

        return view;
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String addProductGet(@ModelAttribute("product") Product product) {
        return "addProduct";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProduct(@Valid Product product, BindingResult result, RedirectAttributes re,@RequestParam("file") MultipartFile file) {
        String view = "redirect:/products";
        //System.out.println("outSide adding product");
        if (!result.hasErrors()) {
            try {
                product.setImage(file.getBytes());
            } catch (IOException ex){
                //Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println("Not error");
            productService.addProduct(product);
        } else {
            //System.out.println("inside error adding product");
            view = "addProduct";
        }
        return view;
    }

//    @RequestMapping(value = "/products/delete", method = RequestMethod.POST)
//    public String delete(@RequestParam(value = "productId", required = true) int prodid) {
//        productService.d
//        return "redirect:/products";
//    }
    
    
    
    @RequestMapping(value="/productResult", method = RequestMethod.GET)
    public String searchProductResult(){
        return "productList";
    }
    
    @RequestMapping(value="/searchProduct", method = RequestMethod.POST)
    public String searchProduct(RedirectAttributes re, Model model, String productName){
      List<Product> products =  productService.getProductByName(productName);
        
        if(products.size()>0) {  //searched product found           
          re.addFlashAttribute("products", products); 
          return "redirect:/productResult";
      }
        else{
            re.addFlashAttribute("msg", "Product not found, please try again"); 
            return "redirect:/notFound";
        }
    }
    @RequestMapping(value="/searchProduct", method = RequestMethod.GET)
    public String searchProductByName(){;      
        return "searchProduct";
    }
    
    /**
     * Image display
     */
@RequestMapping(value = "/poductImage/{id}", method = RequestMethod.GET)
    public void getProductImage(Model model, @PathVariable int id, HttpServletResponse response) {
        try {
            Product p = productService.getProduct(id);
            if (p != null) {
                OutputStream out = response.getOutputStream();
                out.write(p.getImage());
                response.flushBuffer();
            }
        } catch (IOException ex) {
           // Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

