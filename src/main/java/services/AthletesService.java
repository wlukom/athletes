package services;

import models.Zawodnicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AthletesRepository;


import java.util.List;

@Service
public class AthletesService {
    @Autowired
    private AthletesRepository athleteRepository;

    public List<Zawodnicy> getAllAthletes() {
        return athleteRepository.findAll();
    }

    public Zawodnicy getAthleteById(Long id) {
        return athleteRepository.findById(id).orElse(null);
    }

    public void saveAthlete(Zawodnicy book) {
        athleteRepository.save(book);
    }

    public void deleteAthlete(Long id) {
        athleteRepository.deleteById(id);
    }
}
