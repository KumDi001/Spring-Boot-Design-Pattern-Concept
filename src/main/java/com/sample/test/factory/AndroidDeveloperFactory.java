package com.sample.test.factory;

import com.sample.test.AbstractFactory.EmployeeAbstractFactory;
import com.sample.test.entity.AndriodDeveloper;
import com.sample.test.entity.Employees;

public class AndroidDeveloperFactory extends EmployeeAbstractFactory {

    @Override
    public Employees createEmployee() {
        // TODO Auto-generated method stub
        return new AndriodDeveloper();
    }

}
