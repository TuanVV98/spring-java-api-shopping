package com.spring.service.user.profile;

import com.spring.dto.model.UserProfileDTO;
import com.spring.model.User;
import com.spring.model.UserProfile;
import com.spring.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService{

    private UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfileDTO save(UserProfileDTO userProfileDTO) {

        UserProfile userProfile = this.userProfileRepository.save(userProfileDTO.convertDTOToEntity());
        return userProfile.convertEntityToDTO();
    }

    @Override
    public UserProfileDTO update(UserProfileDTO userProfileDTO) {
        UserProfile userProfile = this.userProfileRepository.save(userProfileDTO.convertDTOToEntity());
        return userProfile.convertEntityToDTO();
    }

    @Override
    public Optional<UserProfileDTO> findByUserId(Long id) {
        Optional<UserProfile> entity = this.userProfileRepository.findByUserId(id);
        if(entity.isPresent()){
            return entity.map(UserProfile::convertEntityToDTO);
        }
        return Optional.empty();
    }
}
