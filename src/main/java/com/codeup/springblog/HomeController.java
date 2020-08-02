package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @GetMapping ("/")
    public String home(){
        return "home";
    }

    @GetMapping("/roll-dice")
    public String rollDice(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String showRoll(@PathVariable int n, Model model){
        long die1 = (int)(Math.random()* n +1);
        model.addAttribute("answer", die1);
        model.addAttribute("number", n);
        return "results";
    }
}
