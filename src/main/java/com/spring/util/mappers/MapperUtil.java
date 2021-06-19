package com.spring.util.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperUtil {

	@Autowired
	private ModelMapper mapper;

	/**
	 * Method convert List<Entity> -> List<DTO> & vice versa
	 * 
	 * @since 07/06/2021
	 * 
	 * @param List<S> source & Class<T> targetClass
	 * @return <code>List<Entity></code> object & vice versa
	 * 
	 */
	public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {

		return source
				.stream()
				.map(element -> mapper.map(element, targetClass))
				.collect(Collectors.toList());
	}

}
