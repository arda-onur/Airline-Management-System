import '../../assets/cssClass/SubCompCss/Header.css'
import ModSelection from "../ModComps/ModSelection.tsx";

export default function Airline() {
    const add = {
        iataCode: "IataCode",
        icaoCode: "IcaoCode",
        name: "Name",
        country: "Country",
        fleetSize: "FleetSize",
    }
    const update = {
        icaoCode: "IcaoCode",
        name: "Name",
        country: "Country",
        fleetSize: "FleetSize",
    }
    const deletion = {
        icaoCode: "IcaoCode"
    }

    const list = {
        iataCode: "IataCode",
        icaoCode: "IcaoCode",
        name: "Name",
        country: "Country",
        fleetSize: "FleetSize",
    }



    const api = {
        ApiCreate: "http://localhost:8080/airline/create",
        ApiUpdate:"http://localhost:8080/airline/update",
        ApiDelete:"http://localhost:8080/airline/delete",
        ApiList: "http://localhost:8080/airline/getAllAirlines",
    }
    return(
        <>
            <h2 id="HeaderName">Airline Management</h2>
            <ModSelection add = {add} update = {update} deletion = {deletion} list={list}  api = {api}/>
        </>

    );
}