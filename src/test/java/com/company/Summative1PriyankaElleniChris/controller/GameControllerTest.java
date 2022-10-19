package com.company.Summative1PriyankaElleniChris.controller;

import com.company.Summative1PriyankaElleniChris.model.Game;
import com.company.Summative1PriyankaElleniChris.repository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameRepository gameRepository;

    private ObjectMapper mapper = new ObjectMapper();

    private Game inputGame1;
    private Game inputGame2;
    private Game inputGame3;
    private List<Game> inputGameList = new ArrayList<>();
    private String inputGame1Json;

    private Game outputGame1;
    private Game outputGame2;
    private Game outputGame3;
    private List<Game> outputGameList = new ArrayList<>();
    private String outputGame1Json;
    private String outputGameListJson;
    
    @Before
    public void setUp() throws Exception {
        inputGame1 = new Game("Game1", "Good", "Game 1 description", new BigDecimal("45.30"), "Sony", 4);
        inputGame2 = new Game("Game2", "Average", "Game 2 description", new BigDecimal("245.50"), "Capcom", 5);
        inputGame3 = new Game("Game3", "Average", "Game 3 description", new BigDecimal("2.50"), "Sony", 6);

        inputGameList.add(inputGame1);
        inputGameList.add(inputGame2);
        inputGameList.add(inputGame3);

        inputGame1Json = mapper.writeValueAsString(inputGame1);

        outputGame1 = new Game(1,"Game1", "Good", "Game 1 description", new BigDecimal("45.30"), "Sony", 4);
        outputGame2 = new Game(2, "Game2", "Average", "Game 2 description", new BigDecimal("245.50"), "Capcom", 5);
        outputGame3 = new Game(3, "Game3", "Average", "Game 3 description", new BigDecimal("2.50"), "Sony", 6);

        outputGameList.add(outputGame1);
        outputGameList.add(outputGame2);
        outputGameList.add(outputGame3);

        outputGame1Json = mapper.writeValueAsString(outputGame1);

        outputGameListJson = mapper.writeValueAsString(outputGameList);
    }

    @Test
    public void shouldCreateGame() throws Exception {
        doReturn(outputGame1).when(gameRepository).save(inputGame1);

        mockMvc.perform(
                post("/games")
                        .content(inputGame1Json)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputGame1Json));
    }

    @Test
    public void shouldReturnErrorOnPostingInvalidGameInputWhenAPropertyIsNull() throws Exception {
        Game inputGame = new Game();
        inputGame.setTitle("Game4");
        inputGame.setEsrbRating("Good");
        inputGame.setDescription("Game 4 description");
        inputGame.setPrice(new BigDecimal("56.69"));

        String inputGameJson = mapper.writeValueAsString(inputGame);

        mockMvc.perform(
                        post("/games")
                                .content(inputGameJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturnErrorOnPostingInvalidGameInputWhenPriceHasThreeFractionalDigits() throws Exception {
        Game inputGame = new Game();
        inputGame.setTitle("Game4");
        inputGame.setEsrbRating("Good");
        inputGame.setDescription("Game 4 description");
        inputGame.setPrice(new BigDecimal("56.695"));
        inputGame.setStudio("Sony");
        inputGame.setQuantity(10);

        String inputGameJson = mapper.writeValueAsString(inputGame);

        mockMvc.perform(
                        post("/games")
                                .content(inputGameJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldUpdateGameById() throws Exception {
        inputGame1.setGameId(1);
        inputGame1.setTitle("New game");

        String inputJson = mapper.writeValueAsString(inputGame1);

        mockMvc.perform(put("/games")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnErrorOnUpdatingGameWhenAPropertyIsNull() throws Exception {
        Game inputGame = new Game();
        inputGame.setGameId(1);
        inputGame.setTitle("Game1");
        inputGame.setEsrbRating("Good");
        inputGame.setDescription("Game 1 description");
        inputGame.setPrice(new BigDecimal("100.69"));

        String inputGameJson = mapper.writeValueAsString(inputGame);

        mockMvc.perform(
                        put("/games")
                                .content(inputGameJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturnErrorOnUpdatingGameWhenPriceHasThreeFractionalDigits() throws Exception {
        Game inputGame = new Game();
        inputGame.setGameId(1);
        inputGame.setTitle("Game1");
        inputGame.setEsrbRating("Good");
        inputGame.setDescription("Game 1 description");
        inputGame.setPrice(new BigDecimal("56.695"));
        inputGame.setStudio("Sony");
        inputGame.setQuantity(10);

        String inputGameJson = mapper.writeValueAsString(inputGame);

        mockMvc.perform(
                        put("/games")
                                .content(inputGameJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldDeleteGameById() throws Exception {
        mockMvc.perform(delete("/games/2"))
                .andExpect(status().isNoContent());

        verify(gameRepository).deleteById(2);
    }

    @Test
    public void shouldReturnAllGames() throws Exception {
        doReturn(outputGameList).when(gameRepository).findAll();

        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputGameListJson));
    }

    @Test
    public void shouldReturnGameById() throws Exception {
        doReturn(Optional.of(outputGame1)).when(gameRepository).findById(1);

        mockMvc.perform(get("/games/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputGame1Json));
    }

    @Test
    public void shouldReturnStatusOkForNonExistentGameId() throws Exception {
        doReturn(Optional.empty()).when(gameRepository).findById(1000);

        mockMvc.perform(get("/games/1000"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnGamesByStudio() throws Exception {
        List<Game> gameList = new ArrayList<>();
        gameList.add(outputGame1);
        gameList.add(outputGame3);
        String outputJson = mapper.writeValueAsString(gameList);

        doReturn(gameList).when(gameRepository).findByStudio("Sony");

        mockMvc.perform(get("/games/studio/Sony"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnGamesByRating() throws Exception {
        List<Game> gameList = new ArrayList<>();
        gameList.add(outputGame2);
        gameList.add(outputGame3);
        String outputJson = mapper.writeValueAsString(gameList);

        doReturn(gameList).when(gameRepository).findByEsrbRating("Average");

        mockMvc.perform(get("/games/rating/Average"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnGamesByTitle() throws Exception {
        List<Game> gameList = new ArrayList<>();
        gameList.add(outputGame1);
        String outputJson = mapper.writeValueAsString(gameList);

        doReturn(gameList).when(gameRepository).findByTitle("Game1");

        mockMvc.perform(get("/games/title/Game1"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
}