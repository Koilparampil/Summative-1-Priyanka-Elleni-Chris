package com.company.Summative1PriyankaElleniChris.repository;

import com.company.Summative1PriyankaElleniChris.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    public List<Game> findByStudio(String studio);
    public List<Game> findByEsrbRating(String esrbRating);
    public List<Game> findByTitle(String title);
}
