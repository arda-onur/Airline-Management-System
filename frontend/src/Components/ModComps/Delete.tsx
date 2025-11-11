import SubmitButton from "./SubmitButton.tsx";

export default function Delete(probs) {
    const variables = Object.keys(probs).filter(key => key !== "api");

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const formData = new FormData(e.currentTarget);
        const jsonData: Record<string, string> = {};
        formData.forEach((value, key) => {
            jsonData[key] = value.toString();
        })
        const key = Object.keys(jsonData)[0];
        const value = jsonData[key];

        fetch(`${probs.api.ApiDelete}?${key}=${value}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(res => res.text())
            .then(message => alert(message))
            .catch(err => alert(err));
    }


    return (
        <>
            <div className="add">
                <form className="add" onSubmit={handleSubmit}>
                    {variables.map(variable => (
                        <div className="addSubComp">
                            <label> {variable}</label>
                            <input name={variable} required={true}/>
                        </div>
                    ))}
                    {variables.length > 0 ? <SubmitButton/> : null}
                </form>

            </div>
        </>
    );
}