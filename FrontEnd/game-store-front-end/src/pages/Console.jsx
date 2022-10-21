import {useEffect, useState, useRef} from 'react'
import ConsoleCard from '../components/ConsoleCard'
import ConsoleForm from '../components/ConsoleForm';
import "./Console.css"


const Console = () => {
    const [consoles, setConsoles] = useState([]);
    const [showForm, setShowForm] = useState(false);
    const [scopedConsole, setScopedConsole] = useState({})
    const [error, setError] = useState();
    const inputRef = useRef(null);
    const inputRef2 = useRef(null);
    useEffect(()=>{
        getConsoles()
    },[])


    const getConsoles = () => {
        fetch("http://localhost:8080/consoles")
        .then(response => response.json())
        .then(result => setConsoles(result))
        .catch(console.log);
    }

    const getConsolesByID = () =>{
        fetch(`http://localhost:8080/consoles/manufacturer/${inputRef.current.value}`)
        .then(response => response.json())
        .then(result => setConsoles([result]))
        .catch(console.log);
    }
    const getConsolesByManufacturer = () =>{
        fetch(`http://localhost:8080/consoles/${inputRef2.current.value}`)
        .then(response => response.json())
        .then(result => setConsoles([result]))
        .catch(console.log);
    }


    function addClick(){
        setScopedConsole({ id: 0 });
        setShowForm(true)
    }


    function notify({ action, console, error }) {

        if (error) {
            setError(error);
            setShowForm(false);
            return;
        }

        switch (action) {
            case "add":
                getConsoles();
                console.log("this is the consolelogFiring");
                setConsoles([...consoles, console]);
                break;
            case "edit":
                getConsoles()
                setConsoles(consoles.map(e => {
                    if (e.id === console.id) {
                        return console;
                    }
                    return e;
                }));
                break;
            case "edit-form":
                setScopedConsole(console);
                setShowForm(true);
                return;
            case "delete":
                setConsoles(consoles.filter(e => e.id !== console.id));
                break;
            default:
                break;
        }
        
        setError("");
        setShowForm(false);
    }
    if (showForm) {
        return <ConsoleForm console={scopedConsole} notify={notify} />
    }

  return (
    <div>
        <div id='buttonPanel' className="row mt-2">
            <button className="btn btn-primary" type="button"onClick={addClick}>Add a Console to Our Stock</button>
            <br />
            <input type="text" id="manufacturer" name="manufacturer" className="form-control" ref={inputRef}/>
            <button onClick={getConsolesByManufacturer}>Lookup By Manufacturer</button>
            <br />
            <input type="text" id="id" name="id" className="form-control" ref={inputRef2}/>
            <button onClick={getConsolesByID}>Lookup By ID</button>
            <br />
            <button onClick={getConsoles}>Show me all the consoles Again</button>
        </div>
            <div>
                <h1 id='consolesTitle'>Consoles</h1>
                <table id='consoles'>
                    <tr>
                        <th>ID</th>
                        <th>Model</th>
                        <th>Manufacturer</th>
                        <th>Memory Amount</th>
                        <th>Processor</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Actions</th>
                    </tr>
                    <tbody>
                        {consoles.map(r => {return <ConsoleCard key={r.id} console={r} notify={notify}/>})}
                    </tbody>
                </table>
            </div>
    </div>        
  )
}

export default Console