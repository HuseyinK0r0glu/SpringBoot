package com.luv2code.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloWorldController {

    // need a controller method to show the initial HTML form

    @GetMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }

    // need a controller method to process the HTML form

    @RequestMapping("/processForm")
    public String processForm(){
        return "HelloWorld";
    }

    // need a controller method to read form data and add data to the model

    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request , Model model){

        // read the request parameter from the HTML form

        String theName = request.getParameter("studentName");

        // convert that data to all upper case

        theName = theName.toUpperCase();

        // create a message

        String result = "Yo! " + theName;

        // add message to the model

        model.addAttribute("message",result);

        // spring will understand this as helloworld.html

        return "helloworld";

    }



    @GetMapping("/processFormVersionThree")
    public String processFormVersionThree(@RequestParam("studentName") String theName,
                                          Model model){

        // convert that data to all upper case

        theName = theName.toUpperCase();

        // create a message

        String result = "Hey my friend from v3! " + theName;

        // add message to the model

        model.addAttribute("message",result);

        // spring will understand this as helloworld.html

        return "helloworld";

    }
}
