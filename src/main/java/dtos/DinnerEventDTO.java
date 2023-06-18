package dtos;

import entities.DinnerEvent;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link entities.DinnerEvent}
 */
public class DinnerEventDTO implements Serializable {
    private final Long id;
    private final String time;
    private final String location;
    private final String dish;
    private final int price;

    public DinnerEventDTO(DinnerEvent d) {
        this.id = d.getId();
        this.time = d.getTime();
        this.location = d.getLocation();
        this.dish = d.getDish();
        this.price = d.getPrice();
    }

    public static List<DinnerEventDTO> getDinnerEventsDTOs(List<DinnerEvent> events) {
        return events.stream().map(d -> new DinnerEventDTO(d)).collect(java.util.stream.Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getDish() {
        return dish;
    }

    public int getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "time = " + time + ", " +
                "location = " + location + ", " +
                "dish = " + dish + ", " +
                "price = " + price + ", " +
                ")";

    }
}