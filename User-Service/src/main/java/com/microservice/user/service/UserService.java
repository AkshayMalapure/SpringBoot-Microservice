package com.microservice.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.user.VO.Department;
import com.microservice.user.VO.ResponseTemplateVO;
import com.microservice.user.entity.User;
import com.microservice.user.exception.ResourceNotFoundException;
import com.microservice.user.model.DepartmentDto;
import com.microservice.user.model.UserDto;
import com.microservice.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private ModelMapper modelMapper;

    public UserDto saveUser(UserDto userDto) {
        log.info("Inside saveUser of UserService");
        
        User user=this.modelMapper.map(userDto, User.class);
        
        User savedUser=userRepository.save(user);
        
        return this.modelMapper.map(savedUser, UserDto.class);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id", userId));
        UserDto userDto=this.modelMapper.map(user, UserDto.class);

        Department department =
                restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId()
                        ,Department.class);
        DepartmentDto departmentDto=this.modelMapper.map(department, DepartmentDto.class);

        vo.setUser(userDto);
        vo.setDepartment(departmentDto);

        return  vo;
    }
}
