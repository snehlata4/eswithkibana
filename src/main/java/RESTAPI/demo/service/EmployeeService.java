package RESTAPI.demo.service;

import RESTAPI.demo.models.Employee;
import RESTAPI.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private  EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() throws IOException {
        return employeeRepository.findallemployeefromelasticsearch();
    }


}
