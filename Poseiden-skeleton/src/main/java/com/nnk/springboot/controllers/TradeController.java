package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
public class TradeController {
    //Injects trade service
    @Autowired
    TradeRepository tradeRepository;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        //Find all Trade, add to model
        model.addAttribute("trades",tradeRepository.findAll());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // Check data valid and save to db, after saving return Trade list
        if (!result.hasErrors()) {
            tradeRepository.save(trade);
            model.addAttribute("trade", trade);
            return "redirect:/trade/list";
        }
        else {
            return "trade/add";
        }
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // Gets Trade by Id and to model then show to the form
        Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid trade id:" + id));
        trade.setTradeId(id);
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        // Check required fields, if valid call service to update Trade and return Trade list
        if (!result.hasErrors()) {
            tradeRepository.save(trade);
            trade.setTradeId(id);
            model.addAttribute("trades", trade);
            return "redirect:/trade/list";
        }
        else {
            return "redirect:/trade/update";
        }
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // Find Trade by Id and delete the Trade, return to Trade list
        Trade trade = tradeRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid user id:"+id));
        tradeRepository.deleteById(id);
        model.addAttribute("trade", trade);
        return "redirect:/trade/list";
    }
}
