package dat3.cars.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

//---------------------
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
//-----------------

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private LocalDateTime reservationDate;
    private LocalDate rentalDate;

    @ManyToOne(cascade = CascadeType.ALL)
    Member member;

    @ManyToOne(cascade = CascadeType.ALL)
    Car car;

    public Reservation(Member member, Car car, LocalDate rentalDate){
        this.member = member;
        this.car = car;
        this.rentalDate = rentalDate;
    }
}
