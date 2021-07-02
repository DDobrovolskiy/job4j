package ru.job4j.ood.ocp;

import ru.job4j.ood.srp.Employee;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "employeeStore")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeStore {
    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    private List<Employee> employees;

    public EmployeeStore() {

    }

    public EmployeeStore(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
