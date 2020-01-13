package com.poc.ramiro.controller;

import com.poc.ramiro.exception.RecordNotFoundException;
import com.poc.ramiro.model.EmployeeEntity;
import com.poc.ramiro.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees(){
        List<EmployeeEntity> list=service.getAllEmployees();

        return new ResponseEntity<List<EmployeeEntity>>(list,new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<EmployeeEntity> createOrUpdateEmployee(EmployeeEntity employee) throws RecordNotFoundException {
        EmployeeEntity update=service.createOrUpdateEmployee(employee);
        return new ResponseEntity<EmployeeEntity>(update,new HttpHeaders(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id) throws RecordNotFoundException{
        EmployeeEntity entity=service.getEmployeeById(id);
        return new ResponseEntity<EmployeeEntity>(entity,new HttpHeaders(),HttpStatus.OK);
    }
}
