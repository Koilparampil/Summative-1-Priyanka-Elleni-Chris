import { useState } from "react";
import "./GameForm.css";
import Game from "../Game";

function GameForm({game: initialGame, notify }) {
    const [game, setGame] = useState(initialGame);
    const isAdd = initialGame.gameId === 0;

    function handleChange(event) {
        const clone = { ...game };
        clone[event.target.name] = event.target.value;
        setGame(clone);
    }

    function handleSubmit(evt) {
        evt.preventDefault();

        const url = `http://localhost:8080/games`;
        const method = isAdd ? "POST" : "PUT";
        const expectedStatus = isAdd ? 201 : 204;

        const init = {
            method,
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify(game)
        };

        fetch(url, init)
            .then(response => {

                if (response.status === expectedStatus) {
                    if (isAdd) {
                        return response.json();
                    } else {
                        return game;
                    }
                }
                return Promise.reject(`Didn't receive expected status: ${expectedStatus}`);
            })
            .then(result => notify({
                action: isAdd ? "add" : "edit",
                game: result
            }))
            .catch(error => notify({ error: error }));
    }

    return (
        <>
            <h1>{isAdd ? "Add" : "Edit"} Game</h1>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="title">Title</label>
                    <input type="text" id="title" name="title"
                        className="form-control"
                        value={game.title} onChange={handleChange} />
                </div>
                <div className="mb-3">
                <label htmlFor="esrbRating">ESRB Rating</label>
                    <select name="esrbRating" value={game.esrbRating} onChange={handleChange}>
                    <option value="E">E</option>
                    <option value="E10+">E10+</option>
                    <option value="T">T</option>
                    <option value="M">M</option>
                    <option value="AO">AO</option>
                    </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="description">Description</label>
                    <input type="text" id="description" name="description"
                        className="form-control"
                        value={game.description} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="price">Price</label>
                    <input type="text" id="price" name="price"
                        className="form-control"
                        value={game.price} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="studio">Studio</label>
                    <input type="text" id="studio" name="studio"
                        className="form-control"
                        value={game.studio} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="quantity">Quantity</label>
                    <input type="text" id="quantity" name="quantity"
                        className="form-control"
                        value={game.quantity} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <button id="saveButton" className="btn btn-primary mr-3" type="submit">Save</button>
                    <button className="btn btn-secondary" type="button" onClick={() => notify({ action: "cancel" })}>Cancel</button>
                </div>
            </form>
        </>
    );
}

export default GameForm;