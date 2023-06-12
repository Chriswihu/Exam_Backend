package dtos;

import entities.Rental;
import entities.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * DTO for {@link entities.User}
 */
public class UserDto implements Serializable {
    @NotNull
    private String userName;
    @NotNull
    @Size(min = 1, max = 255)
    private String userPass;
    private String phone;
    private String job;
    private List<Rental> rentals;


    public UserDto() {

    }

    public UserDto(User user) {
        this.userName = user.getUserName();
        this.userPass = user.getUserPass();
        this.phone = user.getPhone();
        this.job = user.getJob();
        this.rentals = user.getRentals();
    }

    public static List<UserDto> getDtos(List<User> users) {
        return users.stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job=job;
    }


}