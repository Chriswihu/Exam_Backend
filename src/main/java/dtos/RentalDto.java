package dtos;

import entities.Rental;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link entities.Rental}
 */
public class RentalDto implements Serializable {
    private Long id;
    private final String startDate;
    private final String endDate;
    private final int priceAnnual;
    private final int deposit;
    private final String contactPerson;
//    private final HouseDto house;

    public RentalDto(Rental r) {
        this.id = r.getId();
        this.startDate = r.getStartDate();
        this.endDate = r.getEndDate();
        this.priceAnnual = r.getPriceAnnual();
        this.deposit = r.getDeposit();
        this.contactPerson = r.getContactPerson();
//        this.house = new HouseDto(r.getHouse());
    }

    public static List<RentalDto> getDtos(List<Rental> rentals) {
        List<RentalDto> rentalDtos = new ArrayList<>();
        rentals.forEach(r -> rentalDtos.add(new RentalDto(r)));
        return rentalDtos;
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

    public String getEndDate() {
        return endDate;
    }

    public int getPriceAnnual() {
        return priceAnnual;
    }

    public int getDeposit() {
        return deposit;
    }

    public String getContactPerson() {
        return contactPerson;
    }

//    public HouseDto getHouse() {
//        return house;
//    }


}