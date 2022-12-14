import { useState } from 'react';

const ConsoleForm = ({ console: initialConsole, notify }) => {

    const [console, setConsole] = useState(initialConsole);
    const isAdd = initialConsole.id === 0;

    function handleChange(evt) {
        const clone = { ...console };
        clone[evt.target.name] = evt.target.value;
        setConsole(clone);
    }
    function handleSubmit(evt) {
        evt.preventDefault();

        const url = `http://localhost:8080/consoles`;
        const method = isAdd ? "POST" : "PUT";
        const expectedStatus = isAdd ? 201 : 204;

        const init = {
            method,
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify(console)
        };

        fetch(url, init)
            .then(response => {

                if (response.status === expectedStatus) {
                    if (isAdd) {
                        return response.json();
                    } else {
                        return console;
                    }
                }
                return Promise.reject(`Didn't receive expected status: ${expectedStatus}`);
            })
            .then(result => notify({
                action: isAdd ? "add" : "edit",
                customer: result
            }))
            .catch(error => notify({ error: error }));
    
    }
if (console.id!==0){
  return (
    <form onSubmit={handleSubmit}>
    <div className="mb-3">
        <label htmlFor="id">Console ID</label>
        <input type="text" id="id" name="id"
            className="form-control"
            value={console.id} onChange={handleChange} />
    </div>
    <div className="mb-3">
        <label htmlFor="model">Model</label>
        <input type="text" id="model" name="model"
            className="form-control"
            value={console.model} onChange={handleChange} />
    </div>
    <div className="mb-3">
        <label htmlFor="manufacturer">Manufacturer</label>
        <input type="text" id="manufacturer" name="manufacturer"
            className="form-control"
            value={console.manufacturer} onChange={handleChange} />
    </div>
    <div className="mb-3">
        <label htmlFor="memoryAmount">Amount of Memory</label>
        <input type="text" id="memoryAmount" name="memoryAmount"
            className="form-control"
            value={console.memoryAmount} onChange={handleChange} />
    </div>
    <div className="mb-3">
        <label htmlFor="processor">Processor</label>
        <input type="text" id="processor" name="processor"
            className="form-control"
            value={console.processor} onChange={handleChange} />
    </div>
    <div className="mb-3">
        <label htmlFor="price">Price</label>
        <input type="text" id="price" name="price"
            className="form-control"
            value={console.price} onChange={handleChange} />
    </div>
    <div className="mb-3">
        <label htmlFor="quantity">Quantity</label>
        <input type="text" id="quantity" name="quantity"
            className="form-control"
            value={console.quantity} onChange={handleChange} />
    </div>

    <div className="mb-3">
        <button id="saveButton" className="btn btn-primary mr-3" type="submit">Save</button>
        <button className="btn btn-secondary" type="button" onClick={() => notify({ action: "cancel" })}>Cancel</button>
    </div>
</form>
  )
} else{
    return (
        <form onSubmit={handleSubmit}>
        <div className="mb-3">
            <label htmlFor="model">Model</label>
            <input type="text" id="model" name="model"
                className="form-control"
                value={console.model} onChange={handleChange} />
        </div>
        <div className="mb-3">
            <label htmlFor="manufacturer">Manufacturer</label>
            <input type="text" id="manufacturer" name="manufacturer"
                className="form-control"
                value={console.manufacturer} onChange={handleChange} />
        </div>
        <div className="mb-3">
            <label htmlFor="memoryAmount">Amount of Memory</label>
            <input type="text" id="memoryAmount" name="memoryAmount"
                className="form-control"
                value={console.memoryAmount} onChange={handleChange} />
        </div>
        <div className="mb-3">
            <label htmlFor="processor">Processor</label>
            <input type="text" id="processor" name="processor"
                className="form-control"
                value={console.processor} onChange={handleChange} />
        </div>
        <div className="mb-3">
            <label htmlFor="price">Price</label>
            <input type="text" id="price" name="price"
                className="form-control"
                value={console.price} onChange={handleChange} />
        </div>
        <div className="mb-3">
            <label htmlFor="quantity">Quantity</label>
            <input type="text" id="quantity" name="quantity"
                className="form-control"
                value={console.quantity} onChange={handleChange} />
        </div>
    
        <div className="mb-3">
            <button id="saveButton" className="btn btn-primary mr-3" type="submit">Save</button>
            <button className="btn btn-secondary" type="button" onClick={() => notify({ action: "cancel" })}>Cancel</button>
        </div>
    </form>
      )
}
}

export default ConsoleForm