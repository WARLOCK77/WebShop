package myapp.webshop.controllers;


import jakarta.servlet.http.HttpSession;
import myapp.webshop.service.SessionObjectHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
public class HomeController {

    private final SessionObjectHolder sessionObjectHolder;

    public HomeController(SessionObjectHolder sessionObjectHolder) {
        this.sessionObjectHolder=sessionObjectHolder;
    }

    @RequestMapping({"","/"})
    public String index(Model model, HttpSession httpSession) {
        model.addAttribute("amountClicks", sessionObjectHolder.getAmountClicks());
        if (httpSession.getAttribute("myID") == null) {
            String uuid = UUID.randomUUID().toString();
            httpSession.setAttribute("myID", uuid);
            System.out.println("Generated UUID -> " + uuid);
        }
        model.addAttribute("uuid", httpSession.getAttribute("myID"));
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

}
