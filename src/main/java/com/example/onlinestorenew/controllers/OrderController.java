package com.example.onlinestorenew.controllers;

import com.example.onlinestorenew.models.GoodEntity;
import com.example.onlinestorenew.models.OrderEntity;
import com.example.onlinestorenew.services.CartService;
import com.example.onlinestorenew.services.GoodService;
import com.example.onlinestorenew.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
    @GetMapping(value = "/admin/order")
    public String viewOrders(Map<String, Object> model) {
        OrderService orderService = new OrderService();
        model.put("orders", orderService.getAllOrders());
        return "/order/index";
    }

    @PostMapping(value = "/order")
    public RedirectView makeOrder(
            HttpServletRequest request,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "city") String city,
            @RequestParam(name = "country") String country,
            @RequestParam(name = "department") String department
    ) {
        List<Integer> cart = CartService.getCart(request.getSession());

        double totalPrice = 0;
        List<GoodEntity> goods = new ArrayList<GoodEntity>();
        GoodService goodService = new GoodService();

        String listInfo = "";
        for(Integer goodId : cart) {
            GoodEntity buf = goodService.findById(goodId);
            if(buf != null) {
                goods.add(buf);
                listInfo += buf.getName() + " | " + buf.getPrice() + "UAH | " + buf.getId() + "\n";
                totalPrice += buf.getPrice();
            }
        }

        OrderEntity order = new OrderEntity();
        order.setName(name);
        order.setPhone(phone);
        order.setEmail(email);
        order.setPlace(country + ", " + city + ". Department: " + department + ".");
        order.setTotalPrice(totalPrice);
        order.setList(listInfo);

        OrderService orderService = new OrderService();
        orderService.createOrder(order);

        CartService.clearCart(request.getSession());

        return new RedirectView("/");
    }
}
