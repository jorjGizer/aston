package org.example.controller;

import org.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@Controller
@RequestMapping("/personHTML")
public class PersonController {
    @Autowired
    private PersonService service;
    @GetMapping
    public String getAllPersons(Model model) {
        model.addAttribute("person", service.getPersons());
        return "person/findAll";
    }
}
