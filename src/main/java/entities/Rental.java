package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "rental")
public class Rental implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String startDate; //TODO: Change to Date - if time is available
    private String endDate; //TODO: Change to Date - if time is available
    private int priceAnnual;
    private int deposit;
    private String contactPerson;

    @ManyToMany(mappedBy = "rentals", cascade = CascadeType.PERSIST)
    private List<User> users;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private House house;

    public Rental() {
    }

    public Rental(String startDate, String endDate, int priceAnnual, int deposit, String contactPerson) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceAnnual = priceAnnual;
        this.deposit = deposit;
        this.contactPerson = contactPerson;
        this.users = new ArrayList<>();
        this.house = new House();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPriceAnnual() {
        return priceAnnual;
    }
    public void setPriceAnnual(int priceAnnual) {
        this.priceAnnual = priceAnnual;
    }

    public int getDeposit() {
        return deposit;
    }
    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getContactPerson() {
        return contactPerson;
    }
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public List<User> getTenants() {
        return users;
    }
    public void setTenants(List<User> tenants) {
        this.users = tenants;
    }
    public void addTenant(User user) {
        this.users.add(user);
        if(!user.getRentals().contains(this)) {
            user.getRentals().add(this);
        }
    }

    public House getHouse() throws IndexOutOfBoundsException {
        try {
            return house;
        } catch (Exception e) {
            System.out.println("No house found");
            return null;
        }
    }
    public void setHouse(House house) {
        this.house = house;
        if(!house.getRentals().contains(this)) {
            house.getRentals().add(this);
        }
    }


    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", priceAnnual=" + priceAnnual +
                ", deposit=" + deposit +
                ", contactPerson='" + contactPerson + '\'' +
                ", house=" + house +
                '}';
    }
}