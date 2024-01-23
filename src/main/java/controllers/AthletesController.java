package controllers;

import models.Zawodnicy;
import org.springframework.ui.Model;
import services.AthletesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping()
public class AthletesController {
    @Autowired
    private AthletesService athletesService;

    @GetMapping("/zawodnicy")
    public String getZawodnicy(Model model) {
        List<Zawodnicy> zawodnicyList = athletesService.getAllAthletes();

       model.addAttribute("zawodnicy", zawodnicyList);

        return "zawodnicy"; // Assuming you have a Thymeleaf template named "zawodnicy.html"
    }
    @GetMapping
    public List<Zawodnicy> getAllAthletes() {
         return athletesService.getAllAthletes();
    }

    @GetMapping("/{id}")
    public Zawodnicy getBookById(@PathVariable Long id) {
        return athletesService.getAthleteById(id);
    }

    @PostMapping
    public void saveBook(@RequestBody Zawodnicy book) {
        athletesService.saveAthlete(book);
    }

    @DeleteMapping("/{id}")
    public void deleteAthlete(@PathVariable Long id) {
        athletesService.deleteAthlete(id);
    }
}
