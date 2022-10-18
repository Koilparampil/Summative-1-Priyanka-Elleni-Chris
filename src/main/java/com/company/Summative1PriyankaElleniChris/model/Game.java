package com.company.Summative1PriyankaElleniChris.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.aspectj.bridge.IMessage;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    @NotNull(message = "Id cannot be null")
    private Integer gameId;

    @NotNull(message = "Title cannot be null")
    @NotEmpty(message = "You must supply a value for title")
    @Size(max = 50, message = "Title cannot be more than 50 characters")
    private String title;

    @Column(name = "esrb_rating")
    @NotNull(message = "Esrb rating cannot be null")
    @NotEmpty(message = "You must supply a value for esrb_rating")
    @Size(max = 50, message = "Esrb rating cannot be more than 50 characters")
    private String esrbRating;

    @NotNull(message = "Description cannot be null")
    @NotEmpty(message = "You must supply a value for description ")
    @Size(max = 255, message = "Description cannot be more than 255 characters")
    private String description;

    @NotNull(message = "Price cannot be null")
    @NotEmpty(message = "You must supply a value for price")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal price;

    @NotNull(message = "Studio cannot be null")
    @NotEmpty(message = "You must supply a value for studio")
    @Size(max = 50, message = "Studio cannot be more than 50 characters")
    private String studio;

    @NotNull(message = "Quantity cannot be null")
    @NotEmpty(message = "You must supply a value for quantity")
    private Integer quantity;

    // Default constructor
    public Game() {}

    public Game(String title, String esrbRating, String description, BigDecimal price, String studio, Integer quantity) {
        this.title = title;
        this.esrbRating = esrbRating;
        this.description = description;
        this.price = price;
        this.studio = studio;
        this.quantity = quantity;
    }

    public Game(Integer gameId, String title, String esrbRating, String description, BigDecimal price, String studio, Integer quantity) {
        this.gameId = gameId;
        this.title = title;
        this.esrbRating = esrbRating;
        this.description = description;
        this.price = price;
        this.studio = studio;
        this.quantity = quantity;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(gameId, game.gameId) && Objects.equals(title, game.title) && Objects.equals(esrbRating, game.esrbRating) && Objects.equals(description, game.description) && Objects.equals(price, game.price) && Objects.equals(studio, game.studio) && Objects.equals(quantity, game.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, title, esrbRating, description, price, studio, quantity);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", title='" + title + '\'' +
                ", esrbRating='" + esrbRating + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", studio='" + studio + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
