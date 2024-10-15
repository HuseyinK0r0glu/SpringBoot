package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.xml.transform.Source;
import java.util.List;

@Controller
public class StudentController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;

    @Value("${systems}")
    private List<String> systems;

    @GetMapping("/showStudentForm")
    public String showForm(Model theModel){

        // create a student object

        Student theStudent = new Student();

        // add student object to model

        theModel.addAttribute("student",theStudent);

        // add list of countries to model

        theModel.addAttribute("countries",countries);

        // add list of languages to model

        theModel.addAttribute("languages",languages);

        // add list of systems to model

        theModel.addAttribute("systems",systems);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent){

        // log the input data

        System.out.println("theStudent: " + theStudent.getFirstName() + " " + theStudent.getLastName());

        System.out.println("theStudent is from " + theStudent.getCountry());

        System.out.println("Favourite language of the student is: " + theStudent.getFavouriteProgrammingLanguage());

        System.out.println("Favourite Operating System: " + theStudent.getFavouriteSystem());

        return "student-confirmation";

    }

}
