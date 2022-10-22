import React from 'react'
import { useState, useEffect } from 'react';
import TshirtCard from './TshirtCard';
import TshirtForm from './TshirtForm';
import "./Tshirts.css";

function Tshirts() {
    const [tshirts, setTshirts] = useState([]); 
    const [scopedTshirt, setScopedTshirt] = useState({});
    const [showForm, setShowForm] = useState(false);
    const [error, setError] = useState();
    
    
    useEffect(() => {
        fetch("http://localhost:8080/Tshirt")
        .then(res => res.json())
        .then(result => setTshirts(result))
        .catch(console.log);
       
    }, []);

    function fetchByColor(e){
        if (e.target.value === "") {
        setTshirts([]);
        } else {
            fetch("http://localhost:8080/Tshirt/color/" + e.target.value)
                .then(response => response.json())
                .then(result => setTshirts(result))
                .catch(console.log);
        }
    }

    function fetchBySize(e){
        if (e.target.value ==="") {
           setTshirts([]);
            fetch("http://localhost:8080/Tshirt/size/" + e.target.value)
                .then(response => response.json())
                .then(result => setTshirts(result))
                .catch(console.log(error));
        }
    }
    function addClick() {
        setScopedTshirt({id:0, tshirt: "", color: "", size: "", description: "", price: 0, quantity:1});

        setShowForm(true);
    }

    function notify({action, tshirt, error}){
        if (error) {
            setError(error);
            setShowForm(false);
            return;
        }

        switch (action) {
            case "add":
                setTshirts([...tshirts, tshirt]);
                break;
            case "edit":
                setTshirts(tshirts.map(e => {
                    if (tshirt.id === e.id) {
                        return tshirt;
                    }
                    return e;
                }))
                break;
            case "edit-form":
                setShowForm(true);
                setScopedTshirt(tshirt);
                return;
            case "delete":
                setTshirts(tshirts.filter(e=> e.id !==  tshirt.id))
                break;
            default:
                console.log("Invalid Action" + action);
        }
        setError("");
        setShowForm(false);
    }

    if (showForm) {
        return <TshirtForm tshirt={scopedTshirt} notify={notify} />
    }

    return(
        <>
        <div>
            <h1 id = "tshirtTitle">Tshirts</h1>

          <div>
            <button className="btn btn-primary" type="button" onClick={addClick}>Add a Tshirt</button>

         
            <select name="color" onChange={fetchByColor}>
                <option value="red">Red</option>
                <option value="gold">Gold</option>
                <option value="green">Green</option>
                <option value="orange">Orange</option>
                <option value="blue">Blue</option>
            </select>

            <select name="size" onChange={fetchBySize}>
                    <option value="s">small</option>
                    <option value="m">medium</option>
                    <option value="l">large</option>
                    <option value="xs">xsmall</option>
            </select>
      
      </div>


        </div>
        {error && <div className="alert alert-danger">{error}</div>}
        <div>
            <table id="tshirts">
               
                <tr>
                
                    <th>Tshirt Id</th>
                    <th>Color</th>
                    <th>Size</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Quantity</th>
            
                </tr>
                
                <tbody>
                    {tshirts.map(res => <TshirtCard key={res.id} tshirt={res} notify={notify} />)}
                </tbody>
            </table>

        </div>
        </>
    )
}

export default Tshirts;

