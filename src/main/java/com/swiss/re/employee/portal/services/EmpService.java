package com.swiss.re.employee.portal.services;

import com.swiss.re.employee.portal.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class EmpService {
    final static Logger log = LogManager.getLogger(EmpService.class);
    @Autowired
    private ExcelService excelSerice;

    public List<Employee> getEmployees(){
        return excelSerice.getEmployees();
    }

    public Employee getEmployee(int id){
        Optional<Employee> emp =  excelSerice.getEmployees().stream().filter(e -> e.getId() == id).findFirst();
        if(emp.isPresent()){
            return emp.get();
        }
        throw new RuntimeException("No record found");
    }

    public Employee getNthLargestSalary(int n){
        if(n < 1) throw new IllegalArgumentException("Invalid input!");
        List<Employee> list = excelSerice.getEmployees().stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).limit(n).toList();
        return list.get(list.size() - 1);
    }

    public List<Employee> getEmployeesEligibleForGratuity(){
        List<Employee> list =  excelSerice.getEmployees();
        return list.stream().filter(this::filterForGratuity).collect(Collectors.toList());
    }

    public Employee getDirectorWithHierarchy(){
        log.info("Get Heirarchy!");
        Map<Integer, Employee> empMap  =  excelSerice.getEmployees()
                .stream().collect(Collectors.toMap(Employee::getId, e-> e ));
        Employee root = new Employee();
        for(Map.Entry<Integer, Employee> entry: empMap.entrySet()){
            if(entry.getValue().getManagerId() == 0){
                root = entry.getValue();
            }else{
                Employee mgr = empMap.get(entry.getValue().getManagerId());
                List<Employee> reportees = mgr.getReportees();
                if(reportees == null) reportees = new ArrayList<>();
                reportees.add(entry.getValue());
                mgr.setReportees(reportees);
            }
        }
        return root;
    }

    public List<Employee> getEmployeeOutEarnManager(){
        List<Employee> list =  excelSerice.getEmployees();
        List<Employee> res = new ArrayList<>();
        Map<Integer, Employee> empMap = list.stream().collect(Collectors.toMap(Employee::getId, Function.identity()));

        for(Employee e: list){
            log.info(e);
            if(e.getManagerId() != 0 && e.getSalary() > empMap.get(e.getManagerId()).getSalary()){
                log.info(empMap.get(e.getManagerId()));
                res.add(e);
            }
        }
        return res;
    }

    boolean filterForGratuity(Employee a){
        LocalDate date = LocalDate.now();
        log.info(a.getDoj());
        log.info(ChronoUnit.MONTHS.between(a.getDoj(), date));
        return ChronoUnit.MONTHS.between(a.getDoj(),date) > 60;
    }
}
