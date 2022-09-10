package com.microservice.department.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.department.entity.Department;
import com.microservice.department.exception.ResourceNotFoundException;
import com.microservice.department.model.DepartmentDto;
import com.microservice.department.repository.DepartmentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    

    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        log.info("Inside saveDepartment of DepartmentService");
        
        Department department=this.modelMapper.map(departmentDto, Department.class);
        
        Department savedDepartment=departmentRepository.save(department);
        
        return this.modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    public DepartmentDto findDepartmentById(Long departmentId) {
        log.info("Inside saveDepartment of DepartmentService");
        
        Department department=departmentRepository.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("Department","Department id", departmentId));
        
        return this.modelMapper.map(department, DepartmentDto.class);
    }
}
