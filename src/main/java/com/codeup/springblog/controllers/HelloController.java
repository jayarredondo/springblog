package com.codeup.springblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello from Spring!";
    }

    @GetMapping("/goodbye")
    @ResponseBody
    public String goodbye(){
        return "Goodbye!";
    }

//    @GetMapping("/hello/{name}")
//    @ResponseBody
//    public String sayHello(@PathVariable String name){
//        return "Hello, " + name;
//    }

    @GetMapping("/book/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        // get this ID and connect to my db to get the book information.
        return "Viewing Book " + id;
    }

    @RequestMapping(path = "/hello/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(@PathVariable String name){
        return "Hello " + name + " from a request mapping!";
    }
}
