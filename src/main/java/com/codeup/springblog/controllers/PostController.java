package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {
    @GetMapping("/posts")
    public String displayPosts(Model model){
        ArrayList<Post> allPosts = new ArrayList<>();
        Post p1 = new Post("Second Title", "This is the second post.");
        Post p2 = new Post("Third Title", "This is the third post.");
        allPosts.add(p1);
        allPosts.add(p2);
        model.addAttribute("allPosts", allPosts);
        return "posts/index";
    }

    @GetMapping ("/posts/{id}")
    public String postDetails(@PathVariable int id, Model model){
        Post singlePost = new Post("First Post", "This is my first post!");
        model.addAttribute("singlePost", singlePost);
        model.addAttribute("post_id", id);
        return "posts/show";
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
