package com.example.javaresto.classes;

import java.util.ArrayList;
import java.util.List;

public class Employee {


    // ----------------- ATTRIBUTES ----------------- //

    private List<Employee> employeeList;
       // name
    private String name;
    // job
    private String job;
    // hours worked
    private int hoursWorked;

    // ----------------- CONSTRUCTOR ----------------- //

    public Employee(String name, String job, int hoursWorked) {
        this.name = name;
        this.job = job;
        this.hoursWorked = hoursWorked;
        this.employeeList = new ArrayList<>();
    }

    // ----------------- GETTERS ----------------- //

    public String getName() {
        return name;
    }


    // ----------------- SETTERS ----------------- //

    public void setName(String name) {
        this.name = name;
    }

}
