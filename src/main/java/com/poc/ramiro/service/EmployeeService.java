package com.poc.ramiro.service;

import com.poc.ramiro.exception.RecordNotFoundException;
import com.poc.ramiro.model.EmployeeEntity;
import com.poc.ramiro.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public List<EmployeeEntity> getAllEmployees(){

        List<EmployeeEntity> employeelist =repository.findAll();

        if(employeelist.size() >0){
            return employeelist;
        }
        else{
            return new ArrayList<EmployeeEntity>();
        }
    }

    public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException{
        Optional<EmployeeEntity> employee= repository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }
        else{
            throw new RecordNotFoundException("No Employee Record exist for given ID");
        }
    }

    public  EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity)throws RecordNotFoundException{
        Optional<EmployeeEntity> employee=repository.findById(entity.getId());

        if(employee.isPresent()){
            EmployeeEntity newEntity=employee.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());

            newEntity=repository.save(newEntity);
            return newEntity;
        }
        else{
            entity=repository.save(entity);
            return entity;
        }
    }

    public void deleteEmployeeById(Long id)throws RecordNotFoundException{
        Optional<EmployeeEntity> employee=repository.findById(id);

        if(employee.isPresent()){
        repository.deleteById(id);
        }
        else
        {
            throw new RecordNotFoundException("Employee not found for given ID");
        }
    }

}
