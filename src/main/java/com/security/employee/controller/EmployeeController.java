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

    // Custom Login Page View
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Public Registration Form View
    @GetMapping("/public/register")
    public String registerPage(Model model) {
        model.addAttribute("employee", new Employee());
        return "register";
    }

    // Process Public Registration Form
    @PostMapping("/public/register")
    public String registerEmployee(@ModelAttribute Employee employee) {
        service.registerUser(employee);
        return "redirect:/login?success=true";
    }

 // Dynamic Dashboard Loader based on Roles
    @GetMapping("/employees")
    public String dashboard(Authentication authentication, Model model) {
        model.addAttribute("employeesList", service.getAll());
        
        // 👑 FIX: Logged-in user ka email yahan nikal kar direct Model mein daal diya
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

    // Admin Panel: Add New Employee
    @PostMapping("/admin/employee/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        service.registerUser(employee); // Naya user add karne ke liye registerUser service use hogi
        return "redirect:/employees";
    }

 // 🔄 NEW: Admin jab "Update" pr click karega, toh NAYA update page khulega
    @GetMapping("/admin/employee/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        // Sirf us specific employee ka data load karke naye page par bhejenge
        model.addAttribute("employee", service.getOne(id)); 
        return "update_employee"; // 👈 Yeh update_employee.html ko open karega
    }

    // Update Submit handle karne ke liye
    @PostMapping("/admin/employee/update/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee) {
        service.updateEmployee(id, employee);
        return "redirect:/employees";
    }

    // Admin Panel: Delete Employee
    @GetMapping("/admin/employee/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id); // ⚠️ Apni service layer mein delete method ka naam check kar lena agar alal ho
        return "redirect:/employees";
    }
}