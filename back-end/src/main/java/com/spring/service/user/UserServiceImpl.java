package com.spring.service.user;

import com.spring.dto.model.UserDTO;
import com.spring.enumeration.RoleEnum;
import com.spring.enumeration.UserStatusEnum;
import com.spring.enumeration.VerificationEnum;
import com.spring.model.User;
import com.spring.model.VerificationToken;
import com.spring.model.email.context.UserVerificationEmailContext;
import com.spring.repository.UserRepository;
import com.spring.service.email.EmailService;
import com.spring.service.verificationToken.VerificationTokenService;
import com.spring.utils.mapper.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    private VerificationTokenService verificationTokenService;

    private EmailService emailService;

    private MapperUtil mapperUtil;

    @Value("${site.base.url.https}")
    private String baseURL;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            VerificationTokenService verificationTokenService,
            EmailService emailService,
            MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.verificationTokenService = verificationTokenService;
        this.emailService = emailService;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public UserDTO register(UserDTO userDTO) {
        userDTO.setStatus(UserStatusEnum.NOT_ACTIVE);
        userDTO.setRole(RoleEnum.ROLE_USER);

        User user = this.userRepository.save(userDTO.convertDTOToEntity());
        sendRegistrationConfirmationEmail(user);
        return user.convertEntityToDTO();
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        User entity = this.userRepository.save(userDTO.convertDTOToEntity());

        return entity.convertEntityToDTO();
    }

    @Override
    public void sendRegistrationConfirmationEmail(User user) {

        VerificationToken verificationToken = this.verificationTokenService.createVerifiedToken();
        verificationToken.setUser(user);
        verificationToken.setType(VerificationEnum.VERIFY_ACCOUNT);

        this.verificationTokenService.saveVerifiedToken(verificationToken);

        UserVerificationEmailContext emailContext = new UserVerificationEmailContext();

        emailContext.init(user);
        emailContext.setToken(verificationToken.getToken());
        emailContext.buildVerificationUrl(baseURL, verificationToken.getToken());

        try {
            this.emailService.sendEmail(emailContext);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean verifyAccount(Optional<VerificationToken> token) {

        LocalDateTime now = LocalDateTime.now();

        if(token.isPresent() && checkExpiresAt(token.get().getExpiresAt())){

            Optional<User> user = this.userRepository.findById(token.get().getUser().getId());

            if(user.isPresent()){
                user.get().setStatus(UserStatusEnum.IS_ACTIVE);
                this.userRepository.save(user.get());

                token.get().setVerifiedAt(now);
                this.verificationTokenService.saveVerifiedToken(token.get());

            }
            return true;
        }
        return false;
    }

    @Override
    public List<UserDTO> getAll() {
        return mapperUtil.mapList(this.userRepository.findAll(),UserDTO.class);
    }

    @Override
    public Optional<User> checkIfEmailExists(String email) {
        return this.userRepository.checkIfEmailExists(email);
    }

    @Override
    public Optional<User> checkIfEmailExistsAndStatus(String email) {
        return this.userRepository.checkIfEmailExistsAndStatus(email);
    }

    @Override
    public Optional<User> checkIfUsernameExists(String username) {
        return this.userRepository.checkIfUsernameExists(username);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public boolean checkExpiresAt(LocalDateTime expiresAt) {

        LocalDateTime now = LocalDateTime.now();
        return !(expiresAt.compareTo(now) < 0) ;
    }
}
