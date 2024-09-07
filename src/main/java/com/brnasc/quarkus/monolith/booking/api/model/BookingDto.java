package com.brnasc.quarkus.monolith.booking.api.model;

import com.brnasc.quarkus.monolith.flight.api.model.Flight;
import com.brnasc.quarkus.monolith.hotel.api.model.Hotel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

  private String fromAirport;

  private String toAirport;

  private Integer nights;

  public static BookingDto of(Booking booking, Flight flight, Hotel hotel) {
    return new BookingDto(flight.flightFrom, flight.flightTo, hotel.nights);
  }

  public static BookingDto of(String fromAirport, String toAirport, Integer nights) {
    return new BookingDto(fromAirport, toAirport, nights);
  }
}
