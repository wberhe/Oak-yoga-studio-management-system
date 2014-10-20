package com.oak_yoga_studio.controller;


import com.oak_yoga_studio.domain.Address;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.Order;
import com.oak_yoga_studio.domain.OrderItem;
import com.oak_yoga_studio.domain.Product;
import com.oak_yoga_studio.domain.ShoppingCartItem;
import com.oak_yoga_studio.service.ICustomerService;
import com.oak_yoga_studio.service.IOrderItemService;
import com.oak_yoga_studio.service.IOrderService;
import com.oak_yoga_studio.service.IProductService;
import com.oak_yoga_studio.service.IShoppingCartItemService;
import com.oak_yoga_studio.service.IShoppingCartService;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Weldino
 */
@Controller
public class ShoppingCartController {
    
    @Resource
    private IShoppingCartService cartService;
    @Resource
    private ICustomerService customerService;
    @Resource
    private IProductService productService;
    @Resource
    private IOrderItemService orderItemService;
    @Resource
    private IOrderService orderService;
    @Resource
    private IShoppingCartItemService shoppingCartItemService;

    public ShoppingCartController() {
    }

    
    
    
    @RequestMapping(value="/cart/{id}", method = RequestMethod.POST)
    public String addToCart(@PathVariable int id, Model model,int quantity,final RedirectAttributes re,HttpSession session){
        
        System.out.println("In side AddCArt methoddddddddddd");
        Customer customer = (Customer) session.getAttribute("loggedUser"); 
        System.out.println("customer-------->"+ customer);
        
        Product product = productService.getProduct(id);
        ShoppingCartItem cartItem = new ShoppingCartItem(quantity,product);
        boolean flag = true;
        
        for(ShoppingCartItem items : customer.getShoppingCart().getShoppingCartItems()){
            if(items.getProduct().equals(product))
            {
                
              ShoppingCartItem item = shoppingCartItemService.getCartItem(items.getId());
               //orderItemService.getOrderItemById(items.getId());
              item.setQuantity(quantity);
              shoppingCartItemService.updateCartItem(item);
              //orderItemService.updateOrderItem(item);
              //orderLineItemService.update(item);
               flag=false;
                break;
            }
        }
       if(flag){
           customer.getShoppingCart().getShoppingCartItems().add(cartItem);
           shoppingCartItemService.addCartItem(cartItem);
        //user.getCart().getItems().add(orderLineItem);
        //orderLineItemService.add(orderLineItem);
       }

         String message = product.getName() +" has been added to shopping cart!";
         
         re.addFlashAttribute("message", message);
        return "redirect:/message";
    }
    
    
    
    
    
    @RequestMapping(value="/viewCart", method = RequestMethod.GET)
    public String getShoppingCart(Model model, HttpSession session){
        
        Customer c = (Customer) session.getAttribute("loggedUser"); 
        //Customer c=customerService.getCustomerById(customer.getId());
        model.addAttribute("shoppingCartItems", c.getShoppingCart().getShoppingCartItems());
        double total=0;
        
        for(ShoppingCartItem item : c.getShoppingCart().getShoppingCartItems()){
            
            total += item.getQuantity() * item.getProduct().getPrice();
            
        }
        
        model.addAttribute("totalPrice", total);
        
        return "shoppingCart";
    }
    
    
    @RequestMapping(value="/cart/addOne/{id}", method = RequestMethod.POST)
    public String addOneToCart(@PathVariable int id,Model model){
        ShoppingCartItem item=shoppingCartItemService.getCartItem(id);
        item.setQuantity(item.getQuantity()+1);
        shoppingCartItemService.updateCartItem(item);
        return "redirect:/viewCart";
    }
    
    
    @RequestMapping(value="/cart/subtractOne/{id}", method = RequestMethod.POST)
    public String subtractOneFromCart(@PathVariable int id,Model model){
        ShoppingCartItem item=shoppingCartItemService.getCartItem(id);
        if(item.getQuantity()>0)
        {
           item.setQuantity(item.getQuantity()-1);
           shoppingCartItemService.updateCartItem(item);
        }
        return "redirect:/viewCart";
    }
    
    
    @RequestMapping(value="/cart/delete/{id}", method = RequestMethod.POST)
    public String deleteCartItem(@PathVariable int id,Model model, HttpSession session){
        
        ShoppingCartItem item=shoppingCartItemService.getCartItem(id);
        Customer customer = (Customer) session.getAttribute("loggedUser"); 
       // Customer c=customerService.getCustomerById(customer.getId());
        customer.getShoppingCart().getShoppingCartItems().remove(item);
//        OrderLineItem item = orderLineItemService.get(id);
//        User user = userService.getByUsername(principal.getName());
//        user.getCart().getItems().remove(item);
//        cartService.update(user.getCart());
        return "redirect:/viewCart";
    }
    
    
    @RequestMapping(value="/checkout" , method = RequestMethod.POST)
    public String checkout(Model model,final RedirectAttributes re,HttpSession session){
         String message ="";
         double totalPrice=0;
        
        Customer c = (Customer) session.getAttribute("loggedUser"); 
        //Customer c=customerService.getCustomerById(customer.getId());
        
        if(c.getAddress()==null){
            model.addAttribute("message", message);
            re.addFlashAttribute("message", "No address, Update your profile please");
            return "redirect:/message";
         }
        
        for(ShoppingCartItem item : c.getShoppingCart().getShoppingCartItems()){
            //calculate total price
            totalPrice += item.getQuantity() * item.getProduct().getPrice();
        }
        
        String view="redirect:/message";
       
        if(c.getShoppingCart().getShoppingCartItems().isEmpty()){
             message="Your shopping cart is Empty";
            re.addFlashAttribute("message", message);
        }else{
//            List<ShoppingCartItem> items = c.getShoppingCart().getShoppingCartItems();
//            List<OrderItem> orderItems=cartItemsToOrderItems(items);
//            
//            
//            Date timeNow = new Date();
//            Order order = new Order(timeNow,c.getEmail(),totalPrice, orderItems);
//            c.getOrders().add(order);
//            customerService.updateCustomer(c.getId(),customer);
//
//            
//            cartService.clearCart(c.getShoppingCart().getId());
            message="Your order has been processed and $"+totalPrice+" will be deducted from your creditcard";
            re.addFlashAttribute("message", message);
            view = "redirect:/message";
        }
        
    return view;
    }
    
    @RequestMapping(value="/message", method = RequestMethod.GET)
    public String getMessage(ModelMap model){
        //model.addAttribute("message", model.get("message"));
        return "shoppingMessage";
    }
    
//    @RequestMapping(value="/purchasingAddress", method = RequestMethod.GET)
//    public String invalidInfo(HttpSession session,@Valid Address address){
//        return "purchaseInfo";
//    }
//    
//    @RequestMapping(value="/purchasingAddress", method = RequestMethod.POST)
//    public String invalidInfoSent(HttpSession session,@Valid Address address, BindingResult result){
//        return "purchasingAddress";
//    }
    
    /**
     * 
     * @param items
     * @return 
     */
    public List<OrderItem> cartItemsToOrderItems(List<ShoppingCartItem> items){
        List<OrderItem> orderitems = null;
        for(ShoppingCartItem shoppingItems : items){
            OrderItem o=new OrderItem();
                o.setProduct(shoppingItems.getProduct());
                o.setQuantity(shoppingItems.getQuantity());
              orderitems.add(o);
        }
        return orderitems;
    }
    
    
}
