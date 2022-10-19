package com.company.Summative1PriyankaElleniChris.controller;
import com.company.Summative1PriyankaElleniChris.model.T_shirt;
import com.company.Summative1PriyankaElleniChris.repository.T_shirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/Tshirt")
public class T_shirtController {
    @Autowired
    private T_shirtRepository repo;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public T_shirt addNewTshirt(@RequestBody @Valid T_shirt t_shirt) {
        return repo.save(t_shirt);

    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<T_shirt> getAllTshirts() {

        return repo.findAll();
    }

    @GetMapping(value = "/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<T_shirt> getTshirtsByColor(@PathVariable String color) {
        return repo.findT_shirtByColor(color);

    }

    @GetMapping("/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<T_shirt> getTshirtBySize(@PathVariable String size) {
        return repo.findT_shirtBySize(size);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public T_shirt updateAT_shirt(@RequestBody @Valid T_shirt t_shirt) {
        return repo.save(t_shirt);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable int id) {

            repo.deleteById(id);
    }

}