import {useEffect, useState, useRef} from 'react'
import "./Invoice.css";
import InvoiceForm from '../components/InoviceForm';
import InvoiceCard from "../components/InvoiceCard"
const Invoice = () => {
  const [invoices, setInvoices] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [scopedInvoice, setScopedInvoice] = useState({})
  const [error, setError] = useState();
  const inputRef2 = useRef(null);
  useEffect(()=>{
    getInvoices()
},[])

const getInvoices = () => {
  fetch("http://localhost:8080/invoices")
  .then(response => response.json())
  .then(result => setInvoices(result))
  .catch(console.log);
}

const getInvoicesByName = () =>{
  fetch(`http://localhost:8080/invoices/CustomerName/${inputRef2.current.value}`)
  .then(response => response.json())
  .then(result => setInvoices(result))
  .catch(console.log);
}


function addClick(){
  setScopedInvoice({ id: 0 });
  setShowForm(true)
  console.log(showForm)
}
function notify({ action, invoice, error }) {

  if (error) {
      setError(error);
      setShowForm(false);
      return;
  }

  switch (action) {
      case "add":
        getInvoices();
          setInvoices([...invoices, invoice]);
          break;
      default:
          break;
  }
  setError("");
  setShowForm(false);
}
if (showForm) {
  return <InvoiceForm invoice={scopedInvoice} notify={notify} />
}


  return (
    <div>
    <div id='buttonPanel' className="row mt-2">
        <button className="btn btn-primary" type="button"onClick={addClick}>Add an Invoice</button>
        <br />
        <button onClick={getInvoices}>Show me all the Invoices </button>
        <br />
        <input type="text" id="id" name="id" className="form-control" ref={inputRef2}/>
        <button onClick={getInvoicesByName}>Lookup By Name</button>
    </div>
        <div>
            <h1 id='invoiceTitle'>Invoices</h1>
            <table id='invoices'>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Street</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Zipcode</th>
                    <th>ItemType</th>
                    <th>ItemId</th>
                    <th>UnitPrice</th>
                    <th>Quantity</th>
                    <th>Subtotal</th>
                    <th>Tax</th>
                    <th>ProcessingFee</th>
                    <th>Total</th>
                </tr>
                <tbody>
                    {invoices.map(r => {
                      console.log("thisisTheMap")
                      console.log(r)
                      return <InvoiceCard key={r.invoiceId} invoice={r} notify={notify}/>})}
                </tbody>
            </table>
        </div>
</div>  
  )
}

export default Invoice