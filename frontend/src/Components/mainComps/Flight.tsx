
import '../../assets/cssClass/SubCompCss/Header.css'
import ModSelection from "../ModComps/ModSelection.tsx";

export default function Flight() {
    const add = {
        icaoCode: "IcaoCode",
        flightNumber: "FlightNumber",
        origin: "Origin",
        destination: "Destination",
        departureTime: "DepartureTime",
        arrivalTime: "ArrivalTime",
        basePrice: "BasePrice",
        capacity: "Capacity",
        bookedSeats: "BookedSeats"
    }
    const update = {
        flightNumber: "FlightNumber",
        origin: "Origin",
        destination: "Destination",
        departureTime: "DepartureTime",
        arrivalTime: "ArrivalTime",
        basePrice: "BasePrice",
        capacity: "Capacity",
        bookedSeats: "BookedSeats"
    };

    const deletion = {
        id:"Id"
    }

    const api = {
        ApiCreate: "http://localhost:8080/flight/create",
        ApiUpdate:"http://localhost:8080/flight/update",
        ApiDelete:"http://localhost:8080/flight/delete",
        ApiList: "http://localhost:8080/flight/getAllFlights"
    } as const

    const list = {
            id: "id",
            flightNumber: "FlightNumber",
            origin: "Origin",
            destination: "Destination",
            departureTime: "DepartureTime",
            arrivalTime: "ArrivalTime",
            basePrice: "BasePrice",
            capacity: "Capacity",
            bookedSeats: "BookedSeats"
        }


    return(
        <>
            <h2 id="HeaderName">Flight Management</h2>
            <ModSelection  add={add} update={update} deletion= {deletion} list={list} api={api}/>
        </>
    );
}