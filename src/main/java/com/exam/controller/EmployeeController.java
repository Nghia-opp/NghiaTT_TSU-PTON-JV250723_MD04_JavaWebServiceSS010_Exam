package com.exam.controller;

import com.exam.model.entity.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@EnableGlobalAuthentication()
public class EmployeeController {
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"Nguyễn Công Hưởng",5000));
        employees.add(new Employee(2,"Phạm Tuấn Bình",5000));
        employees.add(new Employee(3,"Nguyễn Văn A",3000));
        Date date = new Date("22/05/2025");
        return ResponseEntity.ok(employees);
    }
}
