package dtos;

import entities.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link entities.User}
 */
public class UserDto implements Serializable {
    @NotNull
    private String userName;
    @NotNull
    @Size(min = 1, max = 255)
    private String userPass;

    public UserDto() {

    }

    public UserDto(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }

    public UserDto(User user) {

    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName=userName;
    }

    public String getUserPass() {
        return userPass;
    }
    public void setUserPass(String userPass) {
        this.userPass=userPass;
    }

}