
import './assets/cssClass/App.css'
import Header from './Components/SubComps/Header.tsx'
import NavigationButton  from "./Components/SubComps/NavigationButton.tsx";
import {useState} from "react";
import Airline from "./Components/mainComps/Airline.tsx";
import Passenger from "./Components/mainComps/Passenger.tsx";
import Flight from "./Components/mainComps/Flight.tsx";
import Booking from "./Components/mainComps/Booking.tsx";


function App() {
   const [page, setPage] = useState("");
  return (
    <>
        <Header/>
        <div className="NavigationButtons">
            <NavigationButton  name={"Airline"} onClick={() => setPage("Airline")}/>
            <NavigationButton  name={"Flight"} onClick={() => setPage("Flight")}/>
            <NavigationButton  name={"Passenger"} onClick={() => setPage("Passenger")}/>
            <NavigationButton  name={"Booking"} onClick={() => setPage("Booking")}/>
        </div>

        {page === "Airline" && <Airline/>  }
        {page === "Flight" && <Flight/>}
        {page === "Passenger" && <Passenger/>}
        {page === "Booking" && <Booking/>}

    </>
  )
}

export default App
