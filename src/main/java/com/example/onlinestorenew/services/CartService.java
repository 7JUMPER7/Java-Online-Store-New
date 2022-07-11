package com.example.onlinestorenew.services;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CartService {
    public static boolean addToCart(HttpSession session, int goodId) {
        try {
            List<Integer> cart = (List<Integer>) session.getAttribute("cart");
            if(cart == null) {
                cart = new ArrayList<Integer>();
                cart.add(goodId);
            } else {
                cart.add(goodId);
            }
            session.setAttribute("cart", cart);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static List<Integer> getCart(HttpSession session) {
        List<Integer> cart = (List<Integer>) session.getAttribute("cart");
        if(cart == null) {
            cart = new ArrayList<Integer>();
        }
        return cart;
    }

    public static Integer getCount(HttpSession session) {
        Integer count = getCart(session).size();
        if(count == 0) {
            return null;
        }
        return count;
    }

    public static boolean removeFromCart(HttpSession session, Integer goodId) {
        try {
            List<Integer> cart = (List<Integer>) session.getAttribute("cart");
            if(cart == null) {
                return true;
            }
            cart.remove(goodId);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean clearCart(HttpSession session) {
        try {
            session.setAttribute("cart", new ArrayList<Integer>());
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
