package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
public class RatingController {
    // Inject Rating service
    @Autowired
    RatingRepository ratingRepository;

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        //find all Rating and binds to model
        model.addAttribute("ratings", ratingRepository.findAll());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "/rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // Check data is valid and saves record to db, then returns Rating list
        if (!result.hasErrors()) {
            ratingRepository.save(rating);
            model.addAttribute(rating);
            return "redirect:/rating/list";
        }
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // Gets Rating by Id, binds to model, then shows the form
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        rating.setId(id);
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        // Checks required fields, if valid calls service to update Rating and returns Rating list
        if (!result.hasErrors()) {
            rating.setId(id);
            ratingRepository.save(rating);
            model.addAttribute("rating", ratingRepository.findAll()); //gets the latest list with the new rating
            return "redirect:/rating/list";
        }
        else {
            return "rating/update";
        }
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // Finds Rating by Id and delete it, then returns update rating list
        ratingRepository.deleteById(id);
        model.addAttribute("ratings", ratingRepository.findAll());
        return "redirect:/rating/list";
    }
}
