package com.sample.test.factory;

import com.sample.test.AbstractFactory.EmployeeAbstractFactory;
import com.sample.test.entity.Employees;
import com.sample.test.entity.WebDeveloper;

public class WebDeveloperFactory  extends EmployeeAbstractFactory{

    @Override
    public Employees createEmployee() {
        // TODO Auto-generated method stub
       return new WebDeveloper();
    }

}
