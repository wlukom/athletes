package models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Zawodnicy")
public class Zawodnicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Imie;
    private String Nazwisko;
    private Date Data_urodzenia;
    private String Email;
    private String Nr_telefonu;
    private Long Id_dyscypliny;

    public String getImie() {
        return Imie;
    }

}

