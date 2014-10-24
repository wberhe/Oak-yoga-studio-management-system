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
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Weldu
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
        if (!result.hasErrors()) {
            try {
                product.setImage(file.getBytes());
                product.setStatus("ACTIVE");
            } catch (IOException ex){
                ex.printStackTrace();
            }
            productService.addProduct(product);
        } else {
            //System.out.println("inside error adding product");
            view = "addProduct";
        }
        return view;
    }
    /**
     * EDIT PRODUCT
     * @param prodid
     * @return 
     */
    @RequestMapping(value = "/productEdit/{id}", method = RequestMethod.GET)
    public String getProduct(@ModelAttribute("product") Product product,@PathVariable int id) {
        //model.addAttribute("product", productService.getProductDetailInfo(id));
        
        return "editProduct";
    }

    @RequestMapping(value = "/productEdit", method = RequestMethod.POST)
    public String updateProduct(@Valid Product product,BindingResult result,@RequestParam("file") MultipartFile file) {
        
          if (!result.hasErrors()) {
              try {
                product.setImage(file.getBytes());
                product.setStatus("ACTIVE");
                productService.updateProduct(product);
            } catch (IOException ex){
               ex.printStackTrace();
            }
               
              return "redirect:/products";
          } else{
              for (FieldError err : result.getFieldErrors()) {
                System.out.println(err.getField() + ": " + err.getDefaultMessage());
                
            }
              return "editProduct";
          }
            
    }
/**
 * 
 * @return 
 */
    
    
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
            re.addFlashAttribute("msgs", "Product not found, please try again"); 
            return "notFound";
        }
    }
    @RequestMapping(value="/searchProduct", method = RequestMethod.GET)
    public String searchProductByName(){;      
        return "searchProduct";
    }
    
    /**
     * Image display
     */
@RequestMapping(value = "/productImage/{id}", method = RequestMethod.GET)
    public void getProductImage(Model model, @PathVariable int id, HttpServletResponse response) {
        try {
            Product p = productService.getProduct(id);
            if (p != null) {
                OutputStream out = response.getOutputStream();
                out.write(p.getImage());
                response.flushBuffer();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

