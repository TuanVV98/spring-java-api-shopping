package com.spring.service.user.profile;

import com.spring.dto.model.UserProfileDTO;

import java.util.Optional;

public interface UserProfileService {

    UserProfileDTO save(UserProfileDTO userProfileDTO);

    UserProfileDTO update(UserProfileDTO userProfileDTO);

    Optional<UserProfileDTO> findByUserId(Long id);
}
