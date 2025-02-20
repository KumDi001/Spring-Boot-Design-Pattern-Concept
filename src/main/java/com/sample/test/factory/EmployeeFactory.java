package com.sample.test.factory;

import com.sample.test.AbstractFactory.EmployeeAbstractFactory;
import com.sample.test.entity.AndriodDeveloper;
import com.sample.test.entity.Employees;
import com.sample.test.entity.WebDeveloper;

//Factory design pattern
//Next layer is abstract Factory for Employee
public class EmployeeFactory {

    public static Employees getEmployee(String empType) {
        if (empType.trim().equalsIgnoreCase("Android Developer")) {
            return new AndriodDeveloper();
        } else if (empType.trim().equalsIgnoreCase("Web Developer")) {
            return new WebDeveloper();
        } else {
            return null;
        }
    }

    // Abstract Design;
    public static Employees getEmployees(EmployeeAbstractFactory factory) {
        return factory.createEmployee();
    }

}
