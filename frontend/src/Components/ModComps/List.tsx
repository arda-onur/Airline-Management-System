import "../../assets/cssClass/ModCompCss/Mod.css"
import {useEffect, useState} from "react";

export default function List(props) {

    const variables = Object.keys(props).filter(key => key !== "api")
    const [page, setPage] = useState(0);
    const [size] = useState(5);
    const [data, setData] = useState([]);
    const [totalPages, setTotalPages] = useState(0);


    const [passengerId, setPassengerId] = useState("");
    const [flightPage, setflightPage] = useState(0);
    const [flightSize] = useState(5);
    const [totalFlightPages, setTotalFlightPages] = useState(0);
    const [flightData, setflightData] = useState([]);

    const [bookingId, setBookingId] = useState("");

    const fetchData = async () => {
        try {
            const response = await fetch(`${props.api.ApiList}?page=${page}&size=${size}`);
            if (!response.ok) {
                throw new Error("Veri çekilemedi");
            }
            const json = await response.json();
            setData(json.content);
            setTotalPages(json.totalPages);
        } catch (err) {
            console.error(err);
            alert("Veri alınamadı: " + err.message);
        }
    };

    useEffect(() => {
        fetchData();
    }, [page]);


    const handleFlightPageChange = async () => {
        try {
            const idValue = passengerId;
            if (!idValue) {
                alert("Please Enter ID");
                return;
            }

            const response = await fetch(`${props.api.ApiPassengerBookingsList}?page=${flightPage}&size=${flightSize}&id=${idValue}`);
            if (!response.ok) throw new Error("Veri çekilemedi");

            const json = await response.json();
            setflightData(json.content);
            setTotalFlightPages(json.totalPages);
        } catch (err) {
            console.error(err);
            // @ts-ignore
            alert("Veri alınamadı: " + err.message);
        }
    };

    const handleCancel = async () => {
        try {
            if (!bookingId) {
                alert("Please enter an ID");
                return;
            }

            const res = await fetch(`http://localhost:8080/booking/cancel?bookingId=${bookingId}`, {
                method: "POST",
            });

            const text = await res.text(); // JSON değilse hata vermez
            alert(text);
        } catch (err) {
            console.error(err);
            alert("Bir hata oluştu: " + err.message);
        }
    };

        const prevPage = () => {
            setPage((p) => Math.max(0, p - 1));
        };

        const nextPage = () => {
            setPage((p) => Math.min(totalPages - 1, p + 1));
        };

        const prevFlightPage = () => {
            setflightPage((p) => Math.max(0, p - 1));
        };

        const nextFlightPage = () => {
            setPage((p) => Math.min(totalPages - 1, p + 1));
        };


        return (
            <>
                {variables[0] === "email" ? (
                    <div id="search">
                        <input
                            name="passengerId"
                            value={passengerId}
                            onChange={(e) => setPassengerId(e.target.value)}
                            placeholder="Passenger ID"
                        />
                        <button type="button" onClick={handleFlightPageChange}>Search</button>
                    </div>
                ) : null}

                <div className="outerTable">
                    <table id="table">
                        <thead>
                        <tr>
                            {variables.map(variable => (<th key={variable}>{variable}</th>))}
                        </tr>
                        </thead>
                        <tbody>
                        {data.map((item, rowIdx) => (
                            <tr key={rowIdx}>
                                {variables.map((key) => {

                                    let value = item[key];


                                    if (value === undefined && item.flight && key in item.flight) {

                                        value = item.flight[key];
                                    }
                                    if (key === "passenger") {

                                        const p = item.passenger;
                                        return (
                                            <td key={key}>
                                                {p ? (p.email || JSON.stringify(p)) : ""}
                                            </td>
                                        );
                                    }
                                    if (typeof value === "object" && value !== null) {
                                        return <td key={key}>{JSON.stringify(value)}</td>;
                                    }
                                    return <td key={key}>{value ?? ""}</td>;
                                })}
                            </tr>
                        ))}
                        </tbody>
                    </table>

                    <div className="pagination-buttons">
                        <button onClick={prevPage} disabled={page === 0}>Previous</button>
                        <button onClick={nextPage} disabled={page === totalPages - 1}> Next</button>
                    </div>
                </div>

                {variables[8] === "bookingStatus" ? (
                    <div id="search">
                        <input
                            name="bookingId"
                            value={bookingId}
                            onChange={(e) => setBookingId(e.target.value)}
                            placeholder="Booking ID"
                        />
                        <button type="button" onClick={handleCancel}>Cancel</button>
                    </div>
                ) : null}


                {flightData.length > 0 ? (
                    <div className="outerTable">
                        <table id="table">
                            <thead>
                            <tr>
                                {Object.keys(flightData[0]).map((key) => {
                                    let header = key;
                                    if (key === "id") header = "Booking ID"
                                    if (key === "flight") header = "Flight Number"
                                    if (key === "passenger") header = "Passenger Email"
                                    return <th key={key}>{header}</th>;
                                })}
                            </tr>
                            </thead>
                            <tbody>
                            {flightData.map((flight, index) => (
                                <tr key={index}>
                                    {Object.keys(flight).map((key) => (
                                        <td key={key}>
                                            {key === "flight" ? flight.flight?.flightNumber ?? "-" : key === "passenger" ? flight.passenger?.email ?? "-" : flight[key] ?? ""}
                                        </td>
                                    ))}
                                </tr>

                            ))}
                            </tbody>
                        </table>
                        <div className="pagination-buttons">
                            <button onClick={prevFlightPage} disabled={flightPage === 0}>Previous</button>
                            <button onClick={nextFlightPage} disabled={flightPage === totalFlightPages - 1}> Next
                            </button>
                        </div>
                    </div>
                ) : (
                    <div></div>
                )}


            </>
        );
    }
