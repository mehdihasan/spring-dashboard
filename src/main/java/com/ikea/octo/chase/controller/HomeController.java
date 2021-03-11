package com.ikea.octo.chase.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/index")
    public String showHome(Model model) {
        return "index";
    }


    @GetMapping("/tables")
    public String showTables(Model model) {
        return "basic-table";
    }


    @GetMapping("/buttons")
    public String showButton(Model model) {
        return "buttons";
    }


    @GetMapping("/chartjs")
    public String showCharts(Model model) {
        return "chartjs";
    }


    @GetMapping("/typography")
    public String showTypography(Model model) {
        return "typography";
    }


    @GetMapping("/register")
    public String showRegister(Model model) {
        return "register";
    }


    @GetMapping("/mdi")
    public String showMdi(Model model) {
        return "mdi";
    }


    @GetMapping("/blocked")
    public String showLockScreen(Model model) {
        return "lock-screen";
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/elements")
    public String basicElements(Model model) {
        return "basic_elements";
    }
}
