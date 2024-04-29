package com.example.restsb.web.mappers;

import com.example.restsb.domain.User;
import com.example.restsb.web.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto>{
}
