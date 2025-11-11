import ModSelection from "../ModComps/ModSelection.tsx";

export default function Passenger() {
    const add = {
        email: "Email",
        name: "Name",
        surname: "Surname",
    }

    const api = {
        ApiCreate: "http://localhost:8080/passenger/create",
        ApiUpdate:"http://localhost:8080/passenger/update",
        ApiDelete:"http://localhost:8080/passenger/delete",
        ApiList:"http://localhost:8080/passenger/getAllPassenger",
        ApiPassengerBookingsList:"http://localhost:8080/booking/getPassengerBookingsWithId"
    } as const

    const list = {
        email: "Email",
        name: "Name",
        surname: "Surname",
        loyaltyPoints: "Loyalty Points",
        id: "id",
    }
    return(
        <>
            <h2 id="HeaderName">Passenger Management</h2>
            <ModSelection add = {add} list={list} api={api}/>
        </>
    );
}