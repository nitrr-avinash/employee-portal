package com.swiss.re.employee.portal.controller;

import com.swiss.re.employee.portal.Employee;
import com.swiss.re.employee.portal.services.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmpService empService;

    @GetMapping("/employees/")
    public List<Employee> employees(){
        return empService.getEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee employees(@PathVariable int id){
        return empService.getEmployee(id);
    }

    @GetMapping("/employees/highestpay")
    public Employee getNthLargestSalary(@RequestParam int nth){
        return empService.getNthLargestSalary(nth);
    }

    @GetMapping("/employees/gratuity")
    public List<Employee> getEmployeesEligibleForGratuity(){
        return empService.getEmployeesEligibleForGratuity();
    }

    @GetMapping("/employees/out-earn-mgr")
    public List<Employee> getEmployeeWithHighSalary(){
        return empService.getEmployeeOutEarnManager();
    }

    @GetMapping("/employees/heirarchy")
    public Employee getDirectorWithHierarchy(){
        return empService.getDirectorWithHierarchy();
    }
}
