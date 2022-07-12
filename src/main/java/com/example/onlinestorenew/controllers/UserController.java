package com.example.onlinestorenew.controllers;

import com.example.onlinestorenew.models.UserEntity;
import com.example.onlinestorenew.services.CartService;
import com.example.onlinestorenew.services.OrderService;
import com.example.onlinestorenew.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class UserController {
    @GetMapping(value = "/user")
    public String Index(HttpServletRequest request, Map<String, Object> model) {
        String email = request.getRemoteUser();
        if(email == null) {
            return "/errors/404";
        }

        UserService userService = new UserService();
        UserEntity user = userService.findByEmail(email);
        model.put("id", user.getId());
        model.put("name", user.getName());
        model.put("email", user.getEmail());
        model.put("phone", user.getPhone());
        model.put("city", user.getCity());
        model.put("country", user.getCountry());

        OrderService orderService = new OrderService();
        model.put("orders", orderService.getUserOrders(user.getEmail()));

        model.put("cartCount", CartService.getCount(request.getSession()));
        return "/user/user";
    }
    @PostMapping(value = "/user/edit/{id}")
    public RedirectView EditUser(
            @PathVariable(name = "id") Integer id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "city") String city,
            @RequestParam(name = "country") String country,
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "newPassword") String newPassword
    ) {
        UserService userService = new UserService();
        UserEntity user = userService.findById(id);
        user.setName(name);
        user.setCity(city);
        user.setCountry(country);
        user.setPhone(phone);

        userService.updateUser(user, newPassword);
        return new RedirectView("/user");
    }

    @GetMapping(value = "/admin/user")
    public String ViewAllUsers(Map<String, Object> model) {
        UserService userService = new UserService();
        model.put("users", userService.findAll());
        return "/user/index";
    }
}
