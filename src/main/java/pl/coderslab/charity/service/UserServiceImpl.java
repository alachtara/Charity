package pl.coderslab.charity.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.exception.UserAlreadyExistException;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.VerificationToken;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.repository.UserService;
import pl.coderslab.charity.security.CurrentUser;
import pl.coderslab.charity.web.dto.UserEditDto;
import pl.coderslab.charity.web.dto.UserRegistrationDto;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final VerificationTokenService tokenService;

    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, VerificationTokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public User registerNewUserAccount(final UserRegistrationDto registrationDto) {
        if (emailExists(registrationDto.getUsername())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + registrationDto.getName());
        }
        final User user = new User();

        user.setName(registrationDto.getName());
        user.setLastname(registrationDto.getLastname());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setEmail(registrationDto.getUsername());
        Collection<Role> roles = registrationDto.getRoles();
        if(roles.isEmpty()) {
            Role userRole = roleRepository.findByName("ROLE_USER");
            user.setRoles(Arrays.asList(userRole));
        } else user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        tokenService.add(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(final String VerificationToken) {
        return tokenService.findByToken(VerificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        final VerificationToken verificationToken = tokenService.findByToken(token);
        if (verificationToken == null) {
            return TOKEN_INVALID;
        }

        final User user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate()
                .getTime() - cal.getTime()
                .getTime()) <= 0) {
            tokenService.delete(token);
            return TOKEN_EXPIRED;
        }

        user.setEnabled(true);
        userRepository.save(user);
        return TOKEN_VALID;
    }

    @Override
    public User getUser(final String verificationToken) {
        final VerificationToken token = tokenService.findByToken(verificationToken);
        if (token != null) {
            return token.getUser();
        }
        return null;
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        if (emailExist(registrationDto.getUsername())) {
            throw new UserAlreadyExistException("Wrong username or password. ");
        }

        User user = new User();
        user.setName(registrationDto.getName());
        user.setLastname(registrationDto.getLastname());
        user.setEmail(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setEnabled(true);
        Collection<Role> roles = registrationDto.getRoles();
        if(roles.isEmpty()) {
            Role userRole = roleRepository.findByName("ROLE_USER");
            user.setRoles(Arrays.asList(userRole));
        } else user.setRoles(roles);

        return userRepository.save(user);
    }

    public User update( UserEditDto userEditDto) {
        User user = userRepository.findById(userEditDto.getId()).orElseThrow(() -> new UsernameNotFoundException(userEditDto.getUsername()));

        user.setName(userEditDto.getName());
        user.setLastname(userEditDto.getLastname());
        Collection<Role> roles = userEditDto.getRoles();
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public void changeUnable (UserEditDto userEditDto) {
        User user = userRepository.findById(userEditDto.getId()).orElseThrow(() -> new UsernameNotFoundException(userEditDto.getUsername()));
        user.setEnabled(!user.isEnabled());

        userRepository.save(user);
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username));

            Collection<? extends GrantedAuthority> grantedAuthorities = mapRolesToAuthorities(user.getRoles());

            return new CurrentUser(user.getEmail(), user.getPassword(), grantedAuthorities, user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    public UserEditDto getUserDto(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id.toString()));

            UserEditDto userDto = new UserEditDto();
            userDto.setId(id);
            userDto.setName(user.getName());
            userDto.setLastname(user.getLastname());
            userDto.setUsername(user.getEmail());
            userDto.setRoles(user.getRoles());
            return userDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void changeUserPassword(final User user, final String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        tokenService.add(myToken);
    }

}
