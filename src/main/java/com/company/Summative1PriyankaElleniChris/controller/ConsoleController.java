package com.company.Summative1PriyankaElleniChris.controller;

import com.company.Summative1PriyankaElleniChris.model.Console;
import com.company.Summative1PriyankaElleniChris.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consoles")
public class ConsoleController {

    @Autowired
    ConsoleRepository consoleRepository;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsoles(){
        return consoleRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Console getSingleConsole(@PathVariable Integer id) throws NoSuchFieldException {
        Optional<Console> returnVal = consoleRepository.findById(id);
        if (returnVal.isPresent()){
            return returnVal.get();
        } else {
            throw new NoSuchFieldException("Console not Found in Collection");
        }
    }
    @GetMapping("manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsolesByManufacturer(@PathVariable String manufacturer){
        List<Console>  returnVal = consoleRepository.findByManufacturer(manufacturer);
        return returnVal;
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Console addConsole(@RequestBody @Valid Console console) {
        return consoleRepository.save(console);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody @Valid Console console) {
        consoleRepository.save(console);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id){
        consoleRepository.deleteById(id);
    }
}
