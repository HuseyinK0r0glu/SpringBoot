package com.luv2code.springboot.thymeleafdome.controller;

import com.luv2code.springboot.thymeleafdome.entity.Employee;
import com.luv2code.springboot.thymeleafdome.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    // since we have only one constructor @Autowired is optional
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // add a mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model model){

        // get the employees from db
        List<Employee> employees = employeeService.findAll();

        // add to the spring model
        model.addAttribute("employees",employees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        Employee employee = new Employee();

        model.addAttribute("employee",employee);

        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId,Model model){

        // get the employee from the service
        Employee employee = employeeService.findById(theId);

        // set employee in the model to prepopulate the form
        model.addAttribute("employee",employee);

        // send over to our form
        return "/employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){

        // save the employee
        employeeService.save(employee);

        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int theId){

        // delete the employee form the database
        employeeService.deleteById(theId);

        // redirect to the /employees/list
        return "redirect:/employees/list";
    }
}
