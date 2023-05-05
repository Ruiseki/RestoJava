package com.example.javaresto.classes;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private List<Employee> employeeList;
    // name
    private String name;
    // job
    private String job;
    // hours worked
    private int hoursWorked;

    public Employee(String name, String job, int hoursWorked) {
        this.name = name;
        this.job = job;
        this.hoursWorked = hoursWorked;
        this.employeeList = new ArrayList<>();
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }
    
}
