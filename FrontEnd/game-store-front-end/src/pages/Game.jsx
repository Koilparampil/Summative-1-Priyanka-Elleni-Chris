import React, { useState, useEffect } from "react";
import GameCard from "./components/GameCard";
import GameForm from "./components/GameForm";

const Game = () => {
  const [games, setGames] = useState([]);
  const [error, setError] = useState();
  const [title, setTitle] = useState("");
  const [studio, setStudio] = useState("");
  const [scopedGame, setScopedGame] = useState({});
  const [showForm, setShowForm] = useState();

  // Get all games
  useEffect(() => {
    fetch("http://localhost:8080/games")
      .then((response) => response.json())
      .then((result) => setGames(result))
      .catch(console.log);
  }, []);

  //
  const addClick = () => {
    setScopedGame({ gameId: 0, title: "", esrbRating: "E", description: "", studio: "" });
    setShowForm(true);
  };

  // Gets games by ESRB
  const fetchByEsrbRating = (event) => {
    if (event.target.value === "") {
      fetch("http://localhost:8080/games")
        .then((response) => response.json())
        .then((result) => setGames(result))
        .catch(console.log);
    } else {
      fetch("http://localhost:8080/games/rating/" + event.target.value)
        .then((response) => response.json())
        .then((result) => setGames(result))
        .catch((err) => console.log(err));
    }
  };

  const handleTitleChange = (event) => {
    const { target } = event;
    setTitle(target.value);
  };

  // Get games by title
  const handleTitleSearch = (event) => {
    // Prevents the default behavior of the form submit (which is to refresh the page)
    event.preventDefault();

    if (!title) {
      fetch("http://localhost:8080/games")
        .then((response) => response.json())
        .then((result) => setGames(result))
        .catch(console.log);
    } else {
      fetch("http://localhost:8080/games/title/" + title)
        .then((response) => response.json())
        .then((result) => setGames(result))
        .catch((err) => console.log(err));
    }

    setTitle("");
  };

  const handleStudioChange = (event) => {
    const { target } = event;
    setStudio(target.value);
  };

  // Get games by title
  const handleStudioSearch = (event) => {
    // Prevents the default behavior of the form submit (which is to refresh the page)
    event.preventDefault();

    if (!studio) {
      fetch("http://localhost:8080/games")
        .then((response) => response.json())
        .then((result) => setGames(result))
        .catch(console.log);
    } else {
      fetch("http://localhost:8080/games/studio/" + studio)
        .then((response) => response.json())
        .then((result) => setGames(result))
        .catch((err) => console.log(err));
    }

    setStudio("");
  };

  function notify({ action, game, error }) {
    if (error) {
      console.log(error);
      setError(error);
      setShowForm(false);
      return;
    }

    switch (action) {
      case "add":
        setGames([...games, game]);
        break;
      case "edit":
        setGames(
          games.map((g) => {
            if (g.gameId === game.gameId) {
              return game;
            }
            return g;
          })
        );
        break;
      case "edit-form":
        setScopedGame(game);
        setShowForm(true);
        return;
      case "delete":
        setGames(
          games.filter((g) => g.gameId !== game.gameId)
        );
        break;
      default:
        break;
    }

    setError("");
    setShowForm(false);
  }

  if (showForm) {
    return <GameForm game={scopedGame} notify={notify}></GameForm>;
  }

  return (
    <div>
      <h1 id="gameTitle">Games</h1>

      <div>
        <button className="btn btn-primary" type="button" onClick={addClick}>
          Add a game
        </button>

        <select name="esrbRating" onChange={fetchByEsrbRating}>
          <option value="">Get Games by ESRB Rating</option>
          <option value="E">E</option>
          <option value="E10+">E10+</option>
          <option value="T">T</option>
          <option value="M">M</option>
          <option value="AO">AO</option>
        </select>

        <form>
          <input
            value={title}
            name="title"
            onChange={handleTitleChange}
            type="text"
            placeholder="Get games by title"
          ></input>
          <button type="button" onClick={handleTitleSearch}>
            Search
          </button>
        </form>

        <form>
          <input
            value={studio}
            name="studio"
            onChange={handleStudioChange}
            type="text"
            placeholder="Get games by studio"
          ></input>
          <button type="button" onClick={handleStudioSearch}>
            Search
          </button>
        </form>
      </div>

      <table id="games">
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
          {games.map((game) => (
            <GameCard key={game.gameId} game={game} notify={notify} />
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Game;
