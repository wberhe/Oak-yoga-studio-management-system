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
import com.oak_yoga_studio.service.impl.AddressServiceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Weldu
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
    @Resource
    private AddressServiceImpl addressService;

    public ShoppingCartController() {
    }

    /**
     *
     * @param id
     * @param model
     * @param quantity
     * @param re
     * @param session
     * @return
     */
    @RequestMapping(value = "/cart/{id}", method = RequestMethod.POST)
    public String addToCart(@PathVariable int id, Model model, int quantity, final RedirectAttributes re, HttpSession session) {

        Customer customer = (Customer) session.getAttribute("loggedUser");
        Product product = productService.getProduct(id);
        ShoppingCartItem cartItem = new ShoppingCartItem(quantity, product);

        boolean flag = true;

        for (ShoppingCartItem items : customer.getShoppingCart().getShoppingCartItems()) {
            if (items.getProduct().equals(product)) {
                //If the items are of the same product just update the quantity
                ShoppingCartItem item = shoppingCartItemService.getCartItem(items.getId());
                item.setQuantity(item.getQuantity() + quantity);
                //update in the DB
                shoppingCartItemService.updateCartItem(item);
                flag = false;
                break;
            }
        }
        if (flag) {
            //if the items are of different product
            customer.getShoppingCart().addShoppingCartItem(cartItem);

            //persist to DB
            cartService.updateCart(customer.getShoppingCart());

        }

        String message = product.getName() + " has been added to shopping cart!";
        re.addFlashAttribute("message", message);
        return "redirect:/viewCart";

    }

    /**
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/viewCart", method = RequestMethod.GET)
    public String getShoppingCart(Model model, HttpSession session) {

        Customer c = (Customer) session.getAttribute("loggedUser");
        //Customer c=customerService.getCustomerById(customer.getId());
        model.addAttribute("shoppingCartItems", c.getShoppingCart().getShoppingCartItems());
        double total = 0;
        for (ShoppingCartItem item : c.getShoppingCart().getShoppingCartItems()) {
            total += item.getQuantity() * item.getProduct().getPrice();

        }

        model.addAttribute("totalPrice", total);
        //shopping cart jsp
        return "shoppingCart";
    }

    /**
     *
     * @param id
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/cart/delete/{id}", method = RequestMethod.POST)
    public String deleteCartItem(@PathVariable int id, Model model, HttpSession session) {

        ShoppingCartItem item = shoppingCartItemService.getCartItem(id);
        Customer customer = (Customer) session.getAttribute("loggedUser");

        customer.getShoppingCart().getShoppingCartItems().remove(item);

        shoppingCartItemService.deleteCartItem(item);

        cartService.updateCart(customer.getShoppingCart());

        //cartService.deleteShoppingCartItem(customer.getShoppingCart(), item);
        return "redirect:/viewCart";
    }

    /**
     *
     * @param address
     * @param model
     * @param re
     * @param session
     * @return
     */

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String checkout(@ModelAttribute Address address, Model model, RedirectAttributes re, HttpSession session) {
        String message = "";
        double totalPrice = 0;
        Customer c = (Customer) session.getAttribute("loggedUser");
        System.out.println("adresssssssss: " + c.getAddress());
        //
        if (c.getAddress().isEmpty()) {
            model.addAttribute("message", message);
            re.addFlashAttribute("message", "No address, Update your profile please");
            return "purchasingAddress";
        }

        for (ShoppingCartItem item : c.getShoppingCart().getShoppingCartItems()) {
            //calculate total price
            totalPrice += item.getQuantity() * item.getProduct().getPrice();
        }

        String view = "productList";

        if (c.getShoppingCart().getShoppingCartItems().isEmpty()) {
            message = "Your shopping cart is Empty";
            re.addFlashAttribute("message", message);

        } else {
            List<ShoppingCartItem> items = c.getShoppingCart().getShoppingCartItems();

            Date timeNow = new Date();

            Order order = new Order(timeNow, c.getEmail(), totalPrice, new ArrayList<OrderItem>());
            order = cartItemsToOrderItems(order, items);

            order.setCustomer(c);
            orderService.addOrder(order);

            c.addOrder(order);
            // customerService.updateCustomer(c.getId(), c);

            cartService.clearCart(c.getShoppingCart().getId());
            message = "Your order has been processed and $" + totalPrice + " will be deducted from your creditcard";
            re.addFlashAttribute("message", message);
            view = "invoice";
        }

        return view;
    }

    /**
     *
     * @param session
     * @param address
     * @return
     */
    @RequestMapping(value = "/purchaseInfo", method = RequestMethod.POST)
    public String validInformation(HttpSession session, @Valid Address address) {
        Customer c = (Customer) session.getAttribute("loggedUser");
        c.addAddress(address);
        addressService.addAddress(address);
        //customerService.updateCustomer(c.getId(), c);
        return "redirect:/checkout";
    }

    /**
     *
     * @param items
     * @return
     */
    public Order cartItemsToOrderItems(Order order, List<ShoppingCartItem> items) {

        for (ShoppingCartItem shoppingItems : items) {
            OrderItem o = new OrderItem();
            o.setProduct(shoppingItems.getProduct());
            o.setQuantity(shoppingItems.getQuantity());
            o.setPrice(shoppingItems.getProduct().getPrice());

            order.addOrderItem(o);

        }
        return order;
    }

}
