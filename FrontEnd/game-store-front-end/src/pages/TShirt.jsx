import React from 'react'
import { useState, useEffect } from 'react';
import TshirtCard from './TshirtCard';
import TshirtForm from './TshirtForm';

function TShirt() {

  const [tshirts, setTshirts] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [scopedTshirt, setScopedTshirt] = useState({});
  const [error, setError] = useState();

  useEffect(() => {
    fetch("http://localhost:8080/tshirt")
    .then(response => response.json())
    .then(result => setTshirts(result))
    .catch(console.log);
}, []);

function addClick() {
 
  setScopedTshirt({ id: 0, tshirts: "", size: "", color: "", description: "", price: "", quantity: "" });
  setShowForm(true);
}

function notify({ action, tshirt, error }) {
  if (error) {
      setError(error);
      setShowForm(false);
      return;
  }
  switch (action) {
      case "delete":
          setTshirts(tshirts.filter(r => r.id !== tshirt.id))
          break;
      case "edit":
          setTshirts(tshirts.map(r => {
              if (r.id === tshirt.id) {
                  return tshirt;
              }
              return r;
          }));
          break;
      case "edit-form":
          setShowForm(true);
          setScopedTshirt(tshirt);
          return;
      case "add":
          setTshirts([...tshirts, tshirt]);
          break;
      default:
          console.log("INVALID ACTION!", action);
          console.log("also this... INVALID ACTION!" + action);
          alert("INVALID ACTION! " + action);
  }

  setError("");
  setShowForm(false);
}


if (showForm) {
  return <TshirtForm record={scopedTshirt} notify={notify} />
}

return (
  <>
      {error && <div className="alert alert-danger">{error}</div>}
      <div>
          <h1 id='tshirtTitle'>Tshirt</h1>
          <button className="btn btn-primary" type="button" onClick={addClick}>Add a Tshirt</button>
          <table id='tshirt'>
              <tr>
                  <th>Size</th>
                  <th>Color</th>
                  <th>Description</th>
                  <th>Price</th>
                  <th>Quantity</th>
              </tr>
              <tbody>
                  {tshirts.map(r => <TshirtCard key={r.tshirtId} tshirt={r} notify={notify} />)}
              </tbody>
          </table>
      </div>
  </>
)
}

export default TShirt