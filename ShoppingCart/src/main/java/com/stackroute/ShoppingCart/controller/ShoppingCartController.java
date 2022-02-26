package com.stackroute.ShoppingCart.controller;

import com.stackroute.ShoppingCart.exception.CatalogAndQuantityException;
import com.stackroute.ShoppingCart.exception.NoProuctsFound;
import com.stackroute.ShoppingCart.exception.UserIdNotExistException;
import com.stackroute.ShoppingCart.model.ShoppingCart;
import com.stackroute.ShoppingCart.service.CartService;
import com.stackroute.ShoppingCart.service.CatalogServiceFeign;
import com.stackroute.ShoppingCart.service.UserServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cart")
public class ShoppingCartController {

    private CartService cartService;
    private CatalogServiceFeign catalogServiceFeign;
    private UserServiceFeign userServiceFeign;

    @Autowired
    public ShoppingCartController(CartService cartService, CatalogServiceFeign catalogServiceFeign, UserServiceFeign userServiceFeign) {
        this.cartService = cartService;
        this.catalogServiceFeign = catalogServiceFeign;
        this.userServiceFeign = userServiceFeign;
    }

    @PostMapping(value = "/addtocart")
    ResponseEntity<?> saveCatalogItemToCart(@RequestBody ShoppingCart shoppingCart) throws UserIdNotExistException, CatalogAndQuantityException {
        Boolean checkUserIdExist = userServiceFeign.checkUserExist(shoppingCart.getEmailId());

        if (checkUserIdExist == false){
            throw  new UserIdNotExistException("Please check the Email Id");
        }
        double price = catalogServiceFeign.getPriceById(shoppingCart.getCatalogId());
        if(price == -1){
            throw new CatalogAndQuantityException("CatalogId is incorrect");
        }
        shoppingCart.setPrice(price);
        Boolean checkcatalogIdandQuantity = catalogServiceFeign.checkCatalogIdAndQuantity(shoppingCart.getCatalogId(),shoppingCart.getQuantity());
        if (checkcatalogIdandQuantity ==false){
            throw new CatalogAndQuantityException("Either CatalogId is incorrect or Quantity not Available in Stock");
        }

            return new ResponseEntity<ShoppingCart>(cartService.saveCatalogInCart(shoppingCart), HttpStatus.OK);

    }

    @GetMapping("/viewcart/{emailId}")
    ResponseEntity<?> viewItemsFromCartByEmailId(@PathVariable("emailId") String emailId) throws NoProuctsFound {

        return new ResponseEntity<List<ShoppingCart>>(cartService.getAllItemFromCartByUserId(emailId),HttpStatus.OK);
    }


}
