package com.spring.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.model.UserDTO;
import com.spring.dto.model.security.JwtUserDTO;
import com.spring.entity.User;
import com.spring.repository.UserRepository;
import com.spring.util.mappers.MapperUtil;
import com.spring.util.mappers.UserMapper;
import com.spring.util.security.BcryptUtil;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	private MapperUtil mapperUtil;
	
	public UserServiceImpl
	(
		UserRepository userRepository,
		UserMapper userMapper,
		MapperUtil mapperUtil
	) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.mapperUtil = mapperUtil;
	}
	
	/**
	 * @see UserService#save(User)
	 */
	@Override
	public UserDTO save(UserDTO userDTO) {
		User userEntity = this.userMapper.convertDTOToEntity(userDTO); 
		
		System.out.println("Role : "+userEntity.getRole());
		return this.userMapper.convertEntityToDTO(this.userRepository.save(userEntity));
		
	}

	/**
	 * @see UserService#findAll()
	 */
	@Override
	public List<UserDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> findByEmail(String email) {
		
		Optional<User> userEntity = this.userRepository.findByEmailAndDeletedAtIsNull(email);
		
		if(userEntity.isEmpty()) {
			System.out.println("true");
			return userEntity;
		}else {
			System.out.println("false");
			return Optional.empty();
		}
		
	}

	@Override
	public boolean checkAccount(JwtUserDTO jwtUserDTO)  {
		Optional<User> userEntity = this.userRepository.findByEmailAndDeletedAtIsNull(jwtUserDTO.getEmail());
		
		if(userEntity.isPresent()) {
			System.out.println("is present !");
			System.out.println("pasword bcry : "+userEntity.get().getPassword());
			boolean verify = BcryptUtil.decode(userEntity.get().getPassword(),jwtUserDTO.getPassword());
			if(verify) {
				System.out.println("is verify  !");
				return true;
			}
		}
		return false;
	}

	@Override
	public List<UserDTO> findByDeletedAtIsNull() {
		
		return this.mapperUtil.mapList(this.userRepository.findByDeletedAtIsNull(), UserDTO.class);
	}

	@Override
	public List<UserDTO> findByDeletedAtIsNotNull() {
		
		return this.mapperUtil.mapList(this.userRepository.findByDeletedAtIsNotNull(), UserDTO.class);
	}

}
