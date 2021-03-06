package pl.tutors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.tutors.domain.TutorProfile;
import pl.tutors.domain.User;
import pl.tutors.domain.UserDetails;
import pl.tutors.exception.CustomException;
import pl.tutors.query.UserProfileQuery;
import pl.tutors.rest.dtos.AccountResetDTO;
import pl.tutors.rest.dtos.CreateTutorProfileDTO;
import pl.tutors.rest.dtos.PasswordResetDTO;
import pl.tutors.rest.dtos.RegistrationUserDTO;
import pl.tutors.rest.dtos.UpdateTutorProfileLocationDTO;
import pl.tutors.service.currentuser.CurrentUserService;
import pl.tutors.service.tutorprofile.TutorProfileService;
import pl.tutors.service.usercrud.UserService;

import java.util.List;
import java.util.UUID;

@Service
public class UserManagementFacade implements UserService, CurrentUserService, TutorProfileService {
    private UserService userService;
    private CurrentUserService currentUserService;
    private TutorProfileService tutorProfileService;

    @Autowired
    public UserManagementFacade(
            @Qualifier("userServiceImpl") UserService userService,
            @Qualifier("currentUserServiceImpl") CurrentUserService currentUserService,
            @Qualifier("tutorProfileServiceImpl") TutorProfileService tutorProfileService

    )
    {
        this.userService = userService;
        this.currentUserService = currentUserService;
        this.tutorProfileService = tutorProfileService;
    }

    @Override
    public User getCurrentUser() {
        return currentUserService.getCurrentUser();
    }

    @Override
    public User registerUser(RegistrationUserDTO registrationUserDto) {
        return userService.registerUser(registrationUserDto);
    }

    @Override
    public User addDetailsForUser(UserDetails details, UUID userId) throws CustomException {
        return userService.addDetailsForUser(details, userId);
    }

    @Override
    public User requestPasswordChange(String email) {
        return userService.requestPasswordChange(email);
    }

    @Override
    public User changePassword(PasswordResetDTO passwordResetDTO, User user) throws CustomException {
        return userService.changePassword(passwordResetDTO, user);
    }

    @Override
    public User getUserById(UUID userId) {
        return userService.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @Override
    public void logUsedIp(String userEmail, String usedIp) {
        userService.logUsedIp(userEmail, usedIp);
    }

    @Override
    public User updateAttemptedLogins(User user) {
        return userService.updateAttemptedLogins(user);
    }

    @Override
    public User requestAccountReset(String email) {
        return userService.requestAccountReset(email);
    }

    @Override
    public User resetAccount(AccountResetDTO accountResetDTO) throws CustomException {
        return userService.resetAccount(accountResetDTO);
    }

    @Override
    public User createTutorProfile(TutorProfile tutorProfile) {
        return tutorProfileService.createTutorProfile(tutorProfile);
    }

    @Override
    public List<User> readAllUserProfiles(UserProfileQuery userProfileQuery) {
        return tutorProfileService.readAllUserProfiles(userProfileQuery);
    }

    @Override
    public User updateTutorProfileLocation(UpdateTutorProfileLocationDTO updateTutorProfileLocationDTO) {
        return tutorProfileService.updateTutorProfileLocation(updateTutorProfileLocationDTO);
    }
}
