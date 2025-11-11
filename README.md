
# Airline Management System
CaseStudy for Lydia Technologies

This project is a Flight Management System that allows users to manage, search, and view flight information.
The backend is developed with Java/Spring Boot, and the frontend with React.js.
The purpose of this case study is to evaluate both backend and frontend development skills in an integrated way.

## Technical Requirments

- Java 8+
- Spring Boot
- PostgreSQL

- Node 16+
- React.js

 ## Getting Started
### Installation

1. **Clone the Project**
   ```bash
   git clone https://github.com/your-username/flight-management-system.git](https://github.com/arda-onur/Airline-Management-System.git
    ```

2. **Navigate Project**
   ```bash
   cd Airline-Management-System
    ```
3. **Docker Implementation**
    On command
   ```bash
    docker pull postgres:latest
    ```
## Running The Project
1. **Start the Application:**
- **Navigate Frontend**
   ```bash
   cd Airline-Management-System/frontend
    ```
 - **Run Frontend**
   ```bash
    npm run dev
    ```

   
 - **Run Backend**

    **Navigate backend**
   ```bash
   cd Airline-Management-System/backend/casestudy
    ```
   - **On Mac and Linux**
     ```bash
     ./mvnw clean spring-boot:run
     ```

   - **On Windows**
     ```bash
     mvnw clean spring-boot:run
     ```


# API Endpoints

The application provides RESTful endpoints for managing **Airlines**, **Bookings**, **Flights**, and **Passengers**.  
All endpoints support CORS for `http://localhost:5173`.  

## Airline Management (`/airline`)
## POST
```bash
/airline/create
```
- Description - Creates a new airline record
- Body - AirlineRequest (JSON, @Valid)
- Response - 201 Created – Confirmation message

## GET
```bash
/airline/getAllAirlines
```
- Description - Retrieves all airlines with pagination
- Query Parameters - page (int), size (int)
- Response - 200 OK – Returns Page<Airline>

```bash
/airline/getRelatedAirline
```
- Description - Retrieves airlines filtered by ICAO code
- Query Parameters - page (int), size (int), icaoCode (String)
- Response - 200 OK – Returns Page<Airline>

## PATCH
```bash
/airline/update
```
- Description - Updates existing airline information
- Body - AirlineRequest (JSON)
- Response - 200 OK – Confirmation message

## DELETE
```bash
/airline/delete
```
- Description - Deletes an airline by ICAO code
- Query Parameters - icaoCode (String)
- Response - 200 OK – Confirmation message


#Booking Management (/booking)
##POST
```bash
/booking/create
```

- Description - Creates a new booking record
- Body - BookingRequest (JSON)
- Response - 200 OK – Confirmation message

#POST
```bash
/booking/cancel
```

- Description - Cancels an existing booking by booking ID
- Query Parameters - bookingId (int)
- Response - 200 OK – Confirmation message

##GET
```
/booking/getBookingsWithId
```

- Description - Retrieves all bookings for a specific flight ID
- Query Parameters - page (int), size (int), id (long)
- Response - 200 OK – Returns Page<Booking>

``` bash
/booking/getPassengerBookingsWithId
```

- Description - Retrieves all bookings for a specific passenger ID
- Query Parameters - page (int), size (int), id (int)
- Response - 200 OK – Returns Page<Booking>

``` bash
/booking/getAllBookings
```
- Description - Retrieves all bookings with pagination
- Query Parameters - page (int), size (int)
- Response - 200 OK – Returns Page<Booking>

#Flight Management (/flight)

##POST
``` bash
/flight/create
```

- Description - Creates a new flight record
- Body - FlightRequest (JSON, @Valid)
- Response - 200 OK – Confirmation message

##GET
``` bash
/flight/getFlightsByAirlines
```

- Description - Retrieves all flights for a given airline
- Query Parameters - page (int), size (int), airline (String)
- Response - 200 OK – Returns Page<Flight>

``` bash
/flight/getAllFlights
```

- Description - Retrieves all flights with pagination
- Query Parameters - page (int), size (int)
- Response - 200 OK – Returns Page<Flight>

## PATCH
```bash
/flight/update
```

- Description - Updates existing flight details
- Body - FlightRequest (JSON)
- Response - 200 OK – Confirmation message

## DELETE
```bash
/flight/delete
```
- Description - Deletes a flight by ID
- Query Parameters - id (long)
- Response - 200 OK – Confirmation message

#Passenger Management (/passenger)
## POST
```bash
/passenger/create
```

- Description - Registers a new passenger
- Body - PassengerRequest (JSON, @Valid)
- Response - 201 Created – Confirmation message

##GET
```bash
/passenger/getAllPassenger
```
- Description - Retrieves all passengers with pagination
- Query Parameters - page (int), size (int)
- Response - 200 OK – Returns Page<Passenger>

```bash
/passenger/getRelatedFlights
```
- Description - Retrieves passengers filtered by email
- Query Parameters - page (int), size (int), email (String)
- Response - 200 OK – Returns Page<Passenger>
