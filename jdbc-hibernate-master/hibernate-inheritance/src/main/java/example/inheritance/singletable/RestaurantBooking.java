package example.inheritance.singletable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("TestRestaurantBooking")
public class RestaurantBooking extends AbstractBooking {

    @Column
    private Integer numberOfPeople;
}
