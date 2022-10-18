package com.company.Summative1PriyankaElleniChris.repository;


import com.company.Summative1PriyankaElleniChris.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {
    public List<Game> findByStudio(String studio);
    public List<Game> findByEsrbRating(String esrbRating);
    public List<Game> findByTitle(String title);
}
