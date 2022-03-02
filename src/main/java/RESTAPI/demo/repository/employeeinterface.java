package RESTAPI.demo.repository;

import RESTAPI.demo.models.Employee;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
@Repository
public interface employeeinterface {
    List<Employee> findallemployeefromelasticsearch();
}
