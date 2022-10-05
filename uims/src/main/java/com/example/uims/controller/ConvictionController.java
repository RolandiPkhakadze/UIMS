package com.example.uims.controller;

import com.example.uims.entity.Conviction;
import com.example.uims.service.ConvictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/convictions")
public class ConvictionController {

    private final ConvictionService service;

    @Autowired
    public ConvictionController(ConvictionService service) {
        this.service = service;
    }

    @GetMapping("/user/{personalNo}")
    public String getAllConvictionsByUserPersonalNo(@PathVariable(name = "personalNo") final String personalNo, Model model) {
        model.addAttribute("convictions", service.getAllConvictionsByUserPersonalNo(personalNo));
        return "convictions";
    }

    @PostMapping
    public Conviction createConviction(@RequestBody final Conviction conviction) {
        return service.createConviction(conviction);
    }

    @GetMapping("/{id}")
    public Conviction getConvictionById(@PathVariable(name = "id") long id) {
        return service.getConvictionById(id);
    }
}
