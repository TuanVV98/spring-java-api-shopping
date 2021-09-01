package com.spring.dto.model;

import com.spring.enumeration.RoleEnum;
import com.spring.enumeration.UserStatusEnum;
import com.spring.model.User;
import com.spring.utils.BcryptUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull(message = "Username cannot be null.")
    @Length(min = 1, max = 50, message = "Username must contain between 1 and  50 characters.")
    private String username;

    @NotNull(message = "Mobile cannot be null.")
    @Size(min = 10, max = 10, message = "Mobile must contain 10 numbers")
    @Pattern(
            regexp = "(^$|[0-9]{10})",
            message = "Invalid mobile !")
    private String mobile;

    @NotNull(message = "Email cannot be null.")
    @Length(max = 100, message = "Email must be a maximum of 100 characters.")
    @Email(message = "Invalid email !")
    private String email;

    @NotNull(message = "Password cannot be null.")
    @Length(min = 6, message = "Password must contain at least 6 characters.")
    private String password;

    private UserStatusEnum status;

    private RoleEnum role;

    private Date registeredAt;

    private LocalDateTime updateAt;

    private LocalDateTime deletedAt;

    public UserDTO(Long id, RoleEnum role) {
        this.id = id;
        this.role = role;
    }

    public String getPassword() {
        return BcryptUtil.getHash(this.password);
    }

    public User convertDTOToEntity() {
        return new ModelMapper().map(this, User.class);
    }
}
