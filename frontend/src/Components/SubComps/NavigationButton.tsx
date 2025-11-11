import '../../assets/cssClass/SubCompCss/NavigationButtons.css'

export default function NavigationButton({name, onClick}: { name: string, onClick?: () => void }){
    return(
        <button className={"button"} onClick={onClick}> {name} </button>
    );
}