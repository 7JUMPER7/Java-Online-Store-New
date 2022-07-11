package com.example.onlinestorenew.controllers;

import com.example.onlinestorenew.models.GoodEntity;
import com.example.onlinestorenew.models.UserEntity;
import com.example.onlinestorenew.services.CartService;
import com.example.onlinestorenew.services.GoodService;
import com.example.onlinestorenew.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {
    @GetMapping(value = "/cart")
    public String getCart(HttpServletRequest request, Map<String, Object> model) {
        List<Integer> cart = CartService.getCart(request.getSession());

        double totalPrice = 0;
        List<GoodEntity> goods = new ArrayList<GoodEntity>();
        GoodService goodService = new GoodService();

        for(Integer goodId : cart) {
            GoodEntity buf = goodService.findById(goodId);
            if(buf != null) {
                goods.add(buf);
                totalPrice += buf.getPrice();
            }
        }

        String email = request.getRemoteUser();
        if(email != null) {
            UserService userService = new UserService();
            UserEntity user = userService.findByEmail(email);
            model.put("name", user.getName());
            model.put("city", user.getCity());
            model.put("country", user.getCountry());
            model.put("phone", user.getPhone());
            model.put("email", user.getEmail());
        }

        model.put("goods", goods);
        model.put("totalPrice", totalPrice);
        model.put("cartCount", CartService.getCount(request.getSession()));
        return "/cart/cart";
    }

    @GetMapping(value = "/cart/delete/{id}")
    public RedirectView deleteFromCart(HttpServletRequest request, @PathVariable(name = "id") Integer id) {
        CartService.removeFromCart(request.getSession(), id);
        return new RedirectView("/cart");
    }
}
