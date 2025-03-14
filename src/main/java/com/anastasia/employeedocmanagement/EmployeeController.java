package com.anastasia.employeedocmanagement;

import com.anastasia.employeedocmanagement.Employee;
import com.anastasia.employeedocmanagement.EmployeeRepository;
//import com.anastasia.employeedocmanagement.models.Employee;
//import com.anastasia.employeedocmanagement.repositories.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get an employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Add a new employee
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Update an employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(updatedEmployee.getName());
            employee.setPosition(updatedEmployee.getPosition());
            employee.setDepartment(updatedEmployee.getDepartment());
            employee.setNotes(updatedEmployee.getNotes());

            Employee savedEmployee = employeeRepository.save(employee);
            return ResponseEntity.ok(savedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}



