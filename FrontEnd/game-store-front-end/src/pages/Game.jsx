import React, { useState, useEffect } from 'react';
import GameCard from './components/GameCard';

const Game = () => {
  const [games, setGames] = useState([]);
  const [error, setError] = useState();

  useEffect(() => {
    fetch("http://localhost:8080/games")
    .then(response => response.json())
    .then(result => setGames(result))
    .catch(console.log);
}, []);

  function notify() {

  }

  return (
    <div>
      <h1 id='gameTitle'>Games</h1>

      {/* <button className="btn btn-primary" type="button" onClick={addClick}>Add a Recipe</button> */}

      <table id='games'>
        <thead>
                    <tr>
                        <th>Title</th>
                        <th>ESRB rating</th>
                        <th>Desription</th>
                        <th>Price</th>
                        <th>Studio</th>
                        <th>Quantity</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                        {games.map(game => <GameCard key={game.gameId} game={game} notify={notify} />)}
                    </tbody>
                </table>
    </div>
  )
}

export default Game;