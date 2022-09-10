package com.microservice.user.VO;

import com.microservice.user.model.DepartmentDto;
import com.microservice.user.model.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private UserDto user;
    private DepartmentDto department;
}
