package pl.coderslab.charity.web.dto;

import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.validation.PasswordMatches;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

@PasswordMatches
public class UserRegistrationDto {

    @NotBlank
    @Size(min=2, max = 60)
    private String name;

    @NotBlank
    @Size(min=2, max = 60)
    private String lastname;

    @Email
    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 8, max = 60)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\-\\=])(?=.*[A-Z])(?!.*\\s).{8,}$", message = "Hasło musi składać się z 8 do 60 znaków oraz zawierać co najmniej jedną cyfrę, dużą i małą literę oraz znak specjalny.")
    private String password;

    @NotBlank
    private String matchingPassword;

    private Collection<Role> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
