import '../../assets/cssClass/SubCompCss/Header.css'
import ModSelection from "../ModComps/ModSelection.tsx";

export default function Booking() {
    const add = {
        flightId:"FlightId",
        email:"Email",
        seatNumber:"SeatNumber"
    }

    const api = {
        ApiCreate: "http://localhost:8080/booking/create",
        ApiUpdate:"http://localhost:8080/booking/update",
        ApiCancel:"http://localhost:8080/booking/cancel",
        ApiList:"http://localhost:8080/booking/getAllBookings"
    } as const

    const list = {
        id: "Booking ID",
        flightNumber: "Flight Number",
        origin: "Origin",
        destination: "Destination",
        departureTime: "Departure Time",
        arrivalTime: "Arrival Time",
        basePrice: "BasePrice",
        passenger: "Passenger",
        bookingStatus: "Booking Status",
    };

    const update = {
        underMaintenance: "UnderMaintenance",
    }
    return(
        <>
            <h2 id="HeaderName">Booking Management</h2>
            <ModSelection add={add} list={list} update={update} api={api}/>
        </>
    );
}