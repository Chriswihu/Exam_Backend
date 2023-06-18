package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "Assignment.deletAllRows", query = "DELETE from Assignment")
@Table(name = "assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String familyName;
    private String date;
    private String contact;
    @ManyToMany(mappedBy = "assignments", cascade = CascadeType.PERSIST)
    private List<User> users;
    @ManyToOne
    private DinnerEvent event;

    public Assignment() {
    }

    public Assignment(String familyName, String date, String contact) {
        this.familyName = familyName;
        this.date = date;
        this.contact = contact;
        this.users = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<User> getUsers() {
        return users;
    }
    public void addUser(User user) {
        if(user != null){
            this.users.add(user);
            user.getAssignments().add(this);
        }
    }

    public DinnerEvent getEvent() {
        return event;
    }
    public void setEvent(DinnerEvent event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", familyName='" + familyName + '\'' +
                ", date='" + date + '\'' +
                ", contact='" + contact + '\'' +
//                ", users=" + users +
//                ", event=" + event +
                '}';
    }
}
