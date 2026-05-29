package com.security.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.security.employee.entity.Employee;
import com.security.employee.service.EmployeeService;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/public/register")
    public String registerPage(Model model) {
        model.addAttribute("employee", new Employee());
        return "register";
    }

    @PostMapping("/public/register")
    public String registerEmployee(@ModelAttribute Employee employee) {
        service.registerUser(employee);
        return "redirect:/login?success=true";
    }

    @GetMapping("/employees")
    public String dashboard(Authentication authentication, Model model) {
        model.addAttribute("employeesList", service.getAll());
        
        if (authentication != null) {
            model.addAttribute("loggedInEmail", authentication.getName());
        }
        
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            
            model.addAttribute("newEmployee", new Employee()); 
            return "admin_dashboard"; 
        }
        
        return "employee_details"; 
    }

    @PostMapping("/admin/employee/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        service.registerUser(employee); 
        return "redirect:/employees";
    }

    @GetMapping("/admin/employee/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        model.addAttribute("employee", service.getOne(id)); 
        return "update_employee"; 
    }

    @PostMapping("/admin/employee/update/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee) {
        service.updateEmployee(id, employee);
        return "redirect:/employees";
    }

    @GetMapping("/admin/employee/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id); 
        return "redirect:/employees";
    }
}