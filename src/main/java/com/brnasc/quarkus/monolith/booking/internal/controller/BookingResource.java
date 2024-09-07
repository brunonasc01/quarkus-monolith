package com.brnasc.quarkus.monolith.booking.internal.controller;

import com.brnasc.quarkus.monolith.booking.api.model.Booking;
import com.brnasc.quarkus.monolith.booking.api.model.BookingDto;
import com.brnasc.quarkus.monolith.flight.api.model.Flight;
import com.brnasc.quarkus.monolith.flight.internal.controller.FlightResource;
import com.brnasc.quarkus.monolith.hotel.api.model.Hotel;
import com.brnasc.quarkus.monolith.hotel.internal.controller.HotelResource;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("booking")
public class BookingResource {

  @Inject FlightResource flightResource;

  @Inject HotelResource hotelResource;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<BookingDto> bookings() {
    return Booking.<Booking>listAll().stream()
        .map(
            booking ->
                BookingDto.of(
                    booking,
                    flightResource.retrieveByBooking(booking.id),
                    hotelResource.retrieveByBooking(booking.id)))
        .toList();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Booking retrieveBooking(@PathParam("id") Long id) {
    return Booking.findById(id);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Booking createBooking(BookingDto bookingDto) {
    Booking booking = new Booking();
    booking.persist();

    Flight flight = new Flight();
    flight.flightFrom = bookingDto.getFromAirport();
    flight.flightTo = bookingDto.getToAirport();
    flight.bookingId = booking.id;
    flightResource.createFlight(flight);

    Hotel hotel = new Hotel();
    hotel.nights = bookingDto.getNights();
    hotel.bookingId = booking.id;
    hotelResource.createHotel(hotel);

    return booking;
  }
}
