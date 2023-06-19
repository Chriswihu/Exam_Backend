package dtos;

import entities.Assignment;
import entities.User;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class AssignmentDTO implements Serializable {
    private final Long id;
    private final String familyName;
    private final String date;
    private final String contact;
    private final List<UserDTO> users;

    public AssignmentDTO(Assignment a) {
        this.id = a.getId();
        this.familyName = a.getFamilyName();
        this.date = a.getDate();
        this.contact = a.getContact();
        this.users = a.getUsers().stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
    }

    public static List<AssignmentDTO> getDTOs(List<Assignment> assignments) {
        return assignments.stream().map(a -> new AssignmentDTO(a)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getDate() {
        return date;
    }

    public String getContact() {
        return contact;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "familyName = " + familyName + ", " +
                "date = " + date + ", " +
                "contact = " + contact + ", " +
                "users = " + users + ")";
    }


}
