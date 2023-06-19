/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Assignment;
import entities.RenameMe;
import entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author tha
 */
public class UserDTO {
    private final String userName;
    private final String password;
    private final String address;
    private final String phone;
    private final String email;
    private final int birthYear;
    private final int account;
//    private final List<AssignmentDTO> assignments;

    public UserDTO(User u) {
        this.userName = u.getUserName();
        this.password = u.getUserPass();
        this.address = u.getAddress();
        this.phone = u.getPhone();
        this.email = u.getEmail();
        this.birthYear = u.getBirthYear();
        this.account = u.getAccount();
//        this.assignments = u.getAssignments().stream().map(a -> new AssignmentDTO(a)).collect(Collectors.toList());
    }

    public static List<UserDTO> getDTOs(List<User> users) {
        return users.stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getAccount() {
        return account;
    }

//    public List<AssignmentDTO> getAssignments() {
//        return assignments;
//    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birthYear=" + birthYear +
                ", account=" + account +
                '}';
    }
}
