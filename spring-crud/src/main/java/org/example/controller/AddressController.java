package org.example.controller;

import org.example.entity.Address;
import org.example.service.AddressService;
import org.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addressHTML")
public class AddressController {
    @Autowired
    private AddressService service;
    @GetMapping
    public String getAllPersons(Model model) {
        model.addAttribute("address", service.getAddresses());
        return "address/findAll";
    }
}