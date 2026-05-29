# Employee Workspace & Security Management System

A secure, enterprise-grade **Employee Management Web Application** built using **Spring Boot**, **Spring Security**, **Thymeleaf**, and **PostgreSQL**. This application provides a role-based employee workspace where administrators can manage employees while users can securely access their personal profile dashboards.

---

## Live Live Demo

Access the deployed application here:

**Render Live URL:**  
[(https://employee-rest-with-security.onrender.com)](https://employee-rest-with-security.onrender.com)

---

## Features

### Authentication & Security
- Secure login/logout system powered by **Spring Security**
- **BCrypt password encryption** for credential safety
- Custom authentication using employee email as username
- Session invalidation and secure logout mechanism
- Cache-control headers to prevent unauthorized browser back navigation after logout

### Role-Based Access Control (RBAC)
#### Admin (`ROLE_ADMIN`)
- Access secure admin dashboard
- Add new employees
- Update employee details
- Delete employee records
- Manage employee roles and departments

#### User (`ROLE_USER`)
- Access personal employee workspace
- View profile details
- View administrator contacts

### Employee Management
- Employee registration system
- Secure profile management
- Department and salary tracking
- Dynamic dashboard rendering based on roles

### Modern UI
- Responsive and clean interface using **Tailwind CSS**
- Custom login and registration pages
- Interactive employee management dashboard

---

## Tech Stack

| Technology | Usage |
|------------|-------|
| Spring Boot | Backend Framework |
| Spring Security | Authentication & Authorization |
| Thymeleaf | Server-side Rendering |
| PostgreSQL | Database |
| Spring Data JPA | ORM & Database Operations |
| HikariCP | Connection Pooling |
| Tailwind CSS | Frontend Styling |

---

## Project Structure

```bash
src/main/java/com/security/employee
│── config/
│   └── SecurityConfig.java
│
│── controller/
│   └── EmployeeController.java
│
│── entity/
│   └── Employee.java
│
│── repository/
│   └── EmployeeRepository.java
│
│── security/
│   └── SecurityUser.java
│
│── service/
│   ├── EmployeeService.java
│   └── CustomUserDetailService.java
│
└── EmployeeApplication.java


src/main/resources
│── templates/
│   ├── admin_dashboard.html
│   ├── employee_details.html
│   ├── login.html
│   ├── register.html
│   └── update_employee.html
│
└── application.properties
```

### Structure Overview

- **config/** → Spring Security configuration and authentication rules  
- **controller/** → Handles HTTP requests and page navigation  
- **entity/** → Database entity classes  
- **repository/** → Database access layer using Spring Data JPA  
- **security/** → Custom security user implementation for authentication  
- **service/** → Business logic and user management services  
- **templates/** → Thymeleaf frontend pages  
- **application.properties** → Database and application configuration  
- **Dockerfile** → Docker container setup  
- **pom.xml** → Maven dependencies and project configuration  

---

### Configure PostgreSQL Database

Update `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

### Run the Application

Application runs on:

```bash
http://localhost:8080
```

---

## 🔒 Security Highlights

- Role-based endpoint protection using Spring Security
- BCrypt encrypted passwords
- Session invalidation after logout
- Secure authentication flow
- No browser history access after logout
- Protected admin-only endpoints

---

## 📸 Application Modules

- Login Page  
- Registration Page  
- Admin Dashboard  
- Employee Dashboard  
- Employee Update Management  
- Secure Logout Flow  

---

## Author

**Prachi Prajapati**

---
