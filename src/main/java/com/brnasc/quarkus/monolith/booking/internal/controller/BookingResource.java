package com.brnasc.quarkus.monolith.booking.internal.controller;

import com.brnasc.quarkus.monolith.booking.api.model.Booking;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("booking")
public class BookingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Booking> bookings() {
        return Booking.listAll();
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
    public Booking createBooking(Booking booking) {
        booking.persist();
        return booking;
    }
}
