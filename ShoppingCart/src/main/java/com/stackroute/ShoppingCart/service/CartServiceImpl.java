package com.stackroute.ShoppingCart.service;

import com.stackroute.ShoppingCart.exception.NoProuctsFound;
import com.stackroute.ShoppingCart.model.ShoppingCart;
import com.stackroute.ShoppingCart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;

    @Override
    public ShoppingCart saveCatalogInCart(ShoppingCart shoppingCart) {
        double calculatePrice= Math.round(shoppingCart.getPrice() * shoppingCart.getQuantity());
        ShoppingCart cart = new ShoppingCart(shoppingCart.getOrderId(),shoppingCart.getEmailId(), shoppingCart.getCatalogId(), shoppingCart.getQuantity(),calculatePrice);
        return cartRepository.save(cart);
    }

    @Override
    public List<ShoppingCart> getAllItemFromCartByUserId(String emailId) throws NoProuctsFound {
        List<ShoppingCart> byEmailId = cartRepository.findByEmailId(emailId);
        if (byEmailId.isEmpty()){
            throw new NoProuctsFound("No Orders are placed for the email :"+emailId);
        }
        return byEmailId;
    }

}
