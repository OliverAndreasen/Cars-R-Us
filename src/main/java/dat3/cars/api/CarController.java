package dat3.cars.api;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import dat3.cars.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

    CarService carService;

    public CarController(CarService carService){
        this.carService = carService;
    }

    @GetMapping
    public List<CarResponse> getCars(){
        return carService.getCars();
    }

    //Security ADMIN
    @GetMapping(path = "/{id}")
    public CarResponse getCarById(@PathVariable int id) throws Exception { //Obviously we need to be able to limit this in a system with thousands of members
        CarResponse response = carService.findCarById(id);
        return response;
    }

    //Security ADMIN
    @DeleteMapping("{id}")
        public void deleteCarById(@PathVariable int id){
        carService.deleteById(id);
    }

    //Security ADMIN
    @PatchMapping("Model/{id}/{value}")
    public void setModelForCar(@PathVariable int id, @PathVariable String value){

    }

    //Security ADMIN
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable int id){
        carService.editCar(body,id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
