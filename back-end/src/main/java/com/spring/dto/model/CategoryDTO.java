package com.spring.dto.model;


import com.spring.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {


    private Long id;

    @NotNull(message = "Name cannot be null")
    @Length(min = 1, max = 100, message = "Username must contain between 1 and  100 characters.")
    private String name;

    @NotNull(message = "Context cannot be null")
    @Length(min = 1, max = 500, message = "Username must contain between 1 and  500 characters.")
    private String context;

    private Date createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    public Category convertDTOToEntity(){
        return new ModelMapper().map(this,Category.class);
    }
}
