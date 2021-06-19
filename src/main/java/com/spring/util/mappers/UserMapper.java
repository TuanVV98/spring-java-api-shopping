package com.spring.util.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.model.UserDTO;
import com.spring.entity.User;

@Service
public class UserMapper {

	@Autowired
	private ModelMapper mapper;

	public User convertDTOToEntity(UserDTO userDTO) {

		return mapper.map(userDTO, User.class);
	}

	public UserDTO convertEntityToDTO(User user) {

		return mapper.map(user, UserDTO.class);
	}

}
