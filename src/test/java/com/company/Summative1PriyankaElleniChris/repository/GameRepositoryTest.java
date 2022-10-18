package com.company.Summative1PriyankaElleniChris.repository;

import com.company.Summative1PriyankaElleniChris.model.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameRepositoryTest {
    @Autowired
    private GameRepository gameRepository;

    @Before
    public void setUp() throws Exception {
        gameRepository.deleteAll();
    }

    // Testcase for Create, Read by id and delete by id
    @Test
    public void shouldCreateReadDeleteGame() {
        Game game = gameRepository.save(new Game("Game1", "Good", "Game 1 description", new BigDecimal("45.30"), "abc", 4));
        int id = game.getGameId();
        assertEquals(true, gameRepository.existsById(id));
        gameRepository.deleteById(id);
        assertEquals(false, gameRepository.existsById(id));
    }

    // Testcase for Read All
    @Test
    public void shouldReadAllGames() {
        gameRepository.save(new Game("Game1", "Good", "Game 1 description", new BigDecimal("45.30"), "abc", 4));
        gameRepository.save(new Game("Game2", "Average", "Game 2 description", new BigDecimal("245.50"), "def", 5));

        List<Game> games = gameRepository.findAll();
        assertEquals(2, games.size());
    };

    // Testcase for Update
    @Test
    public void shouldUpdateGame() {
        final String newTitle = "New game";

        Game game = gameRepository.save(new Game("Game1", "Good", "Game 1 description", new BigDecimal("45.30"), "abc", 4));
        game.setTitle(newTitle);
        Game updatedGame = gameRepository.save(game);
        assertEquals(updatedGame.getTitle(), newTitle);
    }

    // Testcase for Read by studio
    @Test
    public void shouldFindByStudio() {
        Game game1 = gameRepository.save(new Game("Game1", "Good", "Game 1 description", new BigDecimal("45.30"), "abc", 4));
        Game game2 = gameRepository.save(new Game("Game2", "Average", "Game 2 description", new BigDecimal("245.50"), "def", 5));
        Game game3 = gameRepository.save(new Game("Game3", "Average", "Game 3 description", new BigDecimal("2.50"), "abc", 6));

        List<Game> games = gameRepository.findByStudio("abc");
        assertEquals(2, games.size());
    }

    // Testcase for Read by ESRB
    @Test
    public void shouldFindByESRB() {
        Game game1 = gameRepository.save(new Game("Game1", "Good", "Game 1 description", new BigDecimal("45.30"), "abc", 4));
        Game game2 = gameRepository.save(new Game("Game2", "Average", "Game 2 description", new BigDecimal("245.50"), "def", 5));
        Game game3 = gameRepository.save(new Game("Game3", "Average", "Game 3 description", new BigDecimal("2.50"), "abc", 6));

        List<Game> games = gameRepository.findByEsrbRating("Average");
        assertEquals(2, games.size());
    }

    // Testcase for Read by Title
    @Test
    public void shouldFindByTitle() {
        Game game1 = gameRepository.save(new Game("Game1", "Good", "Game 1 description", new BigDecimal("45.30"), "abc", 4));
        Game game2 = gameRepository.save(new Game("Game2", "Average", "Game 2 description", new BigDecimal("245.50"), "def", 5));

        List<Game> games = gameRepository.findByTitle("Game1");
        assertEquals(1, games.size());
    }
}