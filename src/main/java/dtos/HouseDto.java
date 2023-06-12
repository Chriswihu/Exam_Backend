package dtos;

import entities.House;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * DTO for {@link entities.House}
 */
public class HouseDto implements Serializable {
    private final Long id;
    private final String address;
    private final String city;
    private final int numberOfRooms;


    public HouseDto(House h) {
        this.id = h.getId();
        this.address = h.getAddress();
        this.city = h.getCity();
        this.numberOfRooms = h.getNumberOfRooms();

    }

//    public static List<HouseDto> getDtos(List<House> houses) {
//        List<HouseDto> houseDtos = new ArrayList<>();
//        houses.forEach(h -> houseDtos.add(new HouseDto(h)));
//        return houseDtos;
//
//    }


    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

//    public List<Long> getRentals() {
//        return rentals;
//    }


}