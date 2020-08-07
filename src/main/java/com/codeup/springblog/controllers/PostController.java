package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Category;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.CategoryRepository;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
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

    public PostController(UserRepository usersDao, PostRepository postsDao, CategoryRepository catDao) {
        this.usersDao = usersDao;
        this.postsDao = postsDao;
        this.catDao = catDao;
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
//        List<Category> categories = catDao.findAll();
//        model.addAttribute("categories", categories);
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
        return "redirect:/posts";
    }

    // ----EDIT AND DELETE------

    @PostMapping ("/posts/save")
    public String save(@RequestParam(name = "title") String title, @RequestParam(name="body") String body, @RequestParam(name = "postId") long id){
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
