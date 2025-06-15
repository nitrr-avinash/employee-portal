package com.swiss.re.employee.portal;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Employee {
    private int id;
    private String name;
    private String city;
    private String state;
    private EmpCategory category;
    private int managerId;
    private LocalDate doj;
    private double salary;
    private List<Employee> reportees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public EmpCategory getCategory() {
        return category;
    }

    public void setCategory(EmpCategory category) {
        this.category = category;
    }

    public LocalDate getDoj() {
        return doj;
    }

    public void setDoj(LocalDate doj) {
        this.doj = doj;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public List<Employee> getReportees() {
        return reportees;
    }

    public void setReportees(List<Employee> reportees) {
        this.reportees = reportees;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", category=" + category +
                ", doj=" + doj +
                ", salary=" + salary +
                '}';
    }
}
