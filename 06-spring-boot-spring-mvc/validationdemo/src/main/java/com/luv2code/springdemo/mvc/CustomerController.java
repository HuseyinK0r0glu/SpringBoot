package com.luv2code.springdemo.mvc;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    // add initBinder to convert trim input strings
    // remove leading and trailing whitespaces
    // resolve this issue for validation

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        webDataBinder.registerCustomEditor(String.class,stringTrimmerEditor);

    }

    @GetMapping("/")
    public String showForm(Model theModel){

        theModel.addAttribute("customer",new Customer());

        return "customer-form";

    }

    @PostMapping("/processForm")
    public String processForm(@Valid @ModelAttribute("customer")Customer theCostumer,
                              BindingResult theBindingResult) {

        // for debugging purposes
        System.out.println("BindingResults:" + theBindingResult.toString());

        if(theBindingResult.hasErrors()){
            return "customer-form";
        }
        else{
            return "customer-confirmation";
        }

    }

}
