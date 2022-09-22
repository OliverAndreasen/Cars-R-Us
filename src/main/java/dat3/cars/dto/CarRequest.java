package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.cars.entity.Car;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarRequest {

  String brand;
  String model;
  double pricePrDay;
  double bestDiscount;

  public static Car getCarEntity(CarRequest c) {
    return new Car(
      c.getBrand(),
      c.getModel(),
      c.getPricePrDay(),
      c.getBestDiscount()
    );
  }

  public CarRequest(Car c) {
    this.brand = c.getBrand();
    this.model = c.getModel();
    this.pricePrDay = c.getPricePrDay();
    this.bestDiscount = c.getBestDiscount();
  }
}
