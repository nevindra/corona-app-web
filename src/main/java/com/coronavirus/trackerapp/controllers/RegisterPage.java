package com.coronavirus.trackerapp.controllers;

import com.coronavirus.trackerapp.models.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterPage {

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/daftar")
    public String register(@ModelAttribute CustomerDTO customerDTO, Model model) {
        model.addAttribute("customerDTO", customerDTO);
        return "register";
    }

    @PostMapping("/daftar")
    public String save(@ModelAttribute("customerDTO") CustomerDTO customerDTO) {
        repository.deleteAll();
        repository.save(new CustomerDTO(customerDTO.getUsername(), customerDTO.getPassword()));
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (CustomerDTO customer : repository.findAll()) {
            System.out.println(customer);
        }
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute CustomerDTO customerDTO, Model model) {
        model.addAttribute("customerDTO", customerDTO);
        return "login";
    }
}
