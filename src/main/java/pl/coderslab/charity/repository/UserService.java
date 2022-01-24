package pl.coderslab.charity.repository;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.coderslab.charity.exception.UserAlreadyExistException;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.VerificationToken;
import pl.coderslab.charity.web.dto.UserEditDto;
import pl.coderslab.charity.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto) throws UserAlreadyExistException;

    User update( UserEditDto userEditDto);

    void changeUnable (UserEditDto userEditDto);

    UserEditDto getUserDto(Long id);

    void changeUserPassword(final User user, final String password);

    User registerNewUserAccount(UserRegistrationDto registrationDto);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    public String validateVerificationToken(String token);

    User getUser(String verificationToken);

    void createPasswordResetTokenForUser(User user, String token);
}
