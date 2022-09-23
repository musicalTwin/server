package it.musicaltwin.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.musicaltwin.demo.entities.Genders;
import it.musicaltwin.demo.services.GendersService;

@RestController
@RequestMapping(path = "api/v1/genders")
public class GendersController {

    private final GendersService gendersService;

    @Autowired
    public GendersController(GendersService gendersService) {
        this.gendersService = gendersService;
    }

    @GetMapping("name/{genderName}")
    public Genders getByName(@PathVariable(name = "genderName") String name) {
        return gendersService.getByName(name).orElse(new Genders());
    }

    @GetMapping("id/{genderId}")
    public Genders getById(@PathVariable(name = "genderId") Long id) {
        return gendersService.getById(id).orElse(new Genders());
    }

    @GetMapping
    public List<Genders> getAllGenders() {
        return gendersService.getAllGenders();
    }

}
