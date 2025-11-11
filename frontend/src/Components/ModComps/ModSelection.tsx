import * as React from "react";
import Add from "./Add.tsx";
import Update from "./Update.tsx";
import List from "./List.tsx";
import Delete from "./Delete.tsx";



export default function ModSelection(props ) {
    const [relatedMod, setRelatedMod] = React.useState("");

    return (
        <>
            <div id="modSelectionDiv">
                <select id="modSelection" onChange={(e) => setRelatedMod(e.target.value)}>
                    <option value="" disabled selected hidden>Select</option>
                    <option value="add">Add</option>
                    <option value="update">Update</option>
                    <option value="list">List</option>
                    <option value="delete">Delete</option>
                </select>
            </div>

            {relatedMod === "add" && <Add {...props.add} api={props.api}/>}
            {relatedMod === "update" && <Update {...props.update} api={props.api}/>}
            {relatedMod === "list" && <List{...props.list} api={props.api}/>}
            {relatedMod === "delete" && <Delete{...props.deletion} api={props.api}/>}


        </>
    );
}