package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String displayPosts(){
        return "This is where the posts will be displayed!";
    }

    @GetMapping ("/posts/{id}")
    @ResponseBody
    public String postDetails(@PathVariable int id){
        return "This is where you can get details about post #" +id + "!";
    }

    @GetMapping ("/posts/create")
    @ResponseBody
    public String createPostForm(){
        return "This is where you can fill out the form to create a post.";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "This will create a new post.";
    }
}
