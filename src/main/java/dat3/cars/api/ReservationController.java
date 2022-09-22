package dat3.cars.api;

import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Reservation;
import dat3.cars.service.ReservationService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

  ReservationService reservationService;

  public ReservationController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @GetMapping
  public List<ReservationResponse> getReservations() {
    return reservationService.getReservations();
  }

  @PostMapping("/{username}/{carId}/{rentalDate}")
  public void addReservation(
    @PathVariable String username,
    @PathVariable int carId,
    @PathVariable String rentalDate
  ) {
    LocalDate date = LocalDate.parse(rentalDate);

    reservationService.reserveCar(username, carId, date);
  }

  @GetMapping("/{id}")
  public ReservationResponse getReservationById(@PathVariable int id) {
    return reservationService.getReservation(id);
  }
}
