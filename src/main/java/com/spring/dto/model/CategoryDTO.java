package com.spring.dto.model;


import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

	private Long id;

	@NotNull
	private String name;

	@Null
	private Date created_at;

	@Null
	private Date deleted_at;

	@Null
	private Date deleted_user;
}
