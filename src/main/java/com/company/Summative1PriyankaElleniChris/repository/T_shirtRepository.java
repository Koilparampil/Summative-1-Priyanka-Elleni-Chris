package com.company.Summative1PriyankaElleniChris.repository;

import com.company.Summative1PriyankaElleniChris.model.T_shirt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface T_shirtRepository extends JpaRepository<T_shirt, Integer> {
    List<T_shirt> findT_shirtByColor(String color);
    List<T_shirt> findT_shirtBySize(String size);


}
