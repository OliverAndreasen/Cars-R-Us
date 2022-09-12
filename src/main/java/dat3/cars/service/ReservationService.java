package dat3.cars.service;

import dat3.cars.dto.MemberResponse;
import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ReservationService {
    ReservationRepository reservationRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;

    public ReservationService(ReservationRepository reservationRepository, MemberRepository memberRepository, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
    }


    public List<ReservationResponse> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        List<ReservationResponse> response = reservations.stream().map(reservation -> new ReservationResponse(reservation)).collect(Collectors.toList());

        return response;
    }


    public void reserveCar(String username, int carId, LocalDate rentalDate){
        Member member = memberRepository.findById(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found"));
        Car car = carRepository.findById(carId).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car not found"));

        boolean exists = reservationRepository.existsByCar_IdAndRentalDate(carId, rentalDate);

        if(exists){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car reserved for this date" );
        }else {
            Reservation reservation = new Reservation(member,car,rentalDate);
            reservationRepository.save(reservation);
        }
    }

    public ReservationResponse getReservation(@PathVariable int id){
        Reservation found = reservationRepository.findById(id).orElseThrow(()->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Reservation with this id not found"));
        return new ReservationResponse(found);
    }

}
