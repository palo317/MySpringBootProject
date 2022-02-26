package com.stackroute.ShoppingCart.service;


import com.stackroute.ShoppingCart.exception.NoProuctsFound;
import com.stackroute.ShoppingCart.model.ShoppingCart;

import java.util.List;

public interface CartService {
    ShoppingCart saveCatalogInCart(ShoppingCart shoppingCart);
    List<ShoppingCart> getAllItemFromCartByUserId(String emailId) throws NoProuctsFound;
}
