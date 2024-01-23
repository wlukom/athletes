package repositories;

import models.Zawodnicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthletesRepository extends JpaRepository<Zawodnicy, Long> {
    // Additional custom queries can be defined here if needed
}
