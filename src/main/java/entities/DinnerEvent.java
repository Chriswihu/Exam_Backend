package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "DinnerEvent.deletAllRows", query = "DELETE from DinnerEvent")
@Table(name = "dinnerEvent")
public class DinnerEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String time;
    private String location;
    private String dish;
    private int price;
    @OneToMany(mappedBy = "event", cascade = CascadeType.PERSIST)
    private List<Assignment> assignment;

    public DinnerEvent() {
    }

    public DinnerEvent(String time, String location, String dish, int price) {
        this.time = time;
        this.location = location;
        this.dish = dish;
        this.price = price;
        this.assignment = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getDish() {
        return dish;
    }
    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public List<Assignment> getAssignment() {
        return assignment;
    }
    public void addAssignment(Assignment assignment) {
        this.assignment.add(assignment);
        if (assignment != null) {
            assignment.setEvent(this);
        }
    }

    @Override
    public String toString() {
        return "DinnerEvent{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", dish='" + dish + '\'' +
                ", price=" + price +
//                ", assignment=" + assignment +
                '}';
    }
}
