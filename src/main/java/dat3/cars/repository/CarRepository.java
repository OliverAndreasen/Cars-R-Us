package dat3.cars.repository;

import dat3.cars.entity.Car;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
  List<Car> findCarByBrand(String brand);
  List<Car> findCarByPricePrDayBetween(double min, double max);
}
