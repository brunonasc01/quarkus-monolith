-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

--create sequence booking_sequence start with 1 increment by 1;
INSERT INTO Booking(id) VALUES (nextval('booking_sequence'));
INSERT INTO Booking(id) VALUES (nextval('booking_sequence'));
INSERT INTO Booking(id) VALUES (nextval('booking_sequence'));

INSERT INTO Flight(id, bookingId, flightFrom, flightTo) VALUES (nextval('flight_sequence'), 1, 'Jandira', 'Osasco');
INSERT INTO Flight(id, bookingId, flightFrom, flightTo) VALUES (nextval('flight_sequence'), 2, 'Sao Paulo', 'Cuiaba');
INSERT INTO Flight(id, bookingId, flightFrom, flightTo) VALUES (nextval('flight_sequence'), 3, 'Belo Horizonte', 'Tokyo');

INSERT INTO Hotel(id, bookingId, nights) VALUES (nextval('hotel_sequence'), 1, 3);
INSERT INTO Hotel(id, bookingId, nights) VALUES (nextval('hotel_sequence'), 2, 5);
INSERT INTO Hotel(id, bookingId, nights) VALUES (nextval('hotel_sequence'), 3, 7);