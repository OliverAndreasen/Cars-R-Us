package dat3.cars.repository;

import dat3.cars.entity.Reservation;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository
  extends JpaRepository<Reservation, Integer> {
  boolean existsByCar_IdAndRentalDate(int carId, LocalDate date);
}
