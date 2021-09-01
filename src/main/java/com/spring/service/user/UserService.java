package com.spring.service.user;

import com.spring.dto.model.UserDTO;
import com.spring.model.User;
import com.spring.model.VerificationToken;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO register(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    void sendRegistrationConfirmationEmail(User user);

    boolean verifyAccount(Optional<VerificationToken> token);

    List<UserDTO> getAll();

    Optional<User> checkIfEmailExists(String email);

    Optional<User>  checkIfEmailExistsAndStatus(String email);

    Optional<User> checkIfUsernameExists(String username);

    Optional<User> findByUsername(String username);

}
