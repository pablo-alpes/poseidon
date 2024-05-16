package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BidListController {
    //Injects Bid service
    @Autowired
    BidListRepository bidListRepository;

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // Calls service find all bids to show to the view
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bidList, BindingResult result, Model model) {
        // Checks data is valid and saves to db, then returns bid list
        if (!result.hasErrors()) {
            bidListRepository.save(bidList);
            model.addAttribute("bidList", bidList);
            return "redirect:/bidList/list";
        }
        else {
            return "bidList/add";
        }
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // Gets Bid by Id and to model then show to the form
        BidList bidList = bidListRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("invalid bid id: "+id));
        bidList.setId(id);
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        // Checks required fields, if valid call service to update Bid and return list Bid
        if (!result.hasErrors()) {
            bidListRepository.save(bidList);
            bidList.setId(id);
            model.addAttribute("bidList", bidListRepository.findAll()); //gets all the values with the latest add
            return "redirect:/bidList/list";
        }
        return "bidList/update";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // Finds Bid by Id and delete the bid, return to Bid list
        BidList bidList = bidListRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid bid id"+ id));
        bidListRepository.deleteById(id);
        model.addAttribute("bidList", bidList); //gets all
        return "redirect:/bidList/list";
    }
}