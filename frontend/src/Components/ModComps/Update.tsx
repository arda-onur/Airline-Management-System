
import "../../assets/cssClass/ModCompCss/Mod.css"
import SubmitButton from "./SubmitButton.tsx";

export default function Update(props){
    const variables = Object.keys(props).filter(key => key !== "api");
    const isUnderMaintenance = "underMaintenance" in props;

    if (isUnderMaintenance) {
        return <p style={{
            display: "flex",
                justifyContent: "center",
                alignItems: "center",
                height: "100vh",
                fontSize: "1.5rem",
                fontWeight: "bold"
        }}>Update Section Under Maintenance</p>;
    }


    function handleSubmit(e: React.FormEvent<HTMLFormElement>){
        e.preventDefault();
        const formData = new FormData(e.currentTarget);
        const jsonData: Record<string,string> = {}

        formData.forEach((value, key) => {
            const v = value.toString();

            if (v.trim() !== "") {
                jsonData[key] = v;
            }
        })

        fetch(props.api.ApiUpdate,{
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(jsonData)
        }).then(res => res.text())
            .then(messeage => alert(messeage))
            .catch(err => alert(err));


    }

    return(


      <div className="add">
          <form className="add" onSubmit={handleSubmit}>
              {variables.map(variable => (
                  <div className="addSubComp">
                      <label> {variable}</label>
                      {variable === "DepartureTime" || variable === "ArrivalTime" ?
                          <input type= "datetime-local" name={variable} /> :
                          <input  name={variable} /> }
                  </div>
              ))}

              {variables.length > 0 ? <SubmitButton/> : null}

          </form>

      </div>
    );
}