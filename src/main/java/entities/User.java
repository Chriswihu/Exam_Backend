package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_name", length = 25)
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "user_pass")
    private String userPass;
    private String address;
    private String phone;
    private String email;
    private int birthYear;
    private int account;
    @ManyToMany
    private List<Assignment> assignments;


    @JoinTable(name = "user_roles", joinColumns = {
            @JoinColumn(name = "user_name", referencedColumnName = "user_name")}, inverseJoinColumns = {
            @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
    @ManyToMany
    private List<Role> roleList = new ArrayList<>();

    public User(User u) {
    }

    public List<String> getRolesAsStrings() {
        if (roleList.isEmpty()) {
            return null;
        }
        List<String> rolesAsStrings = new ArrayList<>();
        roleList.forEach((role) -> {
            rolesAsStrings.add(role.getRoleName());
        });
        return rolesAsStrings;
    }

    public User() {
    }

    //TODO Change when password is hashed
    public boolean verifyPassword(String pw) {
        return BCrypt.checkpw(pw, userPass);
    }

    public User(String userName, String userPass) {
        this.userName = userName;
        this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
    }

    public User(String userName, String userPass, String address, String phone, String email, int birthYear, int account) {
        this.userName = userName;
        this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthYear = birthYear;
        this.account = account;
        this.assignments = new ArrayList<>();
    }


    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return this.userPass;
    }
    public void setUserPass(String userPass) {
        this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
        ;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getBirthYear() {
        return birthYear;
    }
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getAccount() {
        return account;
    }
    public void setAccount(int account) {
        this.account = account;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }
    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public List<Role> getRoleList() {
        return roleList;
    }
    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
    public void addRole(Role userRole) {
        roleList.add(userRole);
    }

    @Override
    public String toString() {
        return '\n' + "User{" + '\n' +
                "userName = " + userName + "," + '\n' +
                "address = " + address + "," + '\n' +
                "phone = " + phone + "," + '\n' +
                "email = " + email + "," + '\n' +
                "birthYear = " + birthYear + "," + '\n' +
                "account = " + account + '\n' +
                "assignments = " + assignments + '\n' +
                '}';
    }
}
