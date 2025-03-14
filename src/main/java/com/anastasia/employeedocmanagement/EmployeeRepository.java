package com.anastasia.employeedocmanagement;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    List<Employee> findAll();

    Optional<Employee> findById(Long id);

    Employee save(Employee employee);

    void deleteById(Long id);

    boolean existsById(Long id);
}
