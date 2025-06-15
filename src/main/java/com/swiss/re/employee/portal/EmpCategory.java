package com.swiss.re.employee.portal;

public enum EmpCategory {
    Manager("Manager"),
    Employee("Employee"),
    Director("Director");
    final String value;

    EmpCategory(String value){
        this.value = value;
    }
}
