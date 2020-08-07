package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Category;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.CategoryRepository;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final UserRepository usersDao;
    private final PostRepository postsDao;
    private final CategoryRepository catDao;
    private final EmailService emailService;

    public PostController(UserRepository usersDao, PostRepository postsDao, CategoryRepository catDao, EmailService emailService) {
        this.usersDao = usersDao;
        this.postsDao = postsDao;
        this.catDao = catDao;
        this.emailService = emailService;
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
    public String createPostForm(Model model){
        model.addAttribute("post", new Post());
        List<Category> categories = catDao.findAll();
        model.addAttribute("categories", categories);
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post, @RequestParam(value = "categoryIds", required = false) long [] categoryIds){
        List<Category> postCategories = new ArrayList<>();
        if(categoryIds != null) {
            for (long category : categoryIds) {
                postCategories.add(catDao.getOne(category));
            }
        }
        post.setParentUser(usersDao.getOne(1L));
        post.setCategories(postCategories);
        postsDao.save(post);
        emailService.prepareAndSend(post, "New Post Created", "Your new post has been created!");
        return "redirect:/posts";
    }

    // ----EDIT AND DELETE------

    @PostMapping ("/posts/{id}/edit")
    public String save(@PathVariable long id, @ModelAttribute Post post){
        User user = usersDao.getOne(1L);
        post.setParentUser(user);
        // need to set categories as well
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model){
        Post postToEdit = postsDao.getOne(id);
        model.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/delete")
    public String deletePost(@RequestParam(name = "deletePost") long id){
        Post postToDelete = postsDao.getOne(id);
        postsDao.delete(postToDelete);
        return "redirect:/posts";
    }
}
