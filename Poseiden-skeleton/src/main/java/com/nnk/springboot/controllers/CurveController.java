package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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
public class CurveController {
    // Injects Curve Point service
    @Autowired
    CurvePointRepository curvePointRepository;

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        // Finds all Curve Point, binds to model
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // Checks data valid and save to db, then return Curve list
        if (!result.hasErrors()) {
            curvePointRepository.save(curvePoint);
            model.addAttribute("curvePoint", curvePoint);
            return "redirect:/curvePoint/list";
        }
        else {
            return "curvePoint/add";
        }
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // Gets CurvePoint by Id and to model then show to the form
        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid curve point:" + id));
        curvePoint.setId(id); //why this is needed?
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // Checks required fields, if valid call service to update Curve and return Curve list
        if (!result.hasErrors()) {
            curvePointRepository.save(curvePoint);
            curvePoint.setId(id);
            model.addAttribute("curvePoints", curvePointRepository.findAll()); //gets all the values with the latest add
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/update";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // Finds Curve by Id and delete the Curve, return to Curve list
        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid user id:"+id));
        curvePointRepository.deleteById(id);
        model.addAttribute("curvePoint", curvePoint);
        return "redirect:/curvePoint/list";
    }
}
