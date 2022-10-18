package com.company.Summative1PriyankaElleniChris.controller;

import com.company.Summative1PriyankaElleniChris.model.Game;
import com.company.Summative1PriyankaElleniChris.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    // Create operation
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody @Valid Game game) {
        return gameRepository.save(game);
    }

    // Update operation
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody @Valid Game game) {
        gameRepository.save(game);
    }

    // Delete operation
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id) {
        gameRepository.deleteById(id);
    }

    // Read all games
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    // Read a game by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game findGameById(@PathVariable int id) {
        Optional<Game> game = gameRepository.findById(id);

        if (game.isPresent()) {
            return game.get();
        } else {
            return null;
        }
    }

    // Search for games by studio
    @GetMapping("/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> findGamesByStudio(@PathVariable String studio) {
        return gameRepository.findByStudio(studio);
    }

    // Search for games by ESRB rating
    @GetMapping("/rating/{esrbRating}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> findGamesByEsrbRating(@PathVariable String esrbRating) {
        return gameRepository.findByEsrbRating(esrbRating);
    }

    // Search for games by title
    @GetMapping("/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> findGamesByTitle(@PathVariable String title) {
        return gameRepository.findByTitle(title);
    }
}
