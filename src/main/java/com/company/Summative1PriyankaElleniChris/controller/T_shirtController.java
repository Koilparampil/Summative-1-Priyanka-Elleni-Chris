package com.company.Summative1PriyankaElleniChris.controller;

import com.company.Summative1PriyankaElleniChris.model.T_shirt;
import com.company.Summative1PriyankaElleniChris.repository.T_shirtRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/t_shirt")
public class T_shirtController {
    private T_shirtRepository repo;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public T_shirt createAnewTshirt(@RequestBody T_shirt t_shirt){
        return  repo.save(t_shirt);

    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<T_shirt> getAllTshirts (){

        return repo.findAll();
    }

    @GetMapping("/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<T_shirt> getTshirtsByColor(String color){
        return repo.findT_shirtByColor(color);
    }

    @GetMapping("/size/{size}")
    @ResponseStatus(HttpStatus.OK)

    public List<T_shirt> getTshirtBySize(String size){
        return repo.findT_shirtBySize(size);
    }



}
