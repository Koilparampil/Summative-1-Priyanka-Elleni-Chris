function GameCard({ game, notify }) {
    return (
        <tr key={game.title}>
            <td>{game.esrbRating}</td>
            <td>{game.description}</td>
            <td>{game.price}</td>
            <td>{game.studio}</td>
            <td>{game.quantity}</td>
            {/* <td>
                <button id="deleteButton" className="btn btn-danger mr-3" type="button" onClick={handleDelete}>Delete</button>
                <button id="editButton" className="btn btn-secondary" type="button" onClick={() => notify({ action: "edit-form", record: record })}>Edit</button>
            </td> */}
        </tr>
    )
}

export default GameCard;