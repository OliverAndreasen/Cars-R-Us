package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.cars.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReservationResponse {
    int id;

    /*
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime reservationDate;
     */

    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    LocalDate rentalDate;

    int carId;

    String carBrand;
    public ReservationResponse(Reservation reservation){
        this.id = reservation.getId();
        this.carId = reservation.getCar().getId();
        this.carBrand = reservation.getCar().getBrand();
        this.rentalDate = reservation.getRentalDate();
    }
}
