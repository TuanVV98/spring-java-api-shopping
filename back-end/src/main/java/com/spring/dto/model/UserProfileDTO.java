package com.spring.dto.model;

import com.spring.model.UserProfile;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {

    private Long id;

    @NotNull(message = "FirstName cannot be null")
    @Length(min = 1, max=30, message="FirstName must contain between 1 and  30 characters.")
    private String firstName;

    @NotNull(message = "LastName cannot be null")
    @Length(min = 1, max=30, message="LastName must contain between 1 and  30 characters.")
    private String lastName;

    @NotNull(message = "MiddleName cannot be null")
    @Length(min = 1, max=30, message="MiddleName must contain between 1 and  30 characters.")
    private String middleName;

    @NotNull(message = "Gender cannot be null")
    @Pattern(regexp = "^(MALE|FEMALE|UNKNOWN)$",
            message="Gender only the values MALE or FEMALE or UNKNOWN")
    private String gender;

    private LocalDateTime updatedAt;

    @NotNull(message = "User Id cannot be null")
    private Long userID;

    public UserProfile convertDTOToEntity() {
        return new ModelMapper().map(this,UserProfile.class);
    }
}
