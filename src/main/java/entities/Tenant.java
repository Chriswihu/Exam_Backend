package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tenant")
public class Tenant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "Username", referencedColumnName = "user_name", nullable = false)
    private User user;
    private String phone;
    private String job;

    @ManyToMany
    @JoinTable(name = "tenant_rentals", joinColumns = {
            @JoinColumn(name = "tenant_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "rental_id", referencedColumnName = "id")})
    private List<Rental> rentals;

    public Tenant() {
    }

    public Tenant(User user, String phone, String job) {
        this.user = user;
        this.phone = phone;
        this.job = job;
        this.rentals = new ArrayList<>();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        this.job = job;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void addRental(Rental rental) {
        this.rentals.add(rental);
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id=" + id +
                ", user=" + user.getUserName() +
                ", phone='" + phone + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}