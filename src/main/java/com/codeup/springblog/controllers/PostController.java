package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postsDao;

    public PostController(PostRepository postsDao) {
        this.postsDao = postsDao;
    }

    @GetMapping("/posts")
    public String displayPosts(Model model){
        model.addAttribute("allPosts", postsDao.findAll());
        return "posts/index";
    }


    @GetMapping ("/posts/{id}")
    public String postDetails(@PathVariable long id, Model model){
        model.addAttribute("singlePost", postsDao.getOne(id));
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

    // ----EDIT AND DELETE------

    @PostMapping ("/posts/save")
    public String save(@RequestParam(name = "title") String title, @RequestParam(name="body") String body, @RequestParam(name = "postId") long id, Model model){
        Post updatedPost = new Post(id, title, body);
        postsDao.save(updatedPost);
        return "redirect:/posts";
    }

    @PostMapping("/posts/edit")
    public String editPost(@RequestParam(name = "editPost") long id, Model model){
        Post postToEdit = postsDao.getOne(id);
        model.addAttribute("postToEdit", postToEdit);
        return "/posts/edit";
    }

    @PostMapping("/posts/delete")
    public String deletePost(@RequestParam(name = "deletePost") long id){
        Post postToDelete = postsDao.getOne(id);
        postsDao.delete(postToDelete);
        return "redirect:/posts";
    }
}
