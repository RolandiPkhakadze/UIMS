package com.example.uims.controller;

import com.example.uims.entity.Conviction;
import com.example.uims.service.ConvictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/convictions")
public class ConvictionController {

    private final ConvictionService service;

    @Autowired
    public ConvictionController(ConvictionService service) {
        this.service = service;
    }

    @GetMapping("/user/{userId}")
    public List<Conviction> getAllConvictionsByUserId(@PathVariable(name = "userId") final long userId) {
        return service.getAllConvictionsByUserId(userId);
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