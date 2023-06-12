package dtos;

import entities.Tenant;
import entities.User;
import sun.util.resources.cldr.ext.TimeZoneNames_en_ER;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * DTO for {@link entities.Tenant}
 */
public class TenantDto implements Serializable {
    private Long id;
    private final String phone;
    private final String job;
    private List<String> rentalDtos;

    public TenantDto(Tenant t) {
        this.id = t.getId();
        this.phone = t.getPhone();
        this.job = t.getJob();
        this.rentalDtos = t.getRentals().stream().map(r -> r.getId().toString()).collect(Collectors.toList());
    }



    public static List<TenantDto> getDtos(List<Tenant> tenants) {
        return tenants.stream().map(TenantDto::new).collect(Collectors.toList());
    }

    public List<String> getRentalDtos() {
        return rentalDtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public String getJob() {
        return job;
    }


    public void setRentalDtos(List<String> rentalDtos) {
        this.rentalDtos = rentalDtos;
    }
}