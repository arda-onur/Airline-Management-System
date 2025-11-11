
import "../../assets/cssClass/ModCompCss/Mod.css"
import SubmitButton from "./SubmitButton.tsx";
import * as React from "react";


export default function Add(props) {


    const variables = Object.keys(props).filter(key => key !== "api");


    const handleSubmit = async (e:React.FormEvent<HTMLFormElement>) => {
     e.preventDefault();
     const formData = new FormData(e.currentTarget);
     const jsonData: Record<string, string> = {};

        const capacityValue = formData.get("capacity");
        if (capacityValue != null) {
            const num = Number(capacityValue);

            if (num < 50 || num > 400) {
                alert("Capacity must be between 50 and 400.");
                return;
            }
        }

    formData.forEach((value, key) => {
       jsonData[key] = value.toString();
    });

   fetch(props.api.ApiCreate,{
         method: "POST",
         body: JSON.stringify(jsonData),
         headers: {
             "Content-Type": "application/json",
         }
     }).then(res => res.text())
       .then(message => alert(message))
       .catch(err => {
           alert("Encountered with Error: " + err.message);
       });



    }

    return (
        <>

          <div className="add">
             <form className="add" onSubmit={handleSubmit}>
                 {variables.map(variable => (
                     <div className="addSubComp">
                         <label> {variable}</label>
                         {variable === "departureTime" || variable === "arrivalTime" ?
                             <input type= "datetime-local" name={variable} required={true}/> :
                             <input  name={variable}  required={true}/> }
                     </div>
                 ))}
                 {variables.length > 0 ? <SubmitButton/> : null}
             </form>

          </div>




        </>
    );
}