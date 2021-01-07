package com.coronavirus.trackerapp.controllers;

import com.coronavirus.trackerapp.models.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class RegisterPage {

    @Autowired
    private CustomerRepository repository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/daftar")
    public String getRegister(@ModelAttribute CustomerDTO customerDTO, Model model) {
        model.addAttribute("customerDTO", customerDTO);
        return "register";
    }

    @PostMapping("/daftar")
    public String save(@ModelAttribute("customerDTO") CustomerDTO customerDTO) {
        String username = customerDTO.getUsername();
        CustomerDTO result = repository.findByUsername(username);
        if (result != null) {
            System.out.println("Data ditemukan.");
            return "redirect:login";
        } else {
            repository.save(new CustomerDTO(customerDTO.getUsername(), passwordEncoder.encode(customerDTO.getPassword())));
            return "redirect:login";
        }
    }

    @GetMapping("/login")
    public String getLogin(@ModelAttribute CustomerDTO customerDTO, Model model) {
        model.addAttribute("customerDTO", customerDTO);
        return "login";
    }

    @PostMapping("/login")
    public String Postlogin(@ModelAttribute CustomerDTO customerDTO, Model model) {
        model.addAttribute("customerDTO", customerDTO);
        String username = customerDTO.getUsername();
        CustomerDTO usernameQuery = repository.findByUsername(username);
        boolean isTrue = passwordEncoder.matches(customerDTO.getPassword(), usernameQuery.getPassword());
        if (isTrue) {
            return "redirect:/";
        } else {
            System.out.println("Password tidak cocok");
            return "redirect:login";
        }

    }
}
