package com.example.cms.cart.domain;

import com.example.cms.cartitem.domain.CartItem;
import com.example.cms.item.domain.Item;

public class Carter {

    public Cart make(Cart cart, Item item, int count){
        cart.addTotalPrice(item.getCost() * count);
        cart.addCountCart(count);
        System.out.println("cart = " + cart);

        return cart;
    }

}
