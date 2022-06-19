package example.inheritance.singletable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("TestHotelBooking")
public class HotelBooking extends AbstractBooking {

    @Column
    private Integer roomNumber;

}
